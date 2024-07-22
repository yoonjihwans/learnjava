<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 입력태그에 입력값이 존재할 경우 Ajax 엔진을 사용해 [suggest_two.jsp] 문서를 요청하여
실행결과를 XML로 제공받아 응답하는 JSP 문서 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
#keyword {
	position: absolute;
	top: 100px;
	left: 10px;
	width: 300px;
	font-size: 16px;
}

#suggestListDiv {
	position: absolute;
	top: 120px;
	left: 10px;
	width: 300px;
	padding: 3px;
	border: 1px solid black;
	cursor: pointer;
	font-size: 16px;
	background: rgba(255, 255, 254, 1);
	z-index: 999;
	display: none;
}
</style>
</head>
<body>
	<h1>제시어 기능</h1>
	<hr>
	<div>
		<%-- 검색어를 입력받기 위한 태그 --%>
		<input type="text" id="keyword">
		
		<%-- 검색어가 포함된 제시어를 출력하기 위한 태그 --%>
		<div id="suggestListDiv">
			<div id="suggestList"></div>
		</div>
	</div>
	
	<script type="text/javascript">
	$("#keyword").focus();
	
	$("#keyword").keyup(function() {
		var keyword=$("#keyword").val();
		
		if(keyword == "") {
			$("#suggestListDiv").hide();
			return;
		}
		
		$.ajax({
			type: "post",
			url: "<%=request.getContextPath()%>/jdbc/suggest_two.jsp",
			data: {"keyword":keyword},
			dataType: "xml",
			success: function(xmlDoc) {
				
			},
			errror: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		});
	}); 
	</script>
</body>
</html>








