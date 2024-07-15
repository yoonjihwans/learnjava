<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");

%>

<h1>관리자 메인 페이지</h1>