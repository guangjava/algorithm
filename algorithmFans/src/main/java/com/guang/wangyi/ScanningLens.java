package com.guang.wangyi;

import java.util.Scanner;

/**
 * @comment ��Ŀ��

��N*M�Ĳݵ��ϣ���Ī����K��Ģ����Ģ����ը������������������óȻȥ��������Ģ�������εġ���һ�ֽ���ɨ��͸������Ʒ����ɨ������ε�Ģ��������������һ��ս��ѧԺ,����2��ɨ��͸����һ�� ɨ��͸������ɨ���(3*3)���������е�Ģ����Ȼ�������Ϳ��������һЩ���ε�Ģ�����ʣ�����������������ٸ�Ģ����

ע�⣺ÿ������ɨ��һ��ֻ�������һ��Ģ���� 

��������:
��һ����������:N,M,K,(1��N,M��20,K��100),N,M�����˲ݵصĴ�С;
������K��,ÿ����������x,y(1��x��N,1��y��M).����(x,y)����Ī����һ��Ģ��.
һ����������������Ģ��.

�������:
���һ��,����һ�����һ������,��������������������ٸ�Ģ��.
 * @author guang
 * @date 2017��7��11������11:48:52
 * @version 1.0.0
 */
public class ScanningLens {
	static int N;
	static int M;
	/**
	 * �ݵ�
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
	 * @param x1 ��1���Ͻ�x����
	 * @param y1 ��1���Ͻ�y����
	 * @param x2 ��2���Ͻ�x����
	 * @param y2 ��2���Ͻ�y����
	 * @return
	 * @author zhouchenguang
	 * @param lawn2 
	 * @date 2017��7��12������12:03:42
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
