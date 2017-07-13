package com.guang.sougou;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @comment ��Ŀ��

A[n,m]��һ�� n �� m �еľ���a[i,j] ��ʾ A �ĵ� i �� j �е�Ԫ�أ����� x[i,j] Ϊ A �ĵ� i �к͵� j �г��� a[i,j] ֮������Ԫ��(��n+m-2��)�ĳ˻�����x[i,j]=a[i,1]*a[i,2]*...*a[i,j-1]*...*a[i,m]*a[1,j]*a[2,j]...*a[i-1,j]*a[i+1,j]...*a[n,j],

������Ǹ����εľ��� A[n,m]���� MAX(x[i,j])�������е� x[i,j] �е����ֵ��

��������:

��һ����������n��m��֮��n��������󣬾�Ϊ�Ǹ�������

�������:

һ������𰸡�

��������:

3 5
5 1 8 5 2
1 3 10 3 3
7 8 5 5 16

�������:

358400
 * @author guang
 * @date 2017��7��13������10:04:37
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
