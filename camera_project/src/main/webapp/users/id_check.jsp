<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");

	if(id == null) {
		response.sendRedirect(request.getContextPath()+"/error/error_400.jsp");
		return;
	}	
	
	UsersDTO users=UsersDAO.getDAO().selectUsersById(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
div {
	text-align: center;
	margin: 10px;
}

.id {
	font-weight: bold;
	color: red;
}
</style>
</head>
<body>
	<% if(users == null) { %>
		<div>입력된 <span class="id">[<%=id %>]</span>는 사용 가능한 아이디입니다.</div>
		<div>
			<button type="button" onclick="selectId();">아이디 사용</button>
		</div>
		
		<script type="text/javascript">
		function selectId() {			
			opener.join.id.value="<%=id%>";
			opener.join.idCheckResult.value="1";
			window.close();
		}
		</script>
	<% } else { %>
		<div>입력된 <span class="id">[<%=id %>]</span>는 이미 사용중인 아이디입니다.<br>
		새로운 아이디를 입력하고 [확인] 버튼을 눌러주세요.</div>
		
		<div>			
			<form name="checkForm">
				<input type="text" name="id">
				<button type="button" onclick="idCheck();">확인</button>
			</form>
		</div>
		<div id="message" style="color: red;"></div>
		
		<script type="text/javascript">
		function idCheck() {
			var id=checkForm.id.value;
			if(id == "") {
				document.getElementById("message").innerHTML="아이디를 입력해 주세요.";
				return;
			}
			
			var idReg=/^[a-z0-9]{4,16}$/g;
			if(!idReg.test(id)) {
				document.getElementById("message").innerHTML="아이디를 형식에 맞게 입력해 주세요.";
				return;
			}
			
			checkForm.submit();
		}
		</script>
	<% } %>
</body>
</html>