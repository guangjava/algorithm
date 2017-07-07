package com.guang.leetCode;

public class IntegertoRoman {
	public String intToRoman(int num) {
		String[][] table = new String[][]
				{{"","I","II","III","IV","V","VI","VII","VIII","IX"},
				 {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
				 {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
				 {"","M","MM","MMM"}}; 
		int n = num;
		StringBuffer sb = new StringBuffer();
		for (int i = 3; i >= 0; i--) {
			int a = 0;
			switch (i) {
			case 0:
				a = n;
				break;
			case 1:
				a = n / 10;
				n %= 10;
				break;
			case 2:
				a = n / 100;
				n %= 100;
				break;
			case 3:
				a = n / 1000;
				n %= 1000;
				break;
			default:
				break;
			}
			sb.append(table[i][a]);
		}
		return sb.toString();
	}
}
