package xyz.itwill.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

public class ObjectOutputStreamApp {
	public static void main(String[] args) throws IOException {
		//ObjectOutputStream 클래스 : 객체를 전달할 수 있는 출력스트림을 생성하기 위한 클래스
		// => ObjectOutputStream(OutputStream out) 생성자로 ObjectOutputStream 객체 생성
		// => 매개변수로 출력스트림(OutputStream 객체)을 전달받아 객체를 전달할 수 있는 출력스트림으로 
		//확장하기 위해 사용
		ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("c:/data/object.txt"));
		
		//ObjectOutputStream.writeObject(Object obj) : 매개변수로 전달받은 객체를 확장된 출력
		//스트림으로 전달하기 위한 메소드
		out.writeObject("홍길동");//확장된 출력스트림으로 String 객체 전달
		out.writeObject(new Date());//확장된 출력스트림으로 Date 객체 전달
		out.writeObject(List.of("임꺽정", "전우치", "일지매"));//확장된 출력스트림으로 List 객체 전달
		
		out.close();
		
		System.out.println("c:\\data\\object.txt 파일을 확인해 보세요.");
	}
}