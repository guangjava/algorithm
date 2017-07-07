package com.guang.taskScheduler.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.taskScheduler.domain.Task;
import com.guang.taskScheduler.domain.TimeDetail;
import com.guang.taskScheduler.domain.TimePoint;
import com.guang.taskScheduler.util.GraphTool;

public class TaskScheduler {
	/**
	 * 每个时间点开始任务列表startTaskArray默认长度
	 */
	private static final int DEFAULT_ARRAY_SIZE = 20;
	private static Logger logger = LoggerFactory.getLogger(TaskScheduler.class);
	private static Properties properties;
	private static String filePath;
	/**
	 * 工作地点数
	 */
	private static int nodeNum;
	/**
	 * 地点间路径数
	 */
	private static int pathNum;
	/**
	 * 任务数
	 */
	private static int taskNum;
	private static int endTime;
	/**
	 * 工作地点路径
	 */
	private static int[][] path;
	/**
	 * 全部任务列表
	 */
	private static List<Task> taskList = new ArrayList<>();
	/**
	 * 某个时间点不同工作地点可以开始任务列表
	 */
	private static List<Integer>[][] startTaskArray;
	/**
	 * 时间线，每个时间点取得的最大收益
	 */
	private static List<TimePoint> timeLine;
	static {
		properties = new Properties();
		try {
			properties.load(ClassLoader.getSystemResourceAsStream("taskScheduler.properties"));
			filePath = properties.getProperty("file");
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取最大收益方案
	 * 
	 * @exception @author
	 *                zhouchenguang
	 * @date 2016年8月17日下午6:03:36
	 * @since 1.0.0
	 */
	public static void getMaxiumPay() {
		int totPay = 0;
		int node = 0;
		try {
			readFile();
			getstartTaskArray();
			// 新建timeLine
			timeLine = new ArrayList<>(endTime);
			for (int i = 0; i < endTime; i++) {
				process(i);
				TimePoint timePoint = timeLine.get(i);
				logger.info(timePoint.toString(taskList));
			}
			TimePoint timePoint = timeLine.get(endTime-1);
			for (int i = 0; i < nodeNum; i++) {
				int pay = timePoint.getDetail(i).getTotalPay();
				if (totPay < pay) {
					totPay = pay;
					node = i;
				}
			}
			TimeDetail timeDetail = timePoint.getDetail(node);
			System.out.println(timeDetail.toString(taskList));


		} catch (Exception e) {
			logger.error("", e);
		}
	}

	/**
	 * 读取数据
	 * 
	 * @throws Exception
	 * @exception @author
	 *                zhouchenguang
	 * @date 2016年8月17日下午6:03:55
	 * @since 1.0.0
	 */
	private static void readFile() throws Exception {
		Scanner scanner = new Scanner(new FileInputStream(filePath));
		nodeNum = scanner.nextInt();
		pathNum = scanner.nextInt();
		taskNum = scanner.nextInt();

		initialPath();

		int u, v, w;
		// 输入路径
		for (int i = 0; i < pathNum; i++) {
			// 路径起点
			u = scanner.nextInt();
			// 路径终点
			v = scanner.nextInt();
			// 路径长度
			w = scanner.nextInt();
			int p1 = path[u-1][v-1];
			if (w < p1) {
				path[u - 1][v - 1] = w;
				path[v-1][u-1] = w;
			}
		}

		GraphTool.getShortestPath(path);

		int l, s, e, t, c;
		// 输入任务
		for (int i = 0; i < taskNum; i++) {
			// 工作地点
			l = scanner.nextInt() - 1;
			// 最早开始时间
			s = scanner.nextInt();
			// 最晚开始时间
			e = scanner.nextInt();
			// 时间消耗
			t = scanner.nextInt();
			// 报酬
			c = scanner.nextInt();

			Task task = new Task();
			task.setNode(l);
			task.setEarlyTime(s);
			task.setLastTime(e);
			task.setTimeCost(t);
			task.setPay(c);

			taskList.add(task);
		}
		/*for (int i = 0; i < taskNum; i++) {
			Task task = taskList.get(i);
			logger.info("task i:[node:"+task.getNode()+",earlyTime:"
					+task.getEarlyTime()+",lastTime:"+task.getLastTime()
					+",timeCost:"+task.getTimeCost()+",pay:"+task.getPay()
					+"]");
		}*/
		scanner.close();
	}

	/**
	 * 初始化path数组
	 * 
	 * @exception @author
	 *                zhouchenguang
	 * @date 2016年8月17日下午6:07:27
	 * @since 1.0.0
	 */
	private static void initialPath() {
		path = new int[nodeNum][nodeNum];
		for (int i = 0; i < nodeNum; i++) {
			// 初始化为最大值，对角线为0
			for (int j = 0; j < nodeNum; j++) {
				path[i][j] = Integer.MAX_VALUE;
			}
			path[i][i] = 0;
		}
	}

	/**
	 * 得到每个时间点可以开始的任务列表
	 * 
	 * @exception @author
	 *                zhouchenguang
	 * @date 2016年8月18日下午2:42:53
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	private static void getstartTaskArray() {
		Task task;
		int node, earlyTime, lastTime, timeCost, tempEndTime;
		// 任务结束时间
		endTime = 0;
		List<Integer>[] startTaskItem;
		List<Integer> startTaskNodeItem;
		startTaskArray = new ArrayList[DEFAULT_ARRAY_SIZE][];
		// 循环处理所有任务
		for (int i = 0; i < taskNum; i++) {
			task = taskList.get(i);
			node = task.getNode();
			earlyTime = task.getEarlyTime();
			lastTime = task.getLastTime();
			timeCost = task.getTimeCost();

			tempEndTime = lastTime + timeCost;
			if (endTime < tempEndTime) {
				endTime = tempEndTime;
			}

			// 如果列表长度小于最晚时间，就扩展列表
			if (startTaskArray.length < lastTime) {
				updateStartTaskArray(lastTime);
			}

			// 将时间范围内的列表更新
			for (int j = earlyTime; j <= lastTime; j++) {
				startTaskItem = startTaskArray[j];
				if (startTaskItem == null) {
					startTaskItem = new ArrayList[nodeNum];
				}
				startTaskNodeItem = startTaskItem[node];
				if (startTaskNodeItem == null) {
					startTaskNodeItem = new ArrayList<>();
				}
				startTaskNodeItem.add(i);
				startTaskItem[node] = startTaskNodeItem;
				startTaskArray[j] = startTaskItem;
			}
		}
	}

	/**
	 * 扩展startTaskList
	 * 
	 * @param size
	 * @exception @author
	 *                zhouchenguang
	 * @date 2016年8月18日下午2:38:39
	 * @since 1.0.0
	 */
	@SuppressWarnings("unchecked")
	private static void updateStartTaskArray(int size) {
		List<Integer>[][] list = new ArrayList[size][];
		for (int i = 0; i < startTaskArray.length; i++) {
			list[i] = startTaskArray[i];
		}
		startTaskArray = list;
	}

	/**处理时间time下各工作点的最优收益
	 * @param time
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月22日上午10:18:58
	 * @since 1.0.0
	 */
	private static void process(int time) {
		TimePoint timePoint;
		TimeDetail timeDetail;
		TimeDetail bgnTimeDetail;
		Task task;
		Set<Set<Integer>> tempTaskList;
		int timeCost;
		int pay;
		int endPay;
		int offset;
		int beginTime;
		List<Integer> startTaskItem = null;
		//循环处理所有工作点的起始时间
		for (int i = 0; i < nodeNum; i++) {
			//各工作点起始时间
			beginTime = path[0][i];
			//时间早于该点最早开始时间，直接返回
			if (time < beginTime) {
				continue;
			}
			extendTimeLine(time);
			timePoint = timeLine.get(time);
			//取起始时间点在0工作点工作详情
			bgnTimeDetail = timePoint.getDetail(i);
			//获取初始收益
			pay = bgnTimeDetail.getTotalPay();
			//如果大于起始时间，则与前一时间点判断，如果前一时间点收益大，则替换本时间点
			if (time > beginTime) {
				TimePoint prevTimePoint = timeLine.get(time-1);
				TimeDetail prevDetail = prevTimePoint.getDetail(i);
				int prevPay = prevDetail.getTotalPay();
				if (pay < prevPay) {
					timePoint.addDetail(prevDetail, i);
					timeLine.set(time, timePoint);
				}
			}
			//针对不同工作点，有不同的时间偏移
			for (int j = 0; j < nodeNum; j++) {
				//时间偏移，从i到j的时间消耗
				offset = path[i][j];
				if (time+offset < endTime) {
					List<Integer>[] stratTask = startTaskArray[time + offset];
					if (stratTask!=null) {
						//这里应该是j点开始的任务
						startTaskItem = startTaskArray[time + offset][j];
					}
				}
				// 如果节点任务开始列表不为空
				if (startTaskItem != null) {
					// 取到的是任务编号
					for (Integer integer : startTaskItem) {
						task = taskList.get(integer);
						//消耗时长
						timeCost = task.getTimeCost();
						//增加收益
						pay = task.getPay()+bgnTimeDetail.getTotalPay();
						
						/*********存储结束时间点**********/
						//先从时间线中取，如果没有则新建
						int taskEndTime = time+timeCost+offset;
						if (timeLine.size() <= taskEndTime) {
							extendTimeLine(taskEndTime);
						}
						timePoint = timeLine.get(taskEndTime);
						//修改0点的详情
						timeDetail = timePoint.getDetail(j);
						endPay = timeDetail.getTotalPay();
						//新收益优于旧收益，就替换
						if (endPay < pay) {
							tempTaskList = new HashSet<>();
							timeDetail.setEndTaskList(tempTaskList);
							//修改结束任务列表，开始时刻结束任务列表加上当前任务
							logger.info("time:["+time+"->"+taskEndTime+"];node:["
									+i+"->"+j+"];task:"+integer);
							updateEndTaskList(timeDetail, bgnTimeDetail, integer,pay);
						}
						//相等，则增加记录
						else if (endPay == pay) {
							//在结束任务列表中增加一条记录，开始时刻结束任务列表加上当前任务
							logger.info("time:["+time+"->"+taskEndTime+"];node:["
									+i+"->"+j+"];task:"+integer);
							updateEndTaskList(timeDetail, bgnTimeDetail, integer,pay);
						} else {
							continue;
						}
						timePoint.addDetail(timeDetail, j);
						//保存修改后时间点
						timeLine.set(taskEndTime, timePoint);
					}
				}
			} 
		}
	}

	/**根据起始时间情况，将当前任务增加到结束时间的结束任务列表
	 * @param timeDetail 结束时间情况
	 * @param bgnTimeDetail 起始时间情况
	 * @param task 当前任务
	 * @param pay 总报酬
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月22日上午9:42:29
	 * @since 1.0.0
	 */
	private static void updateEndTaskList(TimeDetail timeDetail, TimeDetail bgnTimeDetail, Integer task,int pay) {
		Set<Integer> endTaskList = new HashSet<>();
		int listSize;
		listSize = bgnTimeDetail.getListSize();
		//开始时间点没有结束任务，则新建结束任务
		if (listSize==0) {
			endTaskList.add(task);
			timeDetail.addTaskList(endTaskList);
			timeDetail.setTotalPay(pay);
			logger.info(timeDetail.toString(taskList));
			return;
		}
		Set<Set<Integer>> detailSet = bgnTimeDetail.getEndTaskList();
		for (Set<Integer> set: detailSet) {
			//任务不能重复，如果包含本任务，则跳过
			if (set.contains(task)) {
				continue;
			}
			endTaskList.addAll(set);
			endTaskList.add(task);
			timeDetail.addTaskList(endTaskList);
			timeDetail.setTotalPay(pay);
		}
		logger.info(timeDetail.toString(taskList));
	}

	/**扩展时间线
	 * @param time
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月19日下午4:20:37
	 * @since 1.0.0
	 */
	private static void extendTimeLine(int time) {
		TimePoint timePoint;
		for (int j = timeLine.size(); j <= time; j++) {
			timePoint = new TimePoint();
			timePoint.setTime(j);
			timeLine.add(timePoint);
		}
	}

}
