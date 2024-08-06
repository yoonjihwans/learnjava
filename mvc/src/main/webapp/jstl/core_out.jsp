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
	<h1>Core - EL 지원 태그</h1>
	<hr>
	<c:set var="num" value="100"/>
	<p>정수값 = ${num }</p>
	<%-- out 태그 : 값(객체)를 출력 처리하기 위한 태그 --%>
	<%-- value 속성 : 출력될 값(객체)를 속성값으로 설정 --%>
	<%-- => value 속성값으로 EL를 사용해 Scope 속성값으로 값(객체)를 제공받아 설정 가능 --%>
	<p>정수값 = <c:out value="${num }"/></p>
	<hr>
	<%-- set 태그를 사용해 Scope 속성값으로 HTML 태그가 포함된 문자열을 저장 --%>
	<c:set var="html" value="<font size='7' color='red'>안녕하세요.</font>"/>
	<%-- EL만 사용하면 Scope 속성값이 HTML 태그가 포함될 문자열인 경우 HTML 태그로 실행되어 
	문자열 출력 처리 - 비정상적인 출력 가능 --%>
	<p>html = ${html }</p>
	
	<%-- out 태그를 사용하면 Scope 속성값이 HTML 태그가 포함될 문자열인 경우 문자열을 그대로 출력 처리 --%>
	<p>html = <c:out value="${html }"/></p>
</body>
</html>