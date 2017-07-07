package com.guang.taskScheduler.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @comment 图里顶点对象
 * @author zhouchenguang
 * @date 2016年8月17日下午3:07:48
 * @version 1.0.0
 */
public class Node {
	private static Logger logger = LoggerFactory.getLogger(Node.class);
	/**
	 * 顶点编号
	 */
	private int id;
	/**
	 * 到其他顶点距离列表
	 */
	private List<Integer> distances = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getDistances() {
		return distances;
	}
	public void setDistances(List<Integer> distances) {
		this.distances = distances;
	}
	public void setDistances(int size) {
		if (size < 0) {
			logger.error("size is negative");
			return;
		}
		distances = new ArrayList<>(size);
	}
	public void addDistance(int spot,int distance) {
		if (spot < 0) {
			logger.error("spot is negative");
			return;
		}
		else if (spot > distances.size()) {
			List<Integer> list = new ArrayList<>(spot);
			list.addAll(distances);
			distances = list;
		}
		distances.add(spot, distance);
	}
}
