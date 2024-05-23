package xyz.itwill.app;

import java.util.Date;
import java.util.Scanner;

//키보드로 이름과 태어난 년도를 입력받아 나이를 계산해 이름과 나이를 출력하는 프로그램 작성
//ex) 이름 입력 >> 홍길동
// 태어난 년도 입력 >> 2000
// 결과 홍길동님의 나이는 >> 24살
public class CalcAgeApp {
	public static void main(String[] args) {
		// Scanner 클래스로 객체를 생성하여 참조변수에 저장
		Scanner scanner = new Scanner(System.in);

		// 사용자로부터 키보드로 이름과 태어난 년도를 입력받아 변수에 저장

//		String name = "";
//		int id;
//		
//		System.out.println("이름 입력 = ");
//		name = scanner.nextLine();
//		System.out.println("태어난 년도 입력 = ");
//		id = scanner.nextInt();
//		System.out.println("결과 = 홍길동님의 나이는" + (2024-id));

		System.out.print("이름 입력 >> ");
		String name = scanner.nextLine();

		System.out.println("태어난 년도 입력 >>");
		int birthYear = scanner.nextInt();

		// Date 클래스로 객체를 생성하여 참조변수에 저장
		// = >java.util.Date 클래스 : 날짜와 시간을 저장하기 위한 ㅡㄹ래스
		// = > Date 클래스의 기본 생성자를 이용하여 객체를 생성할 경우 프로그램을 실행한 플랫폼
		// (OS - 운영체제)의 현재 날짜와 시간이 저장된 Date 객체 생성
		Date now = new Date();

		// Date.getYear() : Data 객체에 저장된 날짜와 시간에서 년도를 얻어와 변환하는 메소드
		// = > Date 객체에 저장된 년도는 1900년을 기준으로 1년에 1씩 증가된 정수값이 저장되어
		// 있으므로 반드시 1900년을 더해야 원하는 년도를 제공받아 사용 가능
		// = > @deprecated 어노테이션이 적용된 메소드
		// @deprecated : 메소드 사용을 권장하지 않도록 설정하는 어노테이션(Annotation)
		// = > @deprecated 어노테이션이 적용된 메소드를 호출할 경우 경고문 발생
		// @SuppressWarnings : 경고를 제거하는 기능을 제공하는 어노테이션
		// = > 어노테이션 속성을 사용하여 원하는 경고가 제거되도록 설정
		// 형식) @SuppressWarnings(속성명 = 속성값, 속석명 = 속성값, ...)
		// = > value 속성에 경고 관련 속성값을 사용하여 경고 제거
		// = > value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
		// = > @SuppressWarnings 어노테이션은 명령 위에 작성 가능하지만 클래스 또는 메소드 위에 
		@SuppressWarnings("deprecation")
		int current = now.getYear() + 1900;

		// 나이를 계산하여 변수에 저장
		int age = current - birthYear;

		System.out.println("결과" + name + "의 나이는 " + age + "살 입니다");

		scanner.close();

	}
}
