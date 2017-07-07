package com.guang.leetCode;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
	public String convert(String s, int numRows) {
		int size;
		if (numRows != 1) {
			size = s.length() / (numRows - 1) + 1;
		}
		else {
			return s;
		}
		char[][] array = new char[numRows][size];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < size; j++) {
				array[i][j] = '\0';
			}
		}
		int index = 0;
		for (int i = 0; i < s.length(); ) {
			for (int j = 0; j < 2*numRows-2; j++) {
				if (j < numRows) {
					array[j][index] = s.charAt(i++);
				}
				else {
					//MYBUG index要加一，否则会覆盖数据
					array[2*(numRows-1)-j][index+1] = s.charAt(i++);
				}
				if (i >= s.length()) {
					break;
				}
			}
			index+=2;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < size; j++) {
				if (array[i][j] != '\0') {
					sb.append(array[i][j]);
				}
			}
		}
		return sb.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String convert1(String s, int numRows) {
		//MYBUG 下面的代码未考虑s=a numRows=1的情况，造成死循环
		if (numRows == 1) {
			return s;
		}
		List<Character>[] array = new ArrayList[numRows];
		//MYBUG 未初始化，会造成空指针异常
		for (int i = 0; i < numRows; i++) {
			array[i] = new ArrayList<>();
		}
		//MYBUG 这里条件加上i++，会导致边界少读两个字符
		for (int i = 0; i < s.length(); ) {
			for (int j = 0; j < 2*numRows-2; j++) {
				if (j < numRows) {
					array[j].add(s.charAt(i++));
				}
				else {
					array[2*(numRows-1)-j].add(s.charAt(i++));
				}
				if (i >= s.length()) {
					break;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		for (List<Character> list: array) {
			for (Character character : list) {
				sb.append(character);
			}
		}
		return sb.toString();
	}
}
