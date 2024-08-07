package xyz.itwill.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//필터(Filter) : 클라이언트 요청에 대한 웹프로그램 실행 전 또는 후에 실행될 명령을 제공하기 위한 기능
// => WAS 프로그램에 의해 관리되며 XSS 방어, 인코딩 변환 처리, 요청 대한 인증 처리, 권한 검사 등의 명령 실행

//필터 클래스 : 요청 웹프로그램 실행 전에 객체로 생성될 클래스
// => Filter 인터페이스를 상속받아 작성 - doFilter() 메소드를 오버라이드 선언
// => 필터 클래스는 @WebFilter 어노테이션 또는 [web.xml] 파일의 엘리먼트를 사용하여 클래스를
//필터로 등록하고 필터가 동작될 URL 패턴정보를 매핑 처리해 사용

//모든 웹프로그램 실행전에 리퀘스트 메세지 몸체부에 저장되어 전달되는 값을 읽기 위해 문자형태
//(CharacterSet)을 변경하는 기능을 제공하기 위한 필터 클래스 - 인코딩 필터를 생성하기 위한 클래스
public class EncodingFilter implements Filter {
	//문자형태(CharacterSet)에 대한 인코딩 정보를 저장하기 위한 필드
	private String encoding;
	
	//필터 클래스가 객체로 생성된 후 가장 먼저 한번만 호출되는 메소드 - 초기화 작성
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding="utf-8";
	}
	
	//필터가 동작될 때마다 자동 호출되는 메소드
	// => 웹프로그램의 명령이 실행되기 전 또는 실행된 후에 동작될 명령 작성
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//웹프로그램의 명령이 실행되기 전 동작될 명령 작성
		if(request.getCharacterEncoding() == null || 
			!request.getCharacterEncoding().equalsIgnoreCase(encoding)) {
			request.setCharacterEncoding(encoding);
		}
		
		//FilterChain.doFilter(ServletRequest request, ServletResponse response)
		// => 현재 필터와 연결된 다른 필터의 명령을 실행하기 위한 메소드
		// => 연결될 필터가 없는 경우 웹프로그램의 명령 실행
		//FilterChain 객체 : 다른 필터와 연결할 수 있는 정보를 저장하기 위한 객체
		chain.doFilter(request, response);
		
		//웹프로그램의 명령이 실행된 후 동작될 명령 작성
	}
	
}