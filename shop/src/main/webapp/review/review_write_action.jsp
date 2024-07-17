<%@page import="xyz.itwill.util.Utility"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 게시글(새글 또는 답글)을 전달받아 REVIEW 테이블의 행으로 삽입하고 [/review/review_list.jsp]
문서를 요청할 수 있는 URL 주소로 응답하는 JSP 문서 - 페이징 처리 및 조회기능 관련 값 전달 --%>    
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%-- => 게시글이 [multipart/form-data]로 전달되므로 COS 라이브러리의 MultipartRequst 객체를 
사용해 전달값 및 파일 처리 - 전달파일은 [/review_image] 폴더에 저장되도록 업로드 처리 --%>

<%@include file="/security/login_check.jspf" %>
<%
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식으로 요청한 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}

	//파일을 저장할 서버 디렉토리의 파일 시스템 경로를 반환받아 저장
	//String saveDirectory=application.getRealPath("/review_images");
	String saveDirectory=request.getServletContext().getRealPath("/review_images");
	//System.out.println("saveDirectory = "+saveDirectory);
	
	//MultipartRequst 객체 생성 - 모든 전달파일이 서버 디렉토리에 자동으로 업로드 처리
	// => cos.jar 라이브러리 파일이 프로젝트에 빌드 처리되어야만 MultipartRequst 클래스 사용 가능
	MultipartRequest multipartRequest=new MultipartRequest(request, saveDirectory
			, 20*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	
	//전달값을 반환받아 저장
	int ref=Integer.parseInt(multipartRequest.getParameter("ref"));
	int restep=Integer.parseInt(multipartRequest.getParameter("restep"));
	int relevel=Integer.parseInt(multipartRequest.getParameter("relevel"));
	String pageNum=multipartRequest.getParameter("pageNum");
	String pageSize=multipartRequest.getParameter("pageSize");
	String search=multipartRequest.getParameter("search");
	String keyword=multipartRequest.getParameter("keyword");
	
	//사용자로부터 입력받아 전달된 값에 HTML 태그가 존재할 경우 웹프로그램 실행시 문제 발생
	// => XSS(Cross Site Scripting) 공격 : 사용자가 악의적인 스크립트를 입력해 페이지가 비정상적으로 
	//출력되거나 다른 사용자의 사용을 방행 또는 개인정보를 특정 사이트로 전달하는 공격
	//String reviewSubject=multipartReques.getParameter("reviewSubject");
	//String reviewSubject=Utility.stripTag(multipartReques.getParameter("reviewSubject"));
	String reviewSubject=Utility.escapeTag(multipartRequest.getParameter("reviewSubject"));
	
	int reviewStatus=1;//전달값이 없는 경우 - 일반글
	if(multipartRequest.getParameter("reviewStatus") != null) {//전달값이 있는 경우 - 비밀글
		reviewStatus=Integer.parseInt(multipartRequest.getParameter("reviewStatus"));
	}
	
	String reviewContent=Utility.escapeTag(multipartRequest.getParameter("reviewContent"));
	
	//업로드 처리된 파일의 이름을 반환받아 저장 - 전달파일이 없는 경우 [null] 반환
	String reviewImage=multipartRequest.getFilesystemName("reviewImage");

	//REVIEW_SEQ 시퀸스의 다음값을 검색하여 반환하는 ReviewDAO 클래스의 메소드 호출
	int nextNum=ReviewDAO.getDAO().selectReviewNextNum();
	
	//게시글을 작성한 클라이언트의 IP 주소를 반환받아 저장
	//requst.getRemoteAddress() : JSP 문서를 요청한 클라이언트의 IP 주소를 반환하는 메소드
	// => 이클립스에 등록된 WAS 프로그램은 기본적으로 128Bit 형식(IPV6)의 IP 주소 제공
	//32Bit(IPV4)의 IP 주소 제공받을 수 있도록 이클립스에 등록된 WAS 프로그램의 환경설정 변경
	// => Run >> Run Configurations... >> Apache Tomcat >> 사용중인 Apache Tomcat 선택
	// >> Arguments >> VM Arguments >> [-Djava.net.preferIPv4Stack=true] 추가 >> Apply 	
	String reviewIp=request.getRemoteAddr();
	//System.out.println("reviewIp = "+reviewIp);
	
	//새글과 답글을 구분하여 전달값이 저장된 변수값(ref, restep, relevel) 변경
	// => 새글인 경우에는 변수에 [0]이 저장되어 있고 답글인 경우에는 부모글의 값 저장 
	if(ref == 0) {//새글인 경우
		//ref 변수값을 시퀸스의 다음값으로 변경
		ref=nextNum;		
	} else {//답글인 경우
		//REVIEW 테이블에 저장된 행에서 REVIEW_REF 컬럼값이 ref 변수값(부모글)과 같은 행 중
		//REVIEW_RESTEP 컬럼값이 restep 변수값(부모글)보다 큰 행의 REVIEW_RESTEP 컬럼값이
		//1 증가되도록 변경 처리
		// => 새로운 답글이 기존 답글보다 먼저 검색되도록 기존 답글의 순서를 증가 처리
		//부모글 관련 정보를 전달받아 REVIEW 테이블에서 저장된 행에서 전달값을 비교하여 REVIEW_REF
		//컬럼값을 1 증가되도록 변경하고 변경행의 갯수를 반환하는 ReviewDAO 클래스의 메소드 호출
		ReviewDAO.getDAO().updateReviewRestep(ref, restep);
		
		//restep 변수값 및 relevel 변수값을 1 증가되도록 변경 
		restep++;
		relevel++;
	}
	
	//ReviewDTO 객체를 생성하여 변수값(전달값)으로 필드값 변경
	ReviewDTO review=new ReviewDTO();
	review.setReviewNum(nextNum);//시퀸스의 다음값으로 필드값 변경
	review.setReviewMemberNum(loginMember.getMemberNum());//로그인 사용자의 회원번호로 필드값 변경
	review.setReviewSubject(reviewSubject);
	review.setReviewContent(reviewContent);
	review.setReviewImage(reviewImage);
	review.setReviewRef(ref);
	review.setReviewRestep(restep);
	review.setReviewRelevel(relevel);
	review.setReviewIp(reviewIp);
	review.setReviewStatus(reviewStatus);
	
	//게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블의 행으로 삽입하고 삽입행의 갯수를 
	//반환하는 ReviewDAO 클래스의 메소드 호출
	ReviewDAO.getDAO().insertReview(review);
	
	//페이지 이동 - 페이징 처리 및 조회 기능 관련 값 전달
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=review&work=review_list"
		+"&pageNum="+pageNum+"&pageSize="+pageSize+"&search="+search+"&keyword="+keyword);
%>    