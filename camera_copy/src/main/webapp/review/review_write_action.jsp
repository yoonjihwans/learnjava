<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@include file="/security/login_check.jspf" %>
    
    <%
    if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식으로 요청한 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
    
    String saveDirectory=request.getServletContext().getRealPath("/review_image");
    
    MultipartRequest multipartRequest=new MultipartRequest(request, saveDirectory
			, 20*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	
	String reviewTitle=Utility.escapeTag(multipartRequest.getParameter("reviewTitle"));
	
	int reviewStatus=1;//전달값이 없는 경우 - 일반글
	
	String reviewContent=Utility.escapeTag(multipartRequest.getParameter("reviewContent"));
	
	String reviewImage=multipartRequest.getFilesystemName("reviewImage");
	
	int nextNum=ReviewDAO.getDAO().selectReviewNextNum();
	
	ReviewDTO review = new ReviewDTO(); 
	review.setReviewNum(nextNum);//시퀸스의 다음값으로 필드값 변경
	review.setReviewUserNo(loginUsers.getUsersNo());//로그인 사용자의 회원번호로 필드값 변경
	review.setReviewTitle(reviewTitle);
	review.setReviewContent(reviewContent);
	review.setReviewImage(reviewImage);
	review.setReviewStatus(reviewStatus);
	
	ReviewDAO.getDAO().insertReview(review);
	
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=review&work=review");
    
    %>
