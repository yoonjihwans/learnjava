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
	<h1>EL - 프로그램 흐름 제어 태그</h1>
	<hr>
	<ul>
		<li>${nameArray[0] }</li>
		<li>${nameArray[1] }</li>
		<li>${nameArray[2] }</li>
		<li>${nameArray[3] }</li>
		<li>${nameArray[4] }</li>
	</ul>
	<hr>
	
	<ul>
	<c:forEach var="i" begin="0" end="4" step="1">
		<li>${nameArray[i] }</li>	
	</c:forEach>
	</ul>
	<hr>
	
	<%-- forEach 태그를 사용해 배열 또는 콜렉션 객체의 요소값을 차례대로 제공받아 반복 처리 --%>
	<%-- var 속성 : 배열 또는 콜렉션 객체의 요소값을 속성값으로 저장하기 위한 이름(속성명)을 속성값으로 설정 --%>
	<%-- items 속성 : 일괄 처리할 배열 또는 콜렉션 객체을 속성값으로 설정 - EL를 사용해 속성값 설정 가능 --%>
	<ul>
	<c:forEach var="name" items="${nameArray }">
		<li>${name }</li>
	</c:forEach>
	</ul>
	<hr>
	
	<c:forEach var="student" items="${studentList }">
		<div>학번 = ${student.num }, 이름 = ${student.name }</div>
	</c:forEach>
</body>
</html>