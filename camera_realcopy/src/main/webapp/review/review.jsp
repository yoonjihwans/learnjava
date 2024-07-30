<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
int pageNum = 1; // 페이지 번호 - 전달값이 없는 경우 사용할 기본값 저장
if (request.getParameter("pageNum") != null) { // 전달값이 있는 경우
    pageNum = Integer.parseInt(request.getParameter("pageNum"));
}

int pageSize = 10; // 게시글 갯수 - 전달값이 없는 경우 사용할 기본값 저장
if (request.getParameter("pageSize") != null) { // 전달값이 있는 경우
    pageSize = Integer.parseInt(request.getParameter("pageSize"));
}

int totalReview = ReviewDAO.getDAO().selectTotalReview(); // 게시글의 총갯수

int totalPage = (int)Math.ceil((double)totalReview/pageSize); // 페이지의 총갯수 계산

if (pageNum <= 0 || pageNum > totalPage) { // 전달받은 페이지번호가 비정상적인 경우 첫번째 페이지로 설정
    pageNum = 1;
}

int startRow = (pageNum - 1) * pageSize + 1; // 페이지번호에 대한 게시글의 시작 행번호 계산
int endRow = pageNum * pageSize; // 페이지번호에 대한 게시글의 종료 행번호 계산

if (endRow > totalReview) { // 마지막 페이지의 게시글의 종료 행번호가 게시글의 총갯수보다 많은 경우 종료 행변호 변경
    endRow = totalReview;
}

List<ReviewDTO> reviewList = ReviewDAO.getDAO().selectReviewList(startRow, endRow); // 리뷰 목록 검색

UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers"); // 로그인 사용자 정보

String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); // 서버의 현재 날짜와 시간

int displayNum = totalReview - (pageNum - 1) * pageSize; // 게시글에 출력될 일련번호 시작값 계산
%>

<style type="text/css">
 a {text-decoration: none !important}

#review_title {
    font-size: 30px;
}

#review_list {
    width: 1000px;
    margin: 0 auto;
    text-align: center;
    margin-top: 40px;
    margin-bottom: 50px;
}

.board {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    font-size: 17px;
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
    color: black;
    font-size: 14px;
   
}

#page_list {
    font-size: 1.5em;
    margin: 10px;
}
</style>

<div id="review_list">
    <div id="review_title">Review (<%= totalReview %>)</div>
    
    <div style="text-align: right; font-size: 19px;">
        게시글 :
        <select id="pageSize">
            <option value="10" <% if (pageSize == 10) { %> selected <% } %>>&nbsp;10개&nbsp;</option>
            <option value="20" <% if (pageSize == 20) { %> selected <% } %>>&nbsp;20개&nbsp;</option>
            <option value="50" <% if (pageSize == 50) { %> selected <% } %>>&nbsp;50개&nbsp;</option>
            <option value="100" <% if (pageSize == 100) { %> selected <% } %>>&nbsp;100개&nbsp;</option>
        </select>
        &nbsp;&nbsp;&nbsp;
      
    </div>

    <table class="board">
        <thead>
            <tr>
                <th width="100">글번호</th>
                <th width="500">제목</th>
                <th width="100">작성자</th>
                <th width="200">작성일</th>
            </tr>
        </thead>

        <% if (totalReview == 0) { %>
            <tr>
                <td colspan="4">작성된 게시글이 없습니다.</td>
            </tr>
        <% } else { %>
            <% for (ReviewDTO review : reviewList) { %>
                <tr>
                    <td><%= displayNum %></td>
                    <% displayNum--; %>
                    <td class="subject">
                        <%
                            String url = request.getContextPath() + "/index.jsp?workgroup=review&work=review_detail"
                                    + "&reviewNo=" + review.getReviewNo() + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
                        %>
                        <% if (review.getReviewStatus() == 1) { %>
                            <a href="<%= url %>"><%= review.getReviewTitle() %></a>
                        <% } else if (review.getReviewStatus() == 0) { %>
                            <span class="subject_hidden">
                                게시글 작성자 또는 관리자에 의해 삭제된 게시글입니다.
                            </span>
                        <% } %>
                    </td>

                    <% if (review.getReviewStatus() != 0) { %>
                        <td><%= review.getUsersName() %></td>
                        <td>
                            <% if (currentDate.equals(review.getReviewDate().substring(0, 10))) { %>
                                <%= review.getReviewDate().substring(11) %>
                            <% } else { %>
                                <%= review.getReviewDate() %>
                            <% } %>
                        </td>
                    <% } else { %>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>                     
                    <% } %>
                </tr>
            <% } %>
        <% } %>
    </table>

    <% // 페이지 번호 출력 
        int blockSize = 5; // 페이지 블럭 크기
        int startPage = (pageNum - 1) / blockSize * blockSize + 1; // 시작 페이지 번호
        int endPage = startPage + blockSize - 1; // 종료 페이지 번호

        if (endPage > totalPage) {
            endPage = totalPage;
        }

        String myUrl = request.getContextPath() + "/index.jsp?workgroup=review&work=review"
                + "&pageSize=" + pageSize;
    %>

    <div id="page_list">
        <% if (startPage > blockSize) { %>
            <a href="<%= myUrl %>&pageNum=<%= startPage - blockSize %>">[이전]</a>
        <% } else { %>
            [이전]
        <% } %>

        <% for (int i = startPage; i <= endPage; i++) { %>
            <% if (pageNum != i) { %>
                <a href="<%= myUrl %>&pageNum=<%= i %>">[<%= i %>]</a>
            <% } else { %>
                [<%= i %>]
            <% } %>
        <% } %>

        <% if (endPage != totalPage) { %>
            <a href="<%= myUrl %>&pageNum=<%= startPage + blockSize %>">[다음]</a>
        <% } else { %>
            [다음]
        <% } %>
    </div>
</div>

<script type="text/javascript">
// 게시글 갯수 변경 이벤트 처리
document.getElementById("pageSize").addEventListener("change", function() {
    location.href = "<%= request.getContextPath() %>/index.jsp?workgroup=review&work=review"
        + "&pageNum=<%= pageNum %>&pageSize=" + this.value;
});


</script>
