package com.guang.taskScheduler.domain;

/**
 * @comment 工作任务
 * @author zhouchenguang
 * @date 2016年8月17日下午4:13:05
 * @version 1.0.0
 */
public class Task {
	/**
	 * 工作地点
	 */
	private int node;
	/**
	 * 最早开始时间
	 */
	private int earlyTime;
	/**
	 * 最晚开始时间
	 */
	private int lastTime;
	/**
	 * 消耗时间
	 */
	private int timeCost;
	public int getNode() {
		return node;
	}
	public void setNode(int node) {
		this.node = node;
	}
	public int getEarlyTime() {
		return earlyTime;
	}
	public void setEarlyTime(int earlyTime) {
		this.earlyTime = earlyTime;
	}
	public int getLastTime() {
		return lastTime;
	}
	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}
	public int getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(int timeCost) {
		this.timeCost = timeCost;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	/**
	 * 报酬
	 */
	private int pay;

}
