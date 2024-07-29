<%@page import="xyz.itwill.dao.AdminNoticeDAO"%>
<%@page import="xyz.itwill.dto.AdminNoticeDTO"%>


<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@include file="/security/login_check.jspf" %>
<%
 String search=request.getParameter("search");
if(search == null) {//전달값이 없는 경우 - 조회기능을 사용하지 않은 경우
	search="";
}
String keyword=request.getParameter("keyword");//조회단어
if(keyword == null) {
	keyword="";
} 

List<AdminNoticeDTO> adminNotice=AdminNoticeDAO.getDAO().selectNoticeList(search,keyword);
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
    <title>Admin notice</title>
     <link rel="stylesheet" href="<%=request.getContextPath() %>/adminnotice/styles.css">
</head>
<body>


    

    <div class="container">
        <h1>관리자 페이지</h1>
        <form action="<%= request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=notice" method="post">
        	<select  name="search">
			<option id="searchInput" value="notice_title" <% if(search.equals("notice_title")) { %>selected<% } %>>&nbsp;title&nbsp;</option>
			<option value="notice_content" <% if(search.equals("notice_content")) { %>selected<% } %>>&nbsp;content&nbsp;</option>
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
                    <th>공지번호 </th>
                    <th>공지제목 </th>
                    <th>공지내용 </th>
                    <th>공지상태 </th>
                    <th>공지 작성일 </th>
                    <td>공지 삭제</td>
                    <td>공지 변경</td> 
                </tr>
         
            
      <%
                           for(AdminNoticeDTO notice : adminNotice){
       %>
                <tr>
                    <td><%=notice.getNoticeNo() %></td>
                    <td><%=notice.getNoticeTitle() %></td>
                    <td><%=notice.getNoticeContent()%></td>
                    <td><%=notice.getNoticeStatus() %></td>
                    <td><%=notice.getNoticeDate() %></td>
                 
            
                  
                    <td><button type="button" onclick="removeNotice(<%=notice.getNoticeNo()%>);" class="status-button" data-status="300">삭제</button></td>
                    <td><button type="button" onclick="updateNotice(<%=notice.getNoticeNo()%>);"class="status-button" data-status="100">변경</button></td>
                
                </tr>
        <% } %>
        
        </table>
    </div>
</div>
    <script type="text/javascript"> 
       document.getElementById("addBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=noticeAddForm";	
	}
	
 	function updateNotice(no) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=noticeUpdateForm&no="+no;	 
 	}
	
 	function removeNotice(no) {
 		if(confirm("notice를  정말로 삭제 하시겠습니까?")) {
 		location.href="<%=request.getContextPath()%>/index.jsp?workgroup=adminnotice&work=noticeRemove&no="+no; 
 		}
 	}
        
        
     </script> 

</body>
</html>