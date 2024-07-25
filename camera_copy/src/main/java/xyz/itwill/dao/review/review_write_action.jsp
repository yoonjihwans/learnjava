<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf"%>

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

int reviewStatus = 1; // 전달값이 없는 경우 - 일반글

String reviewContent = Utility.escapeTag(multipartRequest.getParameter("reviewContent"));

String reviewImage = multipartRequest.getFilesystemName("reviewImage");

// 폼 데이터에서 상품 번호를 가져옴
int reviewProdNo = Integer.parseInt(multipartRequest.getParameter("reviewProdNo"));

int nextNum = ReviewDAO.getDAO().selectReviewNextNum();

ReviewDTO review = new ReviewDTO();
review.setReviewNo(nextNum); // 시퀀스의 다음값으로 필드값 변경
review.setReviewTitle(reviewTitle);
review.setReviewContent(reviewContent);
review.setReviewStatus(reviewStatus);
review.setReviewProdNo(reviewProdNo); // 폼 데이터에서 가져온 상품 번호 설정
review.setReviewUsersNo(loginUsers.getUsersNo()); // 로그인 사용자의 회원번호로 필드값 변경
review.setReviewImage(reviewImage);

ReviewDAO.getDAO().insertReview(review);

request.setAttribute("returnUrl", request.getContextPath() + "/index.jsp?workgroup=review&work=review"
		+"&pageNum="+pageNum+"&pageSize="+pageSize);
%>
