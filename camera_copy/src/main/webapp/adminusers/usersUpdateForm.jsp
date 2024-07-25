<%@page import="xyz.itwill.dao.AdminUsersDAO"%>
<%@page import="xyz.itwill.dto.AdminUsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%


	//비정상적인 요청에 대한 응답 처리
	if(request.getParameter("no") == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	//전달값을 반환받아 저장
	int no=Integer.parseInt(request.getParameter("no"));
	
	//번호를 전달받아 회원 테이블에 저장된 하나의 행을 검색하여 검색된 회원정보(UsersDTO 
	//객체)를 반환하는 UsersDAO 클래스의 메소드 호출
	AdminUsersDTO users=AdminUsersDAO.getDAO().selectUsers(no);
	
	//비정상적인 요청에 대한 응답 처리
	if(users == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>
<style type="text/css">
h1 {
	margin: 0 auto;
	width: 300px; 
	text-align: center; 
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse; 	
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}

.title { width: 100px; }
.input { width: 200px; }
</style>
</head>
<body>
	<h1>User Status</h1>
	<hr>
	<form name="usersForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminusers&work=usersUpdate" method="post">
	<table>
		<tr>
			<th class="title">Users No</th>
			<td class="input">
				<input type="text" name="no" value="<%=users.getUsersNo() %>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">Users ID</th>
			<td class="input">
				<input type="text" name="id" value="<%=users.getUsersId() %>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">User PW</th>
			<td class="input">
				<input type="text" name="pw" value="<%=users.getUsersPw() %>">
			</td>
		</tr>
		<tr>
			<th class="title">Users Status</th>
			<td class="input">
				<input type="text" name="status" value="<%=users.getUsersStatus() %>">
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<button type="submit">회원변경</button> 
				<button type="reset">초기화</button> 
				<button type="button" id="listBtn">회원목록</button> 
			</td>
		</tr>
	</table>
	</form>
	<p align="center" style="color: red;"></p>
	
	<script type="text/javascript">
	usersForm.no.focus();

	
	document.getElementById("listBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminusers&work=users";	
	}
	</script>
</body>
</html>