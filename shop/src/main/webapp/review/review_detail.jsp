<%@page import="xyz.itwill.dto.MemberDTO"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 글번호를 전달받아 REVIEW 테이블에 저장된 하나의 행을 검색하여 HTML 태그에 포함해 응답하는 JSP 문서 --%>
<%-- => 페이징 처리 및 조회기능 관련 값을 전달받아 JSP 문서를 요청할 때 전달 --%>
<%-- => [글변경] 태그를 클릭한 경우 [/review/review_modify.jsp] 문서를 요청하여 페이지 이동
- 글번호, 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%-- => [글삭제] 태그를 클릭한 경우 [/review/review_remove_action.jsp] 문서를 요청하여 페이지 이동
- 글번호, 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%-- => [답글쓰기] 태그를 클릭한 경우 [/review/review_write.jsp] 문서를 요청하여 페이지 이동
- 글그룹, 글순서, 글깊이, 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%-- => [글목록] 태그를 클릭한 경우 [/review/review_list.jsp] 문서를 요청하여 페이지 이동
- 페이지번호, 게시글갯수, 조회대상, 조회단어 전달 --%>
<%-- => [글변경] 태그와 [글삭제] 태그는 게시글 작성자 또는 관리자에게만 출력되도록 작성하고
[답글쓰기] 태그는 로그인 사용자에게만 출력되도록 작성 --%>
<%
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(request.getParameter("reviewNum") == null) {//전달값이 없는 경우
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}

	//전달값을 반환받아 저장
	int reviewNum=Integer.parseInt(request.getParameter("reviewNum"));
	String pageNum=request.getParameter("pageNum");
	String pageSize=request.getParameter("pageSize");
	String search=request.getParameter("search");
	String keyword=request.getParameter("keyword");
	
	//글번호를 전달받아 REVIEW 테이블에 저장된 하나의 행을 검색하여 ReviewDTO 객체로 반환하는
	//ReviewDAO 클래스의 메소드 호출
	ReviewDTO review=ReviewDAO.getDAO().selectReviewByNum(reviewNum);
	
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(review == null) {//검색된 게시글이 없는 경우 
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;
	}
	
	//세션에 저장된 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
	// => 게시글이 비밀글인 경우 로그인 사용자가 게시글 작성자이거나 관리자인 경우에만 권한 제공
	// => [글변경]과 [글삭제] 관련 기능은 로그인 사용자가 게시글 작성자이거나 관리자인 경우에만 권한 제공
	MemberDTO loginMember=(MemberDTO)session.getAttribute("loginMember");
	
	//비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
	if(review.getReviewStatus() == 2) {//검색된 게시글이 비밀글인 경우
		//비로그인 사용자이거나 로그인 사용자가 게시글 작성자가 아니고 관리자도 아닌 경우
		if(loginMember == null || loginMember.getMemberNum() != review.getReviewMemberNum()
			&& loginMember.getMemberAuth() != 9) {
			request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
			return;
		}
	}
	
	//글번호를 전달받아 REVIEW 테이블에 저장된 행의 게시글조횟수를 1 증가되도록 변경하고
	//변경행의 갯수를 반환하는 ReviewDAO 클래스의 메소드 호출
	ReviewDAO.getDAO().updateReviewCount(reviewNum);
%>
<style type="text/css">
#review_detail {
	width: 500px;
	margin: 0 auto;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	padding: 5px;	
}

th {
	width: 100px;
	background: black;
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
	<h1>제품후기</h1>
	
	<%-- 검색된 게시글 출력 --%>
	<table>
		<tr>
			<th>작성자</th>
			<td>
				<%=review.getMemberName() %>
				<%-- 로그인 사용자가 관리자인 경우 게시글을 작성한 클라이언트의 IP 주소 출력 --%>	
				<% if(loginMember != null && loginMember.getMemberAuth() == 9) { %>
					[<%=review.getReviewIp() %>]
				<% } %>
			</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td><%=review.getReviewRegisterDate() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=review.getReviewCount()+1 %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td>
				<%-- 검색된 게시글이 비밀글인 경우에 대한 출력 처리 --%>			
				<% if(review.getReviewStatus() == 2) { %>[비밀글]<% } %>
				<%=review.getReviewSubject() %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<%=review.getReviewContent().replace("\n", "<br>") %>
				<br>
				<% if(review.getReviewImage() != null) { %>
					<img src="<%=request.getContextPath()%>/review_images/<%=review.getReviewImage()%>" width="200">
				<% } %>
			</td>
		</tr>
	</table>
	
	<%-- 링크를 제공하는 태그 출력 --%>
	<div id="review_menu">
		<%-- 로그인 사용자가 게시글 작성자이거나 관리자인 경우 태그 출력 --%>
		<% if(loginMember !=null && (loginMember.getMemberNum() == review.getReviewMemberNum()
			|| loginMember.getMemberAuth() == 9)) { %>
			<button type="button" id="modifyBtn">글변경</button>
			<button type="button" id="removeBtn">글삭제</button>
		<% } %>
			
		<%-- 로그인 사용자인 경우 태그 출력 --%>
		<% if(loginMember !=null) { %>
			<button type="button" id="replyBtn">답글쓰기</button>
		<% } %>
		
		<button type="button" id="listBtn">글목록</button>
	</div>
</div>



