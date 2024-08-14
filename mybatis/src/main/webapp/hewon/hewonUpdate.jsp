<%@page import="xyz.itwill.dto.MyHewon"%>
<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- MYHEWON 테이블에 저장된 행에서 아이디가 [xxx]인 회원의 이름을 [로빈훗]으로 변경 처리 --%>
<%
	/*
	//MyHewon 클래스의 기본 생성자를 사용하여 객체를 생성하여 저장
	// => MyHewon 객체의 필드에서 기본값(숫자형 : 0, 논리형 : false, 참조형 : null) 저장
	MyHewon hewon=new MyHewon();
	//필드에 대한 Setter 메소드를 호출하여 MyHewon 객체의 필드값 변경
	// => 아이디와 이름을 저장하는 필드만 변경 처리
	hewon.setId("xxx");
	hewon.setName("로빈훗");

	//아이디와 이름만 저장된 MyHewon 객체를 DAO 클래스의 메소드 매개변수에 전달하여 SQL 명령을
	//실행할 경우 이름을 제외한 나머지 컬럼값이 비정상적으로 변경
	MyHewonDAO.getDAO().updateHewon(hewon);
	*/
	
	MyHewon hewon=new MyHewon();
	hewon.setId("xxx");
	hewon.setName("로빈훗");
	//MYHEWON 테이블에 저장된 변경행의 컬럼값을 이용해 필드값 변경
	hewon.setPhone("010-2354-3241");
	hewon.setEmail("xxx@itwill.xyz");
	hewon.setScope(4);
	
	MyHewonDAO.getDAO().updateHewon(hewon);
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