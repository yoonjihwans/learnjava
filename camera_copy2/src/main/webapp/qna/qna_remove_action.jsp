<%@page import="java.io.File"%>
<%@page import="xyz.itwill.dao.QnaDAO"%>
<%@page import="xyz.itwill.dto.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/security/login_check.jspf" %>
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

    // 검색된 QnA가 없는 경우 또는 로그인한 사용자가 작성자가 아닌 경우에 대한 응답 처리
    if(qna == null || (loginUsers.getUsersNo() != qna.getQnaUsersNo() && loginUsers.getUsersStatus() != 9)) {
        request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
        return;
    }

    // QnA 상태를 삭제 상태로 업데이트
    qna.setQnaStatus(0);
    QnaDAO.getDAO().updateQna(qna);

    // 필요하다면 QnA와 관련된 파일 삭제 (여기서는 파일 업로드 관련 코드가 없으므로 생략)

    // 삭제 후 QnA 목록으로 리다이렉트
    request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=qna&work=qna_list"
        + "&pageNum=" + pageNum + "&pageSize=" + pageSize);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>QnA 삭제</title>
    <script type="text/javascript">
        alert("QnA 항목이 성공적으로 삭제되었습니다.");
        window.location.href = "<%= request.getAttribute("returnUrl") %>";
    </script>
</head>
<body>
</body>
</html>
