package com.guang.leetCode;

import java.util.Arrays;

public class _3SumClosest {
	public int threeSumClosest1(int[] nums, int target) {
		int[] result = new int[3];
		//传入数据只有3个
		if (nums.length == 3) {
			return nums[0]+nums[1]+nums[2];
		}
		Arrays.sort(nums);
		int min = nums[0];
		int max = nums[nums.length-1];
		//最小的都比target大
		if (nums[0]+nums[1]+nums[2] >= target) {
			return nums[0]+nums[1]+nums[2];
		}
		//最大的都比target小
		else if (nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3] <= target) {
			return nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3];
		}
		//与target偏移值
		int miss = Integer.MAX_VALUE;
		//用来存放各个数值的个数
		int[] countMap= new int[max-min+1];
		//初始化map
		for (int i = 0; i < countMap.length; i++) {
			countMap[i] = 0;
		}
		for(int i=0; i<nums.length; i++){
			countMap[nums[i]-min]++;
		}
		//用来存放各个数值的近似值
		int[] approMap = new int[max-min+1];
		for(int i=0; i<nums.length; i++){
			approMap[nums[i]-min] = nums[i];
			if (i>0 && nums[i]-nums[i-1]>1) {
				int mid = (nums[i]+nums[i-1])/2;
				for(int j=nums[i-1]+1; j<mid; j++){
					approMap[j-min] = nums[i-1];
				}
				for(int j=mid; j<nums[i]; j++){
					approMap[j-min] = nums[i];
				}
			}
		}
		for(int i=0; i<nums.length-2; i++){
			//防止出现重复的结果
			if (i>0 && nums[i]==nums[i-1]) {
				continue;
			}
			for(int j=i+1; j<nums.length-1; j++){
				if (j>i+1 && nums[j]==nums[j-1]) {
					continue;
				}
				int sum_2 = nums[i] + nums[j];
				int comple = target - sum_2;
				//c<b,跳过
				if (comple < nums[j]) {
					break;
				}
				//comple超过范围，选用最大值
				if (comple > max) {
					int sum_3 = sum_2 + max;
					int temp = sum_3 - target;
					if (Math.abs(temp) < Math.abs(miss)) {
						miss = temp;
					}
					if (miss == 0) {
						System.out.println(nums[i]+","+nums[j]+","+max);
						return sum_3;
					}
					continue;
				}
				//a < b
				if (nums[i] < nums[j]) {
					//c存在
					if (countMap[comple - min] > 0) {
						//c>b或c==b同时存在多个
						if (comple > nums[j] || (comple==nums[j]&&countMap[comple-min]>1)) {
							System.out.println(nums[i]+","+nums[j]+","+comple);
							return target;
						}
						//c==b但只有一个，找c近似值
						else {
							//查找比c大的近似数
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									if (Math.abs(approMap[k]-comple) < Math.abs(miss)) {
										miss = approMap[k]-comple;
										result[0] = nums[i];
										result[1] = nums[j];
										result[2] = approMap[k];
									}
									break;
								}
							}
						}
					}
					//c不存在,找到最近似的一个
					else {
						int c = approMap[comple-min];
						//c<b,c改成大的近似值
						if (c < nums[j]) {
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									c = approMap[k];
									break;
								}
							}
						}
						//c>b或c==b同时存在多个
						if (c > nums[j] || (c==nums[j]&&countMap[c-min]>1)) {
							if (Math.abs(c-comple) < Math.abs(miss)) {
								miss = c-comple;
								result[0] = nums[i];
								result[1] = nums[j];
								result[2] = c;
							}
						}
						//c==b但只有一个，找c近似值
						else {
							//查找比c大的近似数
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									if (Math.abs(approMap[k]-comple) < Math.abs(miss)) {
										miss = approMap[k]-comple;
										result[0] = nums[i];
										result[1] = nums[j];
										result[2] = approMap[k];
									}
									break;
								}
							}
						}
					}
				}
				//a==b && a存在2个以上
				else{
					//c存在
					if (countMap[comple - min] > 0) {
						//c>b或c==b同时存在多个
						if (comple > nums[j] || (comple==nums[j]&&countMap[comple-min]>2)) {
							System.out.println(nums[i]+","+nums[j]+","+comple);
							return target;
						}
						//c==b但只有一个，找c近似值
						else {
							//查找比c大的近似数
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									if (Math.abs(approMap[k]-comple) < Math.abs(miss)) {
										miss = approMap[k]-comple;
										result[0] = nums[i];
										result[1] = nums[j];
										result[2] = approMap[k];
									}
									break;
								}
							}
						}
					}
					//c不存在,找到最近似的一个
					else {
						int c = approMap[comple-min];
						//c<b,c改成大的近似值
						if (c < nums[j]) {
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									c = approMap[k];
									break;
								}
							}
						}
						//c>b或c==b同时存在多个
						if (c > nums[j] || (c==nums[j]&&countMap[c-min]>2)) {
							if (Math.abs(c-comple) < Math.abs(miss)) {
								miss = c-comple;
								result[0] = nums[i];
								result[1] = nums[j];
								result[2] = c;
							}
						}
						//c==b但只有一个，找c近似值
						else {
							//查找比c大的近似数
							for (int k = comple-min+1; k < approMap.length; k++) {
								if (approMap[k] > comple) {
									if (Math.abs(approMap[k]-comple) < Math.abs(miss)) {
										miss = approMap[k]-comple;
										result[0] = nums[i];
										result[1] = nums[j];
										result[2] = approMap[k];
									}
									break;
								}
							}
						}
					} 
				}
			}
		}
		System.out.println(result[0]+","+result[1]+","+result[2]);
		return target+miss;
	}
	public int threeSumClosest(int[] nums, int target) throws Exception{
		if (nums.length==3) {
			return nums[0]+nums[1]+nums[2];
		}
		int[] result = new int[3];
		Arrays.sort(nums);
		//偏移值
		int miss = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length-2; i++) {
			//防止出现重复的结果
			if (i>0 && nums[i]==nums[i-1]) {
				continue;
			}
			for (int j = i+1; j < nums.length-1; j++) {
				if (j>i+1 && nums[j]==nums[j-1]) {
					continue;
				}
				int sum_2 = nums[i]+nums[j];
				int tempMiss = Integer.MAX_VALUE;
				int[] array = new int[3];
				for (int j2 = j+1; j2 < nums.length; j2++) {
					int temp = sum_2+nums[j2]-target;
					if (Math.abs(temp) < Math.abs(tempMiss)) {
						tempMiss = temp;
						array[0] = nums[i];
						array[1] = nums[j];
						array[2] = nums[j2];
					}
					else if (Math.abs(temp) > Math.abs(tempMiss)) {
						break;
					}
				}
				if (Math.abs(tempMiss) < Math.abs(miss)) {
					miss = tempMiss;
					result[0] = array[0];
					result[1] = array[1];
					result[2] = array[2];
				}
			}
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		return target+miss;
	}
}
