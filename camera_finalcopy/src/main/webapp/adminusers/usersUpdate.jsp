<%@page import="xyz.itwill.dao.AdminUsersDAO"%>
<%@page import="xyz.itwill.dto.AdminUsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%
      

            	//비정상적인 요청에 대한 응답 처리
            	if(request.getMethod().equals("GET")) {
            		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            		return;
            	}

            	//POST 방식으로 요청하여 전달된 값을 읽기 위한 문자형태 변경
            	request.setCharacterEncoding("utf-8");
            	
            	//전달값을 반환받아 저장
            	
            	int no=Integer.parseInt(request.getParameter("no"));
            	String id=request.getParameter("id");
            	String pw=request.getParameter("pw");
            	int status=Integer.parseInt(request.getParameter("status"));
            	
            	//UsersDTO 객체를 생성하여 전달값으로 필드값 변경
            	AdminUsersDTO users=new AdminUsersDTO();
            	users.setUsersNo(no);
            	users.setUsersId(id);
            	users.setUsersPw(pw);
            	users.setUsersStatus(status);
            	
            	//users정보(UsersDTO 객체)를 전달받아 Users 테이블에 저장된 행을 변경하고 변경행의 갯수를
            	//반환하는 UsersDAO 클래스의 메소드 호출
            	AdminUsersDAO.getDAO().updateUsers(users);
            	
        
            	/* response.sendRedirect(request.getContextPath()+"/index.jsp?workgroup=adminusers&work=users"); */
            	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminusers&work=users");
      %>