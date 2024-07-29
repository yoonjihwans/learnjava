<%@page import="xyz.itwill.dao.AdminProductDAO"%>
<%@page import="xyz.itwill.dto.AdminProductDTO"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//비정상적인 요청에 대한 응답 처리
	if(request.getMethod().equals("GET")) {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	
		return;
	}
	//파일을 저장할 서버 디렉토리의 파일 시스템 경로를 반환받아 저장
	String saveDirectory=request.getServletContext().getRealPath("/product_image");
	
	//MultipartRequst 객체 생성 - 모든 전달파일이 서버 디렉토리에 자동으로 업로드 처리
	MultipartRequest multipartRequest=new MultipartRequest(request, saveDirectory
	, 20*1024*1024, "utf-8", new DefaultFileRenamePolicy());
	//POST 방식으로 요청하여 전달된 값을 읽기 위한 문자형태 변경
	
	//사용할지 말지
	/* request.setCharacterEncoding("utf-8"); */
	
	
	
	//전달값을productAddForm.jsp에서  반환 받아 저장
		int no=Integer.parseInt(multipartRequest.getParameter("no"));
	int type=Integer.parseInt(multipartRequest.getParameter("type"));
	String name=multipartRequest.getParameter("name");
	int price=Integer.parseInt(multipartRequest.getParameter("price"));
	int amount=Integer.parseInt(multipartRequest.getParameter("amount"));
	String image1=multipartRequest.getFilesystemName("image1");
	String image2=multipartRequest.getFilesystemName("image2");
	String image3=multipartRequest.getFilesystemName("image3");
	String image4=multipartRequest.getFilesystemName("image4");
    String info=multipartRequest.getParameter("info");
 

	
	AdminProductDTO product=new AdminProductDTO();
	product.setProdNo(no);
	product.setProdType(type);
	product.setProdName(name);
	product.setProdPrice(price);
	product.setProdAmount(amount);

	String removeProdImage1=AdminProductDAO.getDAO().selectProductByNo(no).getProdImage1();
	if(image1 != null) {
		product.setProdImage1(image1);
		
		new File(saveDirectory, removeProdImage1).delete();
	} else {
		product.setProdImage1(removeProdImage1);
	}
	
	String removeProdImage2=AdminProductDAO.getDAO().selectProductByNo(no).getProdImage2();

	if(image2 != null) {
		product.setProdImage2(image2);
		
		new File(saveDirectory, removeProdImage2).delete();
	} else {
		product.setProdImage2(removeProdImage2);
	}
	
	String removeProdImage3=AdminProductDAO.getDAO().selectProductByNo(no).getProdImage3();
	if(image3 != null) {
		product.setProdImage3(image3);
		
		new File(saveDirectory, removeProdImage3).delete();
	} else {
		product.setProdImage3(removeProdImage3);
	}

	String removeProdImage4=AdminProductDAO.getDAO().selectProductByNo(no).getProdImage4();
	
	if(image4 != null) {
		product.setProdImage4(image4);
		
		new File(saveDirectory, removeProdImage4).delete();
	} else {
		product.setProdImage4(removeProdImage4);
	}
	
	product.setProdInfo(info);
	
	
	/* if(ProductDAO.getDAO().selectProduct(no) != null) {//상품 이 중복된 경우
		session.setAttribute("message", "이미 사용중인 상품번호를 입력 하였습니다. 다시 입력해 주세요.");
		session.setAttribute("product", product);
		response.sendRedirect(request.getContextPath()+"/product/productAddForm.jsp");
		return;
	} */
	//임시 취소 
	/* session.setAttribute("product", product); */

	
	AdminProductDAO.getDAO().updateProduct(product);
	
/* 	response.sendRedirect(request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product"); */
request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product");
%>