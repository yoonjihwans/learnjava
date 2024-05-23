package inheritance;

//상속(Inheritance) : 클래스 선언시 다른 클래스를 물려받아 작성하는 기능
// => 기존 클래스를 재사용하여 새로운 클래스를 쉽고 빠르게 작성 - 생산성 증가
// => 공통적인 속성과 행위를 포함한 다수의 클래스를 작성할 때 공통적인 속성과 행위를 구현한
//클래스를 선언하고 클래스를 물려받아 새로운 클래스 작성 - 유지보수의 효율성 증가

//물려주는 클래스 - 부모클래스, 선조클래스, 기본클래스, 슈퍼클래스(SuperClass)
//물려받은 클래스 - 자식클래스, 후손클래스, 파생클래스, 서브클래스(SubClass)

//형식) public class 자식클래스 extends 부모클래스 {
//			자식클래스에서는 부모클래스의 필드와 메소드를 상속받아 사용 가능
//      }
// => 부모클래스의 생성자는 자식클래스에 상속되지 않으며 부모클래스의 은닉화 처리된 필드 또는
//메소드는 자식클래스에서 사용 불가능
// => Java 언어에서는 자식클래스가 하나의 부모클래스만 상속받아 작성 - 단일상속

//super : 자식클래스의 메소드에서 부모클래스 객체의 메모리 주소를 저장하여 부모 객체를 참조해
//부모클래스의 필드 또는 메소드를 사용하기 위한 키워드
// => 자식클래스의 메소드에서 this 키워드로 참조하는 자식클래스 객체의 필드 또는 메소드가 없는
//경우 자동으로 spuer 키워드로 부모클래스의 객체를 참조하여 필드 또는 메소드 사용

//super 키워드를 사용하는 경우
//1.자식클래스의 생성자에서 부모클래스의 매개변수가 있는 생성자를 호출하여 부모클래스의 객체를
//생성하기 위해 super 키워드 사용
//형식) super(값, 값, ...);
// => 자식클래스의 생성자에서 부모클래스의 매개변수가 있는 생성자를 호출하는 명령 - 부모클래스의 객체 필드 초기화
// => 자식클래스의 생성자에서 부모클래스의 생성자를 호출하는 명령은 반드시 첫번째 명령으로 작성
//2.자식클래스에서 부모클래스의 메소드를 오버라이딩 선언한 경우 자식클래스의 메소드에서
//부모클래스의 숨겨진 메소드를 호출하기 위해 super 키워드 사용

//이벤트 관련 회원정보(아이디, 이름, 이메일)를 저장하기 위한 클래스
// => 회원정보를 저장하기 위한 클래스(Member 클래스)를 상속박아 작성하는 것을 권장
public class MemberEvent extends Member {
	/*
	//부모클래스로부터 상속받은 필드 또는 메소드 작성 불필요
	private String id;
	private String name;
	*/
	private String email;
	
	public MemberEvent() {
		//부모클래스의 매개변수가 없는 기본 생성자를 호출하는 명령 - 생략 가능
		//super();
	}

	/*
	public MemberEvent(String id, String name, String email) {
		super();
		//this.id = id;
		//자식클래스의 메소드에서 this 키워드로 자식 객체를 참조하여 자식클래스의 필드 또는 
		//메소드를 사용하며 자식클래스에 필드 또는 메소드가 없는 경우 자동으로 부모 객체를
		//참조하여 부모클래스의 필드 또는 메소드 사용
		setId(id);
		//this.name = name;
		setName(name);
		this.email = email;
	}
	*/
	
	//[Alt]+[Shift]+[S] >> 팝업메뉴 - [O] >> 부모클래스의 생성자 선택 및 필드 선택 후 [Generate] 클릭
	public MemberEvent(String id, String name, String email) {
		//부모클래스의 매개변수가 있는 생성자를 호출하는 명령 - 부모클래스의 객체 초기화
		super(id, name);
		this.email = email;
	}	

	/*
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	//메소드 오버라이딩(Method Overriding) : 상속관계의 클래스에서 부모클래스의 메소드를 
	//자식클래스에서 다시 선언하는 기능
	// => 부모클래스의 메소드를 자식클래스에서 사용하기 불적절한 경우 메소드 오버라이딩 사용 
	// => 부모클래스의 메소드와 같은 접근 제한자, 반환형, 메소드명, 매개변수, 예외전달을 
	//자식클래스에서 동일하게 작성 - 메소드 오버라이딩 작성 규칙
	// => 메소드 오버라이딩 선언하면 부모클래스의 메소드(Hide Method)는 숨겨지고 자식클래스의
	//메소드만 사용되도록 실행
	/*
	public void display() {
		//System.out.println("아이디 = "+id);
		System.out.println("아이디 = "+getId());
		//System.out.println("이름 = "+name);
		System.out.println("이름 = "+getName());
		System.out.println("이메일 = "+email);
	}
	*/
	
	//이클립스에서는 부모클래스의 메소드를 자식클래스에서 오버라이딩 선언되도록 자동 완성하는 기능 제공
	// => 오버라이딩 선언하고 싶은 부모클래스의 메소드명 입력 >> [Ctrl]+[Space] >> Override 메소드 선택
	//@Override : 오버라이딩 선언된 메소드를 표현하기 위한 어노테이션
	// => 메소드 작성시 오버라이딩 작성 규칙을 위반한 경우 에러 발생
	//어노테이션(Annotation) : 특별한 기능(명령의 모임)을 제공하기 위한 Java 자료형
	// => @Override, @Deprecated, @SuppressWarnings
	@Override
	public void display() {
		//super 키워드를 사용해 부모클래스의 숨겨진 메소드 호출 
		super.display();
		System.out.println("이메일 = "+email);
	}
}