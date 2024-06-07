package xyz.itwill.jdbc;

//Class 객체를 제공받는 방법
//1.Class.forName(String className) 메소드를 호출하여 매개변수로 전달받은 클래스에 대한
//Class 객체를 생성하여 반환받아 사용
//2.Object.getClass() 메소드를 호출하여 메소드를 호출한 클래스에 대한 Class 객체를 반환받아 사용
//3.[클래스명.class] 표현식을 사용하여 직접 클래스 파일을 읽어 Class 객체로 생성하여 사용   

public class StaticBlockApp {
	public static void main(String[] args) throws ClassNotFoundException {
		/*
		//1.JVM은 ClassLoader 프로그램을 실행하여 Class 파일(XXX.class)을 읽어 메모리의  
		//Method 영역에 Class 객체(Clazz)를 생성하여 저장 - 1번만 실행
		//2.new 연산자로 클래스(Class 객체)의 생성자를 호출해 객체 생성 
		// => 객체는 메모리의 Head 영역에 생성 
		//3.참조변수를 만들어 생성된 객체의 메모리 주소를 저장
		// => 참조변수는 메모리의 Static 영역에 생성 
		StaticBlock sb=new StaticBlock();
		
		//참조변수에 저장된 메모리 주소로 객체를 참조하여 메소드 호출
		sb.display();
		*/
		
		//Class.forName(String className) : 매개변수로 패키지 경로가 포함된 클래스를 문자열로
		//전달받아 ClassLoader 프로그램을 사용해 클래스를 메모리에 생성하여 저장하는 정적메소드
		// => 클래스 관련 정보가 저장된 Class 객체를 반환
		// => 매개변수로 전달받은 클래스가 없는 경우 ClassNotFoundException 발생되므로 반드시 예외처리
		Class.forName("xyz.itwill.jdbc.StaticBlock");//수동으로 클래스를 읽어 메모리에 저장
		//StaticBlock sb=new StaticBlock();
		//sb.display();
	}
}