<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%	session.invalidate();

	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=main&work=main_page");
%>