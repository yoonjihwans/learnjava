<%@page import="xyz.itwill.dao.AjaxMemberDAO"%>
<%@page import="xyz.itwill.dto.AjaxMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 전달받아 AJAX_MEMBER 테이블의 행으로 삽입하고 [member_join_result.jsp] 문서를
요청할 수 있는 URL 주소를 응답하는 JSP 문서 --%>    
<%
	if(request.getMethod().equals("GET")) {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

	request.setCharacterEncoding("utf-8");
	
	String id=request.getParameter("id");
	String passwd=request.getParameter("passwd");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	
	AjaxMemberDTO ajaxMember=new AjaxMemberDTO();
	ajaxMember.setId(id);
	ajaxMember.setPasswd(passwd);
	ajaxMember.setName(name);
	ajaxMember.setEmail(email);
	
	AjaxMemberDAO.getDAO().insertAjaxMember(ajaxMember);
	
	response.sendRedirect(request.getContextPath()+"/jdbc/member_join_result.jsp");
%>