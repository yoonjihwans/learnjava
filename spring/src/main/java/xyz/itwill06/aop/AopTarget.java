package xyz.itwill06.aop;

//횡단관심코드가 삽입될 핵심관심코드의 메소드(타겟메소드)를 추상메소드로 선언 - PointCut
public interface AopTarget {
	void display1();
	void display2();
	void display3();
}