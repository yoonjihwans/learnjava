<%@page import="xyz.itwill.util.Utility"%>
<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	if(request.getMethod().equals("GET")) {	
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}

	request.setCharacterEncoding("utf-8");
	
	String id=request.getParameter("id");	
	String pw=Utility.encrypt(request.getParameter("pw"));
	String name=request.getParameter("name");
	String zipcode=request.getParameter("zipcode");
	String address1=request.getParameter("address1");
	String address2=request.getParameter("address2");
	String phone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
	String email=request.getParameter("email");

	UsersDTO users=new UsersDTO();
	users.setUsersId(id);
	users.setUsersPw(pw);
	users.setUsersName(name);
	users.setUsersZipcode(zipcode);
	users.setUsersAddress1(address1);
	users.setUsersAddress2(address2);
	users.setUsersPhone(phone);
	users.setUsersEmail(email);
		
	UsersDAO.getDAO().insertUsers(users);
		
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=users&work=users_login");
%>