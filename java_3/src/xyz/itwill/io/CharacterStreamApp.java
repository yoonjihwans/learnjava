package xyz.itwill.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//문자데이타 기반 스트림(Character Stream) : 원시데이타를 인코딩 처리된 값(문자데이타)으로 
//변환하여 전달하기 위한 입력클래스 또는 출력클래스로 생성된 스트림(객체)
//=> 전달값을 2Byte 단위로 입력 또는 출력하기 위한 스트림
//=> Reader 추상클래스와 Writer 추상클래스를 기반으로 선언된 클래스

//키보드로 입력받은 문자데이타(2Byte)를 모니터로 전달하여 출력하는 프로그램 작성
// => EOF(End Of File) 신호(Ctrl+Z - 입력 종료)를 받기 전까지 반복 처리
public class CharacterStreamApp {
	public static void main(String[] args) throws IOException {
		System.out.println("[메세지]키보드를 눌러 문자값을 입력해 주세요.");

		//InputStreamReader 클래스 : 매개변수로 InputStream 객체를 전달받아 문자데이타를
		//제공받을 수 있는 입력스트림(Reader 객체)을 생성하기 위한 클래스 - 스트림 확장
		// => 키보드 입력값을 인코딩 처리된 문자값으로 전달받기 위한 입력스트림 생성
		InputStreamReader in=new InputStreamReader(System.in);
		
		//OutputStreamWriter 클래스 : 매개변수로 OutputStream 객체를 전달받아 문자데이타를
		//제공할 수 있는 출력스트림(Writer 객체)을 생성하기 위한 클래스 - 스트림 확장
		// => 모니터에 인코딩 처리된 문자값으로 전달받기 위한 출력스트림 생성
		//OutputStreamWriter out=new OutputStreamWriter(System.out);
		
		//PrintWriter 클래스 : 매개변수로 OutputStream 객체를 전달받아 문자데이타를
		//제공할 수 있는 출력스트림(Writer 객체)을 생성하기 위한 클래스 - 스트림 확장
		// => OutputStreamWriter 클래스를 상속받은 자식클래스로 다양한 출력메소드 제공
		PrintWriter out=new PrintWriter(System.out);
		
		//키보드 입력값(2Byte - 문자데이타)을 저장하기 위한 변수
		int readByte;
		
		while(true) {
			//Reader.read() : 입력스트림에 저장된 원시데이타(1Byte)를 인코딩 처리한 문자데이타로
			//얻어와 정수값(int)으로 반환하는 메소드
			readByte=in.read();
			
			if(readByte == -1) break;
			
			//Writer.write(int b) : 매개변수로 전달받은 정수값을 인코딩 처리한 문자데이타로 변환
			//하여출력스트림으로 전달하는 메소드
			// => 모니터 출력스트림(System.out) 버퍼(Buffer)에 문자데이타를 저장하고 일정 크기가
			//되면 출력스트림으로 전달하여 처리
			out.write(readByte);
			
			//Writer.flush() : 출력스트림 버퍼(Buffer)에 저장된 문자데이타를 출력스트림으로
			//전달하는 메소드
			out.flush();
		}
		
		System.out.println("[메세지]프로그램을 종료합니다.");

	}
}