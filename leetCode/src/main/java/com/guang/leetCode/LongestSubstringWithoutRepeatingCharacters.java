package com.guang.leetCode;

import java.util.HashMap;
import java.util.Map;
/*给定字符串，查出最长不重复字符子串的长度
 * Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
public class LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		int start = 0;
		int length = 0;
		Map<Character, Integer> map = new HashMap<>();
		
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			//MYBUG 错误点，需要判断重复的字符在子串内
			if (map.containsKey(ch) && map.get(ch)>=start) {
				if (i-start > length) {
					length = i-start;
				}
				start = map.get(ch)+1;
				if (s.length()-start < length) {
					return length;
				}
			}
			map.put(ch, i);
		}
		
		if (s.length()-start > length) {
			length = s.length()-start;
		}
		return length;
	}
}
