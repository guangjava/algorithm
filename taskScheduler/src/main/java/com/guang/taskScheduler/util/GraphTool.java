package com.guang.taskScheduler.util;

/**
 * @comment 图工具类
 * @author zhouchenguang
 * @date 2016年8月17日下午3:36:03
 * @version 1.0.0
 */
public class GraphTool {
	
	/**获取各点最短路径，Floyd算法
	 * @param path 所有数组元素不能为空，最大值取Integer.MAX_VALUE
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月17日下午3:39:10
	 * @since 1.0.0
	 */
	public static void getShortestPath(int[][] path) {
		//循环所有点
		for (int k = 0; k < path.length; k++) {
			for (int i = 0; i < path.length; i++) {
				//k i 相等跳过
				if (k != i) {
					//路径无穷大跳过
					if (path[i][k] == Integer.MAX_VALUE) {
						continue;
					}
					for (int j = 0; j < i; j++) {
						if (path[i][j] > path[i][k] + path[k][j]) {
							path[i][j] = path[i][k] + path[k][j];
							path[j][i] = path[i][j];
						}
					} 
				}
			} 
		}
	}

}
