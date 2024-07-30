<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dao.AdminOrdersDAO"%>
<%@page import="xyz.itwill.dto.AdminOrdersDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <%@include file="/security/login_url.jspf" %>    
    
<%
 String search=request.getParameter("search");
if(search == null) {//전달값이 없는 경우 - 조회기능을 사용하지 않은 경우
	search="";
}
String keyword=request.getParameter("keyword");//조회단어
if(keyword == null) {
	keyword="";
} 

List<AdminOrdersDTO> adminOrder=AdminOrdersDAO.getDAO().selectOrderByStatus(search,keyword);
/* UsersDTO users=(UsersDTO)session.getAttribute("users");
if(users.getUsersNo() != 9){
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
} */




%>     

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#account-box{width: 1300px; height: 700px; border: 1px solid green; margin: 0 auto;}
#acc1-box{width: 200px; height: 600px; border: 1px solid black; float: left;}
#acc2-box{width: 1090px; height: 600px; border: 1px solid black; float: right; overflow-y: auto}

#acc1-box nav ul{border: 1px solid red; height: 400px; margin: 0 auto; margin-top: 100px;;}
#acc1-box nav ul li{border: 1px solid blue; width: 200px; text-align: center; height: 70px; }

#acc1-box nav ul li a{margin-top:20px; font-size: 23px; color: black;}

#good { 
    color: #000;
    display:inline-block; 
    margin:0;
    text-transform:uppercase; }
    #good:after {
    display:block;
    content: '';
    border-bottom: solid 3px #BBDEF0;  
    transform: scaleX(0);  
    transition: transform 250ms ease-in-out;
  }
  #good:hover:after { transform: scaleX(1); }
  #good:after{ transform-origin:100% 50%; }
  #good:after{  transform-origin:  0% 50%; }
  

.board {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top:20px; 
    font-size:17px;
}
.board th, .board td {
    border: 1px solid #dee2e6;
    padding: 12px;
    text-align: center;
}
.board th {
    background-color: gray;
    color: white;
    font-weight: bold;
}
.board tr:nth-child(even) {
    background-color: #f2f2f2;
}
.board tr:hover {
    background-color: #e9ecef;
}
.hidden-column {
    display: none;
}
 .message-box {
            width: 100%;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 24px;
            color: #333;
            background-color: #f8f9fa;
            text-align: center;
            }
            
#orderlist{
 width: 900px;
 margin: 0 auto;            
}

</style>
</head>
<body>
<div id="account-box">
    <div id="acc1-box">
        <nav>
            <ul>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct" id="good">회원정보</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_review" id="good">리뷰</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_qna" id="good">Q&A</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_orderlist" id="good">주문내역</a></li>
            </ul>
        </nav>
    </div>
   
    <div id="acc2-box">
    <div id="orderlist">
     <form action="<%= request.getContextPath() %>/index.jsp?workgroup=myaccount&work=myacct_orderlist" method="post" style="position: relative; top:40px;">
        <select name="search" style="font-size:17px;">
            <option value="prod_name" <% if (search.equals("prod_name")) { %>selected<% } %>>&nbsp;productName&nbsp;</option>
            <option value="Orders_users_id" <% if (search.equals("Orders_users_id")) { %>selected<% } %>>&nbsp;userId&nbsp;</option>
        </select>
        <input type="text" name="keyword" value="<%= keyword %>" style="height: 20px; position: relative; bottom:2px;">
        <button type="submit" id="searchButton" style="color: white; background-color: white; border:1px solid white;">검색</button>
    </form>
        <h1 style="font-size: 30px; margin-top: 10px; text-align: center;">OrderList</h1>
        <table class="board">
            <thead>
                <tr>
                    <th class="hidden-column"></th>
                    <th class="hidden-column"></th>
                    <th>상품</th>
                    <th>가격</th>
                    <th>수량</th>
                    <th>총금액</th>
                    <th>배송상태</th>
                    <th>리뷰작성</th>
                </tr>
            </thead>
            <% for (AdminOrdersDTO order : adminOrder) { %>
            
            <%--   <%  out.println("주문 사용자 ID: " + order.getOrdersUsersId());%>  --%>
          <% if (loginUsers.getUsersId().equals(order.getOrdersUsersId())) { %>
    <tr>
        <td class="hidden-column"><%= order.getOrdersUsersId() %></td>
        <td class="hidden-column"><%= order.getOrdersProdNo() %></td>
        <td><%= order.getProdName() %></td>
        <td><%= order.getProdPrice() %></td>
        <td><%= order.getOrdersCartAmount() %></td>
        <td><%= order.getOrdersCartPrice() %></td>
        <% if (order.getOrdersStatus() == 0) { %>
        <td>배송 준비중입니다</td>
        <% } else if (order.getOrdersStatus() == 1) { %>
        <td>배송이 완료되었습니다</td>
        <% } %>
        <td>
            <% if (order.getOrdersStatus() == 1) { %>
            <form action="<%= request.getContextPath() %>/index.jsp" method="get">
                <input type="hidden" name="workgroup" value="review">
                <input type="hidden" name="work" value="review_write">
                <input type="hidden" name="reviewProdNo" value="<%= order.getOrdersProdNo() %>">
                <input type="hidden" name="redirect" value="myacct_review">
                <button type="submit">글쓰기</button>
            </form>
            <% } %>
        </td>
    </tr>
<% } %>

            <% } %>
        </table>
        </div>
    </div>
</div>
</body>
</html>
