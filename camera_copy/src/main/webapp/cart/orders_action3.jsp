<%@page import="xyz.itwill.dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.CartDAO"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dao.OrdersDAO"%>
<%@page import="xyz.itwill.dto.OrdersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	//세션에서 로그인 사용자 정보 가져오기
	UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");

	//로그인 여부 확인
	if (loginUsers == null) {
 	response.sendRedirect(request.getContextPath() + "/users/users_login.jsp");
 	return;
	}

	String userId = loginUsers.getUsersId();

	//CartDAO를 사용하여 사용자 카트에서 상품 목록 가져오기
	CartDAO cartDAO = CartDAO.getDAO();
	List<CartDTO> cartList = cartDAO.selectAllCartList(userId);

	//카트에 담긴 상품이 없으면 처리
	if (cartList == null || cartList.isEmpty()) {
	 System.out.println("카트에 담긴 상품이 없습니다.");
	 response.sendRedirect(request.getContextPath() + "/cart/empty_cart.jsp"); // 카트가 비어있는 페이지로 리다이렉트
	 return;
	}

	//요청 파라미터 가져오기
	String id = loginUsers.getUsersId();
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = loginUsers.getUsersEmail();
	String zipcode = request.getParameter("zipcode");
	String address1 = request.getParameter("address1");
	String address2 = request.getParameter("address2");
	String ordersrequest = request.getParameter("ordersrequest");
	String payment = request.getParameter("payment");
	int orderscartprice = Integer.parseInt(request.getParameter("price"));
	int orderstotalprice = Integer.parseInt(request.getParameter("selectedTotal"));
	//OrdersDAO 객체 생성
	OrdersDAO ordersDAO = OrdersDAO.getDAO();
	
	//카트 리스트를 반복하여 주문 처리
	for (CartDTO cartItem : cartList) {
	 OrdersDTO orders = new OrdersDTO();
	 orders.setOrdersProdNo(cartItem.getCartproductNo());
	 orders.setOrdersUsersId(userId);
	 orders.setOrdersUsersName(name);
	 orders.setOrdersUsersPhone(phone);
	 orders.setOrdersUsersEmail(email);
	 orders.setOrdersUsersZipcode(zipcode);
	 orders.setOrdersUsersAddress1(address1);
	 orders.setOrdersUsersAddress2(address2);
	 orders.setOrdersRequest(ordersrequest);
	 orders.setOrdersPayment(payment);
	 orders.setOrdersCartAmount(cartItem.getCartQuantity());
	 orders.setOrdersCartPrice(orderscartprice*cartItem.getCartQuantity());
	
	 // OrdersDAO를 통해 주문을 데이터베이스에 저장
	 ordersDAO.insertOrder(orders);
	 
	 
	 cartDAO.clearCart(loginUsers.getUsersId());
}
	
	
	
%>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니 추가 결과</title>
    <style>
        .container {
            width: 100%;
            min-height: 500px;
            border: 1px solid #e0e0e0; /* 경계선 색상 변경 */
            border-radius: 10px; /* 모서리 둥글게 */
            background-color: #fff; /* 배경색 흰색 */
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
            display: flex; /* Flexbox 사용 */
            justify-content: center; /* 가로 중앙 정렬 */
            align-items: center; /* 세로 중앙 정렬 */
            margin: 50px auto; /* 중앙 정렬 및 여백 설정 */
        }

        .content {
            width: 50%;
            height: auto; /* 높이를 자동으로 조절 */
            margin: 20px 0; /* 상하 여백 추가 */
            text-align: center;
            font-size: 20px;
            padding: 20px; /* 내부 패딩 추가 */
        }

        .header {
            color: #ffc107; /* 제목 색상 */
            margin-bottom: 20px; /* 하단 여백 */
            font-size: 24px; /* 제목 크기 조정 */
            font-weight: bold; /* 굵게 설정 */
        }

        .text {
            font-size: 18px; /* 글자 크기 */
            line-height: 1.5; /* 줄 간격 */
            margin: 10px 0; /* 위아래 여백 */
            color: #555; /* 텍스트 색상 조정 */
        }

   
        
        .button-container {
        display: flex; /* Flexbox 사용으로 가로 정렬 */
        justify-content: center; /* 가운데 정렬 */
        margin-top: 20px; /* 상단 여백 */
    }

    .button {
        display: inline-block; /* 블록 요소로 변경 */
        margin: 0 10px; /* 좌우 여백 추가 */
        padding: 10px 20px; /* 내부 패딩 추가 */
        text-decoration: none; /* 밑줄 제거 */
        color: white; /* 글자색 흰색 */
        background-color: #ffc107; /* 배경색 */
        border-radius: 5px; /* 모서리 둥글게 */
        transition: background-color 0.3s; /* 배경색 전환 효과 */
    }

    .button:hover {
        background-color: #45a049; /* 마우스 오버 시 색상 변경 */
    }

    </style>
</head>
<body>
<div class="container">
    <div class="content">
       <h1 class="header">주문 결과</h1>
    
    <p class="text">주문자 아이디: <%= id %></p>
    <p class="text">수령인 이름: <%= name %></p>
    <p class="text">이메일 : <%= email %></p>
    <p class="text">전화번호: <%= phone %></p>
    <p class="text"p>우편번호: <%= zipcode %></p>
    <p class="text">기본 주소: <%= address1 %></p>
    <p class="text">상세 주소: <%= address2 %></p>
    <p class="text"p>배송 요청 사항: <%= ordersrequest %></p>
    <p class="text">결제 수단: <%=payment %></p>    
    <p class="text">총 결제 가격 : <%=orderstotalprice %></p>
    <br>

        <div class="button-container">
          <a class="button" href="<%= request.getContextPath() %>/index.jsp">메인 페이지로 이동</a>
   	 	</div>
    </div>
</div>
    
</body>
</html>
