package inheritance;

public class MemberEventApp {
	public static void main(String[] args) {
		//new 연산자로 자식클래스의 생성자를 호출하면 부모클래스의 생성자가 먼저 호출되어 부모클래스의
		//객체가 생성되고 자식클래스의 생성자로 자식클래스의 객체를 생성하여 상속관계가 자동으로 완성
		// => 자식클래스로 생성된 참조변수에는 자식클래스의 객체 메모리 주소가 저장되어 참조변수로  
		//자식클래스 객체를 참조하여 필드 또는 메소드 사용할 수 있으며 상속관계에 부모 클래스의
		//객체를 자동으로 참조하여 필드 또는 메소드 사용 가능
		MemberEvent member1=new MemberEvent();
		
		member1.setId("abc123");
		member1.setName("홍길동");
		member1.setEmail("abc@itwill.xyz");
		
		member1.display();
		System.out.println("==============================================================");
		MemberEvent member2=new MemberEvent("xyz789", "임꺽정", "xyz@itwill.xyz");
		member2.display();
		System.out.println("==============================================================");
	}
}