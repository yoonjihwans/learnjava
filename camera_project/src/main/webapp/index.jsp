<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	//페이지 몸체부에 포함될 JSP 문서의 작업 폴더명을 반환받아 저장
	String workgroup = request.getParameter("workgroup");
	if (workgroup == null) {
		workgroup = "main";
	}

	//페이지 몸체부에 포함될 JSP 문서의 파일명을 반환받아 저장
	String work = request.getParameter("work");
	if (work == null) {
		work = "main_page";
	}

	//전달값을 사용하여 페이지 몸체부에 포함될 JSP 문서의 컨텍스트 경로를 생성하여 저장
	String contentPath = workgroup + "/" + work + ".jsp";

	String headerPath = "/header.jsp";
	if (workgroup.equals("admin")) {
		headerPath = "/header_admin.jsp";
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link href="css/style.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Frank+Ruhl+Libre:wght@300..900&display=swap"
	rel="stylesheet">
</head>
<title>Camera</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<body>
	<div id="main-header">
		<%-- <jsp:include page="header.jsp"/> --%>
		<jsp:include page="<%=headerPath%>" />
	</div>

	<div id="content">
		<jsp:include page="<%=contentPath%>" />
		<%
			String returnUrl = (String) request.getAttribute("returnUrl");
			if (returnUrl != null) {
				response.sendRedirect(returnUrl);
				return;
			}
		%>
	</div>

	<div id="footer">
		<jsp:include page="footer.jsp" />
	</div> 
</body>
</html>