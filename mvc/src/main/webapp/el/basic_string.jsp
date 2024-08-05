<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//pageContext.setAttribute(String attributeName, Object attributeValue) : 매개변수로 
	//전달받은 속성명과 속성값을 PageContext 내장객체의 속성값으로 저장하는 메소드
	//Page Scope : 속성값을 저장한 웹프로그램에서만 속성값을 객체로 제공받아 사용 가능 
	pageContext.setAttribute("name", "홍길동");//Page Scope
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL(Expression Language)</h1>
	<hr>
	<p>스코프(Scope) 속성값으로 저장된 객체를 제공받아 출력 처리하기 위한 언어</p>
	<p>Scope(사용범위) : 객체를 속성값으로 저장하여 사용할 수 있는 범위 - JSP 내장객체에 따라
	속성값의 사용범위가 다르게 설정 : pageContext(Page Scope), request(Request Scope)
	, session(Session Scope), application(Application Scope)</p>
	<hr>
	<h2>EL 미사용</h2>
	<%
		//pageContext.getAttribute(String attributeName) : pageContext 내장객체에 저장된 속성값
		//중 매개변수로 전달받은 속성명의 속성값을 Object 객체로 반환하는 메소드
		// => 속성값을 Object 객체로 반환하므로 반드시 명시적 객체 형변환을 사용해 원하는 자료형의
		//객체로 변환해야만 사용 가능
		String name=(String)pageContext.getAttribute("name");
		
		//매개변수로 전달받은 속성명의 속성값이 없는 경우 [null] 반환
		String pageName=(String)pageContext.getAttribute("pageName");
	%>	
	<%-- 반환받은 객체를 JSP 표현식(Expression)으로 태그에 포함해 출력 처리 --%>
	<p>이름(name) = <%=name %></p>
	<%-- JSP 표현식(Expression)은 참조변수에 저장된 [null]을 문자열로 바꾸어 출력 처리 --%>
	<p>이름(pageName) = <%=pageName %></p>
	<%-- if 명령을 사용해 참조변수에 [null]이 저장되어 있지 않은 경우에만 출력 처리 --%>
	<p>이름(pageName) = <% if(pageName !=null) { %><%=pageName %><% } %></p>
	<hr>
	<h2>EL 사용</h2>
	<%-- EL 사용법 : ${표현식} - EL 표현식으로 값(객체)을 제공받아 출력 처리 --%>
	<%-- ${속성명} : EL 표현식으로 Scope 속성명을 사용해 속성값을 제공받아 출력 처리 --%>
	<%-- => 내장객체의 getAttribute() 메소드를 호출하지 않아도 Scope 속성값을 제공받아 출력 처리 가능 --%>
	<p>이름(name) = ${name }</p>
	<%-- EL 표현식으로 제공받은 값(객체)가 없는 경우 EL이 미실행되어 값이 미출력 처리 --%>
	<p>이름(pageName) = ${pageName }</p>
</body>
</html>