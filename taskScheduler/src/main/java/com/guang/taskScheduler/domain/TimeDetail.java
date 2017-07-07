package com.guang.taskScheduler.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @comment 时间节点下人在某个工作地点的任务详情
 * @author zhouchenguang
 * @date 2016年8月17日下午5:19:40
 * @version 1.0.0
 */
public class TimeDetail {
	private static Logger logger = LoggerFactory.getLogger(TimeDetail.class);
	/**
	 * 工作地点
	 */
	private int node;
	/**
	 * 已完成任务列表，双层列表为了储存报酬相等的情况
	 */
	private Set<Set<Integer>> endTaskList = new HashSet<>();
	/**
	 * 获得总报酬，初始为0
	 */
	private int totalPay = 0;
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public Set<Set<Integer>> getEndTaskList() {
		return endTaskList;
	}
	public void setEndTaskList(Set<Set<Integer>> endTaskList) {
		this.endTaskList = endTaskList;
	}
	public void addTaskList(Set<Integer> taskList) {
		endTaskList.add(taskList);
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	public int getListSize() {
		return endTaskList.size();
	}

	public String  toString(List<Task> taskList) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("node:"+node+",totalPay:"+totalPay+"\n");
		int t =0;
		for (Set<Integer> list : endTaskList) {
			buffer.append("list"+t+"\n");
			t++;
			for (Integer integer : list) {
				Task task = taskList.get(integer);
				buffer.append("任务"+integer+":{地点:"+task.getNode()+",起始时间:["
						+task.getEarlyTime()+","+task.getLastTime()+"],"
						+"工作时长:"+task.getTimeCost()+",工作报酬："+task.getPay()
						+"}\n");
			}
		}
		return buffer.toString();
	}

}
