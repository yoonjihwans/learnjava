package xyz.itwill06.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//횡단관심모듈
public class JoinPointAdvice {
	//Around Advice Method를 제외한 나머지 Advice Method는 일반적으로 반환형을 [void]로 작성하고
	//매개변수를 작성하지 않거나 JoinPoint 인터페이스를 자료형을 사용해 매개변수 작성 가능
	// => Advice 클래스의 메소드를 규칙에 맞지 않게 작성할 경우 IllgalArgumentException 발생
	//JoinPoint 객체 : 타겟메소드 관련 정보가 저장된 객체
	// => AspectJ 컴파일러에 의해 Advice 클래스의 메소드가 호출될 때 타겟메소드 관련 정보가
	//저장된 JointPoint 객체를 전달받아 매개변수에 저장
	// => Advice 클래스의 메소드에서 타겟메소드 관련 정보가 필요한 경우 JointPoint 매개변수를
	//작성하여 JointPoint 객체를 제공받아 메소드를 호출해 필요한 기능 구현
	public void beforeDisplay(JoinPoint joinPoint) {//Before Advice Method
		//System.out.println("### [before]핵심관심코드 실행 전에 삽입되어 실행될 횡단관심코드");
		
		//JoinPoint.getTarget() : 타겟메소드를 호출한 객체(핵심관심모듈 - Spring Bean)를
		//Object 객체로 반환하는 메소드
		//Object.getClass() : 객체를 생성한 클래스 정보가 저장된 Class 객체를 반환하는 메소드
		//Class.getName() : Class 객체의 클래스(패키지 포함)의 이름을 문자열로 반환하는 메소드
		//System.out.println(joinPoint.getTarget().getClass().getName());
		
		//Class.getSimpleName() : Class 객체의 클래스(패키지 미포함)의 이름을 문자열로 반환하는 메소드
		//System.out.println(joinPoint.getTarget().getClass().getSimpleName());
		
		//JoinPoint.getSignature() : 타겟메소드 정보가 저장된 Signature 객체를 반환하는 메소드
		//Signature.getName() : 타겟메소드의 이름을 반환하는 메소드
		//System.out.println(joinPoint.getSignature().getName());
		
		//joinPoint.getArgs() : 타겟메소드의 매개변수에 저장된 모든 값(객체)를 Object 배열로 반환하는 메소드
		//System.out.println(joinPoint.getArgs());
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		Object[] params=joinPoint.getArgs();
		
		System.out.print("### [before]"+className+" 클래스의 "+methodName+"(");
		for(int i=0;i<params.length;i++) {
			System.out.print(params[i]);
			if(i < params.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(") 메소드 호출 ###");
	}
	
	public void displayMessage(JoinPoint joinPoint) {//After Advice Method
		//System.out.println("### [after]핵심관심코드가 실행된 후 무조건 삽입되어 실행될 횡단관심코드");
		
		Object[] params=joinPoint.getArgs();
		System.out.println("[after]사원번호가 "+params[0]+"인 사원정보를 삭제 하였습니다.");
	}
	
	//After Returning Advice Method에서는 JoinPoint 인터페이스의 매개변수외에 Object 클래스의
	//매개변수 작성 가능
	// => AspectJ 컴파일러는 Object 클래스의 매개변수에 타겟메소드의 반환값을 제공받아 저장
	// => 타겟메소드에서 반환되는 값(객체)가 고정되어 있는 경우 Object 클래스 대신 반환값의
	//자료형을 사용해 매개변수 작성 가능
	// => After Returning Advice Method에 Object 클래스의 매개변수를 작성한 경우 Spring Bean
	//Configuration File의 AOP 설정에서 after-returning 엘리먼트에 returning 속성을 반드시
	//작성해 타겟메소드의 반환값이 매개변수에 저장되도록 설정
	// => after-returning 엘리먼트에 returning 속성이 없거나 속성값이 잘못된 경우 IllgalArgumentException 예외 발생
	public void displayName(JoinPoint joinPoint, Object object) {//After Returning Advice Method
		//System.out.println("### [after-returning]핵심관심코드가 정상적으로 실행된 후 삽입되어 실행될 횡단관심코드");
		
		String methodName=joinPoint.getSignature().getName();
		String userName=(String)object;
		System.out.println("### [after-returning]"+methodName+" 메소드의 반환값 = "+userName);
	}

	//After Throwing Advice Method에서는 JoinPoint 인터페이스의 매개변수외에 Exception 클래스의
	//매개변수 작성 가능
	// => AspectJ 컴파일러는 Exception 클래스의 매개변수에 타겟메소드에서 발생된 예외정보(Exception 객체)을 제공받아 저장
	// => 타겟메소드에서 발생되는 예외가 고정되어 있는 경우 Exception 클래스 대신 예외를 저장
	//할 수 있는 예외클래스를 자료형을 사용해 매개변수 작성 가능
	// => After Throwing Advice Method에 Exception 클래스의 매개변수를 작성한 경우 Spring Bean
	//Configuration File의 AOP 설정에서 after-throwing 엘리먼트에 throwing 속성을 반드시
	//작성해 타겟메소드에서 발생된 예외가 매개변수에 저장되도록 설정
	// => after-throwing 엘리먼트에 throwing 속성이 없거나 속성값이 잘못된 경우 IllgalArgumentException 예외 발생
	public void exceptionHandle(JoinPoint joinPoint, Exception exception) {//After Throwing Advice Method
		//System.out.println("### [after-throwing]핵심관심코드가 실행시 예외가 발생된 후 실행될 횡단관심코드");
		
		String methodName=joinPoint.getSignature().getName();
		System.out.println("### [after-throwing]"+methodName+" 메소드에서 발생된 예외 = "+exception.getMessage());
	}
	
	//Around Advice Method는 Object 클래스를 반환형으로 작성하고 ProceedingJoinPoint 인터페이스로
	//매개변수를 작성하여 선언
	// => 타겟메소드의 반환값을 제공받아 Around Advice Method에서 반환하기 위해 반환형을 작성
	// => 타겟메소드의 반환값이 없는 경우 void 자료형으로 반환형을 작성할 수 있지만 타겟메소드에
	//반환값이 있는 경우 void 자료형으로 반환형을 작성하면 에러 발생
	// => 타겟메소드의 정보를 ProceedingJoinPoint 인터페이스의 매개변수로 전달받아 사용
	//ProceedingJoinPoint 객체 : 타겟메소드 관련 정보를 저장하기 위한 객체
	// => JoinPoint 객체와 다른점은 ProceedingJoinPoint 객체를 사용해 타겟메소드를 직접 호출
	public Object display(ProceedingJoinPoint joinPoint) throws Throwable {//Around Advice Method
		System.out.println("### [around]핵심관심코드 실행 전에 삽입되어 실행될 횡단관심코드");
		
		//ProceedingJoinPoint.proceed() : ProceedingJoinPoint 객체로 타겟메소드를 호출하는 메소드
		// => 타겟메소드를 호출해 반환되는 결과값을 변수에 저장하여 반환
		// => Throwable(Error 클래스의 Exception 클래스의 부모클래스) 객체가 발생되므로 반드시  
		//예외 처리 또는 예외 전달
		Object object=joinPoint.proceed();//핵심관심코드 실행
		
		System.out.println("### [around]핵심관심코드 실행 후에 삽입되어 실행될 횡단관심코드");
		return object;
	}
}









