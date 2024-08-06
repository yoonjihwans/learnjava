<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- taglib Directive : TLD 파일을 JSP 문서에 포함해 TLD 파일에 등록된 EL 함수 또는 커스텀 
태그를 JSP 문서에서 사용할 수 있도록 제공하는 지시어 --%>
<%-- prefix 속성 : EL 함수 또는 커스텀 태그를 사용하기 위한 접두사(NameSpace)를 속성값으로 설정 --%>
<%-- uri 속성 : TLD 파일을 구분하기 위한 식별자(url 엘리먼트값)을 속성값으로 설정 - 자동 완성 기능 사용 --%>    
<%@taglib prefix="elfun" uri="https://www.itwill.xyz/mvc/el"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL Function</h1>
	<hr>
	<p>EL 함수 : EL 표현식에서 호출하여 사용하기 위한 함수</p>
	<p>함수(Function) : 매개변수로 값을 전달받아 처리한 후 결과값을 반환하는 기능 제공</p>
	<p>EL 표현식에서 EL 함수를 호출하면 EL 함수 관련 클래스의 정적 메소드를 자동 호출하여
	명령을 실행하고 결과값을 반환받아 출력 처리</p>
	<p>EL 함수 관련 클래스를 작성하고 TLD 파일에 클래스에 작성된 정적 메소드를 EL 함수로 등록한 후
	JSP 문서에서 taglib 디렉티브를 사용해 TLD 파일을 제공받아 EL 표현식에서 EL 함수 호출</p>
	<hr>
	<p>${elfun:hello("홍길동") }</p>
	<p>${elfun:hello("임꺽정") }</p>
	<p>${elfun:hello("전우치") }</p>
	</body>
</html>