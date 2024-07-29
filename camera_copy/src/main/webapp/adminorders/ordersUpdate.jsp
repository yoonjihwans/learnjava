<%@page import="xyz.itwill.dao.AdminOrdersDAO"%>
<%@page import="xyz.itwill.dto.AdminOrdersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/security/login_check.jspf" %>
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
            	int prodno=Integer.parseInt(request.getParameter("prodno"));
            	String id=request.getParameter("id");
            	String name=request.getParameter("name");
            	String phone=request.getParameter("phone");
            	String email=request.getParameter("email");
            	String zipcode=request.getParameter("zipcode");
            	String address1=request.getParameter("address1");
            	String address2=request.getParameter("address2");
            	String req=request.getParameter("request");
            	String payment=request.getParameter("payment");
            	int amount=Integer.parseInt(request.getParameter("amount"));
            	int price=Integer.parseInt(request.getParameter("price"));
            	int status=Integer.parseInt(request.getParameter("status"));
            	
            	//UsersDTO 객체를 생성하여 전달값으로 필드값 변경
            	AdminOrdersDTO order=new AdminOrdersDTO();
            	order.setOrdersNo(no);
            	order.setOrdersProdNo(prodno);
            	order.setOrdersUsersId(id);
            	order.setOrdersUsersName(name);
            	order.setOrdersUsersPhone(phone);
            	order.setOrdersUsersEmail(email);
            	order.setOrdersUsersZipcode(zipcode);
            	order.setOrdersUsersAddress1(address1);
            	order.setOrdersUsersAddress1(address1);
            	order.setOrdersRequest(req);
            	order.setOrdersPayment(payment);
            	order.setOrdersCartAmount(amount);
            	order.setOrdersCartPrice(price);
            	order.setOrdersStatus(status);
            	
            	//users정보(UsersDTO 객체)를 전달받아 Users 테이블에 저장된 행을 변경하고 변경행의 갯수를
            	//반환하는 UsersDAO 클래스의 메소드 호출
            	AdminOrdersDAO.getDAO().updateOrder(order);
            	
        
            	/* response.sendRedirect(request.getContextPath()+"/index.jsp?workgroup=adminusers&work=users"); */
            	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminorders&work=orders");
      %>