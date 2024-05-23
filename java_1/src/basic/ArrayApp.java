package basic;

import java.util.Iterator;

//Java 자료형 : 원시형(PrimitiveType)과 참조형(ReferenceType)
// => 원시형(기본형) : 값을 표현하기 위한 자료형 - byte, short, int, long, char, float, double, boolean
// => 참조형 : 특정 대상을 표현하기 위한 자료형 - 배열(Array), 클래스(Class), 인터페이스(Interface), 열거형(Enum)

//원시형을 사용하여 선언된 변수 : 값을 저장하기 위한 변수
//참조형을 사용하여 선언된 변수 : 특정 대상(객체)의 메모리 주소값을 저장하기 위한 변수 - 참조변수(Reference Variable)
// => 힙영역에 생성된 배열 또는 객체의 메모리 주소를 저장하여 배열 또는 객체를 접근하기 위해 사용
// => 참조변수를 비교 연산자로 비교할 경우 참조변수에 저장된 메모리 주소 비교
// => 참조변수에는 초기값으로 null(아무것도 없음을 표현하기 위한 키워드) 저장

//Java 프로그램에서 사용하는 메모리 영역
// => 메소드 영역(Method Area), 힙 영역(Heap Area), 스택 영역(Stack Area)
//1.메소드 영역 : 클래스 파일(XXX.class)에 선언된 Java 자료형(클래스, 인터페이스, 열거형)을
//읽어 저장하기 위한 영역
//2.힙 영역 : 배열 또는 객체를 생성하여 저장하기 위한 영역 - new 연산자를 사용하여 메모리 할당
// => 메모리에 이름 부여 불가능 : 참조변수에 배열 또는 객체의 메모리 주소(HashCode)를 저장하여 참조
//3.스택 영역 : 지역변수(참조변수 포함)를 생성하여 값 또는 메모리 주소를 저장하기 위한 영역

//배열(Array) : 동일한 자료형의 값을 여러개 저장하기 위한 메모리를 할당받을 수 있는 자료형 - 참조형
// => 일괄처리를 목적으로 사용하는 자료형 - 반복문(for)
// => 1차원 배열과 다차원 배열(2차원 배열, 3차원 배열)로 구분
// => 1차원 배열 : 다수의 값을 저장하기 위한 자료형, 다차원 배열 : 다수의 배열을 저장하기 위한 자료형

public class ArrayApp {
	public static void main(String[] args) {
		// 1차원 배열을 생성하여 참조변수에 생성된 배열의 메모리 주소(HashCode) 저장
		// 형식) 자료형[] 참조변수=new 자료형[갯수];
		// => new 자료형[갯수] : 자료형의 값을 갯수만큼 저장 가능한 메모리 할당 - 배열
		// => 자료형[] 참조변수 : 다수의 값이 저장된 1차원 배열의 메모리 주소를 저장하기 위한 참조변수 생성
		// 1차원 배열에서 값을 구분하여 저장하기 위한 메모리 공간을 요소(Element)로 표현
		// => 배열의 요소는 첨자(Index : 0부터 1씩 증가되는 정수값)를 사용하여 구분
		// => 생성된 배열의 요소에는 기본값(숫자형 : 0, 논리형 : false, 참조형 : null) 자동 저장
		int[] numArray = new int[3];

		// 참조변수에 저장된 메모리 주소를 출력할 경우 "자료형@메모리주소" 형식으로 변환되어 출력
		System.out.println("numArray = " + numArray);
		System.out.println("==============================================================");
		// 참조변수에 저장된 메모리 주소를 사용하여 배열을 참조해 배열의 요소를 사용
		// => 참조변수와 배열의 요소를 구분하는 첨자를 사용하여 배열의 요소 표현
		// 형식) 참조변수[첨자]
		System.out.println("numArray[0] = " + numArray[0]);
		System.out.println("numArray[1] = " + numArray[1]);
		System.out.println("numArray[2] = " + numArray[2]);
		// 배열의 요소를 구분하는 첨자를 잘못 사용한 경우 프로그램 실행시 ArrayIndexOutOfBoundsException 발생
		// => 프로그램 실행시 예외가 발생된 경우 프로그램 강제 종료
		// System.out.println("numArray[3] = "+numArray[3]);
		System.out.println("==============================================================");
		// 참조변수.length : 참조변수에 저장된 메모리 주소로 참조한 배열의 요소의 갯수를 제공하는 표현식
		System.out.println("numArray.length = " + numArray.length);
		System.out.println("==============================================================");
		int index = 0;
		// 배열의 요소를 구분하기 위한 첨자는 변수 또는 연산식 사용 가능
		numArray[index] = 10;
		numArray[index + 1] = 20;
		numArray[index + 2] = 30;

		// for 구문을 사용하여 배열 요소에 대한 일괄처리
		// => for 구문의 초기식, 조건식, 증감식에서 사용하는 변수를 첨자로 활용하여 일괄처리
		// => 배열 요소의 입력, 계산, 출력에 대한 일괄처리시 사용
		for (int i = 0; i < numArray.length; i++) {
			System.out.println("numArray[" + i + "] = " + numArray[i]);
		}
		System.out.println("==============================================================");
		// 배열 생성시 배열 요소에 원하는 초기값 저장 가능
		// 형식) 자료형[] 참조변수=new 자료형[]{초기값, 초기값, ...};
		// => 블럭 내부에 나열된 초기값의 갯수와 동일한 갯수의 요소를 가진 배열 생성
		int[] su1Array = new int[] { 10, 20, 30 };

		for (int i = 0; i < su1Array.length; i++) {
			System.out.println("su1Array[" + i + "] = " + su1Array[i]);
		}
		System.out.println("==============================================================");
		// new 연산자와 배열 자료형을 사용하지 않고 블럭 내부에 값을 나열하여 배열 생성 가능
		// 형식) 자료형[] 참조변수={초기값, 초기값, ...};
		int[] su2Array = { 10, 20, 30 };

		// Java 언어는 배열을 일괄처리 하기 위해 향상된 for 구문 제공
		// 형식) for(자료형 변수명 : 참조변수) { 명령; 명령; ... }
		// => 참조변수에 저장된 메모리 주소로 참조하는 배열의 요소값을 커서(Cursor)를 사용해
		// 차례대로 하나씩 제공받아 변수에 저장하여 일괄처리 하는 반복문
		// => 배열의 모든 요소값을 제공받은 후 반복문 종료
		// => 배열 요소의 계산, 출력에 대한 일괄처리시 사용
		for (int number : su2Array) {
			System.out.print(number + "\t");
		}
		System.out.println();
		System.out.println("==============================================================");

		int[] array = { 54, 54, 70, 20, 65, 52, 11, 55, 54, 45, 22, 66, 56 };

		// 배열의 모든 요소값에 대한 합계를 계산하여 출력하는 프로그램 작성
		int tot = 0;

		for (int i = 0; i < array.length; i++) {
			tot += array[i];

		}
		System.out.println("합계 = " + tot);
		System.out.println("=========================================================");
		//배열에 30~60 범위의 정수값이 저장된 요소의 갯수를 계산하여 출력하는 프로그램
		int cnt = 0;
		
		for (int element : array) {
			if(element >= 30 && element <= 60) cnt++;
			
		}
		System.out.println(cnt);
	}
}
