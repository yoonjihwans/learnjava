package basic;

//for : 조건식에 의해 명령을 반복 실행하기 위한 문장을 작성하는 키워드

//형식) for(초기식;조건식;증감식) { 명령; 명령; ... }
// => 초기식 : 변수에 초기값을 저장하는 연산식, 증감식 : 변수값을 증가하거나 감소하는 연산식
// => 반복의 횟수가 명확한 경우 사용하는 반복문(Loop Statement)
// => 초기식, 조건식, 증감식은 [;] 기호를 사용하여 구분 - for 구분에는 반드시 [;] 기호가 2개 존재
// => 초기식 >> 조건식(참) >> 블럭 내부의 명령 실행 >> 증감식 >> 조건식(참) >> 블럭 내부의 명령 실행
//    >> 증감식 >> 조건식(참) >> 블럭 내부의 명령 실행 >> ...
// => 조건식의 결과값이 거짓인 경우 반복문 종료
// => 초기식, 조건식, 증감식 생략 가능 - 조건식이 생략된 경우 무조건 참으로 처리
// => 초기식과 증감식은 [,] 기호를 사용하여 나열 작성 가능
//주의)조건식의 결과값이 무조건 참인 경우 무한 반복 발생 - 무한루프(Endless Loop)
// => 무한루프가 발생된 경우 프로그램 강제 종료 - Terminate

//프로그램의 흐름을 파악하기 위한 이클립스 기능
//1.중지점(BreakPoint) 설정 : 스레드를 일시적으로 중지하기 위한 위치 지정하는 작업
//2.[F11] : Debug 기능을 사용하기 위한 단축키
//3.Debug Perspective 변환
//4.[F6] : 스레드가 위치한 명령을 실행하기 위한 단축키 - 반복 >> 실행 오류 수정
//5.프로그램 강제 종료(Terminate)
//6.Java Perspective 변환 후 중지점(BreakPoint) 제거

public class ForApp {
	public static void main(String[] args) {
		//"Java Programming"를 화면에 5번 출력
		System.out.println("Java Programming");
		System.out.println("Java Programming");
		System.out.println("Java Programming");
		System.out.println("Java Programming");
		System.out.println("Java Programming");
		System.out.println("==============================================================");
		//"Java Programming"를 화면에 5번 출력
		for(int i=1;i<=5;i++) {
			System.out.println("Java Programming");
		}
		System.out.println("==============================================================");
		//"Java Programming"를 화면에 5번 출력
		for(int i=5;i>=1;i--) {
			System.out.println("Java Programming");
		}
		System.out.println("==============================================================");
		//"Java Programming"를 화면에 5번 출력
		for(int i=2;i<=10;i+=2) {
			System.out.println("Java Programming");
		}
		System.out.println("==============================================================");
		//"1     2     3     4     5"를 화면에 출력
		for(int i=1;i<=5;i++) {
			System.out.print(i+"\t");
		}
		System.out.println();
		System.out.println("==============================================================");
		//"5     4     3     2     1"를 화면에 출력
		/*
		for(int i=1;i<=5;i++) {
			System.out.print((6-i)+"\t");
		}
		System.out.println();
		*/
		for(int i=5;i>=1;i--) {
			System.out.print(i+"\t");
		}
		System.out.println();
		System.out.println("==============================================================");
		//"2     4     6     8     10"를 화면에 출력
		/*
		for(int i=2;i<=10;i+=2) {
			System.out.print(i+"\t");
		}
		System.out.println();
		*/
		
		for(int i=1;i<=10;i++) {
			if(i % 2 == 0) {
				System.out.print(i+"\t");
			}
		}
		System.out.println();
		System.out.println("==============================================================");
		//1~100 범위의 정수들의 합계를 계산하여 출력하는 프로그램 작성
		// => 1 + 2 + 3 + ... + 98 + 99 + 100 = ? 
		int tot=0;
		for(int i=1;i<=100;i++) {
			tot+=i;//tot = 1 + 2 + 3 + ... + 98 + 99 + 100
		}
		System.out.println("1~100 범위의 정수들의 합계 = "+tot);
		System.out.println("==============================================================");
		//두 변수에 저장된 정수값에 대한 범위 합계를 계산하여 출력하는 프로그램 작성
		//int begin=20, end=80;
		int begin=80, end=20;

		/*
		//시작값이 종료값보다 큰 경우 에러 메세지를 출력하고 프로그램이 강제 종료되도록 처리
		if(begin > end) {
			System.out.println("[에러]시작값이 종료값보다 작아야 됩니다.");
			System.exit(0);//프로그램 종료
		}
		*/
		
		//시작값이 종료값보다 큰 경우 두 변수에 저장된 값을 서로 바꾸어 저장되도록 처리
		if(begin > end) {
			//두 변수에 저장된 값을 서로 바꾸어 저장하는 명령 - 치환 알고리즘(Swap Algorithm)
			//알고리즘(Algorithm) : 프로그램 작성시 발생되는 문제를 해결하기 위한 명령들의 모임
			int temp=begin;
			begin=end;
			end=temp;
		}
		
		int sum=0;
		for(int i=begin;i<=end;i++) {
			sum+=i;
		}
		
		System.out.println(begin+"~"+end+" 범위의 정수들의 합계 = "+sum);
		System.out.println("==============================================================");
		for(int i=1, j=5;i<=3;i++, j--) {
			System.out.println("i = "+i+", j = "+j);
		}
		System.out.println("==============================================================");
		int i=1;
		
		for(;i<=4;i++) {//i 변수에 [5]가 저장된 경우 반복문 종료
			System.out.print(i+"\t");
		}
		
		//i=5;
		for(;i>=1;i--) {
			System.out.print(i+"\t");
		}
		System.out.println();
		System.out.println("==============================================================");
		/*
		for(;;) {
			System.out.println("무한 반복되어 실행되는 명령");
		}
		System.out.println("==============================================================");
		*/
	}
}