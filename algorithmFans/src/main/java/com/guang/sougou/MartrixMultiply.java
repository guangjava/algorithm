package com.guang.sougou;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @comment 题目：

A[n,m]是一个 n 行 m 列的矩阵，a[i,j] 表示 A 的第 i 行 j 列的元素，定义 x[i,j] 为 A 的第 i 行和第 j 列除了 a[i,j] 之外所有元素(共n+m-2个)的乘积，即x[i,j]=a[i,1]*a[i,2]*...*a[i,j-1]*...*a[i,m]*a[1,j]*a[2,j]...*a[i-1,j]*a[i+1,j]...*a[n,j],

现输入非负整形的矩阵 A[n,m]，求 MAX(x[i,j])，即所有的 x[i,j] 中的最大值。

输入描述:

第一行两个整数n和m。之后n行输入矩阵，均为非负整数。

输出描述:

一行输出答案。

输入例子:

3 5
5 1 8 5 2
1 3 10 3 3
7 8 5 5 16

输出例子:

358400
 * @author guang
 * @date 2017年7月13日下午10:04:37
 * @version 1.0.0
 */
public class MartrixMultiply {
	BigDecimal[] rowMulArray;
	BigDecimal[] columnMulArray;
	int n;
	int m;
	private int[][] martirx;
	public MartrixMultiply(int n, int m, int[][] martirx) {
		super();
		this.n = n;
		this.m = m;
		this.martirx = martirx;
		initRowMul();
		initColMul();
	}
	private void initColMul() {
		columnMulArray = new BigDecimal[m];
		for (int i = 0; i < m; i++) {
			BigDecimal tmp = BigDecimal.ONE;
			for (int j = 0; j < n; j++) {
				tmp = tmp.multiply(new BigDecimal(martirx[j][i]));
			}
			columnMulArray[i] = tmp;
		}
		
	}
	private void initRowMul() {
		rowMulArray = new BigDecimal[n];
		for (int i = 0; i < n; i++) {
			BigDecimal tmp = BigDecimal.ONE;
			for (int j = 0; j < m; j++) {
				tmp = tmp.multiply(new BigDecimal(martirx[i][j]));
			}
			rowMulArray[i] = tmp;
		}
		
	}
	public BigDecimal maxMulti() {
		BigDecimal max = BigDecimal.ZERO;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				BigDecimal tmp = rowMulArray[i].multiply(columnMulArray[j]).divide(new BigDecimal(martirx[i][j]*martirx[i][j]));
				max = max.max(tmp).equals(max) ? max : tmp;
			}
		}
		return max;
	}
	public static void main(String[] args) {
		InputStream input = MartrixMultiply.class.getResourceAsStream("/input.txt");
		Scanner scanner = new Scanner(input);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[][] mar = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mar[i][j] = scanner.nextInt();
			}
		}
		MartrixMultiply martrixMultiply = new MartrixMultiply(n, m, mar);
		System.out.println(martrixMultiply.maxMulti());
		scanner.close();
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
