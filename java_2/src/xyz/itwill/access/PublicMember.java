package xyz.itwill.access;

//public : 클래스, 필드, 생성자, 메소드의 접근을 제한하기 위한 접근 제한자
// => 클래스, 필드, 생성자, 메소드를 같은 패키지 및 다른 패키지의 클래스에서 사용 가능하도록
//설정하는 접근 제한자

//하나의 Java 소스파일에 다수의 Java 자료형(클래스, 인터페이스, 열거형 등) 선언 가능 - 비권장
// => 하나의 Java 소스파일에는 public 접근제한자를 사용한 Java 자료형을 하나만 선언 가능
// => public 접근제한자를 사용한 Java 자료형의 이름으로 소스파일명 저장
public class PublicMember {
	public int num;
	
	public void display() {
		System.out.println("num = "+num);
	}
}