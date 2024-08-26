package xyz.itwill06.aop;

//횡단관심모듈(CrossCutting Concern Module) : 횡단관심코드의 메소드가 작성된 클래스 - Advice 클래스
// => 횡단관심코드 : 데이타 처리 명령을 제외한 보조적인 기능을 제공하는 명령
// => 로그 처리, 보안(권한) 처리, 트렉젝션 처리, 예외 처리 등의 명령
public class HewonAdvice {
	//핵심관심모듈의 메소드 중 PointCut 언어로 지정된 타겟메소드의 명령 실행 전 또는 후에
	//삽입되어 실행될 명령이 작성된 메소드 선언
	// => PointCut 언어 : 핵심관심모듈의 메소드 중 타겟메소드를 지정하기 위해 사용되는 언어
	
	//타겟메소드의 명령(핵심관심코드)이 실행되기 전에 삽입되어 실행될 명령이 작성된 메소드
	// => Before Advice Method
	// => JoinPoint : 핵심관심코드를 기준으로 횡단관심코드가 삽입되어 실행되는 위치
	public void beforeLog() {
		System.out.println("### [before]핵심관심코드 실행 전에 삽입되어 실행될 횡단관심코드");
	}
}