<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf" %>
<%
	if(request.getParameter("reviewNo") == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	int reviewNo=Integer.parseInt(request.getParameter("reviewNo"));
	String pageNum=request.getParameter("pageNum");
	String pageSize=request.getParameter("pageSize");
		
	ReviewDTO review=ReviewDAO.getDAO().selectReviewByNum(reviewNo);
		
	if(review == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}	
	if(loginUsers.getUsersNo() != review.getReviewUsersNo() && loginUsers.getUsersStatus() != 9) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}	
	
	review.setReviewStatus(0);
	
	ReviewDAO.getDAO().updateReview(review);
	
	if(review.getReviewImage() != null) {
		String saveDirectory=request.getServletContext().getRealPath("/review_image");
		new File(saveDirectory, review.getReviewImage()).delete();
	}
	
	
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=myaccount&work=myacct_review"
		+"&pageNum="+pageNum+"&pageSize="+pageSize);
%>