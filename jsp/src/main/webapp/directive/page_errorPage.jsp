<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- page Directive의 errorPage 속성을 사용하여 JSP 문서 실행시 예외가 발생될 경우 에러페이지로
응답되도록 설정할 수 있지만 JSP 문서마다 errorPage 속성을 설정해야 하는 문제점 발생 --%>
<%-- => 에러코드(4XX or 5XX)가 클라이언트에게 전달되어 응답될 때 에러페이지로 응답 처리되도록
[web.xml] 파일에 error-page 엘리먼트를 작성하는 것을 권장 --%>
<%-- => 모든 웹프로그램 실행시 발생되는 예외에 대해 에러페이지로 응답 처리 가능 --%>
<%
	String text="ABCDEFG";
	//String text=null;
	
	int su=10/0;
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>page Directive - errorPage 속성</h1>
	<hr>
	<p>page Directive의 errorPage 속성에는 JSP 문서 실행시 에러(예외)가 발생될 경우 클라이언트에게
	500 상태코드(에러코드) 대신 응답될 웹문서의 컨텍스트 경로(ContextPath)를 속성값으로 설정</p>
	<hr>
	<p>문자열의 문자갯수 = <%=text.length() %></p>
</body>
</html>