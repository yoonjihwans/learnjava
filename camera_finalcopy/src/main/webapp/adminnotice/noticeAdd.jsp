<%@page import="xyz.itwill.dao.AdminNoticeDAO"%>
<%@page import="xyz.itwill.dto.AdminNoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	if(request.getMethod().equals("GET")) {
	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
	return;
	}


	String title=request.getParameter("title");
	String content=request.getParameter("content");
	int status=Integer.parseInt(request.getParameter("status"));
	
	AdminNoticeDTO notice=new AdminNoticeDTO();
	
	notice.setNoticeTitle(title);
	notice.setNoticeContent(content);
	notice.setNoticeStatus(status);
	
	session.setAttribute("notice", notice);
	
	AdminNoticeDAO.getDAO().insertNotice(notice);
	
	
	/* response.sendRedirect(request.getContextPath()+"index.jsp?workgroup=adminnotice&work=notice"); */
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminnotice&work=notice");
	
	
	
%>