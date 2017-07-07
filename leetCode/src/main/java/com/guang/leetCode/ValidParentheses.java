package com.guang.leetCode;

import java.util.ArrayList;
import java.util.List;

public class ValidParentheses {
	public boolean isValid(String s) {
		List<Character> stack = new ArrayList<>();
		int top = -1;
		for(int i=0; i<s.length(); i++){
			char ch = s.charAt(i);
			if (ch=='(' || ch=='{' || ch=='[') {
				stack.add(++top, ch);
			}
			else {
				if (top < 0) {
					return false;
				}
				char item = stack.get(top--);
				if ((item=='('&&ch!=')')||(item=='{'&&ch!='}')||(item=='['&&ch!=']')) {
					return false;
				}
			}
		}
		if (top == -1) {
			return true;
		}
		return false;
	}
}
