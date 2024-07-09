<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 클라이언트의 모든 요청에 대한 실행결과(HTML 문서)를 응답하는 JSP 문서 - 템플릿 페이지 --%>    
<%
	request.setCharacterEncoding("utf-8");

	//String headerFilePath="/action/include/header.jspf";
	String headerFilePath="/action/include/header.jsp";
	
	String worker=request.getParameter("worker");
	if(worker == null) {
		worker="main";
	}
	
	//전달값으로 몸체부에 포함될 JSP 문서의 컨텍스트 경로를 생성하여 저장
	String contentFilePath="/action/include/"+worker+".jsp";
	//System.out.println("contentFilePath = "+contentFilePath);
	
	String webMaster="";
	if(worker.equals("main")) {
		webMaster="홍길동(abc@itwill.xyz)";
	} else if(worker.equals("login") || worker.equals("join")) {
		webMaster="임꺽정(opq@itwill.xyz)";
	} else if(worker.equals("cart") || worker.equals("review")) {
		webMaster="전우치(xyz@itwill.xyz)";
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
div {
	margin: 5px;
	padding: 5px;
}

#header {
	height: 200px;
	border: 1px solid red;
}	

#header h1 {
	text-align: center;
}

#menu {
	font-size: 1.5em;
	text-align: right;
}

#menu a, #menu a:visited {
	text-decoration: none;
	color: black;
}

#menu a:hover {
	color: orange;
}

#content {
	min-height: 500px;
	border: 1px solid green;
	text-align: center;
}

#footer {
	height: 150px;
	border: 1px solid blue;
	text-align: center;
	font-size: 1.2em;
}
</style>
</head>
<body>
	<%-- 머릿부 : 로고, 메뉴 등 --%>
	<div id="header">
		<%-- 
		<h1><a href="<%=request.getContextPath()%>/action/include/index.jsp">쇼핑몰</a></h1>
		<div id="menu">
			<a href="<%=request.getContextPath()%>/action/include/index.jsp?worker=login">로그인</a>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/action/include/index.jsp?worker=join">회원가입</a>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/action/include/index.jsp?worker=cart">장바구니</a>&nbsp;&nbsp;
			<a href="<%=request.getContextPath()%>/action/include/index.jsp?worker=review">제품후기</a>&nbsp;&nbsp;
		</div>
		--%>
		
		<%-- include Directive : 문서파일(JSPF)의 소스코드를 JSP 문서에 포함하는 지시어 --%>
		<%-- => CSL(HTML, CSS, JavaScript) 및 SSL(Java - Script Element)로 작성된 코드를 JSP 문서에 포함 --%>
		<%-- => 문서파일의 소스코드를 JSP 문서에 그대로 포함하여 사용 - 정적포함 --%>
		<%-- JSP 문서를 요청한 경우 include Directive의 file 속성값으로 설정된 문서파일의
		코드를 JSP 문서에 포함하여 실행한 후 실행결과를 HTML 문서로 생성하여 응답 처리 --%>
		<%-- => include Directive의 file 속성값으로 설정된 문서파일이 변경된 경우 JSP 문서가
		변경된 경우 동일하므로 JSP 문서 요청시 서블릿 클래스를 새로 변환하여 실행 처리 --%>
		<%-- <%@include file="/action/include/header.jspf" %> --%>
		<%-- include Directive의 file 속성값으로 표현식(Expression) 사용 불가능 - 에러 발생 --%>
		<%-- <%@include file="<%=headerFilePath %>" %> --%>
		
		<%-- include Tag : 요청 JSP 문서에서 다른 JSP 문서로 스레드를 이동하여 JSP 문서를
		실행하고 실행결과(HTML 태그)를 가져와 JSP 문서에 포함하는 JSP 태그 --%>
		<%-- 형식) <jsp:include page="JSP 문서의 컨텍스트 경로"></jsp:include> --%>
		<%-- => 요청 JSP 문서의 request 객체와 response 객체를 스레드가 이동될 JSP 문서로
		전달하여 사용할 수 있도록 제공 --%>
		<%-- => 스레드가 이동된 JSP 문서의 실행결과(CSL - HTML, CSS, JavaScript)를 요청 JSP 
		문서에 포함 - 동적 포함 --%>
		<%-- => include 태그의 page 속성값으로 설정된 JSP 문서를 변경해도 요청 JSP 문서에는 미영향 --%>
		<%-- <jsp:include page="/action/include/header.jsp"></jsp:include> --%>
		<%-- <jsp:include page="/action/include/header.jsp"/> --%>
		<%-- include 태그의 page 속성값으로 표현식(Expression) 사용 가능 --%>
		<jsp:include page="<%=headerFilePath %>"/>
	</div>
	
	<%-- 몸체부 : 요청에 대한 실행 결과 --%>
	<div id="content">
		<%-- <h2>메인 페이지 - 제품 목록 출력</h2> --%>
		<jsp:include page="<%=contentFilePath %>"/>
	</div>
	
	<%-- 꼬릿부 : 저작권, 약관, 개인정보 보호정책 등 --%>
	<div id="footer">
		<%-- 
		<p>Copyright ⓒ Itwill Corp. All rights reserved</p>
		<p>관리자 : 홍길동(abc@itwill.xyz)</p>
		--%>
		<%-- param 태그 : 스레드가 이동되는 JSP 문서에게 값을 전달하는 태그 --%>
		<%-- => 리퀘스트 메세지(request 객체)의 몸체부에 값을 저장하여 전달 --%>
		<%-- => include 태그와 forward 태그의 자식태그로만 사용 가능 --%>
		<%-- => include 태그와 forward 태그의 자식태그로만 사용 가능 --%>
		<%-- include 태그와 forward 태그에는 param 태그외 다른 코드(주석문 포함)를 작성하면 에러 발생 --%>
		<jsp:include page="/action/include/footer.jsp">
			<jsp:param value="<%=webMaster %>" name="master"/>
		</jsp:include>		
	</div>
	<%
		if(request.getAttribute("errorCode") != null) {
			//클라이언트에게 에러코드를 전달하여 응답 처리
			response.sendError((Integer)request.getAttribute("errorCode"));
			return;
		}
	%>
</body>
</html>