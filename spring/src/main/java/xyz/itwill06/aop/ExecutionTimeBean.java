package xyz.itwill06.aop;

public class ExecutionTimeBean {
	public void one() {
		//System.currentTimeMillis() : 플렛폼(OS)의 현재 날짜와 시간에 대한 타임스템프를 반환하는 정적메소드
		//타임스템프(TimeStamp) : 날짜와 시간의 연산을 목적으로 날짜와 시간을 정수값으로 변환한 값 
		//long startTime=System.currentTimeMillis();
		
		long count=0;
		for(long i=1;i<=30000000000L;i++) {
			count++;
		}
		System.out.println("count = "+count);

		//long endTime=System.currentTimeMillis();
		//System.out.println("ExecutionTimeBean 클래스의 one() 메소드 실행시간 = "
		//		+(endTime-startTime)+"ms");
	}
	
	public void two() {
		//long startTime=System.currentTimeMillis();
		
		long count=0;
		for(long i=1;i<=50000000000L;i++) {
			count++;
		}
		System.out.println("count = "+count);	
		
		//long endTime=System.currentTimeMillis();
		//System.out.println("ExecutionTimeBean 클래스의 two() 메소드 실행시간 = "
		//		+(endTime-startTime)+"ms");
	}
}