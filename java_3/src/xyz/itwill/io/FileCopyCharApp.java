package xyz.itwill.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//원본파일(c:\data\bandizip.exe)에 저장된 내용을 문자데이타로 읽어 타겟파일(c:\data\bandizip_char.exe)에
//전달하여 저장하는 프로그램 작성 - 파일 복사 프로그램
// => 인코딩 처리된 문자데이타로 입력 또는 출력 처리해 문서파일인 원본파일을 타겟파일로 복사하여 사용 가능
// => 문서파일이 아닌 경우 원본파일의 내용을 변형해 타겟파일로 복사하므로 타겟파일 사용 불가능  
public class FileCopyCharApp {
	public static void main(String[] args) throws IOException {
		//BufferedReader 클래스 : 대량의 문자데이타를 전달받을 수 있는 입력스트림을 생성하기 위한 클래스
		// => BufferedReader(Reader in) 생성자를 사용해 BufferedReader 객체 생성
		// => 매개변수로 입력스트림(Reader 객체)을 전달받아 대량의 문자데이타를 전달받을 수 있도록 확장 
		BufferedReader in=null;
		try {
			in=new BufferedReader(new FileReader("c:/data/bandizip.exe"));
		} catch (FileNotFoundException e) {
			System.out.println("[에러]원본파일을 찾을 수 없습니다.");
			System.exit(0);
		}

		//BufferedWriter 클래스 : 대량의 문자데이타를 전달할 수 있는 출력스트림을 생성하기 위한 클래스
		// => BufferedWriter(Writer out) 생성자를 사용해 BufferedWriter 객체 생성
		// => 매개변수로 입력스트림(Writer 객체)을 전달받아 대량의 문자데이타를 전달할 수 있도록 확장 
		BufferedWriter out=new BufferedWriter(new FileWriter("c:/data/bandizip_char.exe"));
		
		int readByte;
		while(true) {
			readByte=in.read();
			if(readByte == -1) break;
			out.write(readByte);
		}
		
		in.close();
		out.close();
		
		System.out.println("[메세지]파일을 성공적으로 복사 하였습니다.");
	}
}







