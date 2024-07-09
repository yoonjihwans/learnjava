<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%-- 클라이언트가 JSP 문서를 요청하면 WAS 프로그램은 JSP 문서를 서블릿 클래스로 변환하여 생성하고
서블릿 클래스를 컴파일하여 객체로 만든 후 요청 처리 메소드를 호출해 요청에 대한 처리와 응답파일을 
동적으로 생성하여 클라이언트에게 응답 처리 --%>
<%-- => JSP 문서에 대한 서블릿 객체가 이미 존재할 경우 요청 처리 메소드만 호출하여 응답 처리 --%>
<%-- => JSP 문서가 변경된 후 클라이언트가 JSP 문서를 요청하면 JSP 문서를 서블릿 클래스로 
변환해 객체 생성하고 요청 처리 메소드를 호출하여 응답 처리 --%>
<%
	Date now=new Date();
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
	String displayNow=dateFormat.format(now);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
#displayDiv {
	width: 600px;
	margin: 0 auto;
	padding: 30px 0;
	font-size: 2em;
	font-weight: bold;
	text-align: center;
	border: 2px solid black;
}
</style>
</head>
<body>
	<h1>Hello, JSP!!!</h1>
	<hr>
	<!-- HTML 주석문 : HTML 문서에 설명을 제공하기 위해 사용 - 클라이언트에게 전달 : 웹디자이너 -->
	<%-- JSP 주석문 : JSP 문서에 설명을 제공하기 위해 사용 - 클라이언트에게 미전달 : 프로그래머 --%>
	<p>JSP(Java Server Page) : 서블릿보다 쉽게 웹프로그램을 작성하기 위해 만들어진 스크립트 
	프로그램 - HTML 문서에 Java 코드를 삽입하여 작성 : 스크립트 요소(Script Element)
	, 지시어(Directive), 표준 액션 태그(Standard Action Tag)</p>
	<hr>
	<div id="displayDiv"><%=displayNow %></div>
	
	<script type="text/javascript">
	setInterval(function() {
		location.reload();
	}, 1000);
	</script>
</body>
</html>