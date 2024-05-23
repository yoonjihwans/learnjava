package xyz.itwill.exception;

//예외(Exception) : JVM(Java Virtual Machine)에 의해 Java 프로그램이 실행될 때 발생되는 오류
// => 프로그램 작성을 잘못했거나 사용자가 프로그램을 잘못 실행한 경우 예외 발생
// => 프로그램 실행시 예외가 발생되면 예외가 발생된 시점에 스레드가 소멸되어 프로그램 강제 종료
// => 예외가 발생되어 프로그램이 강제로 종료되는 것을 방지하거나 예외가 발생된 이유를 알기
//위해 예외처리(ExcenptionHandle) 필요

//Java 언어에서는 예외 관련 정보를 제공하기 위한 다양한 예외클래스를 제공
//예외클래스(Exception Class) : 예외처리에 관련된 정보를 제공하기 위한 클래스
// => Exception 클래스를 상속받아 작성된 자식클래스
// => 프로그램 실행시 예외가 발생되면 JVM은 예외클래스로 예외정보가 저장된 예외객체 생성

//Throwable 클래스 : 프로그램에서 발생되는 모든 오류정보를 저장하기 위한 부모클래스
// => 자식클래스 : Error 클래스, Exception 클래스

//예외의 종류
//1.일반예외 : 프로그램 실행시 예외가 발생될 가능성이 높아 예외처리를 하지 않으면 컴파일시 에러 발생
// => RuntimeException 클래스를 상속받지 않는 예외클래스로 객체 생성
//2.실행예외 : 프로그램 실행시 예외가 발생될 가능성이 낮아 예외처리를 하지 않으도 컴파일시 에러 미발생
// => RuntimeException 클래스를 상속받은 예외클래스로 객체 생성
// => 실행예외를 예외처리 하지 않으면 JVM이 예외클래스로 객체를 생성하여 예외객체에 저장된 정보로 
//예외관련 메세지를 자동으로 출력 처리

//try~catch 구문을 사용하여 예외처리
//형식) try {
//           예외가 발생될 수 있는 명령;
//           ...
//      } catch(예외클래스 참조변수) {
//           예외처리 명령;
//           ...
//      } finally {
//           예외발생과 상관없이 무조건 실행될 명령
//           ...
//      }
// => try 블럭에 명령을 실행할 때 예외가 발생되면 JVM에 의해 예외클래스로 객체 생성
// => try 블럭의 명령 실행시 예외가 발생되면 catch 블록으로 스레드를 가져와 예외처리 명령을
//실행하므로 try 블럭에서 예외가 발생된 명령 하단에 작성된 명령 미실행
// => catch 소괄호에 작성된 예외클래스의 참조변수에는 예외클래스의 객체를 전달받아 저장
// => catch 블럭은 여러번 작성 가능
// => finally 블럭에는 예외발생과 상관없이 무조건 실행될 명령을 작성하면 생략 가능

public class ExceptionHandleApp {
	public static void main(String[] args) {
		int[] array = {10, 20, 30, 40, 50};
	
		try {
			//배열 요소의 첨자가 범위를 벗어난 경우 ArrayIndexOutOfBoundsException 발생
			for(int i=0;i<=array.length;i++) {
				//예외 발생 가능 명령 
				// => 예외가 발생되면 예외클래스로 객체 자동 생성
				System.out.println("array["+i+"] = "+array[i]);
			}
			
			//예외가 발생되지 않아야 실행되는 명령
			System.out.println("[메세지]프로그램이 정상적으로 실행 되었습니다.");
		} catch (ArrayIndexOutOfBoundsException e) {
			//ArrayIndexOutOfBoundsException 객체를 전달받아 참조변수에 저장하여 예외처리에 사용
			
			//프로그램 사용자에게 에러메세지 제공
			// => 사용자 입력값에 의해 예외가 발생된 경우에 대한 예외처리 명령
			System.out.println("[에러]프로그램 실행에 예기치 못한 오류가 발생 되었습니다.");
			
			//프로그램 개발자에게 예외 관련 정보 제공 : 에러로그(기록) - 필수
			// => 예외클래스의 객체에 저장된 정보를 사용하여 예외가 발생된 이유 제공하여 디버깅 처리
			//Throwable.getMessage() : 예외클래스의 객체에 저장된 예외 메세지를 반환하는 메소드
			//System.out.println("[예외]"+e.getMessage());
			
			//Throwable.printStackTrace() : 예외가 발생된 이유를 역으로 추적하여 출력하는 메소드
			//e.printStackTrace();
		} finally {
			System.out.println("[메세지]예외 발생과 상관없이 무조건 실행될 명령");
		}
	}
}