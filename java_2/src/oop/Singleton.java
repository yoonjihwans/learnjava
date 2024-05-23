package oop;

//싱글턴 디자인 패턴을 적용하여 작성된 클래스 - 싱글톤 클래스(Singleton Class)
// => 프로그램에 객체를 하나만 제공하기 위한 목적으로 클래스를 작성하기 위한 디자인 패턴 
// => 프로그램에 불필요한 객체가 여러개 생성되는 것을 방지하기 위한 디자인 패턴
// => 데이타 처리 기능의 메소드만 작성된 클래스를 선언할 때 싱글톤 디자인 패턴 적용

public class Singleton {
	//클래스로 생성된 객체를 저장하기 위한 필드 작성
	// => static 제한자를 사용하여 정적 필드로 작성
	//시스템 필드 : 클래스에서만 사용할 목적으로 작성된 필드
	// => Getter 메소드와 Setter 메소드를 작성하지 않는 필드
	// => 일반적인 필드와 구분하기 위해 필드명을 [_]로 시작되도록 작성하는 것을 권장
	private static Singleton _instance;
	
	//생성자는 private 접근 제한자를 사용하여 은닉화 처리 되도록 작성
	// => new 연산자로 생성자를 호출하여 객체 생성 불가능
	private Singleton() {
		// TODO Auto-generated constructor stub
	}

	//정적 영역(Static Blcok) : 클래스 파일(XXX.class)을 읽어 메모리(methodArea)에 저장된 후
	//자동으로 실행될 명령을 작성하기 위한 영역 - 정적 영역의 명령은 프로그램에서 한번만 실행
	// => 정적 필드 및 정적 메소드만 사용 가능
	static {
		//new 연산자로 생성자를 호출하여 객체를 생성해 시스템 필드(정적 필드)에 저장
		_instance=new Singleton();
	}
	
	//시스템 필드에 저장된 객체를 반환하는 정적 메소드
	public static Singleton getInstance() {
		return _instance;
	}
	
	public void display() {
		System.out.println("Singleton 클래스의 display() 메소드 호출");
	}
}