package com.guang.leetCode;

/**
 * @comment Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".


 * @author zhouchenguang
 * @date 2017年10月17日下午4:58:20
 * @version 1.0.0
 */
public class ReverseVowelsofaString {
	 public String reverseVowels(String s) {
	        if (s == null) {
				return null;
			}
	        char[] arr = s.toCharArray();
	        char temp ;
	        for(int i=0,j=s.length()-1;i<j;){
	        	while(!isVowel(arr[i]) && i<j){
	        		i++;
	        	}
	        	while(!isVowel(arr[j]) && j>i){
	        		j--;
	        	}
	        	if (i < j) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
					i++;
					j--;
				}
	        }
	        return new String(arr);
	    }
	 private boolean isVowel(char ch){
		 //MYBUG 忘了英文大小写了
		 if (ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u'
			|| ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U') {
			return true;
		}else {
			return false;
		}
	 }
	 public static void main(String[] args) {
		 ReverseVowelsofaString s = new ReverseVowelsofaString();
		System.out.println(s.reverseVowels("hello"));
		System.out.println(s.reverseVowels("leetcode"));
	}
}
