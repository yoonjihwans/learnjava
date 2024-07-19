<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Ajax 엔진을 사용해 [songs_two.jsp] 문서를 요청하여 실행결과를 JSON으로 제공받아
태그를 변경하여 응답하는 JSP 문서 - jQuery 라이브러리를 사용해 AJAX 기능 구현 --%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>jQuery AJAX</h1>
	<hr>
	<h1>음원차트(<span id="now"></span> 현재 기준)</h1>
	<div id="songList"></div>
	
	<script type="text/javascript">
	$.ajax({
		type: "get",
		url: "songs_two.jsp",
		dataType: "json",
		success: function(result) {
			//alert(result);//[object Object]
			
			$("#now").html(result.now);
			
			var html="<ol>";
			$(result.songs).each(function() {
				html+="<li><b>"+this.title+"</b>["+this.singer+"]</li>";
			});
			html+="</ol>";
			
			$("#songList").html(html);
		},
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);
		} 
	});
	</script>
</body>
</html>