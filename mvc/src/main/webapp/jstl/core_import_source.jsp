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
	<h1>EL - URL 관리 태그</h1>
	<hr>
	<p>core_import_source.jsp 문서의 실행 결과입니다.</p>
	
	<%-- import 태그 : 웹프로그램을 요청하여 실행결과를 응답받아 포함하기 위한 태그 --%>
	<%-- => JSP의 include 태그와 유사한 기능 제공 --%>
	<%-- url 속성 : 요청하여 응답받을 웹프로그램의 URL 주소를 속성값으로 설정 --%>
	<%-- <c:import url="core_import_target.jsp"/> --%>
	
	<%-- JSP의 include 태그는 현재 접속 서버의 웹프로그램으로 스레드를 이동하여 실행결과를   
	응답받아 포함하지만 import 태그는 현재 접속 서버외에 다른 서버의 웹프로그램을 요청하여 
	실행결과를 응답받아 포함 가능 --%>
	<%-- 웹프로그램을 요청하여 응답받은 결과를 Scope 속성값으로 저장 --%>
	<%-- <c:import url="https://www.yonhapnewstv.co.kr/browse/feed/" />  --%>

	<%-- import 태그로 웹프로그램을 요청하여 응답받은 결과를 Scope 속성값으로 저장 가능 --%>
	<%-- 
	<c:import url="https://www.yonhapnewstv.co.kr/browse/feed/" var="xml"/>
	<p>${xml }</p>
	--%>
	
	<%-- import 태그의 하위태그로 param 태그를 사용해 요청 웹프로그램에게 값 전달 --%>
	<%-- => import 태그에 param 태그외 다른 코드가 존재할 경우 에러 발생 - JSP 주석 제외 --%>
	<c:import url="core_import_target.jsp">
		<%-- param 태그 : 요청 웹프로그램에 값을 전달하기 위한 태그 --%>
		<%-- => URL 관리 태그의 하위태그로만 사용 가능 --%>
		<c:param name="name" value="홍길동"/>
	</c:import>
</body>
</html>