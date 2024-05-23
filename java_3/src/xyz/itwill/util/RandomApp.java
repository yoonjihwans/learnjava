package xyz.itwill.util;

import java.util.Random;

//java.util 패키지 : 프로그램 작성에 유용한 기능을 제공하는 java 자료형이 작성된 패키지

//Scanner 클래스 : 입력장치로부터 값을 입력받기 위한 기능을 제공하는 객체를 생성하는 클래스
//Arrays 클래스 : 배열 요소값을 처리하기 위한 기능을 제공하는 클래스

//Random 클래스 : 난수값 관련 기능을 제공하는 객체를 생성하기 위한 클래스
public class RandomApp {
	public static void main(String[] args) {
		// 시간과 무관한 난수값을 발생할 수 있는 Random 객체 생성
		Random random = new Random();

		for (int i = 1; i <= 5; i++) {
			System.out.println(i + " 번째 난수값 = " + random.nextInt(100));
		}

	}

}
