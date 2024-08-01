
<%@page import="xyz.itwill.dao.AdminProductDAO"%>
<%@page import="xyz.itwill.dto.AdminProductDTO"%>
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
	product.setProdType(type);
	product.setProdName(name);
	product.setProdPrice(price);
	product.setProdAmount(amount);
	product.setProdImage1(image1);
	product.setProdImage2(image2);
	product.setProdImage3(image3);
	product.setProdImage4(image4);
	product.setProdInfo(info);
	
	
	
	
	/* if(ProductDAO.getDAO().selectProduct(no) != null) {//상품 이 중복된 경우
		session.setAttribute("message", "이미 사용중인 상품번호를 입력 하였습니다. 다시 입력해 주세요.");
		session.setAttribute("product", product);
		response.sendRedirect(request.getContextPath()+"/product/productAddForm.jsp");
		return;
	} */
 	session.setAttribute("product", product); 

	
	AdminProductDAO.getDAO().insertProduct(product);

	/* response.sendRedirect(request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product"); */
	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=adminproduct&work=product");
%>