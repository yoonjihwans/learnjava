<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 태그 라이브러리 파일(TLD 파일)을 taglib Directive로 JSP 문서에 포함하면 커스텀 태그 사용 가능--%>
<%-- prefix 속성 : 커스텀 태그를 사용하기 위한 네임스페이스(NameSpace)를 속성값으로 설정 --%>
<!-- => 네임스페이스 : JSP 문서에서 태그 라이브러리 파일에 선언된 커스텀 태그를 구분하기 위한 이름 -->
<!-- uri 속성 : 태그 라이브러리 파일(TLD 파일)을 구분하기 위한 식별자를 속성값으로 설정 -->    
<%@taglib prefix="custom" uri="http://www.itwill.xyz/custom"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>taglib Directive</h1>
	<hr>
	<p>태그 라이브러리 파일(TLD 파일)을 제공받아 JSP 문서에서 커스텀 태그를 사용할 수 있도록
	제공하는 지시어</p>
	<p>태그 라이브러리 파일 : 태그 클래스를 커스텀 태그로 등록하기 위한 XML 파일</p>
	<p>커스텀 태그 : 태그 클래스를 사용해서 만들어진 Java 태그</p>
	<hr>
	<custom:display/>
	<custom:display/>
	<custom:display/>
</body>
</html>