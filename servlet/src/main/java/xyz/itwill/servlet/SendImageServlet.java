package xyz.itwill.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트 요청에 의해 이미지 파일로 응답하기 위한 서블릿
// => /WEB-INF/Koala.jpg 파일(웹자원)을 읽어 클라이언트에게 응답
// => [WEB-INF] 폴더와 [META-INF] 폴더는 서블릿(웹프로그램)에서만 접근 가능한 숨겨진 폴더로
//클라이언트 요청 불가능
@WebServlet("/image.itwill")
public class SendImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트에게 이미지 파일(JPEG 파일)이 응답되도록 응답파일의 형태 변경
		// => 응답파일이 문서파일이 아니므로 캐릭터셋 미설정
		response.setContentType("image/jpeg");
		
		//응답파일(이미지 파일)을 생성하기 위한 출력스트림을 반환받아 저장
		// => 이진파일을 생성하기 위한 원시데이타를 전달하는 ServletOutputStream 객체를 반환받아 저장
		ServletOutputStream out=response.getOutputStream();
		
		//서버에 저장된 이미지 파일의 시스템 경로를 반환받아 저장
		//HttpServletRequest.getServletContext() : HttpServletRequest 객체를 생성한 
		//ServletContext 객체(WAS)를 반환하는 메소드
		//ServletContext.getRealPath(String contextPath) : 매개변수로 전달받은 웹자원에 대한
		//파일 시스템 경로를 반환하는 메소드
		String imageFilePath=request.getServletContext().getRealPath("/WEB-INF/Koala.jpg");
		//System.out.println("imageFilePath = "+imageFilePath);//서버 콘솔 출력
		
		//이미지 파일을 읽기 위한 입력스트림(FileInputStream 객체)을 생성하여 저장
		// => FileInputStream 클래스의 생성자 매개변수에 이미지 파일의 파일 시스템 경로 전달
		FileInputStream in=new FileInputStream(imageFilePath);
		
		//입력스트림으로 원시데이타를 읽어와 출력스트림으로 전달 - 반복 처리
		// => 서버에 저장된 이미지 파일을 클라이언트에게 전달하기 위한 응답파일 생성 - 파일 복사
		while(true) {
			int readByte=in.read();
			if(readByte==-1) break;
			out.write(readByte);
		}
		
		in.close();//파일 입력스트림 제거
	}
}