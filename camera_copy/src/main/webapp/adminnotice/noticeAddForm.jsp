
<%@page import="xyz.itwill.dto.AdminNoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 AdminNoticeDTO notice=(AdminNoticeDTO)session.getAttribute("notice");
 	if(notice != null) {
 		session.removeAttribute("notice");
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
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
	<h1>Notice</h1>
	<hr>
	<form name="noticeForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=noticeAdd" method="post"
	id="noticeForm" >
	<table>
	
		<tr>
			<th class="title">Notice title</th>
			<td class="input">
				<input type="text" name="title" id="title">
			</td>
		</tr>
		<tr>
			<th class="title">Notice Content </th>
			<td class="input">
				<%-- <input type="text" name="content" <% if(notice != null) { %>value="<%=notice.getNoticeContent()%>"<% } %>> --%>
				<textarea rows="7" cols="60" name="content" id="content"></textarea>
				
			</td>
			
		</tr>
		<tr>
			<th class="title">Notice Status </th>
			<td class="input">
				<input type="text" name="status">
			</td>
	
		<tr>
			<td colspan="2">
				<button type="submit">Submit</button> 
				<button type="reset">reset</button> 
				<button type="button" id="listBtn">Back list</button> 
			</td>
		</tr>
	</table>
	</form>
	<div id="message" style="color: red;"></div>
	
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
		
		if($("#reviewContent").val() == "") {
			$("#message").text("내용을 입력해 주세요.");
			$("#reviewContent").focus();
			return false;
		}
	});


	
	
	
	
	</script>

</body>
</html>