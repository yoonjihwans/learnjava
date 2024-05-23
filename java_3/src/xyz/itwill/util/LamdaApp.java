package xyz.itwill.util;

import java.util.function.Consumer;
import java.util.function.Predicate;

//java.util.function 패키지 : 함수형 인터페이스가 선언된 패키지 
// => 함수형 인터페이스를 사용해 람다 표현식으로 객체 생성

public class LamdaApp {
	public static void main(String[] args) {
		// Consumer<T> 함수형 인터페이스 : 제네릭으로 전달된 JAVA 자료형의 객체를 매개변수로
		// 제공받아 아무것도 변환하지 않는 accept() 추상메소드가 선언된 함수형 인터페이스
		Integer number = 100;

		// Consumer 익명클래스를 사용해 객체를 생성하여 참조변수에 저장
		Consumer<Integer> inteConsumer = new Consumer<Integer>() {

			@Override
			public void accept(Integer i) {
				System.out.println(number + i);

			}
		};

	}

}
