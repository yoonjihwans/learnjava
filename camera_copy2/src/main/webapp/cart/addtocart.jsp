<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page import="xyz.itwill.dto.CartDTO" %>
<%@ page import="xyz.itwill.dao.CartDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//수정240729
	UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");
	
	// 로그인 여부 확인
	if (loginUsers == null) {
	    // 로그인되지 않았거나 세션에 사용자 정보가 없는 경우 처리
	    response.sendRedirect(request.getContextPath() + "/users/users_login.jsp"); // 로그인 페이지로 리다이렉트
	    return; // 리다이렉트 후 종료
	}
	
	// 사용자가 전달한 데이터 가져오기
	 String id = loginUsers.getUsersId();
	int prodNo = Integer.parseInt(request.getParameter("prodNo"));
	int quantity = Integer.parseInt(request.getParameter("prodAmount"));
	
	// CartDTO 객체 생성 및 값 설정
	CartDTO cart = new CartDTO();
	cart.setCartproductNo(prodNo);
	cart.setCartusersId(loginUsers.getUsersId()); // 로그인 사용자의 ID 사용
	cart.setCartQuantity(quantity);
	
	// CartDAO를 사용하여 데이터베이스에 장바구니에 상품 추가
	CartDAO cartDAO = CartDAO.getDAO();
	cartDAO.insertCart(cart);
		
	
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
        <h1 class="header">장바구니 추가 결과</h1>
       	<p class="text">주문자 아아디: <%= id %>
        <p class="text">상품 번호: <%= prodNo %></p>
        <p class="text">주문 수량: <%= quantity %></p>
       

      <div class="button-container">
            <a class="button" href="<%= request.getContextPath() %>/index.jsp">메인 페이지로 이동</a>
            <a class="button" href="<%= request.getContextPath() %>/index.jsp?workgroup=cart&work=cart">장바구니로 이동</a>
        </div>
    </div>
</div>
    
</body>
</html>
