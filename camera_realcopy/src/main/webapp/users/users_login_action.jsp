<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	if(request.getMethod().equals("GET")) {				
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
		
	String id=request.getParameter("id");
	String pw=Utility.encrypt(request.getParameter("pw"));
	String url=request.getParameter("url");
	if(url == null) {
		url="";
	}
	
	UsersDTO users=UsersDAO.getDAO().selectUsersById(id);
	
	if(users == null || !users.getUsersPw().equals(pw)
		|| users.getUsersStatus() == 0) {
		session.setAttribute("message", "아이디 또는 비밀번호가 잘못 되었습니다. 아이디와 비밀번호를 정확히 입력해 주세요.");
		session.setAttribute("id", id);
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=users&work=users_login");
		return;
	}

	session.setAttribute("loginUsers", users);
	
	UsersDAO.getDAO().updateLastLogin(users.getUsersNo());

	if(url.equals("")) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=main&work=main_page");
	} else {
		request.setAttribute("returnUrl", url);
	}
%>