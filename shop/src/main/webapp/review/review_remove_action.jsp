<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 글번호를 전달받아 REVIEW 테이블에 저장된 행에서 글상태를 [0]으로 변경하여 삭제 처리하고
[/review/review_list.jsp] 문서를 요청할 수 있는 URL 주소로 응답하는 JSP 문서 --%>
<%-- => 페이징 처리와 조회기능 관련 값 전달 --%>    
<%-- => 로그인 사용자가 게시글 작성자이거나 관리자인 경우에만 JSP 문서 요청 가능 --%>
<%@include file="/security/login_check.jspf" %>
<%
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(request.getParameter("reviewNum") == null) {//전달값이 없는 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	//전달값을 반환받아 저장
	int reviewNum=Integer.parseInt(request.getParameter("reviewNum"));
	String pageNum=request.getParameter("pageNum");
	String pageSize=request.getParameter("pageSize");
	String search=request.getParameter("search");
	String keyword=URLEncoder.encode(request.getParameter("keyword"),"utf-8");
	
	//글번호를 전달받아 REVIEW 테이블에 저장된 하나의 행을 검색하여 ReviewDTO 객체로 반환하는
	//ReviewDAO 클래스의 메소드 호출
	ReviewDTO review=ReviewDAO.getDAO().selectReviewByNum(reviewNum);
	
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(review == null) {//검색된 게시글이 없는 경우 
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	// => 로그인 사용자가 게시글 작성자가 아니고 관리자도 아닌 경우
	if(loginMember.getMemberNum() != review.getReviewMemberNum() && loginMember.getMemberAuth() != 9) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}	
	
	//검색된 게시글(ReviewDTO 객체)의 글상태를 저장한 필드값을 [0]으로 변경
	review.setReviewStatus(0);
	
	//게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 변경행의 갯수를
	//반환하는 ReviewDAO 클래스의 메소드 호출
	ReviewDAO.getDAO().updateReview(review);
	
	if(review.getReviewImage() != null) {//검색된 게시글에 이미지 파일이 있는 경우 삭제 처리
		String saveDirectory=request.getServletContext().getRealPath("/review_images");
		new File(saveDirectory, review.getReviewImage()).delete();
	}
	
	//페이지 이동 - 글번호 및 페이징 처리와 조회기능 관련 값 전달
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=review&work=review_list"
		+"&pageNum="+pageNum+"&pageSize="+pageSize+"&search="+search+"&keyword="+keyword);
%>