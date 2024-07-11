<%@page import="xyz.itwill.dao.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 학번을 전달받아 STUDENT 테이블에 저장된 행을 삭제하고 [displayStudent.jsp] 문서를 요청할 
수 있는 URL 주소로 응답하는 JSP 문서 --%>      
<%
	//비정상적인 요청에 대한 응답 처리
	if(request.getParameter("no") == null) {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}

	//전달값을 반환받아 저장
	int no=Integer.parseInt(request.getParameter("no"));
	
	//학번를 전달받아 STUDENT 테이블에 저장된 행을 삭제하고 삭제행의 갯수를 반환하는 
	//StudentDAO 클래스의 메소드 호출
	int rows=StudentDAO.getDAO().deleteStudent(no);
	
	if(rows > 0) {//삭제행이 있는 경우
		response.sendRedirect(request.getContextPath()+"/student/displayStudent.jsp");
	} else {//삭제행이 있는 경우 - 비정상적인 요청에 대한 응답 처리
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}
%>