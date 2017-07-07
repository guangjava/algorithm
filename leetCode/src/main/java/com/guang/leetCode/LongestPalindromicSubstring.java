package com.guang.leetCode;

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int start = 0;
		int length = 1;
		for (int i = 0; i < s.length(); i++) {
			int tempLength = 1;
			//MYBUG 左边界未包括0
			for (int j = 1; i-j>=0 && i+j<s.length(); j++) {
				if (s.charAt(i-j)!=s.charAt(i+j)) {
					break;
				}
				//MYBUG 长度每次加2，错误增加了
				tempLength += 2;
			}
			if (tempLength > length) {
				length = tempLength;
				start = i - length/2;
			}
			tempLength = 0;
			for (int j = 1; i-j+1>=0 && i+j<s.length(); j++) {
				if (s.charAt(i-j+1)!=s.charAt(i+j)) {
					break;
				}
				tempLength += 2;
			}
			if (tempLength > length) {
				length = tempLength;
				start = i - length/2 + 1;
			}
		}
		return s.substring(start, start+length);
	}

	public String longestPalindrome1(String s) {
		char[] string = new char[s.length()*2+1];
		int[] num = new int[string.length];
		//在原字符串中间添加#，则i位置到最左边相等的位置差就是原来字符串该位置回文字符串最大长度
		for (int i = 0; i < s.length(); i++) {
			string[i*2] = '#';
			string[i*2+1] = s.charAt(i);
		}
		string[s.length()*2] = '#';
		//回文字符串达到的最右边坐标
		int max = 0;
		//上面那个回文子串的中心坐标
		int center = 0;
		//核心算法
		for (int i = 0; i < num.length; i++) {
			//i在max范围内
			if (i < max) {
				//找到i关于center点的镜像点
				int iMirror = 2*center - i;
				//iMirror为中心的回文长度不超过max，由对称性可知，说明i和iMirror回文长度相等
				if (i+num[iMirror] < max) {
					num[i] = num[iMirror];
					continue;
				}
				else {
					judge(string, num, max, i);
				}
			}
			else {
				judge(string, num, i, i);
			}
			if (i+num[i] > max) {
				max = i+num[i];
				center = i;
			}
		}
		
		int length = 0;
		int iResult = 0;
		for (int i = 0; i < num.length; i++) {
			if (num[i] > length) {
				length = num[i];
				iResult = i;
			}
		}
		//MYBUG num坐标和s坐标不一样，需要转换，否则数组越界
		int begin = (iResult-length)/2;
		return s.substring(begin, begin+length);

	}

	/**判断是否回文
	 * @param string 添加了#的字符串
	 * @param num 对应string个位置字符为中心的回文长度
	 * @param max 探索的最右位置
	 * @param i 最右位置的中心点
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月25日上午10:18:52
	 * @since 1.0.0
	 */
	private void judge(char[] string, int[] num, int max, int i) {
		num[i] = max - i;
		for (int j = 1; j+max<num.length && 2*i-j-max>=0; j++) {
			if (string[j+max] == string[2*i-j-max]) {
				num[i]++;
			}
			else {
				break;
			}
		}
	}
}
