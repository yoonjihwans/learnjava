<%@page import="xyz.itwill.dao.AdminProductDAO"%>
<%@page import="xyz.itwill.dto.AdminProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
    
   if(request.getParameter("no") == null) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
  	}

  	//전달값을 반환받아 저장
  	int no=Integer.parseInt(request.getParameter("no"));
  	
  	//번호를 전달받아 product 테이블에 저장된 하나의 행을 검색하여 검색된 회원정보(AdminProductDTO 
  	//객체)를 반환하는 AdminProductDAO 클래스의 메소드 호출
  	AdminProductDTO product=AdminProductDAO.getDAO().selectProductByNo(no);
  	
  	//비정상적인 요청에 대한 응답 처리
  	if(product == null) {
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
<title>Admin Product</title>
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
	<form name="productForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=productUpdate" method="post"
	enctype="multipart/form-data" >
		<input type="hidden" name="no" value="<%=product.getProdNo()%>"> 
		
	<table>
	
		<tr>
			<th class="title">상품타입</th>
			<td class="input">
				<%-- <input type="text" name="type" <% if(products != null) { %>value="<%=products.getProdType()%>"<% } %>> --%>
				<input type="text" name="type" value="<%=product.getProdType()%>">
			</td>
		</tr>
		<tr>
			<th class="title">상품이름</th>
			<td class="input">
				<%-- <input type="text" name="name" <% if(products != null) { %>value="<%=products.getProdName()%>"<% } %>> --%>
				<input type="text" name="name" value="<%=product.getProdName()%>">
			</td>
		</tr>
		<tr>
			<th class="title">상품가격 </th>
			<td class="input">
			<%-- 	<input type="text" name="price" <% if(products != null) { %>value="<%=products.getProdPrice()%>"<% } %>> --%>
				<input type="text" name="price" value="<%=product.getProdPrice()%>">
			</td>
		</tr>
		<tr>
			<th class="title">상품수량 </th>
			<td class="input">
			<%-- 	<input type="text" name="amount" <% if(products != null) { %>value="<%=products.getProdAmount()%>"<% } %>> --%>
				<input type="text" name="amount" value="<%=product.getProdAmount()%>">
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지1 </th>
			<td class="input">
				<input type="file" name="image1"> <br><%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
				<% if(product.getProdImage1() != null) { %>
				<div style="color: red;">이미지를 변경할 경우에만 파일을 입력해 주세요.</div>
				<img src="<%=request.getContextPath()%>/product_image/<%=product.getProdImage1()%>"width="200">
				 <% } %> 
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지2 </th>
			<td class="input">
				<input type="file" name="image2"> <br><%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
				<% if(product.getProdImage2() != null) { %>
				<div style="color: red;">이미지를 변경할 경우에만 파일을 입력해 주세요.</div>
				<img src="<%=request.getContextPath()%>/product_image/<%=product.getProdImage2()%>" width="200"> <% } %>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지3 </th>
			<td class="input">
				<input type="file" name="image3"> <br><%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
				<% if(product.getProdImage3() != null) { %>
				<div style="color: red;">이미지를 변경할 경우에만 파일을 입력해 주세요.</div>
				<img src="<%=request.getContextPath()%>/product_image/<%=product.getProdImage3()%>" width="200"> <% } %>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지4 </th>
			<td class="input">
				<input type="file" name="image4"> <br><%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
				<% if(product.getProdImage4() != null) { %>
				<div style="color: red;">이미지를 변경할 경우에만 파일을 입력해 주세요.</div>
				<img src="<%=request.getContextPath()%>/product_image/<%=product.getProdImage4()%>" width="200"> <% } %>
			
		
				
			</td>
		</tr>
		<tr>
			<th class="title">상품정보  </th>
			<td class="input">
				<%-- <input type="text" name="info" <% if(products != null) { %>value="<%=products.getProdInfo()%>"<% } %>> --%>
				<input type="text" name="info" value="<%=product.getProdInfo() %>">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">상품변경</button> 
				<button type="reset">초기화</button> 
				<button type="button" id="listBtn">상품목록</button> 
			</td>
		</tr>
	</table>
	</form>

	
	<script type="text/javascript">
	productForm.no.focus();

	
	document.getElementById("listBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=product";	
	} 
	</script>

</body>
</html>