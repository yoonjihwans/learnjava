<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dao.QnaDAO"%>
<%@page import="xyz.itwill.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 목록</title>
    <script src="https://kit.fontawesome.com/47ee32fc29.js" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&display=swap" rel="stylesheet">        
<%
    // 비정상적으로 JSP 문서를 요청한 경우에 대한 응답 처리
    if(request.getParameter("qnaNo") == null) { // 전달값이 없는 경우
        request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
        return;
    }
    
    int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
    String pageNum = request.getParameter("pageNum");
    String pageSize = request.getParameter("pageSize");
    
    QnaDTO qna = QnaDAO.getDAO().selectQnaByNum(qnaNo);

    if(qna == null) { // 검색된 게시글이 없는 경우
        request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
        return;
    }
    
    UsersDTO loginUsers = (UsersDTO)session.getAttribute("loginUsers");
%>
    
<style type="text/css">
body {            
    font-family: 'Do Hyeon', sans-serif; 
    color: #333; 
}

.Do Hyeon-regular {
    font-family:  'Do Hyeon', sans-serif;
    font-weight: 400;
    font-style: normal;
}

 .do-hyeon-regular {
        font-family: "Do Hyeon", sans-serif;
        font-weight: 400;
        font-style: normal;
    }

.container {
    width: 60%; 
    margin: 50px auto;
    border: none;
}

table {
    width: 100%;
    background-color: #FFFFFF;
    border: 2px solid #ddd;
    border-collapse: collapse;
    font-size: 20px;
    font-family: 'Do Hyeon', sans-serif; 
}

th, td {
    border: 2px solid #ddd;
    padding: 15px;  
    font-family: 'Do Hyeon', sans-serif;   
}

th {
    width: 150px;
    background-color: #f6d365;
    color: white;
    text-align: center;
    font-family: 'Do Hyeon', sans-serif; 
}

td {
    width: calc(100% - 150px);
}

.subject, .content {
    text-align: left;
}

.content {
    vertical-align: top;
     font-family: 'Do Hyeon', sans-serif; 
}

.text-center {
     text-align: center;
     font-size: 30px;
     font-family: 'Do Hyeon', sans-serif; 
}
#qna_menu {
    text-align: center;
    margin-top: 20px;
}

.btn {
    background-color: #f6d365;
    color: black;
    width: 150px;
    height: 50px;
    font-size: 18px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    text-align: center;
    line-height: 50px;
    font-family: 'Do Hyeon', sans-serif;
    font-weight: bold;
    margin: 10px;
}

.btn:hover {
    background-color: #e0a800;
}
</style>

<div class="container">
    <h1 class="text-center">QnA 상세보기</h1>
    <table>
        <tr>
            <th>글번호</th>
            <td><%= qna.getQnaNo() %></td>
        </tr>
        <tr>
            <th>글유형</th>
            <td><%= qna.getQnaType() %></td>
        </tr>
        <tr>
            <th>제목</th>
            <td><%= qna.getQnaTitle() %></td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="content"><%= qna.getQnaContent().replace("\n", "<br>") %></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td><%= qna.getUsersName() %></td>
        </tr>
        <tr>
            <th>답변상태</th>
            <td>
                <% if(qna.getQnaStatus() == 2) { %>
                    답변완료
                <% } else { %>
                    미답변
                <% } %>
            </td>
        </tr>
        <tr>
            <th>작성일</th>
            <td><%= qna.getQnaDate() %></td>
        </tr>
    </table>
    
    <%-- 링크를 제공하는 태그 출력 --%>
    <div id="qna_menu">
        <%-- 로그인 사용자가 게시글 작성자이거나 관리자인 경우 태그 출력 --%>
        <% if(loginUsers != null && (loginUsers.getUsersNo() == qna.getQnaUsersNo() || loginUsers.getUsersStatus() == 9)) { %>
            <button type="button" class="btn" id="removeBtn">삭제</button>
        <% } %>
        
        <button type="button" class="btn" id="listBtn">글목록</button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$("#removeBtn").click(function() {
    if(confirm("게시글을 정말로 삭제 하시겠습니까?")) {
        location.href="<%= request.getContextPath() %>/index.jsp?workgroup=qna&work=qna_remove_action"
            +"&qnaNo=<%= qna.getQnaNo() %>&pageNum=<%= pageNum %>&pageSize=<%= pageSize %>";
    }
});

$("#listBtn").click(function() {
    location.href="<%= request.getContextPath() %>/qna/qna_list.jsp"
        +"?pageNum=<%= pageNum %>&pageSize=<%= pageSize %>";
});
</script>
</html>
