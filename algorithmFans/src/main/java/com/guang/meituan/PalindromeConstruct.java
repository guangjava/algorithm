package com.guang.meituan;

import java.util.Scanner;

/**
 * @comment 题目描述：
给定一个字符串s，你可以从中删除一些字符，使得剩下的串是一个回文串。如何删除才能使得回文串最长呢？
输出需要删除的字符个数
输入描述:

输入数据有多组，每组包含一个字符串s，且保证:1<=s.length<=1000.
  


输出描述:

对于每组数据，输出一个整数，代表最少需要删除的字符个数。

输入例子:
abcda
google

输出例子:
2
2
 * @author guang
 * @date 2017年7月10日下午9:49:04
 * @version 1.0.0
 */
public class PalindromeConstruct {
	
	private static int getPalindromeLenth(String s) {
		int[][] dp = new int[1001][1001];//动态规划
		for (int i = 0; i < dp.length; i++) {
			dp[0][i] = 0;
			dp[i][0] = 0;
		}
		String rs = reverse(s);
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < rs.length(); j++) {
				if (s.charAt(i) == rs.charAt(j)) {
					dp[i+1][j+1] = dp[i][j]+1;
				}
				else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		return dp[s.length()][rs.length()];
	}
	private static String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = s.length()-1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		System.out.println(s.length()-getPalindromeLenth(s));
		scanner.close();
	}

}
