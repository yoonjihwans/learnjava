<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그아웃 처리 후 [user_login.jsp] 문서를 요청할 수 있는 URL 주소로 응답하는 JSP 문서 --%>
<%
	session.invalidate();
	response.sendRedirect("user_login.jsp");
%>     