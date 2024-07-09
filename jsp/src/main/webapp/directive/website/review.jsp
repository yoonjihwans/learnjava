<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<link href="style.css" type="text/css" rel="stylesheet">
</head>
<body>
	<%-- 머릿부 : 로고, 메뉴 등 --%>
	<div id="header">
		<%@include file="header.jspf"%>
	</div>
	
	<%-- 몸체부 : 요청에 대한 실행 결과 --%>
	<div id="content">
		<h2>제품후기 페이지 - 제품후기 목록 출력</h2>
	</div>
	
	<%-- 꼬릿부 : 저작권, 약관, 개인정보 보호정책 등 --%>
	<div id="footer">
		<%@include file="footer.jspf" %>
	</div>
</body>
</html>