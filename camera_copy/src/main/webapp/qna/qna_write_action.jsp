<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="xyz.itwill.dao.QnaDAO" %>
<%@ page import="xyz.itwill.dto.QnaDTO" %>
<%@ page import="xyz.itwill.dto.UsersDTO" %>

<%
UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");

if (loginUsers == null) {
    response.sendRedirect("login.jsp");
    return;
}

int usersNo = loginUsers.getUsersNo();
String qnaType = request.getParameter("qnaType");
String title = request.getParameter("title");
String content = request.getParameter("content");

QnaDTO qna = new QnaDTO();
qna.setQnaUsersNo(usersNo);
qna.setQnaType(qnaType);
qna.setQnaTitle(title);
qna.setQnaContent(content);
qna.setQnaStatus(0);

int rows = QnaDAO.getDAO().insertQna(qna);

if (rows > 0) {
    response.sendRedirect("index.jsp?workgroup=qna&work=qna_list");
} else {
    out.println("<script>alert('QnA 작성에 실패했습니다. 다시 시도해 주세요.'); history.back();</script>");
}
%>


