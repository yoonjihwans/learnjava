package oop;

//this  : 메소드에서 메소드를 호출한 객체의 메모리 주소를 저장하기 위한 키워드
// = > 메소드에서 this 키워드로 객체를 참조하여 객체의 필드 또는 메소드 사용
// = > this 키워드를 사용하지 않아도 객체의 필드 또는 메소드 사용 가능하도록 자동 제공

//메소드에 this 키워드가 존재하는 이유
// = > 클래스에 작성된 필드는 객체마다 메로리에 독립적으로 생성되지만 메소드는 객체와
//상관없이 메모리에 하나만 생성되어 사용 - 프로토타입 클래스
// => 메소드에서 필드를 사용할 경우 객체를 명확히 구분하여 객체 필드를 사용하기 위한 this 키워드

//this 키워드를 사용하는 경우
//1. 메소드의 매개변수의 이름을 필드의 이름과 동일하게 작성한 경우 필드를 표현하기 위해 this 키워드를 사용
//= > 생성자 또는 Setter 메소드를 작성할 경우 사용
//2.생성자에서 다른 생성자를 호출하여 필드 초기화 처리하기 위해 사용
//3. 이벤트 처리 프로그램 또는 다중 스레드 프로그램에서 객체를 표현하기 위한 this 키워드 사용

//회원정보(아이디, 이름, 이메일)를 저장하기 위한 클래스
public class Member {
	//필드(Field) : 표현대상의 속성값을 저장하기 위한 클래스의 요소
	private String id;
	private String name;
	private String email;
	
	public Member() {
		this("NoId", "NoName", "NoEmail");
	}

	//생성자(Constructor) : 객체를 생성하기 위한 특별한 메소드
	// => 생성자를 작성하지 않으면 매개변수가 없는 기본 생성자를 자동으로 생성하여 제공
	// => 생성자를 작성하면 매개변수가 없는 기본 생성자 미제공
	// => 필드에 원하는 초기값이 저장된 객체를 생성하기 위해 생성자 작성
	//형식) 접근제한자 클래스명(자료형 변수명, 자료형 변수명, ...) { 명령; 명령; ... }
	// => 반환형을 작성하지 않으며 생성자의 이름은 반드시 클래스 이름과 동일하게 작성
	// => 메소드 오버로딩을 사용해 다수의 생성자 작성
	// => 일반적으로 생성자에서는 매개변수로 전달받은 값은 필드의 초기값으로 저장하는 명령 작성
	
	//매개변수가 없는 생성자 작성 - 기본 생성자(Default Constructor)
	// => 필드에 대한 초기화 작업 미구현 - 필드에는 기본값이 초기값으로 저장
	// => 기본 생성자를 작성하지 않으면 클래스 상속시 문제가 발생될 수 있으므로 기본 생성자를 작성하는 것을 권장
	//이클립스 기능을 사용하여 기본 생성자 자동 작성 가능
	// => [Ctrl]+[Space] >> 나열된 목록 중 Constructor 선택
	
	//매개변수가 있는 생성자 작성
	// => 매개변수에 전달되어 저장된 값을 필드의 초기값으로 저장
	//이클립스 기능을 사용하여 매개변수가 있는 생성자 자동 작성 가능
	// => [Alt]+[Shift]+[S] >> 팝업메뉴 - [O] >> 생성자 생성창 - 필드 선택 후 [Generate] 클릭
	public Member(String id) {
		this.id=id;
	}
	
	public Member(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Member(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	//메소드(Method) : 명령을 실행하여 표현대상의 행위(기능)을 구현하기 위한 클래스의 요소
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void display() {
		//System.out.println("아이디 = "+this.id);
		System.out.println("아이디 = "+id);
		System.out.println("이름 = "+name);
		System.out.println("이메일 = "+email);
	}
}