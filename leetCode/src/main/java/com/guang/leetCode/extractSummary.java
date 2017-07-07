package com.guang.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @comment
 * 在搜索引擎中，会针对每一网站生成一段摘要，并展示在相应query的搜索结果中。给你两个字符串S和T，请设计并实现一个高效的最短摘要生成算法，该算法能找出S中包含所有T中的字符的最短子字符串，即最短摘要，如：

S="ADOBECODEBANC"
T="ABC"

最短摘要结果为"BANC"
 * @author zhouchenguang
 * @date 2017年6月20日下午4:12:59
 * @version 1.0.0
 */
public class extractSummary {
	private static String summary(String S,String T){
		Map<String, Integer> countMap = new HashMap<String, Integer>();//记录各字符出现次数
		int countSum = 0;//记录当前摘要中各字符总次数，不重复计算
		int start = 0;//起始位置
		int end = 0;//结束位置
		int min_start = 0;//最优起始位置
		int min_end = S.length();//最优结束位置
		int length = S.length();//最优长度
		List<Mark> markList = new ArrayList<>();//位置信息
		int index_mark = 0;
		//开始处理
		char[] charArray = T.toCharArray();//空值判断
		for (char c : charArray) {
			countMap.put(c+"", 0);//初始化countMap
		}
		while(countSum<charArray.length && end<S.length()){//找到第一个符合要求的点
			String str = S.charAt(end)+"";
			if (T.indexOf(str) > -1) {
				if (countMap.get(str) == 0) {
					countSum++;
				}
				countMap.put(str, countMap.get(str)+1);
				Mark mark = new Mark(end, str);
				markList.add(mark);
			}
			end++;
		}
		if (countSum < charArray.length) {//不存在
			return null;
		}//0->第一个满足的点
		start = markList.get(index_mark).index;
		min_start = start;
		min_end = end;
		length = min_end - min_start;//跳过0->第一个满足点之间无效点
		while(end < S.length()){
			while(countSum == charArray.length){//这里应该不用判断MarkList
				String str_name = markList.get(index_mark++).name;
				Integer countOfStr = countMap.get(str_name) - 1;
				if (countOfStr == 0) {
					countSum--;
				}
				countMap.put(str_name, countOfStr);
				start = markList.get(index_mark).index;
				if (countSum==charArray.length && end-start<length) {
					min_start = start;
					min_end = end;
					length = min_end - min_start;
				}
			}
			while(countSum<charArray.length && end<S.length()){
				String str = S.charAt(end)+"";
				if (T.indexOf(str) > -1) {
					if (countMap.get(str) == 0) {
						countSum++;
					}
					countMap.put(str, countMap.get(str)+1);
					Mark mark = new Mark(end, str);
					markList.add(mark);
				}
				end++;
				if (countSum==charArray.length && end-start<length) {
					min_start = start;
					min_end = end;
					length = min_end - min_start;
				}
			}
		}
		return S.substring(min_start, min_end);
	}
	public static void main(String[] args) {
		String S = "ADOBECODEBANC";
		String T = "ABC";
		System.out.println(summary(S, T));

	}
	
	/**
	 * @comment 关键字位置信息
	 * @author zhouchenguang
	 * @date 2017年6月20日下午4:42:57
	 * @version 1.0.0
	 */
	private static class Mark{
		int index;//下标
		String name;//名称
		public Mark(int index, String name) {
			this.index = index;
			this.name = name;
		}
		
	}

}
