<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 내장객체에 저장된 속성값을 반환받아 HTML 문서에 포함하여 응답하는 JSP 문서 --%>
<%
	//request 내장객체에 저장된 속성값이 없는 경우 에러코드를 클라이언트에게 전달하여 응답
	if(request.getAttribute("hewon") == null) {//JSP 문서를 비정상적으로 요청한 경우
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
	<h1>회원정보확인</h1>
	<%-- useBean 태그를 사용해 request 내장객체에 저장된 속성값을 객체로 반환받아 JSP 문서에 제공 --%>
	<jsp:useBean id="hewon" class="xyz.itwill.bean.HewonBean" scope="request"/>
	<%-- HewonBean hewon=(HewonBean)request.getAttribute("hewon"); --%>
	<hr>

	<%-- getProperty 태그 : useBean 태그로 제공받은 객체의 필드값을 반환하는 태그 --%>
	<%-- => useBean 태그의 종속된 태그 --%>
	<%-- 형식) <jsp:getProperty name="식별자" property="필드명"></jsp:getProperty> --%>
	<%-- => 객체로 Getter 메소드를 호출하여 필드값을 반환하는 기능과 동일 --%>
	<%-- name 속성 : useBean 태그로 제공받은 객체의 식별자(id 속성값)를 속성값으로 설정 --%>
	<%-- property 속성 : 필드값을 반환받기 위한 필드명을 속성값으로 설정 --%>
	<%-- => 속성값으로 설정된 필드의 Getter 메소드를 호출하여 필드값 반환 - Getter 메소드가 없는 경우 에러 발생 --%>		
	<p>이름 = <jsp:getProperty property="name" name="hewon"/></p>
	<p>전화번호 = <jsp:getProperty property="phone" name="hewon"/></p>
	<p>주소 = <jsp:getProperty property="address" name="hewon"/></p>
	<%--
		<p>이름 = <%=hewon.getName() %></p>
		<p>전화번호 = <%=hewon.getPhone() %></p>
		<p>주소 = <%=hewon.getAddress() %></p>	
	--%>
</body>
</html>