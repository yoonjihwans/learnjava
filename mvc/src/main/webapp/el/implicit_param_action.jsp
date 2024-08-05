<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 전달값을 반환받아 HTML 태그에 포함하여 응답하는 JSP 문서 --%>
<%
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - Request Parameter</h1>
	<hr>
	<h2>EL 미사용</h2>
	<ul>
		<li>이름 = <%=request.getParameter("name") %></li>
		<li>주소 = <%=request.getParameter("address") %></li>
		<%-- 같은 이름으로 전달된 값이 여러개인 경우 request 객체로 getParameter() 메소드를
		호출하면 첫번째 전달값만을 반환받아 사용 가능 --%>
		<%-- 
		<li>좋아하는 음식-1 = <%=request.getParameter("food") %></li>
		<li>좋아하는 음식-2 = <%=request.getParameter("food") %></li>
		--%>
		<%-- 같은 이름으로 전달된 값이 여러개인 경우 request 객체로 getParameterValues() 메소드를
		호출하여 모든 전달값이 저장된 배열로 반환받아 사용 --%>
		<%-- => 같은 이름으로 전달된 값을 배열의 요소에 저장하므로 첨자를 사용해 요소값(전달값) 사용 --%>
		<li>좋아하는 음식-1 = <%=request.getParameterValues("food")[0] %></li>
		<li>좋아하는 음식-2 = <%=request.getParameterValues("food")[1] %></li>
	</ul>
	<hr>
	<h2>EL 사용</h2>
	<ul>
		<%-- EL 표현식에서 param 내장객체 또는 paramValues 내장객체를 사용하면 전달값을 제공받아 출력 처리 --%>
		<%-- => param 내장객체 또는 paramValues 내장객체는 Map 객체로 맵키 대신 전달값을 구분하는
		이름을 사용해 전달값을 제공받아 출력 처리 --%>
		<li>이름 = ${param.name }</li>
		<li>주소 = ${param.address }</li>
		<li>좋아하는 음식-1 = ${paramValues.food[0] }</li>
		<li>좋아하는 음식-2 = ${paramValues.food[1] }</li>
	</ul>
</body>
</html>