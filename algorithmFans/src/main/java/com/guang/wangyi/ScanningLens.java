package com.guang.wangyi;

import java.util.Scanner;

/**
 * @comment 题目：

在N*M的草地上，提莫种了K个蘑菇，蘑菇爆炸的威力极大，兰博不想贸然去闯，而且蘑菇是隐形的。有一种叫做扫描透镜的物品可以扫描出隐形的蘑菇，于是他回了一趟战争学院,买了2个扫描透镜，一个 扫描透镜可以扫描出(3*3)方格中所有的蘑菇，然后兰博就可以清理掉一些隐形的蘑菇。问：兰博最多可以清理多少个蘑菇？

注意：每个方格被扫描一次只能清除掉一个蘑菇。 

输入描述:
第一行三个整数:N,M,K,(1≤N,M≤20,K≤100),N,M代表了草地的大小;
接下来K行,每行两个整数x,y(1≤x≤N,1≤y≤M).代表(x,y)处提莫种了一个蘑菇.
一个方格可以种无穷个蘑菇.

输出描述:
输出一行,在这一行输出一个整数,代表兰博最多可以清理多少个蘑菇.
 * @author guang
 * @date 2017年7月11日下午11:48:52
 * @version 1.0.0
 */
public class ScanningLens {
	static int N;
	static int M;
	/**
	 * 草地
	 */
	static int[][] lawn;
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		int K = scanner.nextInt();
		lawn = new int[N][M];
		init(lawn);
		for (int i = 0; i < K; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			lawn[x-1][y-1]++;
		}
		scanner.close();
		if (N<=3 && M<=3) {
			System.out.println(scan(0,0,0,0,lawn));
			return;
		}
		int[][] cornerList = new int[(N-2)*(M-2)][2];
		for (int i = 0; i < N-2; i++) {
			for (int j = 0; j < M-2; j++) {
				cornerList[i*(N-2)+j][0] = i;
				cornerList[i*(N-2)+j][1] = j;
			}
		}
		int max = 0;
		for (int i = 0; i < cornerList.length; i++) {
			for (int j = i; j < cornerList.length; j++) {
				int temp = scan(cornerList[i][0], cornerList[i][1], cornerList[j][0], cornerList[j][1], lawn);
				max = Math.max(max, temp);
			}
		}
		System.out.println(max);
	}

	/**
	 * @param x1 框1左上角x坐标
	 * @param y1 框1左上角y坐标
	 * @param x2 框2左上角x坐标
	 * @param y2 框2左上角y坐标
	 * @return
	 * @author zhouchenguang
	 * @param lawn2 
	 * @date 2017年7月12日上午12:03:42
	 * @since 1.0.0
	 */
	private static int scan(int x1, int y1, int x2, int y2, int[][] lawn2) {
		int sum = 0;
		for (int i = x1; i < Math.min(x1+3, N); i++) {
			for (int j = y1; j < Math.min(y1+3, M); j++) {
				if (lawn2[i][j] > 0) {
					lawn2[i][j]--;
					sum++;
				}
			}
		}
		for (int i = x2; i < Math.min(x2+3, N); i++) {
			for (int j = y2; j < Math.min(y2+3, M); j++) {
				if (lawn2[i][j] > 0) {
					lawn2[i][j]--;
					sum++;
				}
			}
		}
		return sum;
	}

	private static void init(int[][] lawn) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				lawn[i][j] = 0;
			}
		}
		
	}

}
