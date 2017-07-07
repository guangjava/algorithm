package com.guang.leetCode;

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int result = 0;
		for (int l = 0,r = height.length-1; l < r;) {
			int max = (r-l) * Math.min(height[l], height[r]);
			if (max > result) {
				result = max;
			}
			if (height[l] < height[r]) {
				l++;
			}
			else {
				r--;
			}
		}
		return result;
	}
}
