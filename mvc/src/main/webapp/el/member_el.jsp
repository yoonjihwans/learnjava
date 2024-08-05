<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Request Scope 속성값을 객체로 반환받아 HTML 태그에 포함하여 응답하는 JSP 문서 - 응답 처리하는 웹프로그램 --%>
<%-- => 응답 처리하는 웹프로그램을 요청한 경우 Request Scope 속성값이 없으면 EL 미실행되므로
NullPointerException 미발생 --%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 사용</h1>
	<hr>
	<%-- <p>회원정보 = ${member }</p> --%>
	<p>회원의 이름 = ${member.name}</p>
	<%-- <p>회원의 자동차 = ${member.car}</p> --%>
	<p>회원의 자동차(모델명) = ${member.car.modelName}</p>
	<p>회원의 자동차(색상) = ${member.car.carColor}</p>
	<hr>
	<%-- [] 연산자로 "" 안에 필드명을 작성해 필드값을 제공받아 출력 가능 --%>
	<%--  "" 기호 대신 '' 기호 사용 가능 --%>
	<p>회원의 이름 = ${member["name"]}</p>
	<p>회원의 자동차(모델명) = ${member["car"]["modelName"]}</p>
	<p>회원의 자동차(색상) = ${member["car"]["carColor"]}</p>
	
	
</body>
</html>