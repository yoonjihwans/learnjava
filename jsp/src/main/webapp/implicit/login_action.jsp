<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 인증정보(아이디와 비밀번호)를 전달받아 인증 처리하기 위한 JSP 문서 --%>
<%-- => [login_form.jsp] 문서의 form 태그를 사용해 post 방식으로 요청하는 JSP 문서 --%>
<%-- 인증실패 : 전달받은 인증정보와 테이블에 저장된 회원의 인증정보가 일치하지 않은 경우 --%>
<%-- => 아이디와 에러메세지를 세션의 속성값으로 저장하여 [login_form.jsp] 문서에게 제공 --%>
<%-- => 클라이언트에게 [login_form.jsp] 문서를 요청할 수 있는 URL 주소를 전달하여 응답 --%>    
<%-- 인증성공 : 전달받은 인증정보와 테이블에 저장된 회원의 인증정보가 일치한 경우 --%>
<%-- => 권한 관련 정보가 저장된 객체를 세션의 속성값으로 저장 - 클라이언트에게 권한 제공 : 로그인 --%>    
<%-- => 클라이언트에게 [login_form.jsp] 문서를 요청할 수 있는 URL 주소를 전달하여 응답 --%>    
<%
	//request.getMethod() : JSP 문서를 요청한 요청방식(GET 또는 POST)을 반환하는 메소드
	if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식으로 요청한 경우 - 비정상적인 요청
		/*
		//response.sendError(int sc) : 클라이언트에게 에러코드(4XX or 5XX)를 전달하요 응답하는 메소드
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
		*/
		
		//response.sendRedirect(String url) : 클라이언트에게 URL 주소를 전달하영 응답하는 메소드
		// => URL 주소를 응답받은 클라이언트는 브라우저의 요청 URL 주소를 변경하여 JSP 문서를
		//요청해 실행결과를 응답받아 출력 - 리다이렉트 이동
		//JSP 문서의 URL 주소를 전달하여 응답 처리할 때 질의문자열을 사용하여 값 전달 가능
		// => 요청 URL 주소는 영문자, 숫자, 일부 특수문자만을 사용하여 작성 가능하며 영문자,
		//숫자, 일부 특수문자를 제외한 문자는 사용 불가능
		//URL 주소의 질의문자열(QueryString)에 영문자, 숫자, 일부 특수문자를 제외한 문자가
		//포함되어 있는 경우 IllegalArgumentException 예외가 발생되어 URL 주소로 응답 처리 불가능
		// => 영문자, 숫자, 일부 특수문자를 제외한 문자는 부호화 처리하여 URL 주소로 사용 가능
		//String message="비정상적인 방법으로 페이지를 요청 하였습니다.";
		
		//URLEncoder.encode(String s, String enc) : 매개변수로 전달받은 문자열을 원하는 문자형태의
		//유니코드로 부호화 처리하여 반환하는 메소드
		// => JavaScript의 encodeURIComponent() 함수와 동일한 기능을 제공
		String message=URLEncoder.encode("비정상적인 방법으로 페이지를 요청 하였습니다.", "utf-8");
				
		response.sendRedirect("login_form.jsp?msg="+message);
		return;
	}
%>