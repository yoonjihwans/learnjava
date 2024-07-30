<%@page import="xyz.itwill.dao.AdminUsersDAO"%>
<%@page import="xyz.itwill.dto.AdminUsersDTO"%>
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

	
	List<AdminUsersDTO> usersList=AdminUsersDAO.getDAO().selectUsersList(search,keyword);
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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/adminusers/styles.css">>
</head>
<body>
  <%--  <form id="join" action="<%=request.getContextPath() %>/index.jsp?workgroup=member&work=member_join_action" method="post">  --%>

    <div class="container">
        <h1>관리자 페이지</h1>
        
          <form action="<%= request.getContextPath()%>/index.jsp?workgroup=adminusers&work=users" method="post">
        	<select  name="search">
			<option id="searchInput" value="users_id" <% if(search.equals("users_id")) { %>selected<% } %>>&nbsp;id&nbsp;</option>
			<option id="searchInput" value="users_name" <% if(search.equals("users_name")) { %>selected<% } %>>&nbsp;usersName&nbsp;</option>
			<option id="searchInput" value="USERS_STATUS" <% if(search.equals("USERS_STATUS")) { %>selected<% } %>>&nbsp;status&nbsp;</option>
		</select>
		<input type="text" name="keyword" value="<%=keyword%>">
		<button type="submit" id="searchBut">검색</button>
        </form>
   <div class="table-container">
       
        <table class="member-table">
            
                <tr>
                	<th>UserNo</th>
                    <th>ID</th>
                    <th>PASSWORD</th>
                    <th>NAME</th>
                    <th>ZIPCODE</th>
                    <th>ADDRESS1</th>
                    <th>ADDRESS2</th>
                    <th>PHONE</th>
                    <th>EMAIL</th>
                    <td>SIGHDATE</td>
                    <td>LAST LOGIN</td>
                    <td>STATUS</td>
                    <td>삭제</td>
                    <td>변경</td>
                </tr>
            
      <%
                  for(AdminUsersDTO users: usersList){
                  %>
                <tr>
                    <td><%=users.getUsersNo() %></td>
                    <td><%=users.getUsersId() %></td>
                    <td><%=users.getUsersPw() %></td>
                    <td><%=users.getUsersName() %></td>
                    <td><%=users.getUsersZipcode() %></td>
                    <td><%=users.getUsersAddress1() %></td>
                    <td><%=users.getUsersAddress2() %></td>
                    <td><%=users.getUsersPhone() %></td>
                    <td><%=users.getUsersEmail() %></td>
                    <td><%=users.getUsersSigndate().substring(0, 10) %></td>
                    <td><%=users.getUsersLastLogin() %></td>
                    <td><%=users.getUsersStatus() %></td>
                    <td><button type="button" onclick="removeUser(<%=users.getUsersNo()%>);" class="status-button" data-status="300">삭제</button></td>
                    <td><button type="button" onclick="updateUsers(<%=users.getUsersNo()%>);"class="status-button" data-status="100">변경</button></td>
                
                </tr>
        <% } %>
        
        </table>
    </div>
</div>
    <script type="text/javascript"> 

	function updateUsers(no) {
		//alert(no);
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminusers&work=usersUpdateForm&no="+no;	 
	}
	
 	function removeUser(no) {
		if(confirm("회원를 정말로 삭제 하시겠습니까?")) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminusers&work=usersStatus&no="+no; 
	}
	}
        
        
     </script> 

</body>
</html>