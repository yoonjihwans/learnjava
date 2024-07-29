<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dto.ReviewDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.ReviewDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");

if (loginUsers == null) {
    response.sendRedirect("login.jsp");
    return;
}

int usersNo = loginUsers.getUsersNo();

int pageNum = 1;
if (request.getParameter("pageNum") != null) {
    pageNum = Integer.parseInt(request.getParameter("pageNum"));
}

int pageSize = 10;
if (request.getParameter("pageSize") != null) {
    pageSize = Integer.parseInt(request.getParameter("pageSize"));
}

int totalReview = ReviewDAO.getDAO().selectTotalReviewByUser(usersNo);
int totalPage = (int)Math.ceil((double)totalReview / pageSize);

if (pageNum <= 0 || pageNum > totalPage) {
    pageNum = 1;
}

int startRow = (pageNum - 1) * pageSize + 1;
int endRow = pageNum * pageSize;

if (endRow > totalReview) {
    endRow = totalReview;
}


List<ReviewDTO> reviewList = ReviewDAO.getDAO().selectReviewListByUser(usersNo, startRow, endRow);
String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
int displayNum = totalReview - (pageNum - 1) * pageSize;
%>

<style type="text/css">
  #account-wrap {
    width: 700px;
    height: 450px;
    margin: 0 auto;
  }

  #account-wrap-middle {
    width: 700px;
    height: 350px;
    margin-top: 50px;
  }

  #detail1, #detail2 {
    font-size: 17px;
    width: 500px;
    margin: 0 auto;
    text-align: left;
    background-color: #D4B996;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    color: white;
    font-family: Arial, sans-serif;
    line-height: 1.5; 
  }

  #detail1 {
    background-color: #D4B996;
  }

  #detail2 {
    background-color: #C9AD8D;
  }

  #detail p:first-child {
    font-weight: bold; 
    margin-bottom: 10px;
  }

  #detail .date-info {
    font-size: 5px;
    margin-top: 10px; 
  }

  #link {
    font-size: 1.2em; 
    margin-top: 20px;
    text-align: center;
  }

  #link a {
    text-decoration: none;
    color: white;
    background-color: #C9AD8D;
    padding: 8px 15px;
    border-radius: 5px;
    transition: background-color 0.3s ease;
  }

  #link a:hover {
    background-color: black;
  }

  #account-box {
    width: 1300px;
    
    border: 1px solid green;
    margin: 0 auto;
  }

  #acc1-box {
    width: 200px;
    height: 700px;
    border: 1px solid black;
    float: left;
  }

  #acc2-box {
    width: 1300px;
    height: 700px;
    border: 1px solid black;
    float:none;
    margin-left: -20px;
   
   
  }

  #acc1-box nav ul {
    border: 1px solid red;
    height: 400px;
    margin: 0 auto;
    margin-top: 100px;
  }

  #acc1-box nav ul li {
    border: 1px solid blue;
    width: 200px;
    text-align: center;
    height: 70px;
  }

  #acc1-box nav ul li a {
    margin-top: 20px;
    font-size: 23px;
    color: black;
  }

  #review_title {
    font-size: 30px;
  }

  #review_list {
    width: 900px;
    margin: 0 auto;
    text-align: center;    
    height: 650px;
    margin-top: 20px;
    margin-left: 0;
    overflow-y: auto
  }

  .board {
    width: 100%;
    border-collapse: collapse;
    margin: 0 auto;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    font-size: 17px;
  }

  .board th, .board td {
    border: 1px solid #dee2e6;
    padding: 12px;
    text-align: center;
  }

  .board th {
    background-color: #ffc107;
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
    <div id="account-wrap">
      <div id="review_list">
        <div id="review_title">My Review (<%= totalReview %>)</div>
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
              <th width="50">글번호</th>
              <th width="120">제목</th>
              <th width="50">작성자</th>
              <th width="120">작성일</th>
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
                        String currentUrl = request.getRequestURI();
                        String queryString = request.getQueryString();
                        String returnUrl = currentUrl + (queryString != null ? "?" + queryString : "");

                        String url = request.getContextPath() + "/index.jsp?workgroup=review&work=review_detail"
                            + "&reviewNo=" + review.getReviewNo()
                            + "&pageNum=" + pageNum
                            + "&pageSize=" + pageSize
                            + "&returnUrl=" + URLEncoder.encode(returnUrl, "UTF-8");
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
        <% 
          int blockSize = 5;
          int startPage = (pageNum - 1) / blockSize * blockSize + 1;
          int endPage = startPage + blockSize - 1;
          if (endPage > totalPage) {
            endPage = totalPage;
          }
          String myUrl = request.getContextPath() + "/index.jsp?workgroup=myaccount&work=myacct_review"
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
    </div>
  </div>
</div>
<script type="text/javascript">
document.getElementById("pageSize").addEventListener("change", function() {
    location.href = "<%= request.getContextPath() %>/index.jsp?workgroup=myaccount&work=myacct_review"
        + "&pageNum=<%= pageNum %>&pageSize=" + this.value;
});

</script>
</body>
</html>
