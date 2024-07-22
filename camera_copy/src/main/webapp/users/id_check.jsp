<?xml version="1.0" encoding="utf-8"?>
<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("id");

    if (id == null || id.equals("")) {
    	response.sendRedirect(request.getContextPath()+"/error/error_400.jsp");
		return;
    }

    UsersDTO users = UsersDAO.getDAO().selectUsersById(id);
%>
<result>
    <% if (users != null) { %>
    <code>impossible</code>
    <% } else { %>
    <code>possible</code>
    <% } %>
</result>