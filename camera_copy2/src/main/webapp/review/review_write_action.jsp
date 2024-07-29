<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/security/login_check.jspf" %>
<%@ page import="xyz.itwill.dto.ReviewDTO" %>
<%@ page import="xyz.itwill.dao.ReviewDAO" %>
<%@ page import="xyz.itwill.util.Utility" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%
if (request.getMethod().equals("GET")) {
    request.setAttribute("returnUrl", request.getContextPath() + "/index.jsp?workgroup=error&work=error_400");
    return;
}


String saveDirectory = request.getServletContext().getRealPath("/review_image");
MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, 20 * 1024 * 1024, "utf-8", new DefaultFileRenamePolicy());

String pageNum = multipartRequest.getParameter("pageNum");
String pageSize = multipartRequest.getParameter("pageSize");
String reviewTitle = Utility.escapeTag(multipartRequest.getParameter("reviewTitle"));
int reviewStatus = 1;
String reviewContent = Utility.escapeTag(multipartRequest.getParameter("reviewContent"));
String reviewImage = multipartRequest.getFilesystemName("reviewImage");
int reviewProdNo = Integer.parseInt(multipartRequest.getParameter("reviewProdNo"));
String redirect = multipartRequest.getParameter("redirect");

int nextNum = ReviewDAO.getDAO().selectReviewNextNum();

ReviewDTO review = new ReviewDTO();
review.setReviewNo(nextNum);
review.setReviewTitle(reviewTitle);
review.setReviewContent(reviewContent);
review.setReviewStatus(reviewStatus);
review.setReviewProdNo(reviewProdNo);
review.setReviewUsersNo(loginUsers.getUsersNo()); 
review.setReviewImage(reviewImage);

ReviewDAO.getDAO().insertReview(review);

String forwardUrl = "/index.jsp?workgroup=myaccount&work=myacct_review"
    + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
request.getRequestDispatcher(forwardUrl).forward(request, response);

%>
