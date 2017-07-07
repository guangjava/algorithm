package com.guang.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _4Sum {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length < 4) {
			return result;
		}
		Arrays.sort(nums);
		//存放两两求和
		Sum_2[] sum2s = new Sum_2[(nums.length*(nums.length-1)/2)];
		for (int i = 0,m = 0; i < nums.length-1; i++) {
			for(int j=i+1; j<nums.length; j++){
				Sum_2 sum_2 = new Sum_2();
				sum_2.num = nums[i]+nums[j];
				sum_2.x = i;
				sum_2.y = j;
				sum2s[m++] = sum_2;
			}
		}
		Arrays.sort(sum2s);
		//两两求和最小值和最大值
		int max = sum2s[sum2s.length-1].num;
		//两两求和map,存放在sum2s数组中的下标
		Map<Integer, List<Integer>> sum2Map = new HashMap<>();
		for(int i=0; i<sum2s.length; i++){
			Sum_2 sum_2 = sum2s[i];
			if (!sum2Map.containsKey(sum_2.num)) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				sum2Map.put(sum_2.num, list);
			}
			else {
				List<Integer> list = sum2Map.get(sum_2.num);
				list.add(i);
				sum2Map.put(sum_2.num, list);
			}
		}
		Set<List<Integer>> set = new HashSet<>();
		for(Sum_2 sum_2:sum2s){
			//求补值
			int compo = target - sum_2.num;
			if (compo > max) {
				continue;
			}
			//补值小于加数，说明已经检查一半，可以结束了
			if (compo < sum_2.num) {
				break;
			}
			if (sum2Map.containsKey(compo)) {
				for (Integer index : sum2Map.get(compo)) {
					//判断4个数不冲突
					if (sum_2.check(sum2s[index])) {
						List<Integer> list = new ArrayList<>();
						list.add(nums[sum_2.x]);
						list.add(nums[sum_2.y]);
						list.add(nums[sum2s[index].x]);
						list.add(nums[sum2s[index].y]);
						Collections.sort(list);
						set.add(list);
					}
				}
			}
		}
		for(List<Integer> list:set){
			result.add(list);
		}
		return result;
	}
	//存两两之和
	private static class Sum_2 implements Comparable<Sum_2>{
		//和
		int num;
		//加数坐标
		int x;
		int y;
		@Override
		public int compareTo(Sum_2 o) {
			if (num < o.num) {
				return -1;
			}
			if (num == o.num) {
				return 0;
			}
			else {
				return 1;
			} 
		}
		//检查是否冲突
		boolean check(Sum_2 o){
			if (x==o.x || y==o.x || x==o.y || y==o.y) {
				return false;
			}
			else {
				return true;
			}
		}
		@Override
		public String toString() {
			return ("[num:"+num+",x:"+x+",y:"+y+"]");
		}
	}
}
