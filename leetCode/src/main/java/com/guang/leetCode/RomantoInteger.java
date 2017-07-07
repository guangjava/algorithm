package com.guang.leetCode;
import java.util.HashMap;
import java.util.Map;

public class RomantoInteger {
	public int romanToInt(String s) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);
		if (s.length() == 0) {
			return 0;
		}
		int sum = map.get(s.charAt(s.length()-1));
		int a = map.get(s.charAt(0));
		int b = 0;
		for (int i = 1; i < s.length(); i++) {
			b = map.get(s.charAt(i));
			if (a < b) {
				sum -= a;
			}
			else {
				sum += a;
			}
			a = b;
		}
		return sum;
	}
}
