<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
</head>
<body>
	<h1>에러페이지</h1>
	<hr>
	<h3 style="color: red;">다른 컴퓨터에서 로그인 하였습니다. 확인해 보세요.</h3>
	<hr>
	<h3><a href="<c:url value="/"/>">메인페이지</a></h3>
</body>
</html>