<%@page import="xyz.itwill.dto.AdminProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 AdminProductDTO product=(AdminProductDTO)session.getAttribute("product");
 	if(product != null) {
 		session.removeAttribute("product");
 	}
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
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
	<form name="productForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=productAdd" method="post"
	enctype="multipart/form-data" >
	<table>
		<tr>
			<th class="title">상품타입</th>
			<td class="input">
				<input type="text" name="type" <% if(product != null) { %>value="<%=product.getProdType()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">상품이름</th>
			<td class="input">
				<input type="text" name="name" <% if(product != null) { %>value="<%=product.getProdName()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">상품가격 </th>
			<td class="input">
				<input type="text" name="price" <% if(product != null) { %>value="<%=product.getProdPrice()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">상품수량 </th>
			<td class="input">
				<input type="text" name="amount" <% if(product != null) { %>value="<%=product.getProdAmount()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지1 </th>
			<td class="input">
				<input type="file" name="image1"> <%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지2 </th>
			<td class="input">
				<input type="file" name="image2"> <%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지3 </th>
			<td class="input">
				<input type="file" name="image3"> <%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
			</td>
		</tr>
		<tr>
			<th class="title">상품이미지4 </th>
			<td class="input">
				<input type="file" name="image4"> <%-- <% if(product != null) { %>value="<%=product.getProdImage()%>"<% } %>> --%>
			</td>
		</tr>
		<tr>
			<th class="title">상품정보  </th>
			<td class="input">
				<input type="text" name="info" <% if(product != null) { %>value="<%=product.getProdInfo()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">Submit</button> 
				<button type="reset">reset</button> 
				<button type="button" id="listBtn">Back list</button> 
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