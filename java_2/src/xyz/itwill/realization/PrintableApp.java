package xyz.itwill.realization;

public class PrintableApp {
	public static void main(String[] args) {
		Printable printOne = new PrintSingle();

		Printable.power();// 인터페이스를 사용하여 정적메소드 호출
		printOne.print();// 묵시적 객체형변환에 의해 자식클래스의 오버라이딩 선언된 메소드 호출
		printOne.scan(); // 부모인터페이스 기본메소드 호출
		System.out.println("==================================");
		Printable printTwo = new PrintMulti();

		Printable.power();// 
		printOne.print();// 
		printOne.scan(); // 묵시적 객체형변환에 의해 자식클래스의 오버라이딩 선언된 메소드 호출

	}

}
