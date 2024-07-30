<%@page import="xyz.itwill.dao.NoticeDAO"%>
<%@page import="xyz.itwill.dto.NoticeDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
  //비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
  	if(request.getParameter("noticeNo") == null) {//전달값이 없는 경우
  		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
  		return;
  	}
        
	int noticeNo=Integer.parseInt(request.getParameter("noticeNo"));
	String pageNum=request.getParameter("pageNum");
	String pageSize=request.getParameter("pageSize");
	
	NoticeDTO notice=NoticeDAO.getDAO().selectNoticeByNum(noticeNo);

	if(notice == null) {//검색된 게시글이 없는 경우 
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
    
	UsersDTO loginUsers=(UsersDTO)session.getAttribute("loginUsers");
    %>
<style type="text/css">
#review_detail {
	width: 500px;
	margin: 0 auto;

	height: 350px;
	margi-top:20px;
	
}

#review-detail-middle-box{
	width: 500px;
	height: 200px;
	
	margin-top:60px;
}

table {
    background-color: #e9ecef;
	border: 1px solid black;
	border-collapse: collapse;
	font-size: 17px;
	
}

th, td {
	border: 1px solid black;
	padding: 5px;	
}

th {
	background-color: gray;
	width: 100px;	
	color: white;
}

td {
	width: 400px;
}

.subject, .content {
	text-align: left;
}

.content {
	height: 300px;
	vertical-align: middle;
}

#review_menu {
	text-align: right;
	margin: 5px;
}
</style>

<div id="review_detail">
<div id="review-detail-middle-box">
	<h1 style="text-align: center; margin-bottom:20px; font-size:27px;">공지사항</h1>
	
	<%-- 검색된 게시글 출력 --%>
	<table>
		<tr>
			<th>작성자</th>
			<td>
				관리자
				
			</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=notice.getNoticeDate() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td>		
				<%=notice.getNoticeTitle() %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
		      		<%=notice.getNoticeContent().replace("\n", "<br>") %>
				<br>
			</td>
		</tr>
	</table>
	
	<%-- 링크를 제공하는 태그 출력 --%>
	<div id="review_menu">
		
		<button type="button" id="listBtn">글목록</button>
	</div>
	</div>
</div>

<script type="text/javascript">


$("#listBtn").click(function() {
	location.href="<%=request.getContextPath()%>/index.jsp?workgroup=notice&work=notice"
		+"&pageNum=<%=pageNum%>&pageSize=<%=pageSize%>";
});
</script>