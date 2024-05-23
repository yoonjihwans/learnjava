package xyz.itwill.util;

import java.util.Scanner;
import java.util.regex.Pattern;

//java.util.regex 패키지 : 정규표현식 관련 기능을 제공하는 Java 자료형이 선언된 패키지

//정규표현식(RegualarExpression) : 메타문자(Meta Character), 회피문자(Escape Character)등을
//사용해 일정한 패턴의 문자열을 표현하기 위한 표현식 - 사용자 입력값에 대한 검증 처리
/*
^문자 : 문자(열)로 시작됨을 의미
문자$ : 문자(열)로 종료됨을 의미
. : 임의의 문자 하나를 의미(\ 문자 제외)
[문자1문자2문자3] : 나열된 문자 중 하나를 의미
[^문자1문자2문자3] : 나열된 문자를 제외한 나머지 문자 중 하나를 의미
[문자1-문자2] : [문자1]부터 [문자2] 범위의 문자 중 하나를 의미
(문자열1|문자열2|문자열3) : 나열된 문자열 중 하나를 의미
문자열+ : 문자(열)이 1번이상 반복됨을 의미
문자열* : 문자(열)이 0번이상 반복됨을 의미
문자열? : 문자(열)이 0번 또는 1번 존재함을 의미
문자열{숫자} : 문자(열)이 [숫자]만큼 반복됨을 의미
문자열{숫자1,숫자2} : 문자(열)이 [숫자1]부터 [숫자2]만큼 반복됨을 의미
(?!)문자열 : 문자열에서 대소문자를 구분하지 않음을 의미
(?=문자열) : 문자열이 반드시 포함됨을 의미
(!=문자열) : 문자열이 반드시 포함되지 않음을 의미
*/

/*
\s : 공백이 있는 문자열을 의미
\S : 공백이 없는 문자열을 의미
\w : 영문자, 숫자, 특수문자(_)의 문자로만 구성된 문자열을 의미
\W : 영문자, 숫자, 특수문자(_)의 문자를 제외한 나머지 문자로 구성된 문자열을 의미
\d : 숫자 형태의 문자로만 구성된 문자열을 의미
\D : 숫자 형태의 문자를 제외한 문자로 구성된 문자열을 의미
\메타문자 : 메타문자를 일반문자로 표현됨을 의미 - ex) \. : 문자 .
*/

//키보드를 이용하여 사용자로부터 값(문자열)을 입력받아 원하는 패턴의 문자열인지를 비교하여
//결과를 출력하는 메소드
public class RegualarExpressionApp {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		/*
		//사용자로부터 [아이디]를 입력받아 원하는 패턴의 문자열인지를 검사하여 결과 출력
		// => 아이디는 영문자로 시작되며 영문자, 숫자, 특수문자(_)의 조합으로 6~20 범위의 
		//문자로 구성되도록 패턴을 설정하여 처리
		System.out.print("아이디 입력 >> ");
		String id=scanner.nextLine();
		
		if(id==null || id.equals("")) {//사용자로부터 입력받은 값이 없는 경우
			System.out.println("[에러]아이디를 입력해 주세요.");
			System.exit(0);
		}
		
		//아이디 입력값에 대한 패턴정보를 정규표현식으로 작성하여 저장
		//String idReg="^[a-zA-Z][a-zA-Z0-9_]{5,19}$";
		String idReg="^[a-zA-Z]\\w{5,19}$";
		
		//Pattern 클래스 : 정규표현식이 저장된 객체를 생성하기 위한 클래스
		//Pattern.matches(String regEx, CharSequence input) : 매개변수로 전달받은 정규표현식과 입력값의 
		//패턴을 비교하여 다른 경우 [false]를 반환하고 같은 경우 [true]를 반환하는 정적메소드
		// => CharSequence 인터페이스 : 문자열을 저장하기 위한 클래스가 상속받기 위한 인터페리스
		if(!Pattern.matches(idReg, id)) {
			System.out.println("[에러]아이디는 6~20자의 영문자,숫자,특수문자(_)만 사용하여 입력 가능합니다.");
			System.exit(0);
		}
		
		System.out.println("[메세지]형식에 맞는 아이디를 입력했습니다.");
		*/

		/*
		//사용자로부터 [비밀번호]를 입력받아 원하는 패턴의 문자열인지를 검사하여 결과 출력
		// => 비밀번호는 영문자, 숫자, 특수문자가 반드시 1번 이상 표함된 조합으로 8~30 범위의 
		//문자로 구성되도록 패턴을 설정하여 처리
		System.out.print("비밀번호 입력 >> ");
		String password=scanner.nextLine();

		if(password==null || password.equals("")) {//사용자로부터 입력받은 값이 없는 경우
			System.out.println("[에러]비밀번호를 입력해 주세요.");
			System.exit(0);
		}
		
		String passwordReg="^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*_-]).{8,30}$";
		
		if(!Pattern.matches(passwordReg, password)) {
			System.out.println("[에러]비밀번호는 8~30자의 영문자,숫자,특수문자가 반드시 1번이상 포함되도록 입력해 주세요.");
			System.exit(0);
		}
		
		System.out.println("[메세지]형식에 맞는 아이디를 입력했습니다.");
		*/
		
		//사용자로부터 [이메일]를 입력받아 원하는 패턴의 문자열인지를 검사하여 결과 출력
		// => 이메일은 [아이디@도메인] 형식으로 구성되도록 패턴을 설정하여 처리
		System.out.print("이메일 입력 >> ");
		String email=scanner.nextLine();

		if(email==null || email.equals("")) {//사용자로부터 입력받은 값이 없는 경우
			System.out.println("[에러]이메일을 입력해 주세요.");
			System.exit(0);
		}
		
		String emailReg="^([a-zA-Z0-9_]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+)*$";

		if(!Pattern.matches(emailReg, email)) {
			System.out.println("[에러]이메일은 [아이디@도메인] 형식으로 입력해 주세요.");
			System.exit(0);
		}
		
		System.out.println("[메세지]형식에 맞는 이메일을 입력했습니다.");
		
		scanner.close();
	}
}
















