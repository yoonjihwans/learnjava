<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf"%>
<%
	String pageNum = "1", pageSize = "10";	
	int reviewProdNo = 61; // 예시
%>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/write.css">
<title>리뷰 작성하기</title>
</head>
<style type="text/css">
 a {text-decoration: none !important}
#write-wrap {
	width: 720px;
	height: 230px;
	font-size: 17px;
	margin: 0 auto;
	margin-top: 60px;
}
table {
	margin: 0 auto;
}
th {
	width: 100px;
}
td {
	text-align: left;
}
#message {
	text-align: center;
	font-size: large;
}
textarea {
    width: 100%;
    height: 150px; 
    overflow-y: auto; 
    resize: vertical; 
  }
</style>
<div id="write-wrap">	
	<form action="<%=request.getContextPath()%>/index.jsp?workgroup=review&work=review_write_action"
	method="post" enctype="multipart/form-data" id="reviewForm">
	<input type="hidden" name="pageNum" value="<%=pageNum%>">	
	<input type="hidden" name="pageSize" value="<%=pageSize%>">	
	<input type="hidden" name="reviewProdNo" value="<%=reviewProdNo%>"> <!-- 상품 번호 -->
	<table>
	<h1 style="text-align: center; font-size: 22px;">글쓰기</h1>
		<tr>
			<th>제목</th>
			<td>
				<input type="text" name="reviewTitle" id="reviewTitle" size="40">
			</td>	
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea rows="7" cols="60" name="reviewContent" id="reviewContent" ></textarea>
			</td>
		</tr>
		<tr>
			<th>이미지파일</th>
			<td>
				<input type="file" name="reviewImage">
			</td>
		</tr>
		<tr>
			<th colspan="2">
				<button type="submit">글저장</button>
				<button type="reset" id="resetBtn">다시쓰기</button>
			</th>
		</tr>
	</table>
</form>
</div>
<div id="message" style="color: red;"></div>
<script type="text/javascript">
const textarea = document.getElementById('reviewContent');

textarea.addEventListener('input', function() {
    // Calculate number of lines
    const lines = textarea.value.split('\n').length;

    // Limit to 10 lines
    if (lines > 10) {
        // Truncate excess lines
        textarea.value = textarea.value.split('\n').slice(0, 10).join('\n');
        // Alternatively, you can disable further input or show an error message
        // Example: textarea.setAttribute('disabled', 'disabled');
        // Example: alert('Maximum 10 lines allowed!');
    }
});

$("#reviewTitle").focus();

$("#reviewForm").submit(function() {
	if ($("#reviewTitle").val() == "") {
		$("#message").text("제목을 입력해 주세요.");
		$("#reviewTitle").focus();
		return false;
	}
	
	if ($("#reviewContent").val() == "") {
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

</html>
