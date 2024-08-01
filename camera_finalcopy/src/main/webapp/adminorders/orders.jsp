<%@page import="xyz.itwill.dao.AdminOrdersDAO"%>
<%@page import="xyz.itwill.dto.AdminOrdersDTO"%>
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

List<AdminOrdersDTO> orderList=AdminOrdersDAO.getDAO().selectOrdersList(search,keyword);

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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/adminorders/styles.css">
</head>

<body>
  

    <div class="container">
        <h1>관리자 페이지</h1>
        <form action="<%= request.getContextPath()%>/index.jsp?workgroup=adminorders&work=orders" method="post">
        	<select  name="search">
			<option id="searchInput" value="ORDERS_USERS_NAME" <% if(search.equals("ORDERS_USERS_NAME")) { %>selected<% } %>>&nbsp;ORDERS_USERS_NAME&nbsp;</option>
			<option value="Orders_users_id" <% if(search.equals("Orders_users_id")) { %>selected<% } %>>&nbsp;userId&nbsp;</option>
		</select>
		<input type="text" name="keyword" value="<%=keyword%>">
		<button type="submit" id="searchButton">검색</button>
        </form>
       
     <div class="table-container">
        <table class="member-table">
            
                <tr>
                    <th>orderNo</th>
                    <th>ordertProdNo</th>
                    <th>orderUserId</th>
                    <th>orderUserName</th>
                    <th>orderUserPhone</th>
                    <th>orderUserEmail</th>
                    <th>orderUserZipcode</th>
                    <th>orderUserAddress1</th>
                    <th>orderUserAddress2</th>
                    <th>orderRequest</th>
                    <th>orderPayment</th>
                    <th>orderCartAmount</th>
                    <th>orderCartPrice</th>
                    <th>orderDate</th>
                    <th>orderStatus</th>
                    <td>상품삭제</td>
                    <td>상품변경</td>
                </tr>
         
            
      <%
                           for(AdminOrdersDTO order : orderList){
                           %>
                <tr>
                    <td><%=order.getOrdersNo() %></td>
                    <td><%=order.getOrdersProdNo() %></td>
                    <td><%=order.getOrdersUsersId() %></td>
                    <td><%=order.getOrdersUsersName() %></td>
                    <td><%=order.getOrdersUsersPhone() %></td>
                    <td><%=order.getOrdersUsersEmail() %></td>
                    <td><%=order.getOrdersUsersZipcode() %></td>
                    <td><%=order.getOrdersUsersAddress1() %></td>
                    <td><%=order.getOrdersUsersAddress2() %></td>
                    <td><%=order.getOrdersRequest() %></td>
                    <td><%=order.getOrdersPayment() %></td>
                    <td><%=order.getOrdersCartAmount() %></td>
                    <td><%=order.getOrdersCartPrice() %></td>
                    <td><%=order.getOrdersDate().substring(0, 10) %></td>
                    <td><%=order.getOrdersStatus() %></td>
                    
                  
                    <td><button type="button" onclick="removeProduct(<%=order.getOrdersNo() %>);" class="status-button" data-status="300">삭제</button></td>
                    <td><button type="button" onclick="updateProduct(<%=order.getOrdersNo() %>);"class="status-button" data-status="100">변경</button></td>
                
                </tr>
        <% } %>
        
        </table>
    </div>
</div>
    <script type="text/javascript"> 

	
 	function updateProduct(no) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminorders&work=ordersUpdateForm&no="+no;	 
 	}
	
 	function removeProduct(no) {
 		if(confirm("주문 을  정말로 삭제 하시겠습니까?")) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminproduct&work=ordersRemove&no="+no; 
 		}
 	}
        
        
     </script> 

</body>
</html>