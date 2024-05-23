package xyz.itwill.realization;

public interface Printable {
	// 추상메소드(Abstract Method) : 인터페이스를 상속받은 자식클래스에서 무조건 추상메소드를
	// 오버라이딩 선언 - 자식클래스에서 추상메소드를 오버리이딩 선언하지 않으면 에러 발생
	void print();

	// JDK11 이상에서는 인터페이스에 명령을 존재하는 정적메소드와 기본메소드 작성 가능
	// 정적메소드(Static Method) : static 제한자를 사용하여 작성된 메소드
	// = > java 자료형을(클래스 또는 인터페이스)을 사용하여 메소드 호출
	// = > 정적메소드는 자식메소드는 자식클래스에서 오버라이딩 선언 불가능
	static void power() {
		System.out.println("전원 켜짐");
	}

	// 기본메소드(Default Method) : 인터페이스를 상속받은 자식클래스에서 오버라이딩 선언을
	// 선택할 수 있는 메소드 - 오버라이딩 선언하지 않으면 기본메소드의 명령이 실행
	// 형식) [접근제한자]default 반환형 메소드명(자료형 변수명,...){명령; 명령; ...}
	default void scan() {
		System.out.println("error 스캔 기능을 제공하지 않습니다.");
	}

}
