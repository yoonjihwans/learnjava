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
	<h1>메인페이지</h1>
	<hr>
	<h3><a href="<c:url value="/guest/"/>">Guest</a></h3>
	<h3><a href="<c:url value="/user/"/>">User</a></h3>
	<h3><a href="<c:url value="/manager/"/>">Manager</a></h3>
	<h3><a href="<c:url value="/admin/"/>">Admin</a></h3>
	<%-- [/login] 페이지에 대한 요청 처리 메소드 및 뷰를 생성하지 않아도 Spring Security에서
	로그인 페이지를 제공받아 사용 가능 --%>
	<h3><a href="<c:url value="/login"/>">로그인</a></h3>
</body>
</html>