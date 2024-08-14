<%@page import="xyz.itwill.dto.MyHewon"%>
<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- MYHEWON 테이블에 저장된 행에서 아이디가 [xxx]인 회원의 이름을 [홍경래]으로 변경 처리 --%>
<%
	MyHewon hewon=new MyHewon();
	hewon.setId("xxx");
	hewon.setName("홍경래");

	MyHewonDAO.getDAO().updateDynamicHewon(hewon);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
</head>
<body>
	<h1>회원변경</h1>
	<hr>
	<h3>회원정보가 성공적으로 변경 되었습니다.</h3>
</body>
</html>