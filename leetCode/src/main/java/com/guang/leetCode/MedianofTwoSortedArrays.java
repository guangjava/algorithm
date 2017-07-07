package com.guang.leetCode;

/*给定两个排序好了的数组，得出中位数，如果总长度为偶数，则是中间两个数的平均数
 * Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5*/
public class MedianofTwoSortedArrays {
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int length = nums1.length + nums2.length;
		//总长为偶数
		if (length%2 == 0) {
			double median1 = find(nums1, nums2, (length-1)/2, 0, nums1.length-1, 0, nums2.length-1);
			double median2 = find(nums1, nums2, length/2, 0, nums1.length-1, 0, nums2.length-1);
			return (median1+median2)/2;
		}
		else {
			return find(nums1, nums2, length/2, 0, nums1.length-1, 0, nums2.length-1);
		}
	}
	private double find(int[] nums1,int[] nums2,int k,int s1start,int s1end,int s2start,int s2end) {
		if (s1end < s1start) {
			return nums2[s2start+k];
		}
		if (s2end < s2start) {
			return nums1[s1start+k];
		}
		if (k == 0) {
			return nums1[s1start]<nums2[s2start] ? nums1[s1start] : nums2[s2start];
		}
		int k1 = k * (s1end-s1start+1)/(s1end-s1start+1 + s2end-s2start+1);
		//MYBUG 不减一会数组越界
		int k2 = k - k1 - 1;
		int median1 = nums1[s1start+k1];
		int median2 = nums2[s2start+k2];
		if (median1 < median2) {
			s1start = s1start+k1+1;
			s2end = s2start+k2;
			k-= k1+1;
		}
		else {
			s2start = s2start+k2+1;
			s1end = s1start+k1;
			k -= k2+1;
		}
		return find(nums1, nums2, k, s1start, s1end, s2start, s2end);
		
	}
}
