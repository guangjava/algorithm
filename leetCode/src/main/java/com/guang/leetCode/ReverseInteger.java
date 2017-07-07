package com.guang.leetCode;

public class ReverseInteger {
	public int reverse(int x) {
		//MYBUG 判断正负，否则负数无法转换
		int flag = x<0 ? -1 : 1;
		//MYBUG 如果是最小值-2^31，Math.abs(x)返回值依然是原值
		if (x == Integer.MIN_VALUE) {
			return 0;
		}
		x = Math.abs(x);
		int reverse = 0;
		while (x/10 > 0) {
			int a = x%10;
			reverse = reverse*10 + a;
			x = x/10;
		}
		//MYBUG 转换后有可能会发生超过2^31-1的情况
		if (Integer.MAX_VALUE/10 < reverse){
			return 0;
		}
		reverse = reverse*10 + x;
		reverse *= flag;
		return reverse;
		
		
	}
}
