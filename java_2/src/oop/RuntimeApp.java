package oop;

//Runtime 클래스 : 프로그램 실행 관련 정보를 제공하기 위한 기능의 메소드가 작성된 클래스
// => Runtime 클래스는 프로그램에 객체를 하나만 제공하는 싱글톤 클래스
public class RuntimeApp {
	public static void main(String[] args) {
		//Runtime 클래스의 생성자은 은닉화 처리되어 있으므로 new 연산자로 생성자를 호출하여 객체 생성 불가능
		//Runtime runtime=new Runtime();
		
		//Runtime.getRuntime() : Runtime 객체를 반환하는 정적 메소드
		// => getRuntime() 메소드를 여러번 호출해도 같은 객체 반환
		Runtime runtime1=Runtime.getRuntime();
		Runtime runtime2=Runtime.getRuntime();
		
		System.out.println("runtime1 = "+runtime1);
		System.out.println("runtime2 = "+runtime2);
		System.out.println("===============================================================");
		//Runtime.totalMemory() : JVM이 사용 가능한 전체 메소드의 크기를 반환하는 메소드
		//Runtime.freeMemory() : JVM이 사용 가능한 여유 메소드의 크기를 반환하는 메소드
		System.out.println("메모리 정리 전 메모리 사용량 >> "+(Runtime.getRuntime().totalMemory()
				-Runtime.getRuntime().freeMemory())+"Byte");
		
		//Runtime.gc() : 메모리를 정리하는 프로그램(Garbage Collector)을 실행하는 메소드
		//Runtime.getRuntime().gc();
		
		//System.gc() : 메모리를 정리하는 프로그램(Garbage Collector)을 실행하는 정적 메소드
		System.gc();
		
		System.out.println("메모리 정리 후 메모리 사용량 >> "+(Runtime.getRuntime().totalMemory()
				-Runtime.getRuntime().freeMemory())+"Byte");
		System.out.println("===============================================================");
	}
}