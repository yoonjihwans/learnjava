<%@page import="xyz.itwill.dao.AdminNoticeDAO"%>
<%@page import="xyz.itwill.dto.AdminNoticeDTO"%>
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
	
	//번호를 전달받아 Notice 테이블에 저장된 하나의 행을 검색하여 검색된 회원정보(AdminNoticeDTO 
	//객체)를 반환하는 AdminNoticeDAO 클래스의 메소드 호출
	AdminNoticeDTO notice=AdminNoticeDAO.getDAO().selectNoticeNo(no);
	
	//비정상적인 요청에 대한 응답 처리
	if(notice == null) {
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
	<h1>Notice Edit</h1>
	<hr>
	<form name="noticeForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=noticeUpdate" method="post"
	id="noticeForm" >
	<table>
		<tr>
			<th class="title">notice No</th>
			<td class="input">
				<input type="text" name="no" value="<%=notice.getNoticeNo() %>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">notice title</th>
			<td class="input">
				<input type="text" name="title"  id="title" value="<%=notice.getNoticeTitle() %>" >
			</td>
		</tr>
		<tr>
			<th class="title">notice content</th>
			<td class="input">
				<%-- <input type="text" name="content" value="<%=notice.getNoticeContent() %>"> --%>
				<textarea rows="7" cols="60" name="content" id="content"><%=notice.getNoticeContent() %></textarea>
			</td>
		</tr>
		<tr>
			<th class="title">notice status</th>
			<td class="input">
				<input type="text" name="status" value="<%=notice.getNoticeStatus() %>">
			</td>
		</tr>
	
		<tr>
			<td colspan="2">
				<button type="submit">notice edit</button> 
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
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=notice";	
	}
	
	/*버튼을 눌렀을때 글이 아무것도 없으면 나와주는 메세지와 채워야지만 넘어가는것을 나타내는 function */
	$("#noticeForm").submit(function() {
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