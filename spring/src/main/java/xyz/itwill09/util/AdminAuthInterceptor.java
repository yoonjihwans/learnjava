package xyz.itwill09.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import xyz.itwill09.dto.Userinfo;
import xyz.itwill09.exception.BadRequestException;

//인터셉터(Interceptor) : 요청 처리 메소드의 명령 실행 전 또는 실행 후에 실행될 명령을 제공하는 기능
// => HandlerInterceptor 인터페이스를 상속받은 Interceptor 클래스를 작성하여 Spring Bean Configuration  
//File(servlet-context.xml)에 Spring Bean으로 등록하고 인터셉트로 사용되도록 환경설정 설정
// => HandlerInterceptor 인터페이스의 기본 메소드(Default Method) 중 필요한 메소드만 오버라이드 선언하여 사용
// => 필터는 Front Controller의 이전 위치에 존재하여 실행되고 인터셉터는 Front Controller의 다음
//위치에 존재하여 실행 - 필터는 WAS 프로그램에 의해 관리되고 인터셉터는 Front Controller에 의해 관리

//관리자 관련 권한 처리를 위해 작성된 Interceptor 클래스
// => 요청 처리 메소드 호출 전에 비로그인 사용자 또는 관리자가 아닌 사용자가 페이지를 요청할
//경우 인위적 예외 발생
public class AdminAuthInterceptor implements HandlerInterceptor {
	//요청 처리 메소드가 호출되기 전에 실행될 명령을 작성하기 위한 메소드
	// => false 반환 : 요청 처리 메소드 미호출, true : 요청 처리 메소드 호출
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");

		if(loginUserinfo == null || loginUserinfo.getAuth() != 9) {
			//포워드 이동 및 리다이렉트 이동 가능
			//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			//return false;
			
			throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
		}
		
		return true;
	}

	//요청 처리 메소드가 호출된 후에 실행될 명령을 작성하기 위한 메소드
	// => 요청 처리 메소드의 반환값(View Name)으로 뷰를 생성하기 전에 실행될 명령을 작성
	// => ModelAndView 객체를 제공받아 ModelAndView 객체에 저장된 정보를 변경할 때 사용
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
	//요청 처리 메소드가 호출된 후에 실행될 명령을 작성하기 위한 메소드
	// => 요청 처리 메소드의 반환값(ViewName)으로 뷰를 생성한 후에 실행될 명령을 작성
	// => 뷰 관련 정보를 변경할 때 사용
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}