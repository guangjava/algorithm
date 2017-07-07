package com.guang.leetCode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
	private static char[][] map = new char[][]{{},
										   {},
										   {'a','b','c'},
										   {'d','e','f'},
										   {'g','h','i'},
										   {'j','k','l'},
										   {'m','n','o'},
										   {'p','q','r','s'},
										   {'t','u','v'},
										   {'w','x','y','z'}};
	public List<String> letterCombinations(String digits) {
		digits = digits.trim();
		return recusion(digits);
	}
	private List<String> recusion(String digits) {
		List<String> result = new ArrayList<>();
		if (digits==null || digits.trim().equals("")) {
			return result;
		}
		int start = Integer.parseInt(digits.substring(0, 1));
		if (digits.length() == 1) {
			for(int i=0; i<map[start].length; i++){
				String string =map[start][i]+"";
				result.add(string);
			}
		}
		else {
			List<String> sub = recusion(digits.substring(1));
			for(int i=0; i<map[start].length; i++){
				for (String string : sub) {
					String combine = map[start][i] + string;
					result.add(combine);
				}
			}
		}
		return result;
	}
}
