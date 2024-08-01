<%@page import="xyz.itwill.dao.AdminQnaDAO"%>
<%@page import="xyz.itwill.dto.AdminQnaDTO"%>
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
	

	AdminQnaDTO qna=AdminQnaDAO.getDAO().selectQnaNo(no);
	
	//비정상적인 요청에 대한 응답 처리
	if(qna == null) {
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
	<h1>Qna Reply</h1>
	<hr>
	<%-- <form name="qnaForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminqna&work=qnaReply" method="post" --%>
	<form name="qnaForm" action="<%=request.getContextPath()%>/adminqna/qnaReply.jsp" method="post"
	id="qnaForm" >
	<table>
		<tr>
			<th class="title">Qna No</th>
			<td class="input">
				<input type="text" name="no" value="<%=qna.getQnaNo() %>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">QnaUsersNo</th>
			<td class="input">
				<input type="text" name="usersNo"  id="usersNo" value="<%=qna.getQnaUsersno() %>" readonly="readonly" >
			</td>
		</tr>
		<tr>
			<th class="title">UserName</th>
			<td class="input">
				<input type="text" name="name"  id="name" value="<%=qna.getUsersName() %>"  >
			</td>
		</tr>
		<tr>
			<th class="title">UserEmail</th>
			<td class="input">
				<input type="text" name="email"  id="email" value="<%=qna.getUsersEmail() %>" >
			</td>
		</tr>
		<tr>
			<th class="title">Qna Title</th>
			<td class="input">
				<input type="text" name="title"  id="title" >
			</td>
		</tr>
		<tr>
			<th class="title">Qna content</th>
			<td class="input">
				<%-- <input type="text" name="content" value="<%=notice.getNoticeContent() %>"> --%>
				<textarea rows="7" cols="60" name="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
			<th class="title">Qna status</th>
			<td class="input">
				<input type="text" name="status" value="<%=qna.getQnaStatus() %>">
			</td>
		</tr>
	
		<tr>
			<td colspan="2">
				<button type="submit">email reply</button> 
				<button type="reset">reset</button> 
				<button type="button" id="listBtn">back to list</button> 
			</td>
		</tr>
	</table>
	</form>
	<p align="center" style="color: red;"></p>
	
	<script type="text/javascript">
	noticeForm.no.focus();

	
	document.getElementById("listBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminqna&work=qna";	
	}
	
	/*버튼을 눌렀을때 글이 아무것도 없으면 나와주는 메세지와 채워야지만 넘어가는것을 나타내는 function */
	$("#qnaForm").submit(function() {
		if($("#title").val() == "") {
			$("#message").text("제목을 입력해 주세요.");
			$("#title").focus();
			return false;
		}
		
		if($("#content").val() == "") {
			$("#message").text("내용을 입력해 주세요.");
			$("#content").focus();
			return false;
		}
	});
	
	</script>
</body>
</html>