package xyz.itwill.io;

import java.io.File;
import java.io.IOException;

//File 클래스 : 파일(디렉토리) 관련 정보가 저장된 객체를 생성하기 위한 클래스
// => File(String pathname) 생성자를 사용하여 File 객체 생성
// => pathname 매개변수에는 플렛폼의 파일(디렉토리) 경로를 전달하여 사용

//파일경로 : 플렛폼의 파일(디렉토리) 위치를 제공하기 위한 문자열 
// => 절대경로 표현방법 : 최상위 디렉토리를 기준으로 파일(디렉토리)의 위치를 표현하는 방법
// => 상대경로 표현방법 : 현재 실행중인 프로그램의 작업디렉토리를 기준으로 파일(디렉토리)의   
//위치를 표현하는 방법 - 이클립스에서는 프로그램이 작성된 프로젝트 폴더를 작업디렉토리로 사용
public class FileApp {
	public static void main(String[] args) throws IOException {
		//Windows 운영체제에서는 드라이브를 최상위 디렉토리로 사용하여 디렉토리와 파일을 구분
		//하기 위해 [\] 기호 사용
		// => Java 언어에서는 [\] 기호를 사용해 회피문자를 표현하므로 디렉토리와 파일을 구분
		//하기 위해서는 [\\]를 형태로 작성해야 [\] 기호로 사용 가능 
		File fileOne=new File("c:\\data");
		
		//File.exists() : File 객체에 저장된 파일(디렉토리) 경로에 파일(디렉토리)이 없는 경우
		//[false]를 반환하고 파일(디렉토리)가 있는 경우 [true]를 반환하는 메소드 
		if(fileOne.exists()) {
			System.out.println("c:\\data 폴더가 이미 존재합니다.");
		} else {
			//File.mkdir() : File 객체에 저장된 디렉토리 경로를 사용해 디렉토리를 생성하는 메소드
			fileOne.mkdir();
			System.out.println("c:\\data 폴더를 생성 했습니다.");
		}
		System.out.println("==============================================================");
		//File fileTwo=new File("c:\\data\\itwill.txt");
		
		//Windows 운영체제를 제외한 나머지 운영체제에서는 디렉토리와 파일을 [/] 기호를 사용하여 구분
		// => Java 언어에서는 Windows 운영체제를 사용해도 [\] 기호 대신 [/] 기호를 사용해 디렉토리와 파일 구분 가능 
		File fileTwo=new File("c:/data/itwill.txt");
		
		if(fileTwo.exists()) {
			System.out.println("c:\\data\\itwill.txt 파일이 이미 존재합니다.");
		} else {
			//File.createNewFile() : File 객체에 저장된 파일 경로를 사용해 파일을 생성하는 메소드
			// => createNewFile() 메소드에서는 IOException이 발생되므로 예외 처리
			fileTwo.createNewFile();
			System.out.println("c:\\data\\itwill.txt 파일을 생성 했습니다.");
		}
		System.out.println("==============================================================");
		//File(String parent, String child) 생성자를 사용하여 File 객체 생성 가능
		// => parent 매개변수에는 파일이 저장된 디렉토리 경로를 전달하고 child 매개변수에는
		//파일명을 전달하여 File 객체 생성
		File fileThree=new File("c:/data", "itwill.txt");
		
		//File.delete() : File 객체에 저장된 파일 경로를 사용해 파일을 삭제하는 메소드
		// => 파일이 없이 파일을 삭제하지 못한 경우 [false]를 반환하고 파일을 삭제한 경우 [true] 반환
		if(fileThree.delete()) {
			System.out.println("c:\\data\\itwill.txt 파일이 삭제 했습니다.");
		} else {
			System.out.println("c:\\data\\itwill.txt 파일이 없어 삭제할 수 없습니다.");
		}
		System.out.println("==============================================================");
		//매개변수로 파일경로를 상대경로 표현방법으로 전달받아 File 객체 생성
		// => 현재 실행 프로그램의 작업 디렉토리(프로젝트 폴더)를 기준으로 파일 경로 작성
		// => 상대경로 표현방법은 [..] 기호를 사용해 상위 디렉토리를 표현하며 [파일] 형식으로
		//디렉토리에 존재하는 파일(디렉토리) 경로 작성
		File fileFour=new File("src");
		
		if(fileFour.exists()) {
			//File.toString() : File 객체에 저장된 파일(디렉토리) 경로를 문자열로 반환하는 메소드
			// => 참조변수를 출력할 경우 toString() 메소드 자동 호출
			System.out.println("File 객체에 저장된 파일경로 = "+fileFour);
			
			//File.getAbsolutePath() : File 객체에 저장된 파일(디렉토리) 경로를 절대경로의 
			//문자열로 반환하는 메소드
			System.out.println("File 객체에 저장된 파일경로 = "+fileFour.getAbsolutePath());
		} else {
			System.out.println("작업 디렉토리에 [src] 폴더가 없습니다.");
		}
		System.out.println("==============================================================");
		File fileFive=new File("c:/");
		
		//File.isDirectory() : File 객체에 저장된 파일(디렉토리) 경로가 디렉토리 경우 [true]를 반환하는 메소드 
		if(fileFive.isDirectory()) {
			//File.listFiles() : File 객체에 저장된 디렉토리 경로의 디렉토리에서 하위 파일(디렉토리)
			//경로의 File 객체가 요소에 저장된 배열을 반환하는 메소드
			File[] subFiles=fileFive.listFiles();
			
			for(File file : subFiles) {
				//File.isFile() : File 객체에 저장된 파일(디렉토리) 경로가 파일인 경우 [true]를 반환하는 메소드 
				if(file.isFile()) {
					System.out.println("파일 = "+file);
				} else {
					System.out.println("폴더 = "+file);
				}
			}
		}
		System.out.println("==============================================================");
	}
}








