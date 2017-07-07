package com.guang.leetCode;

import java.util.HashMap;
import java.util.Map;

/*Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution.
给定数组和目标值，求出数组中两个数，和是目标值，返回两个数在数组中的位置
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
UPDATE (2016/2/13):
The return format had been changed to zero-based indices. Please read the above updated description carefully.*/
public class TwoSum {
	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i]+nums[j]==target) {
					return new int[]{i,j};
				}
			}
		}
		return null;
	}
	
	public int[] twoSum1(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<nums.length;i++){
			//map存每个数字及所在位置
			map.put(nums[i], i);
		}
		for (int i = 0; i < nums.length; i++) {
			int a = target - nums[i];
			//如果map有a说明数组中有，则找到位置
			if (map.containsKey(a) && map.get(a)!=i) {
				return new int[]{i,map.get(a)};
			}
		}
		return null;
	}
	
	public int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<nums.length;i++){
			int a = target - nums[i];
			if (map.containsKey(a) && map.get(a)!=i) {
				return new int[]{i,map.get(a)};
			}
			map.put(nums[i], i);
		}
		return null;
	}

}
