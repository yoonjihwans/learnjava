<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Ajax 엔진을 사용해 [xsl_two.jsp] 문서를 요청하여 실행결과를 XML로 제공받아 HTML 테그로
변경하여 응답하는 JSP 문서 --%>
<%-- => XML 데이타를 XSL를 사용해 HTML 태그로 변환하여 출력 처리 --%>
<%-- => Ajax 엔진을 사용해 XSL 문서(books.xsl)를 요청하여 XSLT로 응답받아 사용 --%>
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
	var xmlDoc=null;//XML 데이타를 저장하기 위한 전역변수
	var xslDoc=null;//XSLT 데이타를 저장하기 위한 전역변수
	
	$.ajax({
		type: "get",
		url: "xsl_two.jsp",
		dataType: "xml",
		//asyns : 동기식 통신 또는 비동기식 통신을 구분하기 위한 속성
		// => false : 동기식 통신, true : 비동기식 통신 - 기본값
		asyns: false,
		success: function(result) {
			xmlDoc=result;
			//doXSLT();
		}, 
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);
		}
	});
	
	$.ajax({
		type: "get",
		url: "books.xsl",
		dataType: "xml",
		success: function(result) {
			xslDoc=result;
			doXSLT();
		}, 
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);
		}
	});
	
	//XSLT 데이타를 사용해 XML 데이타를 HTML 태그로 변환하여 출력 처리하는 함수
	function doXSLT() {
		//if(xmlDoc == null || xslDoc == null) return;
		
		//XSLT 데이타로 XML 데이타를 변환할 수 있는 XSLTProcessor 객체 생성
		var xsltProcessor=new XSLTProcessor();
		
		//XSLTProcessor.importStylesheet(xslt) : XSLTProcessor 객체에 변환정보가 저장된
		//XSLT 데이타를 전달받아 저장하는 멤버함수
		xsltProcessor.importStylesheet(xslDoc);
		
		//XSLTProcessor.transformToFragment(xml, document) : 매개변수로 XML 데이타를 전달받아
		//XSLTProcessor 객체로 Element 객체로 변환하여 반환하는 멤버함수 
		var fragment=xsltProcessor.transformToFragment(xmlDoc, document);
		
		$("#bookList").append(fragment);
	}
	</script>
</body>
</html>