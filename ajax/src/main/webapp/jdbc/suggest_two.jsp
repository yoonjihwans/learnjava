<?xml version="1.0" encoding="utf-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 검색어를 전달받아 SUGGEST 테이블에 저장된 행에서 검색어가 포함된 모든 행을 검색하여 XML로 
응답하는 JSP 문서 --%>    
<%
	if(request.getMethod().equals("GET")) {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}
	
	//POST 방식으로 요청하여 전달된 값을 읽기 위한 문자형태 변경
	request.setCharacterEncoding("utf-8");
	
	//전달값을 반환받아 저장
	String keyword=request.getParameter("keyword");

	//검색어(String 객체)를 전달받아 SUGGEST 테이블에 저장된 행에서 검색어가 포함된 모든
	//행을 검색하여 List 객체로 반환하는 SuggestDAO 클래스의 메소드 호출
	
%>