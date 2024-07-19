<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- Ajax 엔진을 사용하여 [json_two.jsp] 문서를 요청해 실행결과를 JSON 형식의 데이타로 제공받아 
HTML 태그로 변경하여 응답하는 JSP 문서 --%>    
<%-- JSON(Javascript Object Notation) : Javascript 객체를 사용해 값을 제공하는 구조적인 데이타 표현 방식 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<style type="text/css">
#newsList {
	width: 50%;
	margin: 0 auto;
}	

#newsHeader {
	padding: 5px;
	text-align: center;
	font-size: x-large;
	border: 2px solid black;
}

#newsContents {
	padding: 5px;
	font-size: medium;
	border: 2px dashed black;
	display: none;
}
</style>
</head>
<body>
	<h1>헤드라인 뉴스</h1>
	<hr>
	<div id="newsList">
		<div id="newsHeader">오늘의 뉴스</div>
		<div id="newsContents"></div>
	</div>
	
	<script type="text/javascript">
	document.getElementById("newsList").onmouseover=function() {
		var xhr=new XMLHttpRequest();
		
		xhr.onreadystatechange=function() {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					//JSON.parse(json) : 매개변수로 전달받은 JSON 형식의 문자값을 Javascript
					//객체로 변환하여 반환하는 멤버함수
					//var result=eval("("+xhr.responseText+")");
					var result=JSON.parse(xhr.responseText);
					//alert(result);//[object Object][object Object][object Object][object Object][object Object] >> Array 객체
					//alert(result.length);
					
					var html="<ol>";
					//Array 객체의 요소값(Object 객체)을 제공받아 반복 처리 
					for(i=0;i<result.length;i++) {
						//Object 객체의 멤버변수값을 제공받아 저장
						//var title=result[i]["title"];
						var title=result[i].title;
						var publisher=result[i].publisher;
						
						html+="<li>"+title+"["+publisher+"]</li>";
					}
					html+="</ol>";
					
					document.getElementById("newsContents").innerHTML=html;
					document.getElementById("newsContents").style="display: block;";
				} else {
					alert("에러코드 = "+xhr.status);
				}
			}
		}
		
		xhr.open("get", "json_two.jsp");
		xhr.send(null);
	}
	
	document.getElementById("newsList").onmouseout=function() {
		document.getElementById("newsContents").style="display: none;";
	}
	</script>	
</body>
</html>