package xyz.itwill06.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class ExecutionTimeAdvice {
	//타겟메소드의 명령이 실행되는 시간을 측정하여 기록하기 위한 메소드 - Around Advice Method
	public Object timeWatchLog(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime=System.currentTimeMillis();

		Object object=joinPoint.proceed();
		
		long endTime=System.currentTimeMillis();
		
		String className=joinPoint.getTarget().getClass().getSimpleName();
		String methodName=joinPoint.getSignature().getName();
		System.out.println(className+" 클래스의 "+methodName+"() 메소드 실행시간 = "
				+(endTime-startTime)+"ms");
		
		return object;
	}
}