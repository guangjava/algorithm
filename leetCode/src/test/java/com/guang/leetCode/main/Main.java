package com.guang.leetCode.main;

import com.guang.leetCode.MedianofTwoSortedArrays;
import com.guang.leetCode.RegularExpressionMatching;
import com.guang.leetCode.ReverseInteger;
import com.guang.leetCode.RomantoInteger;
import com.guang.leetCode.StringtoInteger;

import java.util.List;

import com.guang.leetCode.ContainerWithMostWater;
import com.guang.leetCode.GenerateParentheses;
import com.guang.leetCode.IntegertoRoman;
import com.guang.leetCode.LetterCombinationsofaPhoneNumber;
import com.guang.leetCode.LongestPalindromicSubstring;
import com.guang.leetCode.LongestSubstringWithoutRepeatingCharacters;
import com.guang.leetCode.TwoSum;
import com.guang.leetCode.ValidParentheses;
import com.guang.leetCode.ZigZagConversion;
import com.guang.leetCode._3Sum;
import com.guang.leetCode._3SumClosest;
import com.guang.leetCode._4Sum;

public class Main {

	public static void main(String[] args) {
		testGenerateParenthesis();
	}
	
	@SuppressWarnings("unused")
	private static void testTwoSum(){
		TwoSum twoSum = new TwoSum();
		int[] nums = new int[]{3,2,4};
		int target = 6;
		int[] output = twoSum.twoSum2(nums, target);
		for (int i = 0; i < output.length; i++) {
			System.out.print(output[i]+" ");
		}
	}
	
	@SuppressWarnings("unused")
	private static void testLengthOfLongestSubstring() {
		String string = "abba";
		LongestSubstringWithoutRepeatingCharacters lengthOfLongestSubstring = new LongestSubstringWithoutRepeatingCharacters();
		System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring(string));
	}
	
	@SuppressWarnings("unused")
	private static void testFindMedianSortedArrays() {
		MedianofTwoSortedArrays findMedianSortedArrays = new MedianofTwoSortedArrays();
		int[] s1 = new int[]{1,17,23,60,81};
		int[] s2 = new int[]{25,33,47,59,92};
		System.out.println(findMedianSortedArrays.findMedianSortedArrays(s1, s2));
	}
	
	@SuppressWarnings("unused")
	private static void testLongestPalindrome() {
		LongestPalindromicSubstring longestPalindromicSubstring = new LongestPalindromicSubstring();
		String string = "ttabcba";
		System.out.println(longestPalindromicSubstring.longestPalindrome1(string));
	}
	
	@SuppressWarnings("unused")
	private static void testConvert() {
		ZigZagConversion zagConversion = new ZigZagConversion();
		//"A",1;
		//"",1;
		System.out.println(zagConversion.convert("PAYPALISHIRING", 3));
	}
	
	@SuppressWarnings("unused")
	private static void testReverse() {
		ReverseInteger reverseInteger = new ReverseInteger();
		//123，-123，0
		System.out.println(reverseInteger.reverse(-2147483648));
	}
	
	@SuppressWarnings("unused")
	private static void testMyAtoi() {
		StringtoInteger stringtoInteger = new StringtoInteger();
		//"",100,-100,0,+0,+774,+-4,010,"  010"
		System.out.println(stringtoInteger.myAtoi("  010"));
	}
	
	@SuppressWarnings("unused")
	private static void testIsMatch() {
		RegularExpressionMatching matching = new RegularExpressionMatching();
		String s = "a";
		String p = "ab*";
		System.out.println(matching.isMatch(s, p));
	}
	
	@SuppressWarnings("unused")
	private static void testMaxArea() {
		ContainerWithMostWater water = new ContainerWithMostWater();
		int[] height = new int[15000];
		for (int i = 0,j=15000; i < 15000; i++) {
			height[i] = j--;
		}
		System.out.println(water.maxArea(height));
	}
	
	@SuppressWarnings("unused")
	private static void testIntToRoman() {
		IntegertoRoman roman = new IntegertoRoman();
		System.out.println(roman.intToRoman(1));
	}
	
	@SuppressWarnings("unused")
	private static void testRomanToInt() {
		RomantoInteger integer = new RomantoInteger();
		String s = "MMMDCXLIX";
		System.out.println(integer.romanToInt(s));
	}
	
	@SuppressWarnings("unused")
	private static void testThreeSum() {
		_3Sum sum = new _3Sum();
		int[] num = new int[]{-5,1,-3,-1,-4,-2,4,-1,-1};
		List<List<Integer>> list = sum.threeSum(num);
		System.out.println(list);
	}
	
	@SuppressWarnings("unused")
	private static void testThreeSumClosest() throws Exception {
		_3SumClosest sumClosest = new _3SumClosest();
		int[] nums = new int[]{43,75,-90,47,-49,72,17,-31,-68,-22,-21,-30,65,88,-75,23,97,-61,53,87,-3,33,20,51,-79,43,80,-9,34,-89,-7,93,43,55,-94,29,-32,-49,25,72,-6,35,53,63,6,-62,-96,-83,-73,66,-11,96,-90,-27,78,-51,79,35,-63,85,-82,-15,100,-82,1,-4,-41,-21,11,12,12,72,-82,-22,37,47,-18,61,60,55,22,-6,26,-60,-42,-92,68,45,-1,-26,5,-56,-1,73,92,-55,-20,-43,-56,-15,7,52,35,-90,63,41,-55,-58,46,-84,-92,17,-66,-23,96,-19,-44,77,67,-47,-48,99,51,-25,19,0,-13,-88,-10,-67,14,7,89,-69,-83,86,-70,-66,-38,-50,66,0,-67,-91,-65,83,42,70,-6,52,-21,-86,-87,-44,8,49,-76,86,-3,87,-32,81,-58,37,-55,19,-26,66,-89,-70,-69,37,0,19,-65,38,7,3,1,-96,96,-65,-52,66,5,-3,-87,-16,-96,57,-74,91,46,-79,0,-69,55,49,-96,80,83,73,56,22,58,-44,-40,-45,95,99,-97,-22,-33,-92,-51,62,20,70,90
				};
		int target = 284;
		int result = sumClosest.threeSumClosest1(nums, target);
		System.out.println(result);
	}
	
	@SuppressWarnings("unused")
	private static void testLetterCombinations() {
		LetterCombinationsofaPhoneNumber number = new LetterCombinationsofaPhoneNumber();
		String digits = "";
		System.out.println(number.letterCombinations(digits));
	}
	
	@SuppressWarnings("unused")
	private static void testFourSum() {
		_4Sum sum = new _4Sum();
		int[] nums = new int[]{91277418,66271374,38763793,4092006,11415077,60468277,1122637,72398035,-62267800,22082642,60359529,-16540633,92671879,-64462734,-55855043,-40899846,88007957,-57387813,-49552230,-96789394,18318594,-3246760,-44346548,-21370279,42493875,25185969,83216261,-70078020,-53687927,-76072023,-65863359,-61708176,-29175835,85675811,-80575807,-92211746,44755622,-23368379,23619674,-749263,-40707953,-68966953,72694581,-52328726,-78618474,40958224,-2921736,-55902268,-74278762,63342010,29076029,58781716,56045007,-67966567,-79405127,-45778231,-47167435,1586413,-58822903,-51277270,87348634,-86955956,-47418266,74884315,-36952674,-29067969,-98812826,-44893101,-22516153,-34522513,34091871,-79583480,47562301,6154068,87601405,-48859327,-2183204,17736781,31189878,-23814871,-35880166,39204002,93248899,-42067196,-49473145,-75235452,-61923200,64824322,-88505198,20903451,-80926102,56089387,-58094433,37743524,-71480010,-14975982,19473982,47085913,-90793462,-33520678,70775566,-76347995,-16091435,94700640,17183454,85735982,90399615,-86251609,-68167910,-95327478,90586275,-99524469,16999817,27815883,-88279865,53092631,75125438,44270568,-23129316,-846252,-59608044,90938699,80923976,3534451,6218186,41256179,-9165388,-11897463,92423776,-38991231,-6082654,92275443,74040861,77457712,-80549965,-42515693,69918944,-95198414,15677446,-52451179,-50111167,-23732840,39520751,-90474508,-27860023,65164540,26582346,-20183515,99018741,-2826130,-28461563,-24759460,-83828963,-1739800,71207113,26434787,52931083,-33111208,38314304,-29429107,-5567826,-5149750,9582750,85289753,75490866,-93202942,-85974081,7365682,-42953023,21825824,68329208,-87994788,3460985,18744871,-49724457,-12982362,-47800372,39958829,-95981751,-71017359,-18397211,27941418,-34699076,74174334,96928957,44328607,49293516,-39034828,5945763,-47046163,10986423,63478877,30677010,-21202664,-86235407,3164123,8956697,-9003909,-18929014,-73824245
				};
		int target = -236727523;
		System.out.println(sum.fourSum(nums, target));
	}
	
	@SuppressWarnings("unused")
	private static void testIsValid() {
		ValidParentheses parentheses = new ValidParentheses();
		String string = "([)]";
		System.out.println(parentheses.isValid(string));
	}
	
	private static void testGenerateParenthesis() {
		GenerateParentheses parentheses = new GenerateParentheses();
		System.out.println(parentheses.generateParenthesis(1));
	}
}
