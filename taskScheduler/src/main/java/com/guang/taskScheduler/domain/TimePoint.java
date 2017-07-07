package com.guang.taskScheduler.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @comment 时间点
 * @author zhouchenguang
 * @date 2016年8月17日下午5:26:03
 * @version 1.0.0
 */
public class TimePoint {
	private static Logger logger = LoggerFactory.getLogger(TimePoint.class);
	/**
	 * 时间点下各工作地点详情
	 */
	private List<TimeDetail> detailList = new ArrayList<>();
	/**
	 * 时间
	 */
	private int time;
	public List<TimeDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<TimeDetail> detailList) {
		this.detailList = detailList;
	}
	public void setDetailList(int size) {
		detailList = new ArrayList<>(size);
	}
	/**添加detail到index位置，中间空的则新建对象填充
	 * @param detail
	 * @param index
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月19日上午11:22:44
	 * @since 1.0.0
	 */
	public void addDetail(TimeDetail detail,int index){
		if (index < 0) {
			logger.error("index is negative");
			return;
		}
		expandDetailList(index);
		detailList.set(index, detail);
	}
	
	/**获取node工作地点的详情
	 * @param node
	 * @return
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月19日上午11:25:16
	 * @since 1.0.0
	 */
	public TimeDetail getDetail(int node) {
		if (node < 0) {
			logger.error("index is negative");
			return null;
		}
		if (node >= detailList.size()) {
			expandDetailList(node);
		}
		return detailList.get(node);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public String toString(List<Task> taskList) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("time:"+time+";");
		for(TimeDetail detail:detailList){
			buffer.append(detail.toString(taskList));
		}
		return buffer.toString();
	}
	
	/**扩展detailList
	 * @param index
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月19日上午11:21:37
	 * @since 1.0.0
	 */
	private void expandDetailList(int index) {
		TimeDetail timeDetail;
		if (index >= detailList.size()) {
			for (int i = detailList.size(); i <= index; i++) {
				timeDetail = new TimeDetail();
				timeDetail.setNode(i);
				detailList.add(timeDetail);
			}
		}
	}
}
