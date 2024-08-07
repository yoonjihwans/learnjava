<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - URL 관리 태그</h1>
	<hr>
	<%-- 웹자원의 경로를 상대경로로 표현하여 URL 주소 제공 --%>
	<%-- 상대경로 : 요청 웹프로그램의 경로를 기준으로 웹자원의 경로를 표현하는 방법 --%>
	<%-- 문제점)MVC 디자인 패턴을 적용한 JSP Model-2 방식의 웹프로그램에서는 요청 웹프로그램
	(Controller - Servlet)과 응답 웹프로그램(View - JSP)의 경로가 다른 경우 404 에러코드 발생 --%>
	<%-- 해결법)웹자원의 경로를 절대경로로 표현하여 사용 --%>
	<img alt="코알라" src="images/Koala.jpg" width="200">

	<%-- 웹자원의 경로를 절대경로로 표현하여 URL 주소 제공 --%>
	<%-- 절대경로 : 최상위 디렉토리를 기준으로 웹자원의 경로를 표현하는 방법 --%>
	<%-- 문제점) 컨텍스트 최상위 디렉토리의 이름이 변경되면 웹자원의 경로가 변경되어 404 에러코드 발생 --%>
	<%-- 해결법) 컨텍스트 최상위 디렉토리의 경로를 제공받이 웹자원의 경로로 표현하여 사용 --%>
	<img alt="코알라" src="/mvc/jstl/images/Koala.jpg" width="200">

	<%-- request.getContextPath() 메소드를 호출하여 컨텍스트 최상위 디렉토리의 경로를 제공받이 웹자원의 경로 표현 --%>
	<img alt="코알라" src="<%=request.getContextPath()%>/jstl/images/Koala.jpg" width="200">
	
	<%-- EL 표현식에서 pageContext 객체를 사용해 컨텍스트 최상위 디렉토리의 경로를 제공받이 웹자원의 경로 표현 --%>
	<img alt="코알라" src="${pageContext.request.contextPath }/jstl/images/Koala.jpg" width="200">
	
	<%-- url 태그 : 웹자원의 경로를 절대경로로 제공하기 위한 태그 --%>
	<%-- value 속성 : 웹자원의 컨텍스트 경로를 속성값으로 설정 --%>
	<%-- => 컨텍스트 최상위 디렉토리의 경로를 제공받아 웹자원을 절대경로의 URL 주소로 변경하여 제공 --%>
	<img alt="코알라" src="<c:url value="/jstl/images/Koala.jpg"/>" width="200">
</body>
</html>