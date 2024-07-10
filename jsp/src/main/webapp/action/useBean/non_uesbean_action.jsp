<%@page import="xyz.itwill.bean.HewonBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달값(회원정보)을 반환받아 내장객체의 속성값으로 저장하고 [non_useBean_display.jsp] 문서로 
포워드 이동하는 JSP 문서 - 클라이언트 요청에 대한 데이타 처리 기능만 제공하는 JSP 문서 --%>    
<%
	//JSP 문서를 GET 방식으로 요청한 경우 에러코드를 클라이언트에게 전달하여 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서를 비정상적으로 요청한 경우
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	//POST 방식으로 요청하여 전달된 값을 읽기 위한 문자형태 변경
	request.setCharacterEncoding("utf-8");
	
	//전달값을 반환받아 저장
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	String address=request.getParameter("address");
	
	/*
	session.setAttribute("name", name);
	session.setAttribute("phone", phone);
	session.setAttribute("address", address);
	*/
	
	//HewonBean 클래스로 객체를 생성하여 전달값을 객체 필드에 저장
	HewonBean hewon=new HewonBean();
	hewon.setName(name);
	hewon.setPhone(phone);
	hewon.setAddress(address);
	
	/*
	//session 내장객체에 HewonBean 객체를 속성값으로 저장
	// => 동일한 세션이 바인딩된 모든 JSP 문서에서 속성값을 객체로 반환받아 사용 가능
	session.setAttribute("hewon", hewon);
	
	//리다이렉트 이동 : 클라이언트에게 URL 주소를 전달하여 클라이언트가 URL 주소의 JSP 문서를
	//요청해 실행결과를 응답받아 출력 처리되도록 이동
	// => 클라이언트 브라우저의 요청 URL 주소 변경 - 클라이언트를 사용해 JSP 문서의 이동
	// => session 내장객체에 속성값을 저장해 리다이렉트 이동되는 JSP 문서에서 속성값을 반환받아 사용
	// => 리다이렉트 이동되는 JSP 문서에서는 session 내장객체의 속성값을 반환받은 후 삭제 처리
	response.sendRedirect(request.getContextPath()+"/action/useBean/non_useBean_display.jsp");
	*/
	
	//request 내장객체에 HewonBean 객체를 속성값으로 저장
	// => 스레드가 이동된 JSP 문서에서만 속성값을 객체로 반환받아 사용 가능
	request.setAttribute("hewon", hewon);

	//포워드 이동 : 요청 JSP 문서에서 응답 JSP 문서로 스레드를 이동하여 응답 처리되도록 이동
	// => 클라이언트 브라우저의 요청 URL 주소 미변경 - 서버의 JSP 문서에서 이동 처리
	// => request 내장객체에 속성값을 저장해 포워드 이동되는 JSP 문서에서 속성값을 반환받아 사용
	// => 포워드 이동되는 JSP 문서에서는 request 내장객체의 속성값을 반환받은 후 삭제 처리 불필요
	
	//forward 태그 대신 request 객체로 메소드를 호출하여 포워드 이동 가능
	//request.getRequestDispatcher(String contextPath) : 매개변수로 전달받은 웹자원의 컨텍스트
	//경로가 저장된 RequestDispatcher 객체를 반환하는 메소드
	// => RequestDispatcher 객체 : 다른 웹프로그램으로 스레드를 이동하는 기능을 제공하는 객체
	//RequestDispatcher.forward(ServletRequest request, ServletResponse response) : RequestDispatcher
	//객체에 저장된 컨텍스트 경로의 웹프로그램으로 스레드를 이동하여 응답 처리하는 메소드 - 포워드 이동
	// => 매개변수에 requset 객체와 response 객체를 전달하여 스레드가 이동되는 웹프로그램에서
	//requset 객체와 response 객체를 같이 사용하도록 설정
	request.getRequestDispatcher("/action/useBean/non_useBean_display.jsp").forward(request, response);
%>