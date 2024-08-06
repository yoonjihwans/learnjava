<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="simple" uri="https://www.itwill.xyz/mvc/custom"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Custom Tag - NoAttribute And NoBody</h1>
	<hr>
	<%-- 커스텀 태그(Custom Tag) : JSP 문서에서 사용하기 위해 개발자가 만든 태그 --%>
	<%-- => 태그 클래스를 작성하고 TLD 파일에 태그 클래스를 커스텀 태그로 등록한 후 JSP 문서에서
	taglib 디렉티브를 사용해 TLD 파일을 제공받아 JSP 문서에서 커스텀 태그 사용 --%>
	<%-- => JSP 문서에서 커스텀 태그를 사용하는 태그 클래스를 객체로 생성하여 태그 관련
	메소드를 자동 호출해 명령 실행 - WAS 프로그램에 의해 관리 --%>
	<simple:hello></simple:hello>
	<%-- 태그내용이 없는 커스텀 태그는 시작태그와 종료태그를 동시에 구현 가능 --%>
	<simple:hello/>
	<simple:hello/>
</body>
</html>