<%@page import="xyz.itwill.dao.MemberDAO"%>
<%@page import="xyz.itwill.dto.MemberDTO"%>
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
	<p>아이디 = <%=loginMember.getMemberId() %></p>
	<p>이름 = <%=loginMember.getMemberName() %></p>
	<p>이메일 = <%=loginMember.getMemberEmail() %></p>
	<p>전화번호 = <%=loginMember.getMemberMobile() %></p>
	<p>주소 = [<%=loginMember.getMemberZipcode() %>]<%=loginMember.getMemberAddress1() %> 
		<%=loginMember.getMemberAddress2() %></p>
	<p>회원가입날짜 = <%=loginMember.getMemberRegisterDate() %></p>
	<p>마지막 로그인 날짜 = <%=loginMember.getMemberLastLogin() %></p>
</div>

<div id="link">
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=member&work=password_confirm&action=modify">[회원정보변경]</a>&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/index.jsp?workgroup=member&work=password_confirm&action=remove">[회원탈퇴]</a>
</div>









