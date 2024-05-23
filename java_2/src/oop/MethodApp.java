package oop;

import java.util.Arrays;

//프로그램 실행을 목적으로 작성된 클래스 - main() 메소드 작성
public class MethodApp {
	//JVM(Java Virtual Machine)에 의해 자동 호출되는 메소드 - 프로그램 실행의 시작점
	public static void main(String[] args) {
		//클래스로 객체를 생성하여 객체의 메모리 주소를 참조변수에 저장
		// => 같은 패키지에 작성된 클래스는 패키지를 표현하지 않아도 클래스 사용 가능
		Method method=new Method();
		
		//참조변수를 출력할 경우 "클래스@메모리주소" 형식의 문자열을 변환되어 출력 처리
		System.out.println("method = "+method);
		System.out.println("==============================================================");
		//참조변수에 저장된 메모리 주소로 객체를 참조하여 객체로 클래스에 작성된 메소드 호출
		// => 메소드를 호출하면 프로그램의 흐름(스레드)이 메소드로 이동하여 메소드의 명령을
		//실행하고 메소드가 종료되면 다시 되돌아와 다음 명령 실행
		method.displayOne();
		method.displayTwo();
		method.displayOne();
		System.out.println("==============================================================");
		method.printOne();
		method.printOne();
		System.out.println("==============================================================");
		method.printTwo(50);
		method.printTwo(80);
		method.printTwo(-30);
		System.out.println("==============================================================");
		method.printThree(35, 79);
		method.printThree(19, 88);
		method.printThree(67, 12);
		System.out.println("==============================================================");
		//메소드를 호출하여 반환되는 값을 제공받아 변수에 저장
		int total=method.returnTot(30, 70);
		System.out.println("합계 = "+total);
		//메소드를 호출하여 반환되는 값을 제공받아 출력 처리
		System.out.println("합계 = "+method.returnTot(20, 80));
		System.out.println("==============================================================");
		boolean result=method.isOddEven(10);
		
		//조건식 대신 boolean 변수에 저장된 논리값을 비교하여 명령을 선택 실행
		if(result) {
			System.out.println("매개변수에 전달된 값은 [짝수]입니다.");
		} else {
			System.out.println("매개변수에 전달된 값은 [홀수]입니다.");
		}
		
		//조건식 대신 메소드를 호출하여 메소드의 반환값(논리값)을 비교하여 명령을 선택 실행
		if(method.isOddEven(11)) {
			System.out.println("매개변수에 전달된 값은 [짝수]입니다.");
		} else {
			System.out.println("매개변수에 전달된 값은 [홀수]입니다.");
		}
		System.out.println("==============================================================");
		int[] numArray=method.returnArray();
		System.out.println("배열 요소값 = "+Arrays.toString(numArray));
		System.out.println("==============================================================");
		/*
		int[] suArray={10, 20, 30};
		//메소드 매개변수에 참조변수에 저장된 배열의 주소값 전달
		System.out.println("합계 = "+method.sumOne(suArray));
		*/
		
		//배열을 생성하여 메소드 매개변수에 배열의 주소값 전달
		//System.out.println("합계 = "+method.sumOne({10, 20, 30}));//에러 발생
		System.out.println("합계 = "+method.sumOne(new int[]{10, 20, 30}));
		System.out.println("==============================================================");
		//0개 이상의 요소를 가진 배열을 생성하여 메소드 매개변수 전달
		System.out.println("합계 = "+method.sumOne(new int[]{}));
		System.out.println("합계 = "+method.sumOne(new int[]{10, 20}));
		System.out.println("합계 = "+method.sumOne(new int[]{10, 20, 30, 40, 50}));
		System.out.println("==============================================================");
		System.out.println("합계 = "+method.sumTwo());
		System.out.println("합계 = "+method.sumTwo(10, 20));
		System.out.println("합계 = "+method.sumTwo(10, 20, 30));
		System.out.println("합계 = "+method.sumTwo(10, 20, 30, 40, 50));
		System.out.println("==============================================================");
		
		 
	}
}









