<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - Map</h1>
	<hr>
	<p>과일-1 = ${fruitsMap.one }</p>
	<p>과일-2 = ${fruitsMap.two }</p>
	<p>과일-3 = ${fruitsMap.three }</p>
	<hr>
	<p>좋아하는 과일 = ${fruitsMap.two }</p>
	<p>좋아하는 과일 = ${fruitsMap["two"] }</p>
	<hr>
	<p>선택 = ${choice }</p>
	<hr>
	<%-- Scope 속성값(Map 객체)의 맵키로 제공되는 맵값이 없는 EL 미실행  --%>
	<%-- <p>좋아하는 과일 = ${fruitsMap.choice }</p> --%>
	<%-- EL 표현식에서 [] 연산자에 Scope 속성값(문자열)을 제공받아 맵키로 사용해 맵값을 제공받아 출력 처리 가능 --%>
	<p>좋아하는 과일 = ${fruitsMap[choice] }</p>
</body>
</html>