﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 템플릿 페이지를 구현한 JSP 문서 --%>    
<%-- => 클라이언트의 모든 요청에 대한 응답 결과를 제공하는 페이지 --%>
<%-- => 페이지의 몸체부에는 전달값으로 만들어진 JSP 문서의 실행결과(CSL)를 동적으로 포함 --%>
    
<%
	request.setCharacterEncoding("utf-8");

	//페이지 몸체부에 포함될 JSP 문서의 작업 폴더명을 반환받아 저장
	String workgroup=request.getParameter("workgroup");
	if(workgroup == null) {
		workgroup="main";
	}

	//페이지 몸체부에 포함될 JSP 문서의 파일명을 반환받아 저장
	String work=request.getParameter("work");
	if(work == null) {
		work="main_page";
	}
	
	//전달값을 사용하여 페이지 몸체부에 포함될 JSP 문서의 컨텍스트 경로를 생성하여 저장
	String contentPath=workgroup+"/"+work+".jsp";
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%-- Header 영역 : 회사로고,메뉴 등 --%>
	<div id="header">
		<jsp:include page="header.jsp"/>
	</div>
	
	<%-- Content 영역 : 요청에 대한 결과 출력 --%>
	<div id="content">
		<jsp:include page="<%=contentPath %>"/>		
		<%
			String returnUrl=(String)request.getAttribute("returnUrl");
			if(returnUrl != null) {
				response.sendRedirect(returnUrl);
				return;
			}
		%>
	</div>
	
	<%-- Footer 영역 : 저작권,약관,개인정보 보호정책,회사주소등 --%>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</body>
</html>