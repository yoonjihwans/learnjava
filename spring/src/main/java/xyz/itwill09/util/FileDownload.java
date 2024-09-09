package xyz.itwill09.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//파일 다운로드 기능을 제공하기 위한 응답 처리 클래스
// => BeanNameViewResolver 객체의 의해 클래스의 객체로 메소드를 호출하여 응답 처리
// => Spring Bean Configuration File(servlet-context.xml)에 Spring Bean으로 등록
//BeanNameViewResolver 객체의 의해 실행되는 응답 처리 클래스는 반드시 AbstractView 클래스(추상
//클래스)를 상속받아 작성
// => renderMergedOutputModel() 추상메소드를 오버라이드 선언하여 응답 처리에 필요한 명령 작성
public class FileDownload extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//요청 처리 메소드에서 저장된 Request Scope 속성값을 객체로 반환받아 저장
		// => renderMergedOutputModel() 메소드의 model 매개변수에 저장된 Map 객체를 사용해
		//Request Scope 속성값을 맵값으로 반환받아 사용
		String uploadDirectory=(String)model.get("uploadDirectory");
		String uploadFilename=(String)model.get("uploadFilename");
		
		//서버 디렉토리에 저장된 업로드 파일을 사용해 File 객체 생성
		File file=new File(uploadDirectory, uploadFilename);
		
		//클라이언트에게 파일을 전달하여 응답되도록 응답 파일의 형식(MimeType) 변경
		response.setContentType("application/downlown; utf-8");
		
		//클라이언트에게 응답파일 파일 크기 변경
		response.setContentLengthLong(file.length());
		
		//클라이언트에 저장될 파일명을 리스폰즈 메세지 머릿부에 저장되도록 설정
		// => 파일명에 한글이 존재할 경우 부호화 처리하여 리스폰즈 메세지 머릿부에 저장
		String originalFilename=URLEncoder.encode(uploadFilename.substring(37), "utf-8");
		response.setHeader("Content-Disposition", "attachement;filename=\""+originalFilename+"\";");
		
		//업로드 파일을 클라이언트에게 전달하기 위해 리스폰즈 메세지의 몸체부에 파일을 생성
		//할 수 있는 출력스트림을 반환받아 저장
		OutputStream out=response.getOutputStream();
		
		//서버 디렉토리에 저장된 업로드 파일의 내용을 읽기 위한 입력스트림 생성하여 저장
		InputStream in=new FileInputStream(file);
		
		//FileCopyUtils.copy(InputStream in, OutputStream out) : 입력스트림으로 원시 데이타를
		//읽어 출력스트림으로 전달하는 정적 메소드 - 복사 기능을 제공하는 메소드
		FileCopyUtils.copy(in, out);
		
		in.close();
	}
}