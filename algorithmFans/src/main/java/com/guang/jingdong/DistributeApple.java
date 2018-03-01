package com.guang.jingdong;

/**
 * @comment 题目：

果园里有一堆苹果，一共n头(n大于1小于9)熊来分，第一头为小东，它把苹果均分n份后，多出了一个，它扔掉了这一个，拿走了自己的一份苹果，接着第二头熊重复这一过程，即先均分n份，扔掉一个然后拿走一份，以此类推直到最后一头熊都是这样(最后一头熊扔掉后可以拿走0个，也算是n份均分)。问最初这堆苹果最少有多少个。

给定一个整数n，表示熊的个数，返回最初的苹果数。保证有解。

测试样例：

2

返回：

3
 * @author zhouchenguang
 * @date 2017年9月4日上午10:17:26
 * @version 1.0.0
 */
public class DistributeApple {
	/**计算最少苹果数量
	 * @param bearNum 熊数量
	 * @author zhouchenguang
	 * @date 2017年9月4日上午10:18:47
	 * @since 1.0.0
	 */
	public static void calcAppleNum(int bearNum) {
		if (bearNum<=1 || bearNum >=9) {
			System.err.println("n大于1小于9");
			return;
		}
		boolean flag = true;//判断循环标志
		for(int i=0; flag; i++){
			/*设m为分前苹果数，i为分后苹果数，n为熊数
			则有((n-1)/n)*(m-1)=i
			解得m=(i*n)/(n-1)+1
			*/
			if ((i*bearNum)%(bearNum-1) == 0) {
				long m = i*bearNum/(bearNum-1)+1;
				int j = 1;
				for(;j<bearNum;j++){
					if ((m*bearNum)%(bearNum-1) != 0) break;
					m = (m*bearNum)/(bearNum-1)+1;
				}
				if (j == bearNum) {
					System.out.println(m);
					flag = false;
				}
			}
		}
	}
	public static void calcSimple(int bearNum) {
		if (bearNum<=1 || bearNum >=9) {
			System.err.println("n大于1小于9");
			return;
		}
		/*设m为分前苹果数，i为分后苹果数，n为熊数
		则有((n-1)/n)*(m-1)=i
		第一只熊分得和扔掉(m+n-1)/n
		第二只熊分得和扔掉(((n-1)/n)*(m-1)+n-1)/n
					=((n-1)*m+n^2-2*n+1)/n^2
					=((n-1)*m+(n-1)^2)/n^2
					=((n-1)/n^2)*(m+n-1)
		...
		第n只熊分得和扔掉((n-1)^(n-1)/n^n)*(m+n-1)
		因为这个数必须是整数，而且(n-1)^(n-1)和n^n互质
		则必有m+n-1=k*(n^n)
		k最小为1
		m+n-1=n^n
		m=n^n-n+1
		*/
		double apple = Math.pow(bearNum, bearNum)-bearNum+1;
		System.out.println(apple);
	}
	public static void main(String[] args) {
		for (int i = 2; i < 9; i++) {
			System.out.println(i+"只熊：");
			calcAppleNum(i);
			calcSimple(i);
		}
	}
}
