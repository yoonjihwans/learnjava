package xyz.itwill.dbcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Properties 파일 : 프로그램 실행에 필요한 값을 제공하기 위한 텍스트 파일
// => 파일의 확장자를 [properties]로 저장하며 Properties 파일에는 [이름 = 값] 형식의 문자열을 행단위로 저장
// => Properties 파일로 제공되는 값을 이름으로 구분하여 사용하므로 이름이 중복되지 않도록 작성
// => Properties 파일에는 영문자, 숫자, 일부 특수문자를 제외한 문자는 유니코드로 자동 변환
// => 프로그램에서 자주 변경하는 값을 Properties 파일로 제공받으면 프로그램의 유지보수의 효율성 증가 

//[user.properties] 파일에 저장된 값을 얻어와 출력하는 프로그램 작성
public class PropertiesApp {

	public PropertiesApp() throws IOException {
		InputStream in = getClass().getClassLoader().getResourceAsStream("xyz/itwill/dbcp/user.properties");
		
		Properties properties = new Properties();
		
		properties.load(in);
		
		String id = (String) properties.get("id");
		String name = (String) properties.get("name");
		String email = (String) properties.get("email");
		
		System.out.println("아이디 = " + id);
		System.out.println("이름 = " + name);
		System.out.println("이메일 = " + email);
	}

	public static void main(String[] args) throws IOException {
		new PropertiesApp();
	}
}