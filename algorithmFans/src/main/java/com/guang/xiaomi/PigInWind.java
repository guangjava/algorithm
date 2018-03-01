package com.guang.xiaomi;

import java.util.ArrayList;
import java.util.List;

/**
 * @comment 题目：

风口之下，猪都能飞。当今中国股市牛市，真可谓“错过等七年”。 给你一个回顾历史的机会，已知一支股票连续n天的价格走势，以长度为n的整数数组表示，数组中第i个元素（prices[i]）代表该股票第i天的股价。

假设你一开始没有股票，但有至多两次买入1股而后卖出1股的机会，并且买入前一定要先保证手上没有股票。若两次交易机会都放弃，收益为0。 设法计算你能获得的最大收益。 输入数值范围：2<=n<=100，0<=prices[i]<=100

输入例子:

3,8,5,1,7,8

输出例子:

12

 * @author zhouchenguang
 * @date 2017年8月31日上午10:37:59
 * @version 1.0.0
 */
public class PigInWind {
	/**计算最佳收益
	 * @param prices 股票n天股价
	 * @author zhouchenguang
	 * @throws Exception 
	 * @date 2017年8月31日上午10:43:18
	 * @since 1.0.0
	 */
	public static void bestProfit(int[] prices) throws Exception {
		if (prices == null) return;
		for (int i = 0; i < prices.length; i++) System.err.print(i+" ");
		System.out.println();
		for (int i = 0; i < prices.length; i++) System.out.print(prices[i]+" ");
		System.out.println();
		List<Price> lows = new ArrayList<>();//极小值集合
		List<Price> highs = new ArrayList<>();//极大值集合
		int min = 0;//存最小值下标
		boolean lowFlag = false;//判断取极小极大
		int i = -1;
		if (prices.length < 2) {
			System.out.println("交易【0】次，收益【0】");
			return;
		}
		//找到第一个极小点
		if (prices[1] > prices[0]) {//prices[0]为极小点
			lows.add(new Price(0, prices[0], 0));
			min = 0;
			i = 2;
		}
		if (prices[1] <= prices[0]) {//prices[0]不是极小点
			for(int j=2;j<prices.length;j++){
				if (prices[j] > prices[j-1]) {
					lows.add(new Price(j-1, prices[j-1],j-1));
					min = j-1;
					i = j+1;
					break;
				}
			}
		}
		if (i == -1) {//说明单调递减
			System.out.println("交易【0】次，收益【0】");
			return;
		}
		for(;i<prices.length;i++){//取极小值极大值
			if (lowFlag) {//取极小值
				if (prices[i-1] < prices[i]) {
					if (prices[i-1] < prices[min]) {
						min = i-1;
					}
					lows.add(new Price(i-1, prices[i-1], min));
					lowFlag = false;
				}
			}else {//取极大值
				if (prices[i-1] > prices[i]) {
					highs.add(new Price(i-1, prices[i-1], min));
					lowFlag = true;
				}
			}
		}
		//处理最后一个值
		if (lowFlag) {//取极小值
			min = Math.min(min, prices[i-1]);
			lows.add(new Price(i-1, prices[i-1], min));
			lowFlag = false;
		}else {//取极大值
			highs.add(new Price(i-1, prices[i-1], min));
			lowFlag = true;
		}
		//求一次交易最佳收益
		highs.get(0).profit1 = highs.get(0).price - prices[highs.get(0).min];
		highs.get(0).buy1 = highs.get(0).min;
		highs.get(0).sell1 = highs.get(0).index;
		for (int j = 1; j < highs.size(); j++) {
			//卖出点包括当前极大点
			if (highs.get(j-1).profit1 < highs.get(j).price-prices[highs.get(j).min]) {
				highs.get(j).profit1 = highs.get(j).price-prices[highs.get(j).min];
				highs.get(j).buy1 = highs.get(j).min;
				highs.get(j).sell1 = highs.get(j).index;
			}else {//收益与前一个极大点相同
				highs.get(j).profit1 = highs.get(j-1).profit1;
				highs.get(j).buy1 = highs.get(j-1).buy1;
				highs.get(j).sell1 = highs.get(j-1).sell1;
			}
		}
		if (highs.size() < 2) {//只有一个极大点，只交易一次
			System.out.println("交易【1】次，收益【"+highs.get(0).profit1+"】,"
					+ "第一次【"+highs.get(0).buy1+"】买入，"
							+ "【"+highs.get(0).sell1+"】卖出。");
			return;
		}
		//求两次交易最佳收益
		highs.get(0).profit2 = highs.get(0).profit1;
		//在第二个极大点，两次交易必比一次交易收益大
		highs.get(1).profit2 = highs.get(0).profit1 + highs.get(1).price-lows.get(1).price;
		highs.get(1).buy2[0] = lows.get(0).index;
		highs.get(1).sell2[0] = highs.get(0).index;
		highs.get(1).buy2[1] = lows.get(1).index;
		highs.get(1).sell2[1] = highs.get(1).index;
		for (int j = 2; j < highs.size(); j++) {
			int sell1 = 0;//第一次卖出点
			int min2 = getMin(lows,highs,1,j);//阶段最小点
			int profit2 = highs.get(0).profit1 + highs.get(j).price-prices[min2];
			for(int k=1; k<j; k++){
				int minLoop = getMin(lows,highs,k+1,j);
				int profitLoop = highs.get(k).profit1 + highs.get(j).price-prices[minLoop];
				if (profitLoop > profit2) {
					sell1 = k;
					min2 = minLoop;
					profit2 = profitLoop;
				}
			}
			if (profit2 < highs.get(j-1).profit2) {//收益与前一个极大点相同
				highs.get(j).profit2 = highs.get(j-1).profit2;
				highs.get(j).buy2 = highs.get(j-1).buy2;
				highs.get(j).sell2 = highs.get(j-1).sell2;
			}else {//卖出点包括当前极大点
				highs.get(j).profit2 = profit2;
				highs.get(j).buy2[0] = highs.get(sell1).buy1;
				highs.get(j).sell2[0] = highs.get(sell1).sell1;
				highs.get(j).buy2[1] = min2;
				highs.get(j).sell2[1] = highs.get(j).index;
			}
		}
		System.out.println("交易【2】次，收益【"+highs.get(highs.size()-1).profit2+"】,"
				+ "第一次【"+highs.get(highs.size()-1).buy2[0]+"】买入，"
				+ "【"+highs.get(highs.size()-1).sell2[0]+"】卖出。"
				+ "第二次【"+highs.get(highs.size()-1).buy2[1]+"】买入，"
				+ "【"+highs.get(highs.size()-1).sell2[1]+"】卖出。");
	}

	/**范围内最小值下标
	 * @param lows
	 * @param highs
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 * @author zhouchenguang
	 * @date 2017年8月31日下午3:17:20
	 * @since 1.0.0
	 */
	private static int getMin(List<Price> lows, List<Price> highs, int start, int end) throws Exception {
		if (start > end) throw new Exception("getMin方法参数范围不对");
		if (start == end) return lows.get(start).index;
		if (highs.get(end).min > lows.get(start).index) return highs.get(end).min;
		Price min = lows.get(start);
		for(int i=start+1; i<=end; i++){
			if (lows.get(i).price < min.price) min = lows.get(i);
		}
		return min.index;
	}

	public static void main(String[] args) {
		int[] input = {3,8,5,1,7,8};
		try {
			bestProfit(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @comment 每天股价
	 * @author zhouchenguang
	 * @date 2017年8月31日上午10:51:55
	 * @version 1.0.0
	 */
	private static class Price{
		int index;//prices数组下标
		int price;//股价
		int min;//最小值
		int buy1;//一次买入点
		int sell1;//一次卖出点
		int[] buy2 = new int[2];//二次买入点
		int[] sell2 = new int[2];//二次卖出点
		int profit1;//最多一次交易截止到n天最佳收益
		int profit2;//最多两次交易截止到n天最佳收益
		/**
		 * @param index
		 * @param price
		 * @param min
		 */
		public Price(int index, int price, int min) {
			this.index = index;
			this.price = price;
			this.min = min;
		}
		
	}
}
