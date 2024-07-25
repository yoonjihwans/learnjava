<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf" %>
<%
	if(request.getParameter("reviewNo") == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	int reviewNo=Integer.parseInt(request.getParameter("reviewNo"));
	String pageNum=request.getParameter("pageNum");
	String pageSize=request.getParameter("pageSize");
	
	ReviewDTO review=ReviewDAO.getDAO().selectReviewByNum(reviewNo);
		
	if(review == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}	
	
	if(loginUsers.getUsersNo() != review.getReviewUsersNo() && loginUsers.getUsersStatus() != 9) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
%>
<style type="text/css">
table {
	margin: 0 auto;
}

th {
	width: 100px;
}

td {
	text-align: left;
}
</style>
<h1>게시글 변경</h1>

<form action="<%=request.getContextPath()%>/index.jsp?workgroup=review&work=review_modify_action"
	method="post" enctype="multipart/form-data" id="reviewForm">
	<input type="hidden" name="reviewNo" value="<%=reviewNo%>">	
	<input type="hidden" name="pageNum" value="<%=pageNum%>">	
	<input type="hidden" name="pageSize" value="<%=pageSize%>">	
	<table>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="reviewTitle" 
					id="reviewTitle" size="40" value="<%=review.getReviewTitle()%>">				
			</td>	
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="7" cols="60" name="reviewContent" 
					id="reviewContent"><%=review.getReviewContent() %></textarea>
			</td>
		</tr>
		<tr>
			<th>이미지파일</th>
			<td>
				<input type="file" name="reviewImage"><br>
				<% if(review.getReviewImage() != null) { %>
					<div style="color: red;">이미지를 변경할 경우에만 파일을 입력해 주세요.</div>
					<img src="<%=request.getContextPath()%>/review_image/<%=review.getReviewImage()%>" width="200">
				<% } %>
		
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<button type="submit">글변경</button>
				<button type="reset" id="resetBtn">다시쓰기</button>
			</th>
		</tr>
	</table>
</form>
<div id="message" style="color: red;"></div>

<script type="text/javascript">
$("#reviewTitle").focus();

$("#reviewForm").submit(function() {
	if($("#reviewTitle").val() == "") {
		$("#message").text("제목을 입력해 주세요.");
		$("#reviewTitle").focus();
		return false;
	}
	
	if($("#reviewContent").val() == "") {
		$("#message").text("내용을 입력해 주세요.");
		$("#reviewContent").focus();
		return false;
	}
});

$("#resetBtn").click(function() {
	$("#reviewTitle").focus();
	$("#message").text("");
});
</script>