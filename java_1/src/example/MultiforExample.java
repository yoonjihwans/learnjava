package example;

import java.util.Iterator;

public class MultiforExample {
	public static void main(String[] args) {
		// ★★★★★
		// ☆★★★★
		// ☆☆★★★
		// ☆☆☆★★
		// ☆☆☆☆★

		System.out.println("===============================================================");
		// ★★★★★
		// ☆★★★★
		// ☆☆★★★
		// ☆☆☆★★
		// ☆☆☆☆★

		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j < i; j++) {
				System.out.print("☆");
			}
			for (int j = 1; j <= 6 - i; j++) {
				System.out.print("★");
			}
			System.out.println();

		}

		System.out.println("===============================================================");
		// ☆☆☆☆★ // 1
		// ☆☆☆★★★ // 3
		// ☆☆★★★★★// 5
		// ☆★★★★★★★ // 7
		// ★★★★★★★★★ // 9

		for (int i = 1; i <= 5; i++) { // 가로
			for (int j = 1; j < 6 - i; j++) { // 공백
				System.out.print("☆");
			}
			for (int j = 1; j <= (i * 2) - 1; j++) { // 세로

				System.out.print("★");
			}
			System.out.println();

		}

		System.out.println("===============================================================");
		// ★☆☆☆★
		// ☆★☆★☆
		// ☆☆★☆☆
		// ☆★☆★☆
		// ★☆☆☆★
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 5; j++) {
				if(i== j || i == 6 - j) {
					System.out.print("★");
				}else{
					System.out.print("☆");
				}

			}
			System.out.println();
		}

		System.out.println("===============================================================");
		
//		★☆☆☆☆☆☆☆☆★
//		★★☆☆☆☆☆☆★★
//		★★★☆☆☆☆★★★
//		★★★★☆☆★★★★
//		★★★★★★★★★★
//		★★★★★★★★★★
//		★★★★★★★★★★
//		★★★★☆☆★★★★
//		★★★☆☆☆☆★★★
//		★★☆☆☆☆☆☆★★
//		★☆☆☆☆☆☆☆☆★
	}

}
