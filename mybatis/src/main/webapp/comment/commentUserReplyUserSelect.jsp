<%@page import="xyz.itwill.dto.MyReplyUser"%>
<%@page import="xyz.itwill.dao.MyCommentDAO"%>
<%@page import="xyz.itwill.dto.MyCommentUserReplyUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전달값(게시글번호)이 없는 경우 게시글목록을 출력하는 페이지로 이동 처리
	if(request.getParameter("commentNo") == null) {
		response.sendRedirect("commentUserListSelect2.jsp");
		return;
	}

	int commentNo=Integer.parseInt(request.getParameter("commentNo"));

	MyCommentUserReplyUser commentUserReplyUser=MyCommentDAO.getDAO().selectCommentUserReplyUser(commentNo);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

td {
	border: 1px solid black;
	text-align: center;
	padding: 3px;	
}

.no { width: 100px; }
.name { width: 150px; }
.content { width: 300px; }
.date { width: 200px; }
.comment { width: 100px; }
</style>
</head>
<body>
	<h1>게시글과 댓글목록</h1>
	<hr>
	<%-- 게시글 출력 --%>
	<table>
		<tr>
			<td width="200">게시글번호</td>
			<td width="300"><%=commentUserReplyUser.getCommentNo() %></td>
		</tr>	
		<tr>
			<td width="200">게시글작성자</td>
			<td width="300">
				<%=commentUserReplyUser.getUser().getUserName() %>
				[<%=commentUserReplyUser.getUser().getUserId() %>]
			</td>
		</tr>
		<tr>
			<td width="200">게시글내용</td>
			<td width="300"><%=commentUserReplyUser.getCommentContent() %></td>
		</tr>
		<tr>
			<td width="200">게시글작성일</td>
			<td width="300"><%=commentUserReplyUser.getCommentDate() %></td>
		</tr>
	</table>
	<br>
	
	<%-- 댓글목록 출력 --%>
	<table>
		<tr>
			<td class="no">댓글번호</td>
			<td class="name">댓글작성자</td>
			<td class="content">댓글내용</td>
			<td class="date">댓글작성일</td>
			<td class="comment">게시글번호</td>
		</tr>
		<% if(commentUserReplyUser.getReplyUserList().isEmpty()) { %>
		<tr>
			<td colspan="5">댓글이 존재하지 않습니다.</td>
		</tr>
		<% } else { %>
			<% for(MyReplyUser replyUser : commentUserReplyUser.getReplyUserList()) { %>
			<tr>
				<td class="no"><%=replyUser.getReply().getReplyNo() %></td>
				<td class="name">
					<%=replyUser.getUser().getUserName() %>[<%=replyUser.getUser().getUserId() %>]
				</td>
				<td class="content"><%=replyUser.getReply().getReplyContent() %></td>
				<td class="date"><%=replyUser.getReply().getReplyDate() %></td>
				<td class="comment"><%=replyUser.getReply().getReplyCommentNo() %></td>
			</tr>
			<% } %>
		<% } %>
	</table>	
</body>
</html>