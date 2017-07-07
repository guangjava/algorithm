package com.guang.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _3Sum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		//MYBUG 传入数据可能为空
		if (nums.length == 0) {
			return result;
		}
		int min = nums[0];
		int max = nums[nums.length-1];
		//用来存放各个数值的个数
		int[] map= new int[max-min+1];
		//初始化map
		for (int i = 0; i < map.length; i++) {
			map[i] = 0;
		}
		for(int i=0; i<nums.length; i++){
			map[nums[i]-min]++;
		}
		for(int i=0; i<nums.length; i++){
			//MYBUG 防止出现重复的结果
			if (i>0 && nums[i]==nums[i-1]) {
				continue;
			}
			for(int j=i+1; j<nums.length; j++){
				if (j>i+1 && nums[j]==nums[j-1]) {
					continue;
				}
				int sum_2 = nums[i] + nums[j];
				//a+b>0,直接跳出本循环，只需要考虑a+b<=0的情况,后面的都是重复
				if (sum_2 > 0) {
					break;
				}
				int comple = Math.abs(sum_2);
				//MYBUG comple超过范围，直接跳过
				if (comple > max) {
					continue;
				}
				//a < b
				if (nums[i] < nums[j]) {
					//b<c && c存在且小于max
					if (nums[j] < comple && map[comple - min] > 0) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(comple);
						result.add(list);
					}
					//MYBUG 判断条件b==c,还有num[j]>comple的情况，不排除还是会重复 && c存在2个以上
					else if (nums[j]==comple && map[comple - min] > 1) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(comple);
						result.add(list);
					} 
				}
				//a==b && a存在2个以上
				else if(map[nums[i]-min] > 1){
					//b<c && c存在
					if (nums[j]<comple && map[comple-min]>0) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(comple);
						result.add(list);
					}
					//MYBUG 排除nums[j]>comple情况，还要确定个数超过两个;b==c==0
					else if (nums[j]==comple && map[nums[i]-min] > 2) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(comple);
						result.add(list);
					} 
				}
			}
		}
		return result;
	}
}
