package com.guang.meituan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**给你六种面额 1、5、10、20、50、100 元的纸币，假设每种币值的数量都足够多，编写程序求组成N元（N为0~10000的非负整数）的不同组合的个数。 

输入描述:

输入包括一个整数n(1 ≤ n ≤ 10000)

输出描述:

输出一个整数,表示不同的组合方案数

输入例子:

1

输出例子:

1
 * @comment
 * @author zhouchenguang
 * @date 2017年7月3日下午3:43:38
 * @version 1.0.0
 */
@SuppressWarnings("unused")
public class PieceCoins {
	/**
	 * 基本纸币面额
	 */
	static final int[] note = {5,10,20,50,100};
	//MYBUG 不能用缓存
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, Calendar.JUNE, 8);
		cal.add(Calendar.DATE, 34);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		System.out.println(df.format(cal.getTime()));
		Scanner scanner = new Scanner(System.in);
		int total = scanner.nextInt();
		long begin = System.currentTimeMillis();
		System.out.println(changeMoney(total, note.length-1));
		long cost = System.currentTimeMillis()-begin;
		System.out.println("消耗时间："+cost+"ms");
		scanner.close();
	}
	/**递归方法，从最大面额开始换零钱，依次减少最大面额纸币个数
	 * @param total 总金额
	 * @param level note数组下标 4->0
	 * @return 组合方案数
	 * @author zhouchenguang
	 * @date 2017年7月3日下午4:05:10
	 * @since 1.0.0
	 */
	private static BigDecimal changeMoney(int total,int level) {
		int n = total/note[level];//可兑换最大面额纸币个数
		if (level == 0) {//5元情况
			BigDecimal value = new BigDecimal(n+1);
			return value;//依次对应5元张数从n->0
		}
		BigDecimal result = BigDecimal.ZERO;//返回结果
		while (n >= 0) {
			BigDecimal temp = changeMoney(total-n*note[level], level-1);
//			print(level,n,temp);
			result = result.add(temp);
			n--;
		}
		return result;
	}
	private static void print(int level, int n, int temp) {
		for(int i=level;i<note.length-1;i++){
			System.out.print("\t");
		}
		System.out.println(n+"张["+note[level]+"]元纸币下有"+temp+"种情况");
	}
}
