package xyz.itwill.realization;

//인터페이스(Interface) : 현실에 존재하는 표현대상을 클래스보다 추상적으로 표현하기 위한 자료형
// => 인터페이스에는 상수필드(Constant Field)와 추상메소드(Abstract Method)만 작성 가능
// => JDK11 이상에서는 인터페이스에 정적메소드(Static Method)와 기본메소드(Default Method)도 작성 가능
//형식) public interface 인터페이스명 {
//      		자료형 필드명=초기값;//public static final 생략 가능
//              ...
//              반환형 메소드명(자료형 변수명, 자료형 변수명,...);//public abstract 생략 가능
//              ...
//      }
// => 인터페이스의 이름은 파스칼 표기법을 사용하여 작성하는 것을 권장

//인터페이스로 객체를 생성할 수 없지만 클래스 선언시 인터페이스를 상속받아 작성 - 실체화 관계(Realization)
//형식) public class 클래스명 implements 인터페이스명, 인터페이스명, ... { }
// => 클래스는 implements 키워드를 사용하여 다수의 인터페이스 상속 가능 - 다중상속
// => 인터페이스를 상속받은 클래스는 상속받은 인터페이스의 모든 추상메소드를 무조건 오버라이딩 선언
// => 인터페이스로 참조변수를 생성하여 클래스의 생성자로 객체를 생성하여 저장 가능 - 묵시적
//객체 형변환에 의해 참조변수로 클래스의 객체를 참조해 클래스의 메소드 호출 가능

//인터페이스 선언시 다른 인터페이스를 상속받아 작성 가능 - 다중상속 가능
//형식) public interface 인터페이스명 extends 인터페이스명, 인터페이스명, ... { }

//인터페이스를 선언하여 클래스가 작성시 상속 받아야 되는 이유
//1.클래스의 단일상속 관련 문제를 일부 보완하기 위해 인터페이스 사용
//ex) public class 늑대인간 extends 인간, 늑대 => 불가능 : 에러 발생
//    public class 늑대인간 extends 인간 implements 늑대 => 가능
//    public class 흡혈늑대인간 extends 인간 implements 늑대, 흡혈귀 => 가능
//2.클래스 선언시 반드시 작성해야될 메소드를 제공하기 위해 인터페이스 사용 - 작업지시어
//ex) TV and Radio and SmartPhone >> 볼륨 증가, 볼륨 감소 - 인터페이스
// => 인터페이스를 상속받은 모든 자식클래스에 동일한 메소드가 선언되도록 규칙 제공
// => 클래스간의 결합도를 낮춰 클래스 교체에 따른 기존 클래스의 영향 최소화 - 유지보수의 효율성 증가

public class WolfHumanApp {
	public static void main(String[] args) {
		//자식클래스의 생성자를 호출하여 부모클래스의 객체와 자식클래스의 객체를 모두 생성하여
		//자식클래스의 참조변수에 저장
		// => 자식클래스의 참조변수로 자식클래스의 객체를 참조하여 자식클래스의 메소드를 호출
		//할수 있으며 상속관계의 의해 부모클래스의 객체를 참조해 부모클래스의 메소드 호출 가능
		WolfHuman wolfHuman=new WolfHuman();
		
		wolfHuman.speak();
		wolfHuman.walk();
		wolfHuman.smile();
		wolfHuman.change();
		wolfHuman.fastWlak();
		wolfHuman.cryLoudly();
		System.out.println("==============================================================");
		//자식클래스의 생성자를 호출하여 부모클래스의 객체와 자식클래스의 객체를 모두 생성하여
		//부모클래스의 참조변수에 저장
		// => 부모클래스의 참조변수로 부모클래스의 객체를 참조하여 부모클래스의 메소드 호출 가능
		// => 객체 형변환을 사용하면 참조변수를 이용해 자식클래스의 객체를 참조해 자식클래스의 메소드 호출 가능
		Human human=new WolfHuman();
		
		human.speak();
		human.walk();
		human.smile();
		System.out.println("==============================================================");
		//명시적 객체 형변환을 사용하여 자식클래스의 객체를 참조해 자식클래스의 메소드 호출
		((WolfHuman)human).change();
		//((WolfHuman)human).fastWalk();
		//((WolfHuman)human).cryLoudly();
		System.out.println("==============================================================");
		//자식클래스의 생성자를 호출하여 부모클래스의 객체와 자식클래스의 객체를 모두 생성하여
		//부모인터페이스의 참조변수에 저장
		//Wolf wolf=new WolfHuman();
		
		//자식클래스가 같은 클래스와 인터페이스는 명시적 객체 형변환을 사용하여 자식클래스의
		//객체를 공유하여 저장 가능
		//Wolf wolf=(WolfHuman)human;
		//참조변수를 자식클래스로 형변환하지 않고 부모클래스 또는 부모인터페이스로 명시적 객체
		//형변환하여 참조변수에 저장 가능
		Wolf wolf=(Wolf)human;
		
		//부모인터페이스의 참조변수로 추상메소드를 호출하면 묵시적 객체 형변환에 의해 자식클래스의
		//객체를 참조하여 자식클래스의 메소드 호출
		wolf.fastWlak();
		wolf.cryLoudly();
		System.out.println("==============================================================");
	}
}