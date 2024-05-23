package xyz.itwill.thread;

//다중 스레드 프로그램의 문제점
// => 같은 클래스로 생성된 스레드가 동일한 메소드를 동시에 요청한 경우 스레드로 동작될 명령이
//순서없이 실행되어 잘못된 처리결과 발생 가능
//해결법)스레드를 동기화 처리하여 스레드에 대한 메소드 호출 제어

//스레드 동기화(Thread Synchronize) : 스레드가 메소드를 호출한 경우 메소드의 모든 명령을 실행
//하기 전까지 메소드를 호출한 다른 스레드로 명령이 실행하지 못하도록 메소드 잠김(Lock) 기능 제공
// => 메소드 잠김 상태인 경우 메소드를 호출한 다른 스레드는 일지 중지 상태로 변환하고 메소드
//잠김 상태가 해제되면 스레드로 메소드의 명령 실행

//메소드 호출에 대한 스레드 동기화 처리 방법
//1.synchronized 키워드를 사용하여 메소드 작성 - 동기화 메소드(Synchronized Method)
//형식) 접근제한자 synchronized 반환형 메소드명(자료형 변수명, ...) { 명령; ... }
//2.synchronized 키워드로 블럭을 설정하여 메소드 호출
//형식) synchronized(공유객체) { 객체.메소드명(값,...); ... }
// => synchronized 키워드로 제공되는 객체로 호출되는 모든 메소드는 동기화 처리되어 실행

public class AccountUserApp {
	public static void main(String[] args) {
		//Account 클래스로 객체를 생성하여 참조변수에 저장 - 은행계좌 생성
		Account account=new Account(10000);
		
		//AccountUser 클래스로 객체를 생성하여 참조변수에 저장 
		//=> 동일한 은행계좌를 사용할 수 있는 다수의 사용자 생성
		AccountUser userOne=new AccountUser(account, "홍길동");
		AccountUser userTwo=new AccountUser(account, "임꺽정");
		AccountUser userThree=new AccountUser(account, "전우치");
		
		/*
		//사용자(AccountUser 객체)로부터 은행계좌(Account 객체)를 제공받아 입금 처리(메소드 호출)
		// => 단일스레드(main 스레드)를 이용해 객체를 참조해 메소드 호출
		// => 단일스레드를 사용하므로 동시 입금 처리 불가능
		userOne.getAccount().disposit(userOne.getUserName(), 5000);
		userTwo.getAccount().disposit(userTwo.getUserName(), 5000);
		userThree.getAccount().disposit(userThree.getUserName(), 5000);
		*/
		
		//새로운 스레드를 생성하여 AccountUser 클래스의 run() 메소드 명령 실행
		userOne.start();
		userTwo.start();
		userThree.start();
	}
}