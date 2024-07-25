<?xml version="1.0" encoding="utf-8"?>
<%@ page import="xyz.itwill.dto.UsersDTO"%>
<%@ page import="xyz.itwill.dao.UsersDAO"%>
<%@ page language="java" contentType="text/xml; charset=UTF-8" 
	pageEncoding="UTF-8"%> 
<%
	if(request.getMethod().equals("GET")) {				
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return;
	}

    request.setCharacterEncoding("utf-8");
    
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    
    UsersDTO users = new UsersDTO();
    
    users.setUsersName(name);
    users.setUsersEmail(email);
    
    String id = UsersDAO.getDAO().selectUsersId(users);
%>
<result>
    <% if (id != null) { %>
        <code>success</code>
        <id><%= id %></id>
    <% } else { %>
        <code>empty</code>	
    <% } %>		
</result>
