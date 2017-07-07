package com.guang.leetCode;

public class StringtoInteger {
	public int myAtoi(String str) {
		str = str.trim();
		int result = 0;
		boolean negative = false;
		if (str.length() < 1) {
			return 0;
		}
		char first = str.charAt(0);
		if (first=='-') {
			negative = true;
		}
		else if (first>='0' && first<='9') {
			result = result*10 + first-'0';
		}
		else if (first != '+') {
			return 0;
		}
		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			
			if (ch>='0' && ch<='9') {
				result = result*10 + ch-'0';
			}
			else {
				return 0;
			}
		}
		if (negative) {
			return result * -1;
		}
		return result;
	}
}
