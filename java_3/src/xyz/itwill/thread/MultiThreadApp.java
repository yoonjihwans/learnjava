package xyz.itwill.thread;

//다중 스레드 프로그램 : 2개이상의 스레드를 사용하여 명령을 제공받아 실행
// => 프로그램 개발자가 직접 스레드를 생성하여 다수의 스레드로 동시에 명령이 실행되는 프로그램
// => 프로그램을 실행하는 모든 스레드가 소멸돼야만 프로그램 종료
// => GUI 프로그램 또는 Web 프로그램 등은 다중 스레드 프로그램으로 작성

//프로그램 개발자를 스레드를 생성하여 원하는 명령을 실행하는 방법-1
//1.Thread 클래스를 상속받은 자식클래스 선언
//2.Thread 클래스를 상속받은 자식클래스에서 무조건 run() 메소드를 오버라이딩 선언
// => run() 메소드에는 Thread 객체에 의해 생성된 새로운 스레드가 실행하기 위한 명령 작성
//3.Thread 클래스를 상속받은 자식클래스의 생성자로 객체 생성 - Thread 객체
//4.생성된 Thread 객체를 참조하여 Thread 클래스의 start() 메소드 호출 
// => Thread 객체dx로 새로운 스레드가 생성되어 자동으로 run() 메소드를 호출해 명령 실행
// => 스레드로 run() 메소드의 명령을 모두 실행하면 스레드 자동 소멸

//프로그램 개발자를 스레드를 생성하여 원하는 명령을 실행하는 방법-2
//1.Runnable 인터페이스를 상속받은 자식클래스 선언

// => 자식클래스가 이미 다른 클래스를 상속받아 Thdread 클래스를 상속받지 못하는 경우 사용
//2.Runnable 인터페이스를 상속받은 자식클래스에서 무조건 run() 메소드를 오버라이딩 선언
//=> run() 메소드에는 Thread 객체에 의해 생성된 새로운 스레드가 실행하기 위한 명령 작성
//3.Runnable 인터페이스를 상속받은 자식클래스의 생성자로 객체 생성 - Runnable 객체
//4.Thread 클래스의 매개변수가 작성된 생성자를 호출하여 Thread 객체 생성
// => 생성자 매개변수에 Runnable 객체를 전달하여 Thread 객체 생성
// => Runnable 인터페이스를 상속받은 자식클래스의 run() 메소드를 Thread 객체로 사용 가능
//5.생성된 Thread 객체를 참조하여 Thread 클래스의 start() 메소드 호출 

public class MultiThreadApp {
	//JVM에 의해 [main] 스레드가 자동으로 생성되어 main() 메소드를 호출하여 명령 실행
	// => main() 메소드에 의해 전달된 예외는 JVM에 의해 자동으로 예외처리
	public static void main(String[] args) throws InterruptedException {
		/*
		//새로운 스레드를 생성하여 생성된 스레드로 원하는 명령 실행
		// => Thread 클래스로 객체를 생성한 후 객체를 start() 메소드 호출 - 스레드가 생성되어
		//Thread 클래스의 run() 메소드를 자동 호출해 명령 실행
		// => Thread 클래스의 run() 메소드에는 실행될 명령이 없으므로 스레드가 생성된 후 바로 소멸
		Thread thread=new Thread();
		thread.start();
		*/
		
		/*
		MultiThreadOne one=new MultiThreadOne();
		//Thread.start() : Thread 객체를 사용하여 새로운 스레드를 생성하기 위한 메소드
		// => 생성된 스레드는 Thread 객체를 참조하여 run() 메소드 자동 호출하여 명령 실행
		one.start();
		//하나의 스레드 객체로 하나의 스레드만 생성 가능
		// => Thread 객체로 start() 메소드를 다시 호출할 경우 IllegalThreadStateException 발생
		// => 다중 스레드 프로그램에서 예외가 발생될 경우 예외가 발생된 스레드만 소멸
		one.start();
		*/
		
		//Thread 객체를 참조하여 start() 메소드외에 다른 메소드를 호출하지 않을 경우 참조변수에
		//Thread 객체를 저장하기 않고 Thread 객체 생성시 start() 메소드를 직접 호출
		new MultiThreadOne().start();
		new MultiThreadOne().start();

		/*
		MultiThreadTwo two=new MultiThreadTwo();
		Thread thread=new Thread(two);
		thread.start();
		*/
		new Thread(new MultiThreadTwo()).start();
		
		/*
		//Runnable 인터페이스를 상속받은 자식클래스로 객체를 여러개 생성할 필요가 없는 경우
		//Runnable 인터페이스를 상속받은 익명클래스로 객체를 생성해 Thread 클래스의 생성자
		//매개변수에 전달하여 Thread 객체 생성
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(char i='a';i<='z';i++) {
					System.out.print(i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		*/
		
		//Runnable 인터페이스에는 반환형이 [void]이고 매개변수가 없는 run() 메소드가 하나만
		//작성된 함수형 인터페이스로 사용 가능
		// => 람다표현식을 사용하여 객체 생성 가능
		new Thread(() -> {
			for(char i='a';i<='z';i++) {
				System.out.print(i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		
		for(int i=1;i<=9;i++) {
			System.out.print(i);
			
			//Thread.sleep(long millis) : 스레드를 원하는 시간(ms)동안 일시 중지하는 정적메소드
			// => InterruptedException 발생 - 일반예외이므로 예외처리 하지 않으면 에러 발생
			// => try~catch 구문을 사용하지 않고 메소드에 throws 키워드를 사용하여 예외 전달
			Thread.sleep(500);
		}
	}
}