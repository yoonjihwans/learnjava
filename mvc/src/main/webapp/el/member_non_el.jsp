<%@page import="xyz.itwill.el.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Request Scope 속성값을 객체로 반환받아 HTML 태그에 포함하여 응답하는 JSP 문서 - 응답 처리하는 웹프로그램 --%>
<%-- => 응답 처리하는 웹프로그램을 요청한 경우 Request Scope 속성값이 없으므로 [null]이 반환되어
참조변수에 저장되고 참조변수로 메소드를 호출하면 NullPointerException 발생 --%>    
<%
	Member member=(Member)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 미사용</h1>
	<hr>
	<%-- <p>회원정보 = <%=member %></p> --%>
	<p>회원의 이름 = <%=member.getName() %></p>
	<%-- <p>회원의 자동차 = <%=member.getCar() %></p> --%>
	<p>회원의 자동차(모델명) = <%=member.getCar().getModelName() %></p> 
	<p>회원의 자동차(색상) = <%=member.getCar().getCarColor() %></p> 
</body>
</html>