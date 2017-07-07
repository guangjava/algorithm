package com.guang.leetCode;

public class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) {
			return "";
		}
		for (int j = 0; j < strs[0].length(); j++) {
			char ch = strs[0].charAt(j);
			for (int i = 0; i < strs.length; i++) {
				if (strs[i].length() <= j) {
					return strs[i];
				}
				if (strs[i].charAt(j) != ch) {
					return strs[0].substring(0, j);
				}
			}
		}
		return strs[0];
	}
}
