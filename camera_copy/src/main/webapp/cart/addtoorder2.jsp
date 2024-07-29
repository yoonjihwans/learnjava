<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page import="xyz.itwill.dto.CartDTO" %>
<%@ page import="xyz.itwill.dao.CartDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//addtoorder2 상세페이지용
        //세션에서 로그인 사용자 정보 가져오기
    // 만약 필요하다면 다음과 같이 세션에서 상품 정보도 가져올 수 있습니다.
    ProductDTO product = (ProductDTO)session.getAttribute("product");
   UsersDTO loginUsers = (UsersDTO)session.getAttribute("loginUsers");
    
   
   // 로그인 여부 확인
     if(loginUsers == null) {
      request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=users&work=users_login");
      return;   
   }
  
  // 사용자가 전달한 데이터 가져오기
     String id = loginUsers.getUsersId();
     int prodNo = Integer.parseInt(request.getParameter("prodNo"));
      
   int finalQuantity;
   if (request.getParameter("finalQuantity") == null || request.getParameter("finalQuantity").isEmpty()) {
       finalQuantity = 1; // 기본값 설정
   } else {
       finalQuantity = Integer.parseInt(request.getParameter("finalQuantity"));
   }
      
    
    // CartDTO 객체 생성 및 값 설정
    CartDTO cart = new CartDTO();
    cart.setCartproductNo(prodNo);
    cart.setCartusersId(loginUsers.getUsersId()); // 로그인 사용자의 ID 사용
    cart.setCartQuantity(finalQuantity);

    // CartDAO를 사용하여 데이터베이스에 장바구니에 상품 추가
    CartDAO.getDAO().insertCart(cart);
  
    request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=cart&work=cartorder0719");
%>