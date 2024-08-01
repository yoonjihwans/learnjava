<%@page import="xyz.itwill.dao.UserinfoModelOneDAO"%>
<%@page import="xyz.itwill.dto.UserinfoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 인증정보를 전달받아 USERINFO 테이블에 저장된 회원정보와 비교하여 인증 처리하고
[user_login.jsp] 문서를 요청할 수 있는 URL 주소로 응답하는 JSP 문서 --%>    
<%
	if(request.getMethod().equals("GET")) {
		response.sendRedirect("user_error.jsp");
		return;
	}

	String userid=request.getParameter("userid");
	String password=request.getParameter("password");
	
	UserinfoDTO userinfo=UserinfoModelOneDAO.getDAO().selectUserinfo(userid);
	
	if(userinfo == null || !userinfo.getPassword().equals(password)) {//인증 실패
		session.setAttribute("message", "입력된 아이디가 잘못 되었거나 비밀번호가 맞지 않습니다.");
		session.setAttribute("userid", userid);
		response.sendRedirect("user_login.jsp");
		return;
	}
	
	//인증 성공 - 세션에 권한 관련 정보가 저장된 객체를 속성값으로 저장
	session.setAttribute("loginUserinfo", userinfo);
	
	response.sendRedirect("user_login.jsp");
%>