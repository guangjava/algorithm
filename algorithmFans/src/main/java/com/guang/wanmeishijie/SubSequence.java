package com.guang.wanmeishijie;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @comment 题目：

给定一个长度为N的数组，找出一个最长的单调自增子序列（不一定连续，但是顺序不能乱） 例如：给定一个长度为8的数组A{1，3，5，2，4，6，7，8}，则其最长的单调递增子序列为{1，2，4，6，7，8}，长度为6。

输入描述:

第一行包含一个整数T，代表测试数据组数。
对于每组测试数据： N-数组的长度
a1 a2 … an （需要计算的数组）
保证： 1<=N<=3000,0<=ai<=MAX_INT.

输出描述:

对于每组数据，输出一个整数，代表最长递增子序列的长度。

输入例子:

2
7
89 256 78 1 46 78 8
5
6 4 8 2 17

输出例子:

3
3
 * @author zhouchenguang
 * @date 2017年9月7日下午4:50:00
 * @version 1.0.0
 */
public class SubSequence {
	private static void longestIncSubSeq(Item[] array) {
		if (array.length == 1) {
			System.out.println("长度【1】，序列："+array[0].value);
			return;
		}
		for (int i = 1; i < array.length; i++) {//求每个节点最长子序列长度
			int maxPrev = -1;//临时存最长子序列前置节点下标
			for(int j=i-1; j>=0; j--){//求该节点前最长子序列
				if (array[i].value > array[j].value) {
					if (maxPrev==-1 || array[maxPrev].len<array[j].len) maxPrev = j;
				}
			}
			if (maxPrev != -1) {
				array[i].len = array[maxPrev].len + 1;
				array[i].prev = maxPrev;
			}
		}
		int maxLen = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i].len > array[maxLen].len) maxLen = i;
		}
		List<Integer> subSeq = new ArrayList<>();
		for(int i=array[maxLen].len,index=maxLen; i>0; i--){
			subSeq.add(array[index].value);
			index = array[index].prev;
		}
		Collections.reverse(subSeq);
		System.out.print("长度【"+array[maxLen].len+"】，序列：");
		for (Integer integer : subSeq) {
			System.out.print(integer + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		InputStream is = SubSequence.class.getResourceAsStream("/SubSequence_input.txt");
		Scanner scanner = new Scanner(is);
		int T = scanner.nextInt();
		for(int i=0; i<T; i++){
			int N = scanner.nextInt();
			System.out.print("原始数组：");
			Item[] array = new Item[N];
			for(int j=0; j<N; j++){
				int a = scanner.nextInt();
				System.out.print(a+" ");
				array[j] = new Item(a);
			}
			System.out.println();
			longestIncSubSeq(array);
		}
		scanner.close();
	}

	/**
	 * @comment 节点
	 * @author zhouchenguang
	 * @date 2017年9月7日下午4:51:58
	 * @version 1.0.0
	 */
	private static class Item{
		int value;//值
		int prev = -1;//最长序列中前一个节点位置
		int len = 1;//最长序列长度
		/**
		 * @param value
		 */
		public Item(int value) {
			this.value = value;
		}
	}
}
