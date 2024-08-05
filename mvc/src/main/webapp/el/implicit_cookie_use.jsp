<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 클라이언트로부터 전달받은 쿠키값을 HTML 태그에 포함하여 응답하는 JSP 문서 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장객체 - Cookie</h1>
	<hr>
	<h2>EL 미사용</h2>
	<%
		//클라이언트로부터 전달받은 모든 쿠키를 배열로 반환받아 저장
		Cookie[] cookies=request.getCookies();
		String userName="";//쿠키값을 저장하기 위한 변수
		//배열에 저장된 요소값(Cookie 객체)를 차례대로 제공받아 일괄처리하기 위한 반복문
		for(Cookie cookie : cookies) {
			//Cookie 객체에 저장된 쿠키명을 반환받아 문자열로 비교하여 같은 경우
			if(cookie.getName().equals("userName")) {
				//Cookie 객체에 저장된 쿠키값을 반환받아 변수에 저장
				userName=cookie.getValue();
			}
		}
	%>
	<p>쿠키에 저장된 이름 = <%=userName %></p>
	<hr>
	<%-- EL 표현식에서 cookie 내장객체 사용하면 클라이언트에서 전달받은 쿠키값을 제공받아 출력 처리 --%>
	<%-- => cookie 내장객체는 Map 객체로 맵키 대신 쿠키명을 사용해 Cookie 객체를 제공받아 
	value 이름을 사용해 쿠키값을 출력 처리 --%>
	<%-- <p>쿠키에 저장된 이름 = ${cookie.userName}</p> --%>	
	<p>쿠키에 저장된 이름 = ${cookie.userName.value}</p>	
</body>
</html>







