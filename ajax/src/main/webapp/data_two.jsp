<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달값을 반환받아 HTML 문서(TEXT 문서)로 응답하는 JSP 문서 --%>
<%
	request.setCharacterEncoding("utf-8");

	String id=request.getParameter("id");
	String name=request.getParameter("name");
%>    
<%=name %>[<%=id %>]님, 환영합니다.