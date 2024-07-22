<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 로그인 사용자의 정보(회원정보)를 HTML 문서에 포함하여 응답하는 JSP 문서 --%>
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%-- => [회원정보변경] 태그를 클릭한 경우 [/member/password_confirm.jsp] 문서를 요청하여 
페이지 이동 - 페이지 이동 관련 값 전달 --%>
<%-- => [회원탈퇴] 태그를 클릭한 경우 [/member/password_confirm.jsp] 문서를 요청하여 페이지 
이동 - 페이지 이동 관련 값 전달 --%>
<%--
	//session 내장객체에서 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	//비로그인 사용자가 JSP 문서를 요청한 경우에 대한 응답 처리 - 비정상적인 요청
	if(loginMember == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;	
	}
--%>
<%@include file="/security/login_check.jspf" %> 
<style type="text/css">
#account-wrap{
width: 700px;
height:450px;

margin: 0 auto;
}

#account-wrap-middle{

width: 700px;
height:350px;

margin-top: 50px;}


#detail1 {
	font-size:17px;
	width: 500px;
	margin: 0 auto;
	text-align: left;
	background-color: #D4B996;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	color: white;
	font-family: Arial, sans-serif;
	line-height: 1.5; /* Adding some line height for better spacing */
}
#detail2 {
font-size:12px;
	width: 500px;
	margin: 0 auto;
	text-align: left;
	background-color: #C9AD8D;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	color: white;
	font-family: Arial, sans-serif;
	line-height: 1.5; /* Adding some line height for better spacing */
}

#detail p:first-child {
	font-weight: bold; /* Making the first paragraph (ID) bold */
	margin-bottom: 10px; /* Adding margin bottom for separation */
}

#detail .date-info {
	font-size:5px;
	margin-top: 10px; /* Adding margin top to separate date information */
}

#link {
	font-size: 1.2em; /* Increasing font size */
	margin-top: 20px;
	text-align: center;
}

#link a {
	text-decoration: none;
	color: white;
	background-color: #C9AD8D;
	padding: 8px 15px; /* Increasing padding for better click area */
	border-radius: 5px;
	transition: background-color 0.3s ease;
}

#link a:hover {
	background-color: black;
}
</style>

<div id="account-wrap">
<div id="account-wrap-middle">
<h1 style="text-align: center; margin-bottom: 20px;">내정보</h1>
<div id="detail1">
	<p><strong>아이디 =</strong> <%=loginUsers.getUsersId() %></p>
</div>
<div id="detail2">	
	<p><strong>이름 =</strong> <%=loginUsers.getUsersName() %></p>
	<p><strong>이메일 =</strong> <%=loginUsers.getUsersEmail() %></p>
	<p><strong>전화번호 =</strong> <%=loginUsers.getUsersPhone() %></p>
	<p><strong>주소 =</strong> [<%=loginUsers.getUsersZipcode() %>]<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=loginUsers.getUsersAddress1() %> <br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=loginUsers.getUsersAddress2() %></p>
</div>
<div id="link">
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=password_confirm&action=modify">[회원정보변경]</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=password_confirm&action=remove">[회원탈퇴]</a>
</div>
	<div class="date-info">
    <p><strong>회원가입날짜 =</strong> <%=loginUsers.getUsersSigndate().substring(0, 10) %></p>
    <% if(loginUsers.getUsersLastLogin() == null) { %>
        <p style="display: none;"><strong>마지막 로그인 날짜 =</strong></p>
    <% } else { %>
        <p><strong>마지막 로그인 날짜 =</strong> <%=loginUsers.getUsersLastLogin() %></p>
    <% } %>
</div>
 </div>
</div>



<%-- <style type="text/css">
#detail {
	width: 500px;
	margin: 0 auto;
	text-align: left;
}
#link {
	font-size: 1.1em;
}
#link a:hover {
	color: white;
	background: black;
}
</style>
<h1>내정보</h1>
<div id="detail">
	<p>아이디 = <%=loginUsers.getUsersId() %></p>
	<p>이름 = <%=loginUsers.getUsersName() %></p>
	<p>이메일 = <%=loginUsers.getUsersEmail() %></p>
	<p>전화번호 = <%=loginUsers.getUsersPhone() %></p>
	<p>주소 = [<%=loginUsers.getUsersZipcode() %>]<%=loginUsers.getUsersAddress1() %> 
		<%=loginUsers.getUsersAddress2() %></p>
	<p>회원가입날짜 = <%=loginUsers.getUsersSigndate() %></p>
	<p>마지막 로그인 날짜 = <%=loginUsers.getUsersLastLogin() %></p>
</div>
<div id="link">
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=password_confirm&action=modify">[회원정보변경]</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=password_confirm&action=remove">[회원탈퇴]</a>
</div>

 --%>






