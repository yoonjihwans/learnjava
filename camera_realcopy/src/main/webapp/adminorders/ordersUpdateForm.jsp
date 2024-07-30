<%@page import="xyz.itwill.dao.AdminOrdersDAO"%>
<%@page import="xyz.itwill.dto.AdminOrdersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/security/login_check.jspf" %>
<%
    
   if(request.getParameter("no") == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
  	}

  	//전달값을 반환받아 저장
  	int no=Integer.parseInt(request.getParameter("no"));
  	
  	//번호를 전달받아 product 테이블에 저장된 하나의 행을 검색하여 검색된 회원정보(AdminProductDTO 
  	//객체)를 반환하는 AdminProductDAO 클래스의 메소드 호출
  AdminOrdersDTO order=AdminOrdersDAO.getDAO().selectOrderByNo(no);
  	
  	//비정상적인 요청에 대한 응답 처리
  	if(order == null) {
  		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
  	}
  	
  	
   
  /* 	ProductDTO product=(ProductDTO)session.getAttribute("product");
  	if(product != null) {
  		session.removeAttribute("product");
  	} */
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin OrderList</title>
<style type="text/css">
h1 {
	margin: 0 auto;
	width: 300px; 
	text-align: center; 
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse; 	
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}

.title { width: 100px; }
.input { width: 200px; }
</style>
</head>
<body>
	<h1>상품추가</h1>
	<hr>
	<form name="productForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminorders&work=ordersUpdate" method="post">
		<%-- 
		<input type="hidden" name="no" value="<%=order.getOrdersNo()%>">  --%>
		
	<table>
	
		<tr>
			<th class="title">orderNo</th>
			<td class="input">
				<%-- <input type="text" name="type" <% if(products != null) { %>value="<%=products.getProdType()%>"<% } %>> --%>
				<input type="text" name="no" value="<%=order.getOrdersNo()%>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">orderProdNo</th>
			<td class="input">
				<%-- <input type="text" name="type" <% if(products != null) { %>value="<%=products.getProdType()%>"<% } %>> --%>
				<input type="text" name="prodno" value="<%=order.getOrdersProdNo()%>" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserId</th>
			<td class="input">
				<%-- <input type="text" name="name" <% if(products != null) { %>value="<%=products.getProdName()%>"<% } %>> --%>
				<input type="text" name="id" value="<%=order.getOrdersUsersId()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserName </th>
			<td class="input">
			<%-- 	<input type="text" name="price" <% if(products != null) { %>value="<%=products.getProdPrice()%>"<% } %>> --%>
				<input type="text" name="name" value="<%=order.getOrdersUsersName()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserPhone </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="phone" value="<%=order.getOrdersUsersPhone()%>">
			</td>
		</tr>
	
		<tr>
			<th class="title">orderUserEmail </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="email" value="<%=order.getOrdersUsersEmail()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserZipcode </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="zipcode" value="<%=order.getOrdersUsersZipcode()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserAddress1 </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="address1" value="<%=order.getOrdersUsersAddress1()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderUserAddress2 </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="address2" value="<%=order.getOrdersUsersAddress2()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderRequest </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="request" value="<%=order.getOrdersRequest()%>">
			</td>
		</tr>
	
		<tr>
			<th class="title">orderPayment </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="payment" value="<%=order.getOrdersPayment()%>">
			</td>
		</tr>
		<tr>
			<th class="title">orderCartAmount </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="amount" value="<%=order.getOrdersCartAmount()%>">
			</td>
		</tr>
	
		<tr>
			<th class="title">orderCartPrice </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="price" value="<%=order.getOrdersCartPrice()%>">
			</td>
		</tr>

		
		<tr>
			<th class="title">order status  </th>
			<td class="input">
				<%-- <input type="text" name="info" <% if(products != null) { %>value="<%=products.getProdInfo()%>"<% } %>> --%>
				<input type="text" name="status" value="<%=order.getOrdersStatus() %>">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">order변경</button> 
				<button type="reset">초기화</button> 
				<button type="button" id="listBtn">상품목록</button> 
			</td>
		</tr>
	</table>
	</form>

	
	<script type="text/javascript">
	productForm.no.focus();

	
	document.getElementById("listBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminorders&work=orders";	
	} 
	</script>

</body>
</html>