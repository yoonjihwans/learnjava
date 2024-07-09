<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그인 사용자와 비로그인 사용자를 구분하여 다른 처리결과로 응답하는 JSP 문서 --%>

<%-- 비로그인 사용자인 경우 사용자로부터 로그인 처리에 필요한 인증정보(아이디와 비밀번호)를 
입력받기 위한 HTML 문서로 응답 처리 --%>
<%-- => [로그인] 태그를 클릭한 경우 [login_action.jsp] 문서를 요청하여 페이지 이동 - 입력값(인증정보) 전달 --%>

<%-- 로그인 사용자인 경우 환영메세지를 출력하는 HTML 문서로 응답 처리 --%>
<%-- => [로그아웃] 태그를 클릭한 경우 [logout_action.jsp] 문서를 요청하여 페이지 이동 --%>
<%-- => [내정보] 태그를 클릭한 경우 [login_user.jsp] 문서를 요청하여 페이지 이동 --%>

<%
	//세션에 저장된 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
	String loginId=(String)session.getAttribute("loginId");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<% if(loginId == null) {//JSP 문서를 요청한 사용자가 비로그인 사용자인 경우 %>

		<%--
			//request.getParameter(String name) : 매개변수로 전달받은 이름의 전달값을 반환하는 메소드
			// => 매개변수로 전달받은 이름의 전달값이 없는 경우 null 반환
			String msg=request.getParameter("msg");
			if(msg == null) {//전달값이 없는 경우
				//표현식(Expression)으로 인해 [null]이 문자열로 변환되어 출력되는 것을 방지
				msg="";
			}
		--%>
		<%
			//session.getAttribute(String attributeName) : JSP 문서에 바인딩된 클라이언트의 세션
			//(HttpSession 객체)에 저장된 속성값(객체)를 매개변수로 전달받은 이름으로 찾아 반환하는 메소드
			// => 세션에 저장된 속성값을 Object 객체로 반환하므로 명시적 객체 형변환을 반드시 사용 
			// => 매개변수로 전달받은 이름의 속성값이 없는 경우 [null] 반환
			String msg=(String)session.getAttribute("msg");
			if(msg == null) {//세션에 저장된 속성값이 없는 경우
				msg="";
			} else {
				//session.removeAttribute(String attributeName) : JSP 문서에 바인딩된 클라이언트의 세션
				//(HttpSession 객체)에 저장된 속성값(객체)를 매개변수로 전달받은 이름으로 찾아 삭제하는 메소드
				// => 현재 JSP 문서외에 다른 JSP 문서에서 속성값(에러메시지)을 사용하지 못하도록 속성값 삭제 
				session.removeAttribute("msg");
			}
			
			String id=(String)session.getAttribute("id");
			if(id == null) {
				id="";
			} else {
				session.removeAttribute("id");
			}
		%>
		<h1>로그인</h1>
		<hr>
		<form action="login_action.jsp" method="post" name="loginForm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="<%=id%>"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">로그인</button></td>
			</tr>
		</table>	
		</form>
		<p id="message" style="color: red;"><%=msg %></p>
		
		<script type="text/javascript">
		loginForm.id.focus();
		
		loginForm.onsubmit=function() {
			if(loginForm.id.value == "") {
				document.getElementById("message").innerHTML="아이디를 입력해 주세요.";
				loginForm.id.focus();
				return false;
			}
			
			if(loginForm.passwd.value == "") {
				document.getElementById("message").innerHTML="비밀번호를 입력해 주세요.";
				loginForm.passwd.focus();
				return false;
			}
		}
		</script>
	<% } else {//JSP 문서를 요청한 사용자가 로그인 사용자인 경우 %>
		<h1>메인페이지</h1>
		<hr>
		<p>
			<%=loginId %>님, 환영합니다.&nbsp;&nbsp;
			<a href="logout_action.jsp">[로그아웃]</a>
			<a href="login_user.jsp">[내정보]</a>
		</p>
	<% } %>	
</body>
</html>