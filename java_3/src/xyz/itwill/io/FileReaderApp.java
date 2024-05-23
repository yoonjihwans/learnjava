package xyz.itwill.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

//파일에 저장된 내용을 문자데이타로 제공받아 모니터(콘솔)에 출력하는 프로그램 작성
public class FileReaderApp {
	public static void main(String[] args) throws IOException {
		//FileReader 클래스 : 파일에 저장된 내용을 문자데이타로 제공받을 수 있는 입력스트림을
		//생성하기 위한 클래스
		// => FileReader(String name) 생성자를 사용하여 FileReader 객체 생성
		// => name 매개변수에는 입력스트림을 생성하기 위한 파일의 경로를 전달하여 저장
		// => name 매개변수로 전달받은 파일경로에 파일이 없는 경우 FileNotFoundException 발생
		// => FileNotFoundException이 발생되면 반드시 try~catch 구문을 사용하여 예외 처리		
		FileReader in=null;
		try {
			in=new FileReader("c:/data/char.txt");
		} catch (FileNotFoundException e) {
			System.out.println("[에러]c:\\data\\char.txt 파일을 찾을 수 없습니다.");
			System.exit(0);
		}
		
		//모니터 출력스트림을 매개변수로 전달받아 문자데이타를 전달할 수 있는 출력스트림으로 확장
		OutputStreamWriter out=new OutputStreamWriter(System.out);
		
		System.out.println("[메세지]c:\\data\\char.txt 파일에 저장된 내용입니다.");
		
		int readByte;
		
		while(true) {
			//파일 입력스트림을 사용해 파일에 저장된 내용을 읽어 문자데이타로 반환받아 변수에
			//저장 - Load
			readByte=in.read();
			
			//파일 마지막에 존재하는 EOF(End Of File)를 반환받은 경우 반복문 종료
			if(readByte == -1) break;
			
			//확장된 모니터(콘솔) 출력스트림으로 문자데이타를 전달하여 출력 처리
			out.write(readByte);
			out.flush();
		}

		//FileReader.close() : 파일 입력스트림을 제거하는 메소드
		in.close();
	}
}