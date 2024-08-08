<%@page import="xyz.itwill.dao.MyMemberXMLDAO"%>
<%@page import="xyz.itwill.dto.MyMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String id=request.getParameter("id");
	String name=request.getParameter("name");
	String phone=request.getParameter("phone");
	String email=request.getParameter("email");
	
	MyMember member=new MyMember();
	member.setId(id);
	member.setName(name);
	member.setPhone(phone);
	member.setEmail(email);
	
	try {
		//SqlSession 객체를 사용해 SQL 명령을 DNMS 서버에 전달하여 실행한 경우 문제가 발생되면
		//PersistenceException 발생 - 예외 처리
		MyMemberXMLDAO.getDAO().insertMyMember(member);
		response.sendRedirect("member_display.jsp");
	} catch(Exception e) {
		//PK 제약조건을 위반한 경우 - 아이디 중복
		out.println("<script type='text/javascript'>");
		out.println("alert('이미 사용중인 아이디를 입력해 회원등록이 불가능합니다.');");
		out.println("history.go(-1);");
		out.println("</script>");
	}
%>









