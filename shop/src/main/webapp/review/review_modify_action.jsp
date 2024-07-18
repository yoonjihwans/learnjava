<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.util.Utility"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 게시글(변경글)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 [/review/review_detail.jsp]
문서를 요청할 수 있는 URL 주소로 응답하는 JSP 문서 --%>
<%-- => 글번호 및 페이징 처리와 조회기능 관련 값 전달 --%>    
<%-- => 로그인 사용자가 게시글 작성자이거나 관리자인 경우에만 JSP 문서 요청 가능 --%>
<%@include file="/security/login_check.jspf" %>
<%
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식으로 요청한 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}

	//파일을 저장할 서버 디렉토리의 파일 시스템 경로를 반환받아 저장
	String saveDirectory=request.getServletContext().getRealPath("/review_images");
	
	//MultipartRequst 객체 생성 - 모든 전달파일이 서버 디렉토리에 자동으로 업로드 처리
	MultipartRequest multipartRequest=new MultipartRequest(request, saveDirectory
			, 20*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	
	
	//전달값을 반환받아 저장
	int reviewNum=Integer.parseInt(multipartRequest.getParameter("reviewNum"));
	String pageNum=multipartRequest.getParameter("pageNum");
	String pageSize=multipartRequest.getParameter("pageSize");
	String search=multipartRequest.getParameter("search");
	String keyword=multipartRequest.getParameter("keyword");

	String reviewSubject=Utility.escapeTag(multipartRequest.getParameter("reviewSubject"));
	int reviewStatus=1;//전달값이 없는 경우 - 일반글
	if(multipartRequest.getParameter("reviewStatus") != null) {//전달값이 있는 경우 - 비밀글
		reviewStatus=Integer.parseInt(multipartRequest.getParameter("reviewStatus"));
	}
	String reviewContent=Utility.escapeTag(multipartRequest.getParameter("reviewContent"));
	//업로드 처리된 파일의 이름을 반환받아 저장 - 전달파일이 없는 경우 [null] 반환
	String reviewImage=multipartRequest.getFilesystemName("reviewImage");
	
	//ReviewDTO 객체를 생성하여 변수값(전달값)으로 필드값 변경
	ReviewDTO review=new ReviewDTO();
	review.setReviewNum(reviewNum);
	review.setReviewSubject(reviewSubject);
	review.setReviewContent(reviewContent);
	//업로드 처리된 파일이 있는 경우 기존 이미지 파일 삭제 처리
	String removeReviewImage=ReviewDAO.getDAO().selectReviewByNum(reviewNum).getReviewImage();
	if(reviewImage != null) {
		review.setReviewImage(reviewImage);
		//글번호를 전달받아 REVIEW 테이블에 저장된 하나의 행을 검색하여 ReviewDTO 객체로 반환하는
		//ReviewDAO 클래스의 메소드 호출 - ReviewDTO 객체에서 기존 이미지 파일의 이름을 반환받아 저장
		//서버 디렉토리의 이미지 파일이 저장된 File 객체를 생성해 delete() 메소드를 호출하여 파일 삭제
		new File(saveDirectory, removeReviewImage).delete();
	} else {
		review.setReviewImage(removeReviewImage);
	}
	review.setReviewStatus(reviewStatus);

	//게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 변경행의 갯수를
	//반환하는 ReviewDAO 클래스의 메소드 호출
	ReviewDAO.getDAO().updateReview(review);
	
	//페이지 이동 - 글번호 및 페이징 처리와 조회기능 관련 값 전달
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=review&work=review_detail"
		+"&reviewNum="+reviewNum+"&pageNum="+pageNum+"&pageSize="+pageSize+"&search="+search+"&keyword="+keyword);
%>