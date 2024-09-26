<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>게시글 변경</h1>
	<hr>
	<form action="<c:url value="/board/modify"/>" method="post" id="boardForm">
		<sec:csrfInput/>
		<input type="hidden" name="num" value="${securityBoard.num }">
		<input type="hidden" name="writer" value="${securityBoard.writer }">
		<input type="hidden" name="pageNum" value="${searchMap.pageNum }">
		<input type="hidden" name="pageSize" value="${searchMap.pageSize }">
		<input type="hidden" name="column" value="${searchMap.column }">
		<input type="hidden" name="keyword" value="${searchMap.keyword }">
		<table>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" id="subject" value="${securityBoard.subject }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="60" name="content" id="content">${securityBoard.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">글변경</button></td>
			</tr>
		</table>	
	</form>
	
	<script type="text/javascript">
	$("#boardForm").submit(function() {
		if($("#subject").val() == "") {
			alert("제목을 입력해 주세요.");
			return false;
		}
		
		if($("#content").val() == "") {
			alert("내용을 입력해 주세요.");
			return false;
		}
	});
	</script>
</body>
</html>
