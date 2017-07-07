package com.guang.leetCode;

public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		//MYBUG 用来规避".*"结尾来匹配""的情况
		String s1 = s+"#";
		String p1 = p+"#";
		return match(s1, p1, 0, 0);
	}
	/**递归调用，匹配s,p
	 * @param s 匹配字符串
	 * @param p 匹配模式
	 * @param sBegin s开始位置
	 * @param pBegin p开始位置
	 * @return
	 * @exception
	 * @author zhouchenguang
	 * @date 2016年8月26日上午9:20:07
	 * @since 1.0.0
	 */
	private boolean match(String s,String p,int sBegin,int pBegin) {
		//
		if (sBegin>=s.length() && pBegin>=p.length()) {
			return true;
		}
		for(int i=sBegin,j=pBegin;i<s.length()&&j<p.length();){
			char ch = p.charAt(j);
			//.匹配任意字符
			if (ch == '.') {
				if (j+1 < p.length()) {
					//.*匹配0到多个任意字符
					if (p.charAt(j+1)=='*') {
						//s向后移动0到多个位置，进行匹配
						for(int i1=i,j1=j+2;i1<=s.length();i1++){
							//有一个匹配成功则认为成功
							if (match(s, p, i1, j1)) {
								return true;
							}
						}
						return false;
					}
					//只有.则s向后移动1个位置
					else {
						i++;
						j++;
						continue;
					}
				}
				//p到了末尾
				else {
					//判断s是否也到末尾了
					if (i+1 < s.length()) {
						return false;
					}
					else {
						return true;
					}
				}
			}
			else {
				if (j+1 < p.length()) {
					if (p.charAt(j+1)=='*') {
						//移动0个位置
						if (match(s, p, i, j+2)) {
							return true;
						}
						//s向后移动1到多个位置，进行匹配
						for(int i1=i,j1=j+2;i1<s.length();i1++){
							if (s.charAt(i1)!=ch) {
								return false;
							}
							//有一个匹配成功则认为成功
							if (match(s, p, i1+1, j1)) {
								return true;
							}
						}
						return false;
					}
					else {
						if (s.charAt(i)!=ch) {
							return false;
						}
						i++;
						j++;
						continue;
					}
				}
				//p到了末尾
				else {
					if (s.charAt(i)!=ch) {
						return false;
					}
					//判断s是否也到末尾了
					if (i+1 < s.length()) {
						return false;
					}
					else {
						return true;
					}
				}
			}
		}
		//暂时认为不会走到这里
		return false;
	}
}
