package xyz.itwill.lang;

import java.util.Scanner;

//키보드로 주민번호를 입력받아 생년월일과 성별을 계산하여 출력하는 프로그램 작성
// => 주민번호는 14자리로 입력받고 7번째 자리에 [-] 문자가 존재하도록 입력 처리
// => 비정상적인 주민번호가 입력된 경우 에러메세지를 출력하고 재입력 되도록 처리
//ex) 주민번호[ex.901225-1234567] >> 000101-4567890
//    [결과]생년월일 = 2000년 01월 01일, 성별 = 여자
public class PersonNumberApp {
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		
		String number;//주민번호를 저장하기 위한 변수
		//사용자부터 입력받은 주민번호를 검증하기 위한 반복문
		// => 정상적인 주민번호를 입력받은 경우 반복문 종료
		while(true) {
			System.out.print("주민번호[ex.901225-1234567] >> ");
			//키보드로 입력받은 문자열에서 앞과 뒤에 존재하는 모든 공백을 제거하여 변수에 저장
			number=scanner.nextLine().trim();
			//if(number.length() == 14 && number.charAt(6) == '-') break;
			if(number.length() == 14 && number.indexOf("-") == 6) break;
			System.out.println("[에러]형식에 맞게 주민번호를 다시 입력해 주세요.");
		}
		
		scanner.close();
		
		//주민번호에서 8번째 위치의 문자 하나를 문자열로 분리하여 저장
		String separation=number.substring(7, 8);
		
		//생년월일을 저장하기 위한 변수
		String birthday="";
		//separation 변수에 저장된 문자열을 비교하여 태어난 년도 계산
		if(separation.equals("1") || separation.equals("2")) {//20세기(19XX)에 태어난 경우
			birthday+="19";//변수에 저장된 문자열에 문자열을 결합하여 변수에 저장
		} else if(separation.equals("3") || separation.equals("4")) {//21세기(20XX)에 태어난 경우
			birthday+="20";
		}
		
		birthday+=number.substring(0, 2)+"년 ";
		birthday+=number.substring(2, 4)+"월 ";
		birthday+=number.substring(4, 6)+"일";
		
		//성별을 저장하기 위한 변수
		String gender="";
		if(separation.equals("1") || separation.equals("3")) {
			gender="남자";
		} else if(separation.equals("2") || separation.equals("4")) {
			gender="여자";
		}
		
		System.out.println("[결과]생년월일 = "+birthday+", 성별 = "+gender);
	}
}










