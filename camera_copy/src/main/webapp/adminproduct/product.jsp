<%@page import="xyz.itwill.dto.AdminUsersDTO"%>
<%@page import="xyz.itwill.dto.AdminProductDTO"%>
<%@page import="xyz.itwill.dao.AdminProductDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file="/security/login_check.jspf" %>
<%
String search=request.getParameter("search");
if(search==null){
	search="";
}
String keyword=request.getParameter("keyword");
if(keyword==null){
	keyword="";
}


/* int totalProduct=AdminProductDAO.getDAO().selectTotalProduct(search,keyword); */
/* List<AdminProductDTO> productList=AdminProductDAO.getDAO().selectProductList(); */
List<AdminProductDTO> productList=AdminProductDAO.getDAO().selectProductSearchList(search,keyword);
/* UsersDTO users=(UsersDTO)session.getAttribute("users");
if(users.getUsersNo() != 9){
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
} */
%> 
    
<!DOCTYPE html>
<html lang="en">

<style type="text/css">
    .table-container {
            max-width: 100%;
            overflow-x: auto;
            overflow-y: auto;
            height: 500px; /* Adjust this height as needed */
        }
</style>
<head>

    <meta charset="UTF-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <title>Admin Page</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/adminproduct/styles.css">
</head>

<body>
    <!-- <form id="join" action="<%=request.getContextPath() %>/index.jsp?workgroup=member&work=member_join_action" method="post"> -->

    <div class="container">
        <h1>관리자 페이지</h1>
        <form action="<%= request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=product" method="post">
        	<select  name="search">
			<option id="searchInput" value="prod_name" <% if(search.equals("prod_name")) { %>selected<% } %>>&nbsp;name&nbsp;</option>
			<option value="prod_type" <% if(search.equals("prod_type")) { %>selected<% } %>>&nbsp;type&nbsp;</option>
		</select>
		<input type="text" name="keyword" value="<%=keyword%>">
		<button type="submit" id="searchButton">검색</button>
        </form>
       
<div>
    <button type="button" id="addBtn" class="status-button" data-status="100">추가</button>
    
</div>
          <div class="table-container">
        <table class="member-table">
            
                <tr>
                    <th>상품코드</th>
                    <th>상품종류</th>
                    <th>상품명</th>
                    <th>상품가격</th>
                    <th>상품수량</th>
                    <th>상품이미지1</th>
                    <th>상품이미지2</th>
                    <th>상품이미지3</th>
                    <th>상품이미지4</th>
                    <th>상품설명</th>
                    <th>상품등록일</th>
                    <td>상품삭제</td>
                    <td>상품변경</td>
                </tr>
         
            
      <%
                           for(AdminProductDTO product : productList){
                           %>
                <tr>
                    <td><%=product.getProdNo() %></td>
                    <td><%=product.getProdType() %></td>
                    <td><%=product.getProdName() %></td>
                    <td><%=product.getProdPrice() %></td>
                    <td><%=product.getProdAmount() %></td>
                    <td><%=product.getProdImage1() %></td>
                    <td><%=product.getProdImage2() %></td>
                    <td><%=product.getProdImage3() %></td>
                    <td><%=product.getProdImage4() %></td>
                    <td><%=product.getProdInfo() %></td>
                    <td><%=product.getProdInDate().substring(0, 10) %></td>
                  
                    <td><button type="button" onclick="removeProduct(<%=product.getProdNo()%>);" class="status-button" data-status="300">삭제</button></td>
                    <td><button type="button" onclick="updateProduct(<%=product.getProdNo()%>);"class="status-button" data-status="100">변경</button></td>
                
                </tr>
        <% } %>
        
        </table>
    </div>
</div>
    <script type="text/javascript"> 
       document.getElementById("addBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=productAddForm";	
	}
	
 	function updateProduct(no) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=productUpdateForm&no="+no;	 
 	}
	
 	function removeProduct(no) {
 		if(confirm("상품을  정말로 삭제 하시겠습니까?")) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=productRemove&no="+no; 
 		}
 	}
        
        
     </script> 

</body>
</html>