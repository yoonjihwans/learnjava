package xyz.itwill.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.List;

public class ObjectInputStreamApp {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//ObjectInputStream 클래스 : 객체를 전달받을 수 있는 입력스트림을 생성하기 위한 클래스
		// => ObjectInputStream(InputStream in) 생성자를 사용하여 ObjectInputStream 객체 생성
		// => 매개변수로 입력스트림(InputStream 객체)을 전달받아 객체를 전달받을 수 있는 
		//입력스트림으로 확장하기 위해 사용  
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("c:/data/object.txt"));
		
		//ObjectInputStream.readObject() : 확장된 입력스트림으로 객체를 제공받아 반환하기 위한 메소드
		// => Object 타입의 객체를 반환하므로 반드시 명시적 객체 형변환 후 사용 
		// => 반환된 객체의 자료형(클래스)이 없는 경우 ClassNotFoundException 발생
		String object1=(String)in.readObject();
		Date object2=(Date)in.readObject();
		@SuppressWarnings("unchecked")
		List<String> object3=(List<String>)in.readObject();
		
		System.out.println("object1 = "+object1);
		System.out.println("object2 = "+object2);
		System.out.println("object3 = "+object3);
		
		in.close();
	}
}