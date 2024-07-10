<%@page import="xyz.itwill.bean.HewonBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 내장객체에 저장된 속성값을 반환받아 HTML 문서에 포함하여 응답하는 JSP 문서 --%>
<%--
	//session 내장객체에 저장된 속성값이 없는 경우 에러코드를 클라이언트에게 전달하여 응답
	if(session.getAttribute("hewon") == null) {//JSP 문서를 비정상적으로 요청한 경우
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	//session 내장객체에 저장된 속성값을 객체로 반환받아 저장
	HewonBean hewon=(HewonBean)session.getAttribute("hewon");
	
	//session 내장객체에 저장된 속성값을 다른 JSP 문서에서 사용할 수 없도록 속성값 삭제
	session.removeAttribute("hewon");
--%>
<%
	//request 내장객체에 저장된 속성값이 없는 경우 에러코드를 클라이언트에게 전달하여 응답
	if(request.getAttribute("hewon") == null) {//JSP 문서를 비정상적으로 요청한 경우
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
	
	//request 내장객체에 저장된 속성값을 객체로 반환받아 저장
	HewonBean hewon=(HewonBean)request.getAttribute("hewon");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>회원정보확인</h1>
	<hr>
	<p>이름 = <%=hewon.getName() %></p>
	<p>전화번호 = <%=hewon.getPhone() %></p>
	<p>주소 = <%=hewon.getAddress() %></p>
</body>
</html>