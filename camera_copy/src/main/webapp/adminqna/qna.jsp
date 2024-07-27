
<%@page import="xyz.itwill.dao.AdminQnaDAO"%>
<%@page import="xyz.itwill.dto.AdminQnaDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
 String search=request.getParameter("search");
if(search == null) {//전달값이 없는 경우 - 조회기능을 사용하지 않은 경우
	search="";
}
String keyword=request.getParameter("keyword");//조회단어
if(keyword == null) {
	keyword="";
} 

List<AdminQnaDTO> adminQna=AdminQnaDAO.getDAO().selectQnaList(search,keyword);
/* UsersDTO users=(UsersDTO)session.getAttribute("users");
if(users.getUsersNo() != 9){
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
} */
%> 
    
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
<!--     <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
    <title>Admin Qna</title>
     <link rel="stylesheet" href="<%=request.getContextPath() %>/adminqna/styles.css">
</head>
<body>
    

    <div class="container">
        <h1>관리자 페이지</h1>
        <form action="<%= request.getContextPath()%>/index.jsp?workgroup=adminqna&work=qna" method="post">
        	<select  name="search">
			<option id="searchInput" value="notice_title" <% if(search.equals("notice_title")) { %>selected<% } %>>&nbsp;title&nbsp;</option>
			<option value="notice_content" <% if(search.equals("notice_content")) { %>selected<% } %>>&nbsp;content&nbsp;</option>
		</select>
		<input type="text" name="keyword" value="<%=keyword%>">
		<button type="submit" id="searchButton">검색</button>
        </form>
       
        <table class="member-table">
            
                <tr>
                    <th>QNA Number </th>
                    <th>QNA UsersNo </th>
                    <th>Users Name </th>
                    <th>Users Email </th>
                    <th>QNA Type </th>
                    <td>QNA Title</td>
                    <td>QNA Content</td> 
                    <td>QNA Status</td> 
                    <td>QNA Date</td> 
                    <td>reply</td> 
                     
                </tr>
         
            
      <%
                           for(AdminQnaDTO qna : adminQna){
       %>
                <tr>
                    <td><%=qna.getQnaNo() %></td>
                    <td><%=qna.getQnaUsersno() %></td>
                    <td><%=qna.getUsersName()%></td>
                    <td><%=qna.getUsersEmail() %></td>
                    <td><%=qna.getQnaType() %></td>
                    <td><%=qna.getQnaTitle() %></td>
                    <td><%=qna.getQnaContent() %></td>
                    <td><%=qna.getQnaStatus() %></td>
                    <td><%=qna.getQnaDate() %></td>
     
                    <td><button type="button" onclick="updateQna(<%=qna.getQnaNo() %>);"class="status-button" data-status="100">reply</button></td>
                
                </tr>
        <% } %>
        
        </table>
    </div>

    <script type="text/javascript"> 

	
 	function updateQna(no) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminqna&work=qnaReplyForm&no="+no;	 
 	}
	

        
        
     </script> 

</body>
</html>