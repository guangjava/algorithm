package com.guang.leetCode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<>();
		List<String> list = generateParenthesis(n*2-1, n-1);
		for (String string : list) {
			String s = "(" + string;
			result.add(s);
		}
		return result;
	}
	/**递归算法，用来生成括号字符串
	 * @param n 生成字符串长度
	 * @param leftParenthesis 剩余的左括号个数
	 * @return
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年9月18日下午2:30:56
	 * @since 1.0.0
	 */
	private List<String> generateParenthesis(int n,int leftParenthesis) {
		List<String> result = new ArrayList<>();
		if (n == 1) {
			String string = ")";
			result.add(string);
			return result;
		}
		//')'个数大于'('
		if (leftParenthesis < n-leftParenthesis) {
			List<String> list = generateParenthesis(n-1, leftParenthesis);
			for (String string : list) {
				String s = ")"+string;
				result.add(s);
			}
		}
		//'('个数大于0
		if (leftParenthesis > 0) {
			List<String> list = generateParenthesis(n-1, leftParenthesis-1);
			for (String string : list) {
				String s = "("+string;
				result.add(s);
			}
		}
		return result;
	}
}
