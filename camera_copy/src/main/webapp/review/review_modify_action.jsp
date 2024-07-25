<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf" %>
<%	
	if(request.getMethod().equals("GET")) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	String saveDirectory=request.getServletContext().getRealPath("/review_image");
	
	MultipartRequest multipartRequest=new MultipartRequest(request, saveDirectory
			, 20*1024*1024, "utf-8", new DefaultFileRenamePolicy());
		
	int reviewNo=Integer.parseInt(multipartRequest.getParameter("reviewNo"));
	String pageNum=multipartRequest.getParameter("pageNum");
	String pageSize=multipartRequest.getParameter("pageSize");

	String reviewTitle=Utility.escapeTag(multipartRequest.getParameter("reviewTitle"));
	int reviewStatus=1;
	
	String reviewContent=Utility.escapeTag(multipartRequest.getParameter("reviewContent"));
	
	String reviewImage=multipartRequest.getFilesystemName("reviewImage");
	
	ReviewDTO review=new ReviewDTO();
	review.setReviewNo(reviewNo);
	review.setReviewTitle(reviewTitle);
	review.setReviewContent(reviewContent);
	
	String removeReviewImage=ReviewDAO.getDAO().selectReviewByNum(reviewNo).getReviewImage();
	if(reviewImage != null) {
		review.setReviewImage(reviewImage);
		
		new File(saveDirectory, removeReviewImage).delete();
	} else {
		review.setReviewImage(removeReviewImage);
	}
	review.setReviewStatus(reviewStatus);
	
	ReviewDAO.getDAO().updateReview(review);	
	
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=review&work=review_detail"
		+"&reviewNo="+reviewNo+"&pageNum="+pageNum+"&pageSize="+pageSize);
%>