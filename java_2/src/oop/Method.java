package oop;

//메소드(Method) : 클래스에 작성된 함수 - 멤버함수
// => 필드(멤버변수)를 사용하여 원하는 기능을 구현하기 위한 명령의 모임
// => 메소드에서만 Java 명령은 작성 가능

//함수(Function) : 매개변수로 전달받은 값을 연산 처리하여 결과값을 반환하는 명령의 모임

//메소드 작성 방법
//형식) 반환형 메소드명(자료형 변수명, 자료형 변수명, ...) {
//            명령;
//            ...
//      }
// => 메소드를 호출(Invoke)해야만 메소드에 작성된 명령 실행 - 기능 구현

//반환형(ReturnType) : 메소드를 호출하여 얻을 수 있는 결과값에 대한 자료형 
// => void 자료형(무반환형) : 메소드를 호출하여 얻어을 있는 결과값이 없는 경우 사용하는 자료형

//메소드명은 식별자로 카멜 표기법을 사용하여 작성하는 것을 권장

//메소드 () 기호 안에 작성된 변수를 매개변수(Parameter - Argument)라고 하며 [,] 기호를 사용하여 나열 작성 가능
// => 메소드에 작성된 명령을 실행하기 위해 필요한 값을 메소드 호출시 전달받아 저장하기 위한 변수
// => 전달값이 필요없는 경우 매개변수 작성 생략 가능

//메소드 호출(Invoke) : 메소드에 작성된 명령을 실행하여 필요한 기능 구현
//형식) 객체.메소드명(값, 값, ...);
// => 참조변수에 저장된 메모리 주소로 객체를 참조하여 클래스에 작성된 메소드 호출
// => 메소드 호출시 매개변수에 차례대로 값을 전달하여 저장해야만 메소드 호출 가능
// => 매개변수에 정상적으로 값이 전달되지 않은 경우 메소드 호출 불가능 - 에러 발생

//return : 메소드를 강제로 종료하여 프로그램의 흐름(스레드)를 메소드를 호출한 위치로 되돌리는 키워드 - 제어문
// => 일반적으로 if 구문과 같이 사용
//형식) if(조건식) return;  >> 메소드의 반환형을 [void]로 작성

//return 키워드를 사용하여 메소드의 처리 결과값을 메소드를 호출한 명령으로 반환하는 기능 제공
//형식) return 반환값;
// => 메소드의 반환형을 반환값과 동일한 자료형으로 작성

//객체 생성을 목적으로 작성된 클래스
public class Method {
	void displayOne() {
		System.out.println("Method 클래스에 작성된 displayOne() 메소드 호출");
	}
	
	void displayTwo() {
		System.out.println("Method 클래스에 작성된 displayTwo() 메소드 호출");
	}
	
	void printOne() {
		int tot=0;
		for(int i=1;i<=100;i++) {
			tot+=i;
		}
		System.out.println("1~100 범위의 정수들의 합계 = "+tot);
	}
	
	void printTwo(int num) {
		//데이타 검증
		if(num < 0) {
			System.out.println("[에러]매개변수에는 0 보다 큰 값이 전달되어 저장돼야 합니다.");
			return;
		}
		
		int tot=0;
		for(int i=1;i<=num;i++) {
			tot+=i;
		}
		System.out.println("1~"+num+" 범위의 정수들의 합계 = "+tot);
	}
	
	void printThree(int num1, int num2) {
		int tot=0;
		
		if(num1 > num2) {
			int temp=num1;
			num1=num2;
			num2=temp;
		}
		
		for(int i=num1;i<=num2;i++) {
			tot+=i;
		}
		System.out.println(num1+"~"+num2+" 범위의 정수들의 합계 = "+tot);
	}
	
	int returnTot(int num1, int num2) {
		int tot=0;
		
		if(num1 > num2) {
			int temp=num1;
			num1=num2;
			num2=temp;
		}
		
		for(int i=num1;i<=num2;i++) {
			tot+=i;
		}
		
		return tot;
	}
	
	//매개변수로 전달받은 정수값을 홀수와 짝수로 구분하여 논리값을 반환하는 메소드
	// => false 반환 : 홀수, true 반환 : 짝수
	boolean isOddEven(int num) {
		if(num % 2 != 0) {
			return false;
		} else {
			return true;
		}
	}
	
	//배열의 메모리 주소를 반환하는 메소드
	int[] returnArray() {
		/*
		int[] array={10, 20, 30, 40, 50};
		return array;
		*/
		
		//배열을 생성하여 참조변수에 저장하지 않고 직접 반환 처리 
		//return {10, 20, 30, 40, 50};//에러 발생
		return new int[]{10, 20, 30, 40, 50};
	}
	
	//매개변수로 배열을 전달받아 배열의 모든 요소값의 합계를 계산하여 반환하는 메소드
	int sumOne(int[] array) {//매개변수에 배열의 메모리 주소를 전달받아 저장
		int sum=0;
		for(int su : array) {
			sum+=su;
		}
		return sum;
	}
	
	//메소드 작성시 [...] 기호를 사용하여 매개변수를 작성하면 0개 이상의 값을 전달받아 
	//매개변수에 저장 가능
	// => 0개 이상의 값을 전달받아 저장한 매개변수는 메소드에서 배열과 동일하게 처리
	int sumTwo(int... num) {
		int sum=0;
		for(int su : num) {
			sum+=su;
		}
		return sum;
	}
}