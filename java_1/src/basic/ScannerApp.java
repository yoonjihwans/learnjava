package basic;

//import : 다른 패키지의 클래스를 소스파일에서 사용할 수 있도록 제공하는 키워드
import java.util.Scanner;

//System.out : 값(1Byte - 원시데이타)을 화면(콘솔)에 출력하기 위한 객체 - 출력스트림
//System.in : 키보드로부터 입력된 값(1Byte)을 얻어와 제공하기 위한 객체 - 입력스트림

//키보드로 이름과 나이를 입력받아 화면에 출력하는 프로그램 작성
public class ScannerApp {
	public static void main(String[] args) {
		//Scanner 클래스 : 입력스트림(키보드, 파일 등)을 이용하여 원하는 자료형의 값을 제공받을
		//수 있는 기능(메소드)이 작성된 클래스
		// => Scanner 클래스로 객체를 생성해야만 객체로 클래스의 메소드 호출 가능
		//new Scanner(System.in) 명령으로 키보드 입력스트림을 사용해 Scanner 객체 생성하고
		//생성된 객체를 참조변수에 저장
		Scanner scanner=new Scanner(System.in);
		
		//참조변수에 저장된 객체를 사용하여 메소드 호출
		//Scanner.nextLine() : Scanner 객체의 입력스트림을 사용해 입력값을 문자열로 변환하여 반환하는 메소드
		System.out.print("이름 입력 >> ");
		//키보드로 입력된 값을 문자열로 변환하여 반환받아 변수에 저장
		// => 키보드 입력값이 없는 경우 프로그램의 흐름(스레드)이 일시 중지
		// => 키보드로 값을 입력한 후 엔터(Enter)를 눌러 프로그램의 흐름이 재실행되도록 처리
		String name=scanner.nextLine();
		
		//Scanner.nextInt() : Scanner 객체의 입력스트림을 사용해 입력값을 정수값으로 변환하여 반환하는 메소드
		// => 입력값이 정수값이 아닌 경우 예외(Exception) 발생 - 예외가 발생되면 프로그램 강제 종료 
		System.out.print("나이 입력 >> ");
		//키보드로 입력된 값을 정수값으로 변환하여 반환받아 변수에 저장
		int age=scanner.nextInt();
		
		System.out.println("[결과]"+name+"님의 나이는 "+age+"살입니다.");
		
		//Scanner.close() : Scanner 객체가 사용한 입력스트림을 제거하는 메소드
		scanner.close();
	}
}