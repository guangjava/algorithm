package com.guang.meituan;

import java.util.Scanner;

/**
 * @comment ��Ŀ������
����һ���ַ���s������Դ���ɾ��һЩ�ַ���ʹ��ʣ�µĴ���һ�����Ĵ������ɾ������ʹ�û��Ĵ���أ�
�����Ҫɾ�����ַ�����
��������:

���������ж��飬ÿ�����һ���ַ���s���ұ�֤:1<=s.length<=1000.
  


�������:

����ÿ�����ݣ����һ������������������Ҫɾ�����ַ�������

��������:
abcda
google

�������:
2
2
 * @author guang
 * @date 2017��7��10������9:49:04
 * @version 1.0.0
 */
public class PalindromeConstruct {
	
	private static int getPalindromeLenth(String s) {
		int[][] dp = new int[1001][1001];//��̬�滮
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
