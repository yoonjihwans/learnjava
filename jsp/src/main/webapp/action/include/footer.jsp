<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//문제점)스레드가 이동된 JSP 문서에서는 리퀘스트 메세지 몸체부에 저장된 전달값을 읽기
	//위한 문자형태(CharacterSet - Encoding)를 변경하는 메소드를 호출해도 인코딩 변경 불가능
	//해결법)요청 JSP 문서에서 리퀘스트 메세지 몸체부에 저장된 전달값을 읽기 위한 문자형태를
	//변경하는 메소드 호출 - 스레드가 이동된 JSP 문서에서는 문자형태를 변경하는 메소드 호출 생략
	//request.setCharacterEncoding("utf-8");
	
	String master=request.getParameter("master");

	//문제점)스레드가 이동된 JSP 문서에서는 response 객체로 sendError() 메소드 또는 sendRedirect()
	//메소드를 호출해도 클라이언트에게 에러코드 또는 URL 주소가 전달되어 응답 불가능
	//해결법)request 내장객체에 에러코드 또는 URL 주소를 속성값으로 저장하여 요청 JSP 문서에게 제공
	if(master.equals("홍길동(abc@itwill.xyz)")) {
		//에러코드를 클라이언트가 아닌 요청 JSP 문서에게 전달하여 응답
		//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		request.setAttribute("errorCode", HttpServletResponse.SC_BAD_REQUEST);
		return; 
	}
%>    
<p>Copyright ⓒ Itwill Corp. All rights reserved</p>
<%-- <p>관리자 : 홍길동(abc@itwill.xyz)</p> --%>
<p>관리자 : <%=master %></p>