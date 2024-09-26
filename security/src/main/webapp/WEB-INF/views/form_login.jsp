<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>로그인</h1>
	<hr>
	<form action="<c:url value="/user_login"/>" method="post" id="loginForm">
	<table>
		<tr>
			<td>아이디</td>
			<%-- 인증 처리 페이지에 아이디를 전달하는 name 속성값은 반드시 [username]으로 설정 --%>
			<%-- <td><input type="text" name="username" id="userid"></td> --%>
			<td><input type="text" name="userid" id="userid" value="${userid }"></td>
			<c:remove var="userid"/>
		</tr>
		<tr>
			<td>비밀번호</td>
			<%-- 인증 처리 페이지에 비밀번호를 전달하는 name 속성값은 반드시 [password]으로 설정 --%>
			<%-- <td><input type="password" name="password" id="passwd"></td> --%>
			<td><input type="password" name="passwd" id="passwd"></td>
		</tr>
		<tr>
			<td colspan="2"><button type="submit">로그인</button></td>
		</tr>
	</table>
	
	<%-- 브라우저가 종료돼도 로그인이 유지되는 기능을 제공하기 위한 태그 --%>
	<%-- => input 태그의 type 속성값을 [checkbox]로 설정하고 name 속성값을 [remember-me]로 설정 --%>
	<input type="checkbox" name="remember-me">자동 로그인
	
	<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	</form>
	<%-- SPRING_SECURITY_LAST_EXCEPTION : Spring Security에 의해 마지막에 발생된 예외(Exception 객체)가
	Session Scope 속성값으로 저장된 속성명 --%>
	<%-- => Spring Security에 의해 예외가 발생된 경우 태그를 포함하여 출력 처리 --%>
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
		<hr>
		<%-- <h3 style="color: red;">아이디 또는 비밀번호가 맞지 않습니다.</h3> --%>
		<h3 style="color: red;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</h3>
		<%-- 예외가 저장된 Session Scope 속성값 제거 --%>
		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION"/>
	</c:if>
	
	<hr>
	<a href="<c:url value="/kakao/login"/>">
		<img alt="카카오 로그인" src="<c:url value="/resources/images/kakao_login_medium_narrow.png"/>">
	</a>
	
	<hr>
	<h3><a href="<c:url value="/"/>">메인페이지</a></h3>
	
	<script type="text/javascript">
	$("#loginForm").submit(function() {
		if($("#userid").val() == "") {
			alert("아이디를 입력해 주세요.");
			return false;
		}		
		
		if($("#passwd").val() == "") {
			alert("비밀번호를 입력해 주세요.");
			return false;
		}
	});
	</script>
</body>
</html>





