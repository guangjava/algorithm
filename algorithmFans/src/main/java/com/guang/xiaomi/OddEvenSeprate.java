package com.guang.xiaomi;

/**
 * @comment 题目：

在一个N个整数数组里面，有多个奇数和偶数，设计一个排序算法，令所有的奇数都在左边。请完成 sort 的代码实现（C++或Java）
例如： 当输入a = {8,4,1,6,7,4,9,6,4}，
a = {1,7,9,8,4,6,4,6,4}为一种满足条件的排序结果
 * @author zhouchenguang
 * @date 2017年8月23日上午10:18:47
 * @version 1.0.0
 */
public class OddEvenSeprate {
	void sort(int[]a){
		if (a==null || a.length==0) {
			return;
		}
		int i = 0;
		int j = a.length-1;
		while (j > i) {
			while (i<j &&a[i]%2==1) {
				i++;
			}
			while (i<j && a[j]%2==0) {
				j--;
			}
			if (i<j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		OddEvenSeprate odd = new OddEvenSeprate();
		int[][] a = {{1,3,4,8},{2,6,1,7},{1,2,3,4,5}};
		for (int[] is : a) {
			odd.sort(is);
			for (int i = 0; i < is.length; i++) {
				System.out.print(is[i]+",");
			}
			System.out.println();
		}
	}
}
