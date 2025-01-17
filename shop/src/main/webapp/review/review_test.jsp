<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- REVIEW 테이블에 행(게시글)을 500개 삽입하는 JSP 문서 - 테스트 프로그램 --%>
<%
	ReviewDTO review=new ReviewDTO();
	for(int i=1;i<=500;i++) {
		int nextNum=ReviewDAO.getDAO().selectReviewNextNum();
		review.setReviewNum(nextNum);//글번호 변경
		review.setReviewMemberNum(1);//회원번호(작성자) 변경
		review.setReviewSubject("테스트-"+i);//제목 변경
		review.setReviewContent("게시글 테스트-"+i);//내용 변경
		review.setReviewIp("192.168.13.25");//IP 주소 변경
		review.setReviewRef(nextNum);//글그룹 변경
		review.setReviewRestep(0);//글순서 변경
		review.setReviewRelevel(0);//글깊이 변경
		review.setReviewNum(nextNum);//글번호 변경
		review.setReviewStatus(1);//글상태 변경
		
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
	<h1>500개의 테스트 게시글을 삽입 하였습니다.</h1>
</body>
</html>