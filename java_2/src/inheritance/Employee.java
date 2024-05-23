package inheritance;

//abstract : 클래스 또는 메소드 작성시 사용할 수 있는 제한자

//추상클래스(Abstract Class) : abstract 제한자를 사용하여 작성된 클래스 
// => 객체 생성이 목적이 아닌 상속을 목적으로 작성하기 위한 클래스 
//형식) public abstract class 클래스명 { }
// => new 연산자로 클래스의 생성자를 호출하여 객체 생성시 에러 발생

//추상메소드(Abstract Method) : abstract 제한자를 사용하여 작성된 메소드
//형식) public abstract 반환형 메소드명(자료형 변수명, 자료형 변수명,...);
// => 메소드의 머릿부만 작성하고 몸체부가 없는 메소드 - 명령이 없는 메소드(미완성된 메소드)
// => 자식클래스에서 반드시 오버라이딩 선언해야될 메소드를 제공하기 위한 사용 - 메소드 작성 규칙 제공
// => 자식클래스에서 추상메소드를 오버라이딩 선언하지 않으면 에러 발생
// => 추상메소드가 하나라도 작성된 클래스는 반드시 추상클래스로 선언

//final : 클래스, 필드, 메소드 작성시 사용할 수 있는 제한자

//final 클래스 : final 제한자를 사용하여 작성된 클래스 
//형식) public final class 클래스명 { }
// => 클래스를 상속받지 못하도록 제한하는 클래스

//final 필드 : final 제한자를 사용하여 작성된 필드 
//형식) 접근제한자 final 자료형 필드명=초기값;
// => 필드에 저장된 초기값을 변경하지 못하도록 제한하는 필드
// => final 필드에 직접 초기값을 저장하는 대신 생성자를 사용하여 final 필드에 초기값 저장 가능

//final 메소드 : final 제한자를 사용하여 작성된 메소드 
//형식) 접근제한자 final 반환형 메소드명(자료형 변수명, 자료형 변수명,...) { 명령; 명령; ... }
// => 자식클래스에서 메소드를 오버라이드 선언하지 못하도록 제한하는 메소드

//사원정보(사원번호, 사원이름)를 저장하기 위한 클래스
// => 고용형태에 떠른 사원 관련 클래스가 반드시 상속받아야 되는 부모클래스
// => 객체 생성이 아닌 상속을 목적으로 작성된 클래스 - 추상클래스로 선언하는 것을 권장
public abstract class Employee {
	private int empNo;
	private String empName;
	//상수필드(Constant Field) : 값 대신 사용하기 위한 이름 제공 - 상수
	//형식) public static final 자료형 상수필드명=값;
	// => 상수필드명은 대문자로 사용하며 스네이크 표기법을 사용하여 작성
	public static final double INCENTIVE_RATE=1.5;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int empNo, String empName) {
		super();
		this.empNo = empNo;
		this.empName = empName;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	//급여를 계산하여 반환하는 메소드
	// => 자식클래스에서 무조건 오버라이딩 선언되도록 추상메소드로 작성하는 것을 권장
	public abstract int computePay();
	
	//모든 사원에게 사원급여의 150%를 성과급으로 계산하여 반환하는 메소드
	//문제점) 자식클래스에서 메소드를 오버라이드 선언할 경우 부모클래스의 메소드는 숨겨지고 
	//자식클래스가 메소드가 호출되어 비정상적인 결과 발생
	//해결법)final 제한자를 사용하여 메소드를 작성하면 자식클래스에서 오버라이딩 선언 불가능
	public final int computeIncentive() {
		//추상메소드를 호출하면 자동으로 자식클래스의 오버라이딩 선언된 메소드 호출
		//return (int)(computePay()*1.5);
		return (int)(computePay()*INCENTIVE_RATE);
	}
}