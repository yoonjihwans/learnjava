package xyz.itwill06.aop;

public class AopTwo implements AopTarget {
	@Override
	public void display1() {
		System.out.println("*** AopOne 클래스의 display1() 메소드 호출 ***");
	}
	
	@Override
	public void display2() {
		System.out.println("*** AopOne 클래스의 display2() 메소드 호출 ***");
	}
	
	@Override
	public void display3() {
		System.out.println("*** AopOne 클래스의 display3() 메소드 호출 ***");
	}
}