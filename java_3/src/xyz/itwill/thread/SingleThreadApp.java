package xyz.itwill.thread;

//스레드(Thread) : 프로세스의 명령을 실행하기 위한 최소의 명령 실행 단위 - 프로그램의 흐름
//프로세스(Process) : 메모리에 저장된 프로그램 - 중앙처리장치(CPU)에 위해 실행될 명령 제공

//단일 스레드 프로그램 : 하나의 스레드를 사용하여 명령을 제공받아 실행
// => JVM(Java Virtual Machine)이 Java 프로그램을 실행하기 위해 자동으로 [main] 스레드 생성
// => [main] 스레드는  클래스의 main() 메소드를 자동 호출하여 명령 실행
// => main() 메소드가 종료되면 [main] 스레드는 자동 소멸되어 프로그램 종료

public class SingleThreadApp {
	public static void main(String[] args) {
		System.out.println("SingleThreadApp 클래스의 main() 메소드 시작");
		
		//Thread 스레드 : 스레드 관련 정보가 저장된 객체를 생성하기 위한 클래스
		//Thread.currentThread() : 현재 명령을 읽어 처리하는 스레드에 대한 정보가 저장된
		//Thread 객체를 반환하는 정적메소드
		//Thread.getName() : 스레드에 설정된 이름(식별자)을 반환하는 메소드
		System.out.println("["+Thread.currentThread().getName()
				+"]스레드에 의해 SingleThreadApp 클래스의 main() 메소드 명령 실행");
		
		for(char i='A';i<='Z';i++) {
			System.out.print(i);
		}
		System.out.println();
		
		new SingleThread().display();
		
		System.out.println("SingleThreadApp 클래스의 main() 메소드 종료");
	}
}