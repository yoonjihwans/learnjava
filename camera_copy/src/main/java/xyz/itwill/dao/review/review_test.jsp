<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- REVIEW 테이블에 행(게시글)을 500개 삽입하는 JSP 문서 - 테스트 프로그램 --%>
<%
ReviewDTO review = new ReviewDTO();
for(int i = 1; i <= 500; i++) {
    int nextNum = ReviewDAO.getDAO().selectReviewNextNum();
    review.setReviewNo(nextNum); // 글번호 설정
    review.setReviewTitle("테스트-" + i); // 제목 설정
    review.setReviewContent("게시글 테스트-" + i); // 내용 설정
    review.setReviewStatus(1); // 글상태 설정
    review.setReviewProdNo(61); // 제품 번호 설정
    review.setReviewUsersNo(1); // 회원번호(작성자) 설정
    review.setReviewNo(nextNum); // 글번호 설정

    ReviewDAO.getDAO().insertReview(review);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
    <h1>500개의 테스트 게시글을 삽입하였습니다.</h1>
</body>
</html>
