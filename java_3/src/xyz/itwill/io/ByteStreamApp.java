package xyz.itwill.io;

import java.io.IOException;

//java.io 패키지 : 입력 스트림과 출력스트림을 생성할 수 있는 Java 자료형이 선언된 패키지

//스트림(Stream) : 값을 전달하기 위한 목적으로 선언된 입력클래스 또는 출력클래스로 생성된 객체
// => 시냇물이 흐르는 모양을 모델링 하여 만들어진 클래스로 한쪽방향으로 순차적으로 전달되도록 처리

//원시데이타 기반 스트림(Byte Stream) : 가공하지 않은 순수한 값(원시데이타)를 전달하기 위한
//입력클래스 또는 출력클래스로 생성된 스트림(객체)
// => 전달값을 1Byte 단위로 입력 또는 출력하기 위한 스트림
// => InputStream 추상클래스와 OutputStream 추상클래스를 기반으로 선언된 클래스

//키보드로 입력받은 원시데이타(1Byte)를 모니터로 전달하여 출력하는 프로그램 작성
// => EOF(End Of File) 신호(Ctrl+Z - 입력 종료)를 받기 전까지 반복 처리
public class ByteStreamApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 문자값을 입력해 주세요.");
		
		//키보드 입력값(1Byte - 원시데이타)을 저장하기 위한 변수
		int readByte;
		
		while(true) {
			//System.in : 프로그램 개발을 위해 제공되는 키보드 입력스트림이 저장된 필드
			// => InptStream 클래스를 기반으로 생성된 입력스트림
			// => 키보드를 누르면 키보드 입력값(1Byte)을 입력스트림으로 전달
			//InptStream.read() : 입력스트림에 저장된 원시데이타(1Byte)를 얻어와 정수값(int)으로
			//반환하는 메소드
			// => 입력스트림에 원시데이타가 하나도 없는 경우 스레드가 일시 중지 상태로 전환
			// => 키보드로 엔터(Enter)를 입력하면 일시 중지된 스레드는 재실행
			//입력스트림 또는 출력스트림으로 메소드를 호출할 경우 대부분 IOException 발생
			// => IOException : 입력스트림 또는 출력스트림에 문제가 있는 경우 발생되는 일반예외 
			readByte=System.in.read();
			
			//입력종료신호(Ctrl+Z : -1)가 전달된 경우 반복문 종료 
			if(readByte == -1) break;
			
			//System.out : 프로그램 개발을 위해 제공되는 모니터(콘솔) 출력스트림이 저장된 필드
			// => PrintStream 클래스(OutputStream 클래스)를 기반으로 생성된 출력스트림
			//OutputStream.write(int b) : 매개변수로 전달받은 정수값을 출력스트림에 원시데이타
			//(1Byte)로 전달하는 메소드
			System.out.write(readByte);
		}
		
		System.out.println("[메세지]프로그램을 종료합니다.");
	}
}