package xyz.itwill.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//입력페이지의 form 태그를 사용해 사용자 입력값(파일)을 [multipart/form-data]로 전달받은 경우
//HttpServletRequest 객체로 리퀘스트 메세지 몸체부에 저장된 이진파일을 읽기 위해 입력스트림을
//(ServletInputStream 객체)를 반환받아 전달값과 전달파일을 구분하여 처리
// => [multipart/form-data]로 전달되는 문자값과 파일을 처리하기 위한 클래스를 사용하는 것을 권장

//[multipart/form-data]로 전달되는 문자값과 파일을 처리하기 위한 클래스가 포함된 라이브러리
//파일을 다운로드 받아 프로젝트로 빌드 처리
//1.Apache 그룹에서 배포한 commons-fileupload 라이브러리의 클래스 사용 - 선택적 파일 업로드
//2.Oreilly 그룹에서 배포한 cos 라이브러리의 클래스 사용 - 무조건 파일 업로드

//Oreilly 그룹에서 배포한 cos 라이브러리 파일을 다운로드 받아 프로젝트에 빌드 처리하는 방법
//1.http://www.servlets.com 사이트 접속 >> COS File Upload Library 메뉴 클릭 >> cos-22.05.zip 다운로드
//2.cos-22.05.zip 파일 압축 풀기 >> cos-22.05 폴더 이동 >> lib 폴더 이동 >> cos.jar 복사
//3.프로젝트 >> src/main/webapp >> WEB-INF >> lib >> cos.jar 붙여넣기
// => /WEB-INF/lib 폴더에 라이브러리 파일(jar 파일)을 붙여넣기 하면 자동으로 프로젝트에
//라이브러리 파일을 빌드 처리

//입력페이지(file_upload.html)에서 전달된 입력값과 입력파일의 이름을 HTML 문서에 포함하여 
//출력처리 되도록 응답하는 서블릿 
// => 사용자로부터 입력받아 전달된 파일은 서버 디렉토리에 저장 - 파일 업로드(File Upload)
@WebServlet("/upload.itwill")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		if(request.getMethod().equals("GET")) {
			response.sendRedirect("/servlet/file_upload.html");//입력페이지로 이동
			return;
		}
		
		/*
		//form 태그를 사용해 [multipart/form-data]로 전달된 입력값은 HttpServletRequest 객체의
		//getParameter() 메소드로 반환받아 사용 불가능
		request.setCharacterEncoding("utf-8");
		String uploader=request.getParameter("uploader");
		*/
		
		//전달파일이 저장될 서버 디렉토리의 파일 시스템 경로를 반환받아 저장
		// => 이클립스의 작업 디렉토리가 아닌 WAS 프로그램이 사용하는 웹디렉토리의 파일 시스템 경로 반환
		// => 이클립스에 생성된 업로드 디렉토리가 웹디렉토리에 전달받아 업로드되어 저장
		//WAS 프로그램이 실행(Start)될 때 이클립스 작업 디렉토리의 파일을 이용해 웹디레토리의
		//자원(Context)으로 변환하여 저장 - 동기화 처리
		// => 작업 디렉토리에 업로드 파일이 없으므로 동기화 처리되면 웹디렉토리에 업로드 되어
		//있던 파일 소멸
		String saveDirectory=request.getServletContext().getRealPath("/upload");
		//System.out.println("saveDirectory = "+saveDirectory);
		
		//cos 라이브러리의 MultipartRequest 클래스로 객체 생성
		// => MultipartRequest 객체 : [multipart/form-data]로 전달된 문자값 및 파일을 처리하는
		//기능을 제공하기 위한 객체
		// => MultipartRequest 객체를 생성하면 모든 전달파일을 자동으로 서버 디렉토리 저장 - 무조건적인 파일 업로드
		//MultipartRequest(HttpServletRequest request, String saveDirectory[, int maxPostSize]
		//[, String encoding][, FileRenamePolicy policy]) 생성자를 사용해 객체 생성
		// => request : 요청 정보가 저장된 HttpServletRequest 객체 전달
		// => saveDirectory : 전달파일을 저장할 서버 디렉토리의 파일 시스템 경로 전달 - 업로드 디렉토리
		// => maxPostSize : 전달파일의 용량을 제한하기 위한 크기(Byte) 전달 - 생략하면 무제한을 기본값으로 사용
		// => encoding : 전달값을 읽기 위한 문자형태(CharacterSet)를 전달 - 생략하면 서유럽어를 기본값으로 사용
		// => policy : FileRenamePolicy 인터페이스를 상속받은 자식클래스로 생성된 객체 전달 - 생략되면
		//서버 디렉토리에 저장된 파일 대신 전달파일을 저장 - 덮어씌우시(OverWrite)		
		//FileRenamePolicy 객체 : 파일의 이름을 변경하는 규칙이 저장된 객체
		// => 서버 디렉토리에 전달파일과 같은 이름의 파일이 있는 경우 전달파일의 이름을 바꿔
		//서버 디렉토리에 저장하기 위한 필요한 객체
		MultipartRequest mr=new MultipartRequest(request, saveDirectory
				, 30*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
		//MultipartRequest.getParameter(String name) : [multipart/form-data]로 전달된 문자값을
		//읽어와 반환하는 메소드
		String uploader=mr.getParameter("uploader");
		
		//MultipartRequest.getParameter(String name) : [multipart/form-data]로 전달된 입력파일의
		//이름을 얻어와 반환하는 메소드
		String fileone=mr.getOriginalFileName("fileone");	
		String filetwo=mr.getOriginalFileName("filetwo");
		
		//MultipartRequest.getFilesystemName(String name) : 서버디렉토리에 저장된 업로드 처리된
		//파일의 이름을 얻어와 반환하는 메소드
		String uploadone=mr.getFilesystemName("fileone");	
		String uploadtwo=mr.getFilesystemName("filetwo");	
	 			
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>파일 업로드</h1>");
		out.println("<hr>");
		out.println("<p>올린이 = "+uploader+"</p>");
		out.println("<p>파일-1(원본 파일명) = "+fileone+"</p>");
		out.println("<p>파일-1(업파일 파일명) = "+uploadone+"</p>");
		out.println("<p>파일-2(원본 파일명) = "+filetwo+"</p>");
		out.println("<p>파일-2(업파일 파일명) = "+uploadtwo+"</p>");
		out.println("<hr>");
		out.println("<img src='/servlet/upload/"+uploadone+"' width='200'>");		
		out.println("<img src='/servlet/upload/"+uploadtwo+"' width='200'>");		
		out.println("</body>");
		out.println("</html>");	
	}
}