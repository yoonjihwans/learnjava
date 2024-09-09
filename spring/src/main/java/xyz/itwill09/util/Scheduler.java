package xyz.itwill09.util;

import org.springframework.scheduling.annotation.Scheduled;

//스프링 스케줄링(Spring Scheduling) : 특정 날짜 및 시간마다 원하는 명령이 자동으로 실행하는 기능 제공
// => 휴면 계정 처리, 정기적인 메일 전송등의 기능이 자동 실행되도록 설정
// => 스케줄링 관련 클래스를 작성하여 Spring Bean Configuration File(servlet-context.xml)에
//Spring Bean으로 등록하고 특정 날짜 및 시간마다 메소드가 자동 호출되도록 설정
// => 스케줄링 관련 클래스의 메소드에 @Scheduled 어노테이션을 사용해 메소드가 특정 날짜 및 
//시간마다 자동 호출되도록 설정 가능
public class Scheduler {
	@Scheduled(cron = "1 * * * * *")
	public void autoUpdate() {
		System.out.println("### Scheduler 클래스의 autoUpdate() 메소드 호출 ###");
	}
}