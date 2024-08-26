package xyz.itwill06.aop;

//핵심관심코드에 횡단관심코드가 삽입된 메소드가 선언된 클래스 - Proxy 클래스 : Aspect
public class AopProxy implements AopTarget {
	//핵심관심코드의 메소드가 작성된 클래스의 객체를 저장하기 위한 필드
	// => 필드의 자료형을 인터페이스로 작성하여 모든 자식클래스의 객체가 저장되도록 설정
	private AopTarget target;
	
	//횡단관심코드의 메소드가 작성된 클래스의 객체를 저장하기 위한 필드
	private AopLogger logger;
	
	//생성자 매개변수로 전달받은 핵심관심모듈에 대한 객체를 전달받아 필드에 저장하고 횡단관심모듈에
	//대한 객체를 생성하여 필드에 저장
	public AopProxy(AopTarget target) {
		this.target=target;
		logger=new AopLogger();
	}

	//인터페이스를 상속받아 작성된 오버라이드 메소드에서 핵심관심코드에 횡단관심코드가 삽입된 
	//메소드 작성 - Weaving 
	@Override
	public void display1() {
		//핵심관심코드 전 또는 후에 횡단관심코드가 삽입되어 실행되도록 메소드 호출
		// => 횡단관심코드가 삽입되는 위치 : JoinPoint
		logger.beforeLog();//횡단관심코드 실행
		target.display1();//핵심관심코드 실행
	}

	@Override
	public void display2() {
		logger.beforeLog();//횡단관심코드 실행
		target.display2();//핵심관심코드 실행
	}

	@Override
	public void display3() {
		logger.beforeLog();//횡단관심코드 실행
		target.display3();//핵심관심코드 실행
	}
}