package xyz.itwill.nested;

public class AnonymousApp {
	public static void main(String[] args) {
		//인터페이스를 사용해 참조변수 생성은 가능하지만 인터페이스에는 생성자가 없으므로 객체 생성 불가능
		// => 인터페이스는 클래스가 상속받아 사용하기 위한 자료형
		// => 인터페이스를 상속받은 자식클래스는 인터페이스의 모든 추상메소드를 무조건
		//오버라이딩 선언 - 작업지시서의 역활 수행
		//Anonymous anonymous=new Anonymous();
		
		/*
		//인터페이스를 상속받은 자식클래스 작성 - 지역클래스(Local Class) 
		class AnonymousParent implements Anonymous {
			@Override
			public void display() {
				System.out.println("지역클래스의 display() 메소드 호출");
			}
		}
		
		Anonymous anonymous=new AnonymousParent();
		anonymous.display();
		*/

		//인터페이스를 상속받은 이름이 없는 자식클래스의 기본 생성자를 호출하여 객체를 생성해
		//인터페이스 참조변수에 저장
		// => 인터페이스를 상속받은 이름이 없는 자식클래스가 자동 생성 - 익명 내부클래스
		//익명 내부클래스(Anonymous InnerClass) : 추상클래스 또는 인터페이스를 상속받아
		//자동으로 만들어진 이름이 없는 지역클래스
		// => 익명 내부클래스로 메소드에서 사용될 하나의 객체만을 생성할 목적으로 사용
		// => 추상클래스 또는 인터페이스에 작성된 모든 추상메소드를 익명 내부클래스에서 
		//무조건 오버라이딩 선언
		Anonymous anonymous=new Anonymous() {
			@Override
			public void display() {
				System.out.println("익명클래스의 display() 메소드 호출");
			}
		};
		
		anonymous.display();
	}
}







