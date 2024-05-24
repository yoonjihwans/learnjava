package xyz.itwill.lang;

import java.util.Scanner;

//키보드로 하나의 사칙 연산식을 입력받아 연산결과를 계산하여 출력하는 프로그램 작성
//ex) 연산식 입력 >> 20 + 10
//    [결과]30
// => 입력 연산식에서 사용 가능한 연산자는 사칙 연산자(*, /, +, -)만 허용
// => 형식에 맞지 않는 연산식이 입력될 경우 에러 메세지 출력 후 프로그램 종료
// => 입력 연산식에 공백 입력이 가능하도록 처리
// 필요한 메소드 = >
//  Scanner 메소드, 리플레이스,라스트인덱스,서브스트링,연산자비교 이퀄스
public class ConsoleCalculateApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

//		// 만들어야 할 변수 : String
//		String str; // 연산식 값s
//		int index; // 사칙연산자
//
//		while (true) {
//			System.out.println("연산식 입력 >> ");
//			str = scanner.nextLine().replace(" ", "");
//			index = str.lastIndexOf("*");
//			Integer num1 = (Integer.parseInt(str.substring(0, index))); // 첫번째 값
//			Integer num2 = (Integer.parseInt(index + 1, str.length())); // 두번째 값
//
//			int result = 0;
//
//			if (str.lastIndexOf("*") != -1) {
//				result *= num1 * num2;
//			} else if (str.lastIndexOf("-") != -1) {
//				result -= num1 - num2;
//			} else if (str.lastIndexOf("+") != -1) {
//				result += num1 + num2;
//			} else if (str.lastIndexOf("/") != -1) {
//				result /= num1 / num2;
//			}
//			
//			System.out.println(result);
//			
//			scanner.close();
//
//		}

		System.out.print("연산식 입력 >> ");
		// 키보드로 입력받은 연산식에서 모든 공백을 찾아 제거하여 변수에 저장
		// => 연산식을 첨자로 구분하여 분리하기 위해서는 공백 불필요
		String operation = scanner.nextLine().replace(" ", "");
		// System.out.println("operation = "+operation);

		scanner.close();

		// 연산식에서 검색할 연산자가 저장된 문자열 배열 생성
		String[] operatorArray = { "*", "/", "+", "-" };

		// 연산식에서 연산자를 검색하여 연산자의 시작첨자를 저장하기 위한 변수
		int index = -1;

		// 배열의 요소값(연산자)을 차례대로 제공받아 처리하기 위한 반복문
		for (String operator : operatorArray) {
			// 연산식에서 연산자를 찾아 시작첨자를 반환받아 변수에 저장
			// String.indexOf(String str, int fromIndex) : String 객체에 저장된 문자열에서
			// 매개변수로 전달받은 문자열을 시작첨자부터 찾아 문자열의 첨자를 반환하는 메소드
			index = operation.indexOf(operator, 1);// 첨자가 [1]인 위치부터 문자열을 검색하여 첨자 반환

			// 연산식에서 연산자를 찾은 경우 반복문 종료
			if (index != -1)
				break;
		}

		// 연산식에 연산자가 없거나 연산자가 뒤에 있는 경우 프로그램 종료
		if (index < 0 || index >= operation.length() - 1) {
			System.out.println("[에러]연산식을 잘못 입력 하였습니다.");
			System.exit(0);
		}

		try {
			// 연산식에서 첫번째 피연산자를 분리한 후 정수값으로 변환하여 변수에 저장
			// => Integer.parseInt(String str) 메소드는 매개변수로 전달받은 문자열을 정수값으로
			// 변환할 수 없는 경우 NumberFormatException 발생 - 예외 처리
			int num1 = Integer.parseInt(operation.substring(0, index));

			// 연산식에서 연산자를 분리하여 변수에 저장
			String operator = operation.substring(index, index + 1);

			// 연산식에서 두번째 피연산자를 분리한 후 정수값으로 변환하여 변수에 저장
			int num2 = Integer.parseInt(operation.substring(index + 1));

			// 연산자를 비교하여 피연산자에 대한 연산결과를 계산하여 저장
			int result = 0;
			switch (operator) {
			case "*":
				result = num1 * num2;
				break;
			// 두번째 피연산자의 값이 [0]인 경우 ArithmeticException 발생
			case "/":
				result = num1 / num2;
				break;
			case "+":
				result = num1 + num2;
				break;
			case "-":
				result = num1 - num2;
				break;
			}

			System.out.println("[결과]" + result);
		} catch (NumberFormatException e) {
			System.out.println("[에러]연산식을 숫자가 아닌 값이 입력 되었습니다.");
		} catch (ArithmeticException e) {
			System.out.println("[에러]0으로 나눌 수 없습니다.");
		} catch (Exception e) {
			System.out.println("[에러]프로그램 실행에 예기치 못한 오류가 발생 되었습니다.");
		}
	}

}
