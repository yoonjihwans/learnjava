
<%@page import="xyz.itwill.dto.CartDTO"%>
<%@page import="xyz.itwill.dao.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@include file="/security/login_url.jspf" %> --%>
<%
	request.setCharacterEncoding("UTF-8");	



	String[] checkP=request.getParameterValues("checkP");
	String[] cart = checkP[0].split(",");
	
	for(int i = 0;i<cart.length;i++){
		int cartItem = Integer.parseInt(cart[i].trim()); // 문자열을 정수형으로 변환
	    CartDAO.getDAO().deleteCart(cartItem);
	}
	
	out.println("<script type='text/javascript'>");	
	out.println("location.href='"+request.getContextPath()+"/index.jsp?workgroup=cart&work=cart';");
	out.println("</script>");	
%>