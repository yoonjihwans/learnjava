<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Functions 태그 라이브러리의 EL 함수를 JSP 문서에서 사용할 수 있도록 설정 - 접두사로 [fn] 사용 --%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Functions - 문자열 관련 EL 함수</h1>
	<hr>
	<c:set var="phone" value="010-1234-5678"/>
	<p>전화번호 = ${phone }</p>
	<hr>
	
	<%-- split 함수 : 매개변수로 전달받은 문자열을 구분자로 분리하여 배열로 반환하는 함수 --%>
	<c:set var="array" value="${fn:split(phone, '-') }"/>
	<c:forEach var="num" items="${array }">
		<div>${num }</div>
	</c:forEach>
	<hr>
	<%-- substring 함수 : 매개변수로 전달받은 문자열을 시작첨자(첨자 위치의 문자 포함)와 
	종료첨자(첨자 위치의 문자 미포함)로 분리하여 반환하는 함수 --%>
	<div>${fn:substring(phone, 0, 3) }</div>
	<div>${fn:substring(phone, 4, 8) }</div>
	<div>${fn:substring(phone, 9, 13) }</div>
	<hr>
	
	<c:set var="content" value="안녕하세요\n반갑습니다."/>
	<div>${content }</div>
	<%-- split 함수 : 매개변수로 전달받은 문자열에서 검색 문자열을 찾아 치환 문자열로 변경하여
	반환하는 함수 --%>
	<div>${fn:replace(content, '\\n', '<br>') }</div>
	<hr>
	
	<c:set var="html" value="<font size='7' color='red'>안녕하세요.</font>"/>
	<%-- EL 표현식으로 제공받은 문자열에 HTML 태그가 포함된 경우 HTML 태그가 태그로 동작되어
	문자열 출력 - XSS 공격에 취약 --%>
	<div>${html }</div>
	
	<%-- out 태그를 사용하면 HTML 태그가 포함된 문자열을 그대로 출력 처리 - XSS 공격에 대한 방어 --%>
	<div><c:out value="${html }"/></div>
	
	<%-- escapeXml 함수 : 매개변수로 전달받은 문자열에서 HTML 태그 관련 문자를 회피문자로
	변환하여 반환하는 함수 - XSS 공격에 대한 방어 --%>
	<div>${fn:escapeXml(html) }</div>
</body>
</html>