﻿<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- REVIEW 테이블에 저장된 행을 검색하여 검색된 행을 HTML 태그에 포함해 응답하는 JSP 문서 --%>
<%-- => REVIEW 테이블에 저장된 행을 페이지 단위로 구분하여 검색해 응답 처리 - 페이징 처리 --%>
<%-- => [페이지번호] 태그를 클릭한 경우 [/review/review_list.jsp] 문서를 요청하여 페이지 이동 
- 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 : 조회기능을 유지하기 위해 조회대상과 조회단어 전달 --%>
<%-- => [게시글갯수] 태그의 입력값을 변경한 경우 [/review/review_list.jsp] 문서를 요청하여 페이지 이동    
- 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%-- => [검색]태그를 클릭한 경우 [/review/review_list.jsp] 문서를 요청하여 페이지 이동
- 조회대상, 조회단어 전달 : 페이지번호와 게시글갯수는 기본값 사용 --%>
<%-- => [글쓰기] 태그를 클릭한 경우 [/review/review_write.jsp] 문서를 요청하여 페이지 이동 
- 로그인 사용자에게만 태그를 출력하여 링크가 제공되도록 작성 --%>
<%-- 게시글의 [글제목]을 클릭한 경우 [/review/review_detail.jsp] 문서를 요청하여 페이지 이동
- 글번호, 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%
	//게시글 조회기능에 필요한 전달값(조회대상과 조회단어)을 반환받아 저장
	String search=request.getParameter("search");//조회대상
	if(search == null) {//전달값이 없는 경우 - 조회기능을 사용하지 않은 경우
		search="";
	}
	
	String keyword=request.getParameter("keyword");//조회단어
	if(keyword == null) {
		keyword="";
	}
	
	//페이징 처리에 필요한 전달값(페이지번호와 게시글갯수)을 반환받아 저장
	int pageNum=1;//페이지번호 - 전달값이 없는 경우 사용할 기본값 저장
	if(request.getParameter("pageNum") != null) {//전달값이 있는 경우
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
	}
	
	int pageSize=10;//게시글갯수 - 전달값이 없는 경우 사용할 기본값 저장
	if(request.getParameter("pageSize") != null) {//전달값이 있는 경우
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
	}
	
	//조회정보(조회대상과 조회단어)를 전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 
	//행의 갯수를 검색하여 반환하는 RviewDAO 클래스의 메소드 호출
	int totalReview=ReviewDAO.getDAO().selectTotalReview(search, keyword);//게시글의 총갯수
	        	
	//페이지의 총갯수를 계산하여 저장
	//int totalPage=totalReview/pageSize+totalReview%pageSize==0?0:1;
	int totalPage=(int)Math.ceil((double)totalReview/pageSize);
	
	//전달받은 페이지번호가 비상적인 경우 첫번째 페이지를 요청할 수 있는 기본값 저장
	if(pageNum <= 0 || pageNum > totalPage) {
		pageNum=1;
	}
	
	//페이지번호에 대한 게시글의 시작 행번호를 계산하여 저장
	//ex) 1Page : 1, 2Page : 11, 3Page : 21, 4Page : 31, ...
	int startRow=(pageNum-1)*pageSize+1;

	//페이지번호에 대한 게시글의 종료 행번호를 계산하여 저장
	//ex) 1Page : 10, 2Page : 20, 3Page : 30, 4Page : 40, ...
	int endRow=pageNum*pageSize;

	//마지막 페이지의 게시글의 종료 행번호가 게시글의 총갯수보다 많은 경우 종료 행변호 변경
	if(endRow > totalReview) {
		endRow=totalReview;
	}

	//페이징 관련 정보(시작행번호, 종료행번호)와 게시글 조회기능 관련 정보(조회대상과 조회단어)를
	//전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 행을 페이징 처리로 검색하여
	//List 객체를 반환하는 ReviewDAO 클래스의 메소드 호출
	List<ReviewDTO> reviewList=ReviewDAO.getDAO().selectReviewList(startRow, endRow, search, keyword);
	
	//세션에 저장된 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
	// => 로그인 사용자에게만 글쓰기 권한 제공
	// => 게시글이 비밀글인 경우 로그인 사용자가 게시글 작성자이거나 관리자인 경우에만 권한 제공
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	//서버의 현재 날짜와 시간이 저장된 Date 객체를 생성하여 SimpleDateFormat 객체에 저장된
	//패턴의 문자열로 변환하여 저장
	// => 게시글의 작성날짜와 비교하여 게시글 작성날짜를 다르게 출력되도록 응답 처리
	String currentDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	//게시글에 출력될 일련번호 시작값을 계산하여 저장
	// => 게시글의 총갯수가 91개인 경우 => 1Page : 91, 2Page : 81, 3Page : 71, ...\
	int displayNum=totalReview-(pageNum-1)*pageSize;
%>                   
<style type="text/css">
#review_list {
	width: 1000px;
	margin: 0 auto;
	text-align: center;
}

#review_title {
	font-size: 1.2em;
	font-weight: bold;
}

table {
	margin: 5px auto;
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	background: black;
	color: white;
}

td {
	border: 1px solid black;
	text-align: center;	
}

.subject {
	text-align: left;
	padding: 5px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

#review_list a:hover {
	text-decoration: none; 
	color: blue;
	font-weight: bold;
}

.subject_hidden {
	background: black;
	color: white;
	font-size: 14px;
	border: 1px solid black;
	border-radius: 4px;
}

#page_list {
	font-size: 1.1em;
	margin: 10px;
}

#page_list a:hover {
	font-size: 1.3em;
}
</style>

<h1>제품후기</h1>
<div id="review_list">
	<%-- 검색된 게시글의 총갯수 출력 --%>
	<div id="review_title">제품후기(<%=totalReview %>)</div>
	
	<div style="text-align: right;">
		게시글갯수 : 
		<select id="pageSize">
			<option value="10" <% if(pageSize==10) { %> selected <% } %>>&nbsp;10개&nbsp;</option>	
			<option value="20" <% if(pageSize==20) { %> selected <% } %>>&nbsp;20개&nbsp;</option>	
			<option value="50" <% if(pageSize==50) { %> selected <% } %>>&nbsp;50개&nbsp;</option>	
			<option value="100" <% if(pageSize==100) { %> selected <% } %>>&nbsp;100개&nbsp;</option>	
		</select>
		&nbsp;&nbsp;&nbsp;
		<% if(loginMember != null) {//로그인 사용자인 경우 %>
			<button type="button" id="writeBtn">글쓰기</button>
		<% } %>	
	</div>
	
	<%-- 검색된 게시글 목록 출력 --%>	
	<table>
		<tr>
			<th width="100">글번호</th>
			<th width="500">제목</th>
			<th width="100">작성자</th>
			<th width="100">조회수</th>
			<th width="200">작성일</th>
		</tr>
		
		<% if(totalReview == 0) {//검색된 게시글이 없는 경우 %> 
			<tr>
				<td colspan="5">검색된 게시글이 없습니다.</td>
			</tr>
		<% } else { %>
			<%-- List 객체의 요소값(ReviewDTO 객체)을 차례대로 제공받아 변수에 저장하는 반복문 --%>
			<% for(ReviewDTO review : reviewList) { %>
			<tr>
				<%-- 게시글의 일련번호 출력 --%>
				<td><%=displayNum %></td>
				<% displayNum--; %><%-- 게시글의 일련번호를 1씩 감소하여 저장 --%>
				
				<%-- 게시글 제목 출력 --%>
				<td class="subject">
					<%-- 게시글이 답글인 경우에 대한 응답 처리 --%>
					<% if(review.getReviewRestep() != 0) {//답글인 경우 %>
						<span style="margin-left: <%=review.getReviewRelevel()*20%>px;">┖[답글]</span>
					<% } %>
					
					<%-- 게시글 상태를 비교하여 제목 출력 --%>
					<%
						String url=request.getContextPath()+"/index.jsp?workgroup=review&work=review_detail"
							+"&reviewNum="+review.getReviewNum()+"&pageNum="+pageNum+"&pageSize="+pageSize
							+"&search="+search+"&keyword="+keyword;
					%>
					<% if(review.getReviewStatus() == 1) {//일반글인 경우 %>
						<a href="<%=url%>"><%=review.getReviewSubject() %></a>
					<% } else if(review.getReviewStatus() == 2) {//비밀글인 경우 %>
						<span class="subject_hidden">
						<%--로그인 사용자가 게시글 작성자이거나 관리자인 경우 제목 출력 --%>
						<% if(loginMember != null && (loginMember.getMemberNum() == 
							review.getReviewMemberNum() || loginMember.getMemberAuth() == 9)) { %>
							<a href="<%=url%>"><%=review.getReviewSubject() %></a>
						<% } else { %>
							게시글 작성자 또는 관리자만 확인 가능합니다.
						<% } %>	
						</span>
					<% } else if(review.getReviewStatus() == 0) {//삭제글인 경우 %>
						<span class="subject_hidden">
							게시글 작성자 또는 관리자에 의해 삭제된 게시글입니다.
						</span>
					<% } %>
				</td>
				
			<% if(review.getReviewStatus() != 0) {//삭제글이 아닌 경우  %>	
				<%-- 게시글 작성자(회원이름) 출력 --%>
				<td><%=review.getMemberName() %></td>
				
				<%-- 게시글 조횟수 출력 --%>
				<td><%=review.getReviewCount() %></td>
				
				<%-- 게시글 작성일 출력 --%>
				<td>
				<%-- 오늘 작성된 게시글인 경우 시간만 출력하고 아닌 경우 날짜와 시간 출력 --%>
					<% if(currentDate.equals(review.getReviewRegisterDate().substring(0, 10))) { %>
						<%=review.getReviewRegisterDate().substring(11) %>	
					<% } else { %>
						<%=review.getReviewRegisterDate() %>	
					<% } %>
				</td>
			<% } else {//삭제글인 경우 %>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>		
			<% } %>
			</tr>
			<% } %>
		<% } %>
	</table>
	
	<%-- 페이지 번호 출력 --%>
	<%
		String myUrl=request.getContextPath()+"/index.jsp?workgroup=review&work=review_list"
			+"&pageSize="+pageSize+"&search="+search+"&keyword="+keyword;
	%>
	<div id="page_list">
		<% for(int i = 1 ; i <= totalPage ; i++) { %>
			<%-- 현재 처리중인 페이지 번호와 출력된 페이지 번호가 같지 않은 경우 링크 제공 --%>
			<% if(pageNum != i) { %>
				<a href="<%=myUrl%>&pageNum=<%=i%>">[<%=i %>]</a>
			<%} else { %>
				[<%=i %>]
			<% } %>
		<% } %>
	</div>
	
	<form action="<%=request.getContextPath() %>/index.jsp?workgroup=review&work=review_list" method="post">
		<%-- select 태그로 전달되는 값은 반드시 컬럼명을 전달되도록 작성 --%>
		<select name="search">
			<option value="member_name" <% if(search.equals("member_name")) { %>selected<% } %>>&nbsp;작성자&nbsp;</option>
			<option value="review_subject" <% if(search.equals("review_subject")) { %>selected<% } %>>&nbsp;제목&nbsp;</option>
			<option value="review_content" <% if(search.equals("review_content")) { %>selected<% } %>>&nbsp;내용&nbsp;</option>
		</select>
		<input type="text" name="keyword" value="<%=keyword%>">
		<button type="submit">검색</button>
	</form>
</div>

<script type="text/javascript">
//입력태그(게시글갯수)의 입력값을 변경한 경우 호출되는 이벤트 처리 함수 등록
$("#pageSize").change(function() {	
	location.href="<%=request.getContextPath()%>/index.jsp?workgroup=review&work=review_list"
		+"&pageNum=<%=pageNum%>&pageSize=<%=pageSize%>&search=<%=search%>&keyword=<%=keyword%>";
});

$("#writeBtn").click(function() {
	location.href="<%=request.getContextPath()%>/index.jsp?workgroup=review&work=review_write";
});
</script>