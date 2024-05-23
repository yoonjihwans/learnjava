package xyz.itwill.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputStreamApp {
	public static void main(String[] args) throws IOException {
		//DataOutputStream 클래스 : 원하는 자료형의 값을 전달할 수 있는 출력스트림을 생성하기 위한 클래스
		// => DataOutputStream(OutputStream out) 생성자로 DataOutputStream 객체 생성
		// => 매개변수로 출력스트림(OutputStream 객체)을 전달받아 원하는 자료형의 값을 전달할
		//수 있는 출력스트림으로 확장하기 위해 사용
		DataOutputStream out=new DataOutputStream(new FileOutputStream("c:/data/data.txt"));
		
		//DataOutputStream.writeInt(int v) : 확장된 출력스트림으로 정수값을 전달하기 위한 메소드 
		out.writeInt(100);
		
		//DataOutputStream.writeBoolean(boolean v) : 확장된 출력스트림으로 논리값을 전달하기 위한 메소드 
		out.writeBoolean(true);
		
		//DataOutputStream.writeUTF(String v) : 확장된 출력스트림으로 문자열을 전달하기 위한 메소드 
		out.writeUTF("홍길동");
		
		out.close();
		
		System.out.println("c:\\data\\data.txt 파일을 확인해 보세요.");
	}
}