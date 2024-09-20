<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
</head>
<body>
	<h1>입력페이지</h1>
	<hr>
	<form method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="name">
		<%-- CSRF 공격을 방어하기 위해 Spring Security에서 발급된 CSRF Token을 hidden으로 전달 --%>
		<%-- => 서버에 전달된 요청이 실제 서버에서 허용된 요청인지를 확인하기 위해 CSRF Token 발급 --%>
		<%-- => 뷰페이지를 생성할 때마다 Spring Security는 랜덤으로 토큰을 발행하여 세션에 저장하고
		사용자가 서버의 페이지를 요청할 때 hidden 타입으로 토큰을 전달해 세션에 저장된 토큰과
		비교하여 클라이언트가 같은지를 확인 --%>
		<%-- => 일치 여부를 확인한 토큰은 삭제하고 새로운 뷰에 대한 토큰을 다시 발생 --%>
		<%-- CSRF(Cross-Site Request Forgery) 공격 : 사이트의 요청을 위조하는 공격 방법 --%>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }">
		<button type="submit">제출</button>
	</form>
</body>
</html>