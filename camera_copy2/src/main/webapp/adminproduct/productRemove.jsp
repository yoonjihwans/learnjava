<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.AdminProductDAO"%>
<%@page import="xyz.itwill.dto.AdminProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if(request.getParameter("no")==null){
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
	return;	
}
int no=Integer.parseInt(request.getParameter("no"));

AdminProductDTO product=AdminProductDAO.getDAO().selectProductByNo(no);

if(product ==null){
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
	return;	
}
product.setProdType(0);
AdminProductDAO.getDAO().updateProduct(product);

if(product.getProdImage1() != null){ 
	String saveDirectory=request.getServletContext().getRealPath("/product_image");
	new File(saveDirectory, product.getProdImage1()).delete();
}
if(product.getProdImage2() != null){ 
	String saveDirectory=request.getServletContext().getRealPath("/product_image");
	new File(saveDirectory, product.getProdImage2()).delete();
}
if(product.getProdImage3() != null){ 
	String saveDirectory=request.getServletContext().getRealPath("/product_image");
	new File(saveDirectory, product.getProdImage3()).delete();
}
if(product.getProdImage4() != null){ 
	String saveDirectory=request.getServletContext().getRealPath("/product_image");
	new File(saveDirectory, product.getProdImage4()).delete();
}


/* response.sendRedirect(request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product"); */
request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product");
%>