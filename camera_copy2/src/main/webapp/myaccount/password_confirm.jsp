<%@page import="java.text.NumberFormat.Style"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
		#password-wrap{
		width: 100%;
		height: 250px;

		}	
		
		#password-wrap-middle{
		width: 450px;
		height: 250px;
		margin: 0 auto;
	
		}
		
		#last-wrap{
		width: 450px;
		height: 100px;
		margin-top: 60px;
		}
		
		p{font-size:20px;}
		}	
		
		
   </style>
<%-- 사용자로부터 비밀번호를 입력받기 위한 JSP 문서 --%>
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%-- => [입력완료] 태그를 클릭한 경우 전달값에 따라 다른 JSP 문서를 요청하여 페이지 이동 - 입력값(비밀번호) 전달 --%>

<%@include file="/security/login_check.jspf" %>    
<%

	//전달값을 반환받아 저장
	String action=request.getParameter("action");

	//전달값이 없거나 비정상적인 값이 전달된 경우에 대한 응답 처리 - 비정상적인 요청
	if(action == null || !action.equals("modify") && !action.equals("remove")) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;	
	}
	// member_modify 만든 후에 추가된 코드 
	String message=(String)session.getAttribute("message");
	if(message == null) {
		message="";
	} else {
		session.removeAttribute("message");
	}
%>
<div id="password-wrap">
<div id="password-wrap-middle">
<div id="last-wrap">
<% if(action.equals("modify")) { %>
	<p>회원정보변경을 위해 비밀번호를 입력해 주세요.</p>
<% } else { %>
	<p>회원탈퇴를 위해 비밀번호를 입력해 주세요.</p>
<% } %>


<form method="post" id="passwordForm">
	<input type="password" name="passwd" id="passwd">
	<button type="button" id="submitBtn">입력완료</button>
</form>
<p id="message" style="color: red;"><%=message %></p><%-- <%=message %> member_modify 만든 후에 추가된 코드  --%>
  </div>
  </div>
</div>
<script type="text/javascript">
$("#passwd").focus();

$("#submitBtn").click(function() {
	if($("#passwd").val() == "") {
		$("#message").text("비밀번호를 입력해 주세요.");
		return;
	}
	
	//전달값에 따라 form 태그의 action 속성값을 다르게 설정
	<% if(action.equals("modify")) { %>
		$("#passwordForm").attr("action", "<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=myacct_modify")
	<% } else { %>
		$("#passwordForm").attr("action", "<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=myacct_remove_action")
	<% } %>
	
	$("#passwordForm").submit();//form 태그 실행
});
</script>



