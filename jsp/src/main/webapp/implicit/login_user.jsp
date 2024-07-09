<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그인 사용자 정보를 HTML 문서에 포함하여 응답하는 JSP 문서 --%>
<%-- => 비로그인 사용자가 JSP 문서를 요청한 경우 [login_form.jsp] 문서를 요청할 수 있는 
URL 주소를 전달하여 응답 --%>
<%-- => [로그아웃] 태그를 클릭한 경우 [logout_action.jsp] 문서를 요청하여 페이지 이동 --%>
<%-- => [메인으로] 태그를 클릭한 경우 [login_form.jsp] 문서를 요청하여 페이지 이동 --%>
<%
	//세션에 저장된 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
	String loginId=(String)session.getAttribute("loginId");

	//반환받은 속성값이 없는 경우 - 비로그인 사용자이므로 JSP 문서를 비정상적인 요청
	if(loginId == null) {
		session.setAttribute("msg", "로그인 사용자만 요청 가능한 페이지 입니다.");
		response.sendRedirect("login_form.jsp");
		return;
	}
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>내정보</h1>
	<hr>
	<a href="logout_action.jsp">[로그아웃]</a>
	<a href="login_form.jsp">[메인으로]</a>
	<hr>
	<p>아이디 = <%=loginId %>&nbsp;&nbsp;</p>
	<p>로그인 사용자만 확인 가능한 정보입니다.</p>
	<p>로그인 사용자만 확인 가능한 정보입니다.</p>
	<p>로그인 사용자만 확인 가능한 정보입니다.</p>
	<p>로그인 사용자만 확인 가능한 정보입니다.</p>
	<p>로그인 사용자만 확인 가능한 정보입니다.</p>
	<hr>
	<%-- 상대경로로 웹자원을 설정하여 사용 --%>
	<%-- => 상대경로 : 요청 JSP 문서의 폴더를 기준으로 웹자원의 경로를 표현 --%>
	<img alt="코알라" src="../images/Koala.jpg" width="200">
	
	<%-- 절대경로로 웹자원을 설정하여 사용 - 권장 --%>
	<%-- => 절대경로 : 최상위 폴더(서버 디렉토리)를 기준으로 웹자원의 경로를 표현 --%>
	<%-- 문제점) 컨텍스트 최상위 폴더의 이름이 변경될 경우 웹자원의 경로가 변경되어 404 에러코드 발생 가능 --%>
	<img alt="코알라" src="/jsp/images/Koala.jpg" width="200">
	
	<%-- 해결법)컨텍스트 최상위 폴더의 경로를 반환하는 메소드를 호출하여 URL 주소를 작성 --%>
	<%-- request.getContextPath() : 컨텍스트 최상위 폴더의 경로를 반환하는 메소드 --%>
	<img alt="코알라" src="<%=request.getContextPath() %>/images/Koala.jpg" width="200">
</body>
</html>