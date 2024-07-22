<%@page import="xyz.itwill.util.Utility"%>
<%@page import="xyz.itwill.dao.UsersDAO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 회원정보를 전달받아 MEMBER 테이블에 저장된 행을 변경하고 [/member/member_mypage.jsp] 문서를 
요청할 수 있는 URL 주소로 응답하는 JSP 문서 --%>    
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%@include file="/security/login_check.jspf" %>
<%
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(request.getMethod().equals("GET")) {//JSP 문서를 GET 방식으로 요청한 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	//전달값을 반환받아 저장
	int no=Integer.parseInt(request.getParameter("no"));
	String passwd=request.getParameter("passwd");
	if(passwd != null && !passwd.equals("")) {
		passwd=Utility.encrypt(passwd);
	}
	String name=request.getParameter("name");
	String email=request.getParameter("email");
	String phone=request.getParameter("phone1")+"-"+request.getParameter("phone2")+"-"+request.getParameter("phone3");
	String zipcode=request.getParameter("zipcode");
	String address1=request.getParameter("address1");
	String address2=request.getParameter("address2");
	//MemberDTO 객체를 생성하여 전달값으로 필드값 변경
	UsersDTO users=new UsersDTO();
	users.setUsersNo(no);
	users.setUsersPw(passwd);
	users.setUsersName(name);
	users.setUsersEmail(email);
	users.setUsersPhone(phone);
	users.setUsersZipcode(zipcode);
	users.setUsersAddress1(address1);
	users.setUsersAddress2(address2);
	
	//회원정보(MemberDTO 객체)를 전달받아 MEMBER 테이블에 저장된 행을 변경하고 변경행의 갯수를 
	//반환하는 MemberDAO 클래스의 메소드 호출
	UsersDAO.getDAO().updateUsers(users);
	
	if(passwd != null && !passwd.equals("")) {//전달값(비밀번호)이 있는 경우//전달값(비밀번호)이 있는 경우
		//회원정보(MemberDTO 객체)를 전달받아 MEMBER 테이블에 저장된 행의 비밀번호를 변경하고 
		//변경행의 갯수를 반환하는 MemberDAO 클래스의 메소드 호출
		UsersDAO.getDAO().updatePassword(users);
	}
	
	//세션에 저장된 로그인 관련 정보가 저장된 속성값 변경
	// => 회원번호를 전달받아 MEMBER 테이블에 저장된 하나의 행을 검색하여 MemberDTO 객체로
	//반환하는 MemberDAO 클래스의 메소드 호출하여 속성값 변경
	session.setAttribute("loginUsers", UsersDAO.getDAO().selectUsersByNo(no));
	
	//request 내장객체의 속성값으로 URL 주소를 저장하여 요청 JSP 문서(index.jsp)에게 제공
	// => 요청 JSP 문서(index.jsp)에서 URL 주소를 반환받아 리다이렉트 이동 
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=myaccount&work=myacct");
%>
