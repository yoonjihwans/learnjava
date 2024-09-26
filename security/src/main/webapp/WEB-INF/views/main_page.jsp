<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- security 태그 라이브러리를 JSP 문서에 포함해 Spring Security 관련 태그 사용 가능 --%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
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
	<%-- <h3><a href="<c:url value="/login"/>">로그인</a></h3> --%>

	<%-- authorize 태그 : 권한을 비교하여 태그의 포함 여부를 설정하기 위한 태그 --%>
	<%-- access 속성 : 권한(Role)을 속성값으로 설정 - SpEL 사용 가능 --%>
	<%-- 비로그인 사용자인 경우 태그가 포함되도록 설정  --%>
	<sec:authorize access="isAnonymous()">
		<%-- <h3><a href="<c:url value="/login"/>">로그인</a></h3> --%>
		<h3><a href="<c:url value="/user_login"/>">로그인</a></h3>
	</sec:authorize>
	
	<%-- 로그인 사용자 정보(Authentication 객체의 Principal 필드값)를 Scope 속성값으로 
	저장하여 사용 가능 --%>
	<sec:authentication property="principal" var="loginUser"/>
	
	<%-- 로그인 사용자인 경우 태그가 포함되도록 설정  --%>
	<sec:authorize access="isAuthenticated()">
		<%-- authentication 태그 : 인증된 사용자 정보(UserDetails 객체)를 제공하기 위한 태그 --%>
		<%-- => 인증된 사용자만 사용 가능한 태그 --%>
		<%-- => JSP 문서에 Authentication 객체를 제공하기 위한 태그 --%>
		<%-- property 속성 : 인증된 사용자의 값이 저장된 필드의 이름을 속성값으로 설정 --%>
		<%-- <h3><sec:authentication property="principal.username"/>님, 환영합니다.</h3> --%>
		<%-- UserDetails 인터페이스를 상속받은 클래스로 인증된 사용자 정보가 저장되어 있는 경우
		클래스의 필드값을 제공받아 사용 가능 --%>
		<%-- <h3><sec:authentication property="principal.userid"/>님, 환영합니다.</h3> --%>
		<%-- <h3><sec:authentication property="principal.name"/>님, 환영합니다.</h3> --%>
		<h3>${loginUser.name}님, 환영합니다.</h3>
		
		<%-- 로그아웃 처리 기능을 제공하는 페이지는 반드시 form 태그를 사용해 요청 --%>
		<%-- => CSRF 토큰을 전달하기 위한 form 태그 사용 --%>
		<form action="<c:url value="/logout"/>" method="post">
			<%-- <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> --%>
			<%-- csrfInput 태그 : CSRF 토큰을 전달하기 위한 태그 --%>
			<sec:csrfInput/>			
			<button type="submit">로그아웃</button>	
		</form>
	</sec:authorize>
	<hr>
	<%-- embed 태그 : 내장 브라우저를 사용해 파일을 제공받아 출력하는 태그 --%>
	<%-- src 속성 : 내장 브라우저에 출력될 파일의 URL 주소를 속성값으로 설정  --%>
	<embed src="<c:url value="/resources/eclipse_cheatsheet.pdf"/>" width="1000" height="1500">
</body>
</html>









