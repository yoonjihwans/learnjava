<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Ajax 엔진을 사용해 [xsl_two.jsp] 문서를 요청하여 실행결과를 XML로 제공받아 HTML 테그로
변경하여 응답하는 JSP 문서 --%>
<%-- => XML 데이타를 XSL를 사용해 HTML 태그로 변환하여 출력 처리 --%>
<%-- XSL(eXtensible Stylesheet Language) : XML 데이타를 제공받아 변환하는 기능의 프로그램
(Parser)을 작성하기 위한 언어 - XML 기반으로 작성 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<h1>교재목록</h1>
	<hr>
	<div id="bookList"></div>
	
	<script type="text/javascript">
	var xmlDoc=null;
	
	$.ajax({
		type: "get",
		url: "xsl_two.jsp",
		dataType: "xml",
		success: function(result) {
			xmlDoc=result;
		}, 
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);
		}
	});
	</script>
</body>
</html>









