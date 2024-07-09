<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>메인 페이지</h1>
	<hr>
	<%-- 
	<a href="<%=request.getContextPath()%>/action/forward/login.jsp">로그인</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/join.jsp">회원가입</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/cart.jsp">장바구니</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/review.jsp">제품후기</a>&nbsp;&nbsp;
	--%>

	<a href="<%=request.getContextPath()%>/action/forward/controller.jsp?pageName=login">로그인</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/controller.jsp?pageName=join">회원가입</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/controller.jsp?pageName=cart">장바구니</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/action/forward/controller.jsp?pageName=review">제품후기</a>&nbsp;&nbsp;
</body>
</html>