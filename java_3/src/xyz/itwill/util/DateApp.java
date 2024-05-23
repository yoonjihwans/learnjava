package xyz.itwill.util;

import java.util.Date;

//Date 클래스 : 날짜와 시간이 저장된  객체를 생성하기 위한 클래스
public class DateApp {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//new 연산자로 Date 클래스의 기본 생성자를 호출하여 객체를 생성하면 Date 객체에는
		//플렛폼의 현재 날짜와 시간 저장
		Date now=new Date();
		
		//Date.toString() : Date 객체에 저장된 날짜와 시간을 문자열로 변환하여 반환하는 메소드
		//System.out.println("현재 날짜와 시간 = "+now.toString());
		//Date 객체가 저장된 참조변수를 출력하면 자동으로 toString() 메소드 호출
		System.out.println("현재 날짜와 시간 = "+now);
		
		//Date.toLocaleString() : Date 객체에 저장된 날짜와 시간을 플렛폼의 지역에 맞는 형태의
		//문자열로 변환하여 반환하는 메소드 - @Deprecated 어노테이션이 적용된 메소드
		System.out.println("현재 날짜와 시간 = "+now.toLocaleString());
		
		//요일을 저장한 배열 생성
		String[] day={"일","월","화","수","목","금","토"};
		
		//Date 객체에 저장된 날짜와 시간을 활용하여 원하는 형태의 날짜로 출력 처리
		// => Date 객체에 저장된 날짜와 시간 중 원하는 값만 반환하는 메소드 호출
		// => @Deprecated 어노테이션이 적용된 메소드
		//Date.getYear() : Date 객체에 저장된 날짜와 시간 중 [년도]를 반환하는 메소드 
		// => 1900년부터 1씩 증가된 정수값 반환
		//Date.getMonth() : Date 객체에 저장된 날짜와 시간 중 [월]을 반환하는 메소드
		// => 0(1월) ~ 11(12월) 범위의 정수값 반환
		//Date.getDate() : Date 객체에 저장된 날짜와 시간 중 [일]을 반환하는 메소드
		//Date.getDay() : Date 객체에 저장된 날짜와 시간 중 [요일]을 반환하는 메소드
		// => 0(일) ~ 6(토) 범위의 정수값 반환
		String printDate=(now.getYear()+1900)+"년 "+(now.getMonth()+1)+"월 "
				+now.getDate()+"일 "+day[now.getDay()]+"요일";
		
		System.out.println("현재 날짜 = "+printDate);
		
		//Date.getTime() : Date 객체에 저장된 날짜와 시간을 시간값(TimeStamp)로 변환하여 반환하는 메소드
		long currentTime=now.getTime();//long currentTime=System.currentTimeMillis();
		
		//new 연산지로 Date 클래스의 Date(int year, int month, int date) 생성자를 호출하여 객체 생성
		// => [2000년 1월 1일 0시 0분 0초]의 날짜와 시간이 저장된 Date 객체 생성
		Date wantDate=new Date(100, 0, 1);
		long wantTime=wantDate.getTime();
		
		System.out.println("연산결과(일) = "+((currentTime-wantTime)/(1000*86400)));
	}
}