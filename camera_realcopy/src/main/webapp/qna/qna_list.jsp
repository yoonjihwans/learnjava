<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dto.QnaDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dao.QnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/security/login_url.jspf" %> 
<%
    // 페이징 처리에 필요한 전달값(페이지번호와 게시글갯수)을 반환받아 저장
    int pageNum=1;//페이지번호 - 전달값이 없는 경우 사용할 기본값 저장
    if(request.getParameter("pageNum") != null) {//전달값이 있는 경우
        pageNum=Integer.parseInt(request.getParameter("pageNum"));
    }
    
    int pageSize=10;//게시글갯수 - 전달값이 없는 경우 사용할 기본값 저장
    if(request.getParameter("pageSize") != null) {//전달값이 있는 경우
        pageSize=Integer.parseInt(request.getParameter("pageSize"));
    }
    int no=loginUsers.getUsersNo();
    int totalQna = QnaDAO.getDAO().selectTotalQna(no); // 게시글의 총갯수
    
    // 페이지의 총갯수를 계산하여 저장
    int totalPage=(int)Math.ceil((double)totalQna/pageSize);
    
    // 전달받은 페이지번호가 비상적인 경우 첫번째 페이지를 요청할 수 있는 기본값 저장
    if(pageNum <= 0 || pageNum > totalPage) {
        pageNum=1;
    }
    
    // 페이지번호에 대한 게시글의 시작 행번호를 계산하여 저장
    int startRow=(pageNum-1)*pageSize+1;

    // 페이지번호에 대한 게시글의 종료 행번호를 계산하여 저장
    int endRow=pageNum*pageSize;

    // 마지막 페이지의 게시글의 종료 행번호가 게시글의 총갯수보다 많은 경우 종료 행번호 변경
    if(endRow > totalQna) {
        endRow=totalQna;
    }

    // 페이징 관련 정보(시작행번호, 종료행번호)와 게시글 조회기능 관련 정보(조회대상과 조회단어)를
    // 전달받아 QNA 테이블에 저장된 행에서 조회정보가 포함된 행을 페이징 처리로 검색하여
    // List 객체를 반환하는 QnaDAO 클래스의 메소드 호출
   
  
    List<QnaDTO> qnaList=QnaDAO.getDAO().selectQnaList(no,startRow, endRow);
    
    // 세션에 저장된 권한 관련 정보가 저장된 속성값을 객체로 반환받아 저장
    // => 로그인 사용자에게만 글쓰기 권한 제공
    
    // 서버의 현재 날짜와 시간이 저장된 Date 객체를 생성하여 SimpleDateFormat 객체에 저장된
    // 패턴의 문자열로 변환하여 저장
    // => 게시글의 작성날짜와 비교하여 게시글 작성날짜를 다르게 출력되도록 응답 처리
    String currentDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    
    // 게시글에 출력될 일련번호 시작값을 계산하여 저장
    int displayNum=totalQna-(pageNum-1)*pageSize;
%>        

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
     <link rel="stylesheet" href="<%=request.getContextPath() %>/qna/qna_list.css">
</head>
<body>
<div class="container">
<div id="qna_list">
    <div id="qna_title">QnA(<%=totalQna %>)</div>
    
    <div style="text-align: right;">
        게시글갯수 :
        <select id="pageSize" class="pageSizeform">
            <option value="10" <% if(pageSize==10) { %> selected <% } %>>&nbsp;10개&nbsp;</option>    
            <option value="20" <% if(pageSize==20) { %> selected <% } %>>&nbsp;20개&nbsp;</option>    
            <option value="50" <% if(pageSize==50) { %> selected <% } %>>&nbsp;50개&nbsp;</option>    
            <option value="100" <% if(pageSize==100) { %> selected <% } %>>&nbsp;100개&nbsp;</option>    
        </select>
        &nbsp;&nbsp;&nbsp;
        <% if(loginUsers != null) {//로그인 사용자인 경우 %>
            <button type="button" class="btn-btn1" id="writeBtn">글쓰기</button>
        <% } %>    
    </div>
    
    <table class="board">
        <thead>
            <tr>
                <th width="100">글번호</th>
                <th width="200">글유형</th>
                <th width="300">제목</th>
                <th width="300">내용</th>
                <th width="100">작성자</th>
                <th width="100">답변상태</th>
                <th width="200">작성일</th>
            </tr>
        </thead>
        
        <% if(totalQna == 0) {//검색된 게시글이 없는 경우 %>
            <tr>
                <td colspan="7">검색된 게시글이 없습니다.</td>
            </tr>
        <% } else { %>
            
            <%-- List 객체의 요소값(QnaDTO 객체)을 차례대로 제공받아 변수에 저장하는 반복문 --%>
            <% for(QnaDTO qna : qnaList) { 
                if (qna.getQnaStatus() != 3) { // 삭제된 글은 숨김 처리
            %>
            <tr>
                <%-- 게시글의 일련번호 출력 --%>
                <td><%=displayNum %></td>
                	 <% displayNum--; %>
                <%
                    // 게시글의 일련번호를 1씩 감소하여 저장
                String currentUrl = request.getRequestURI();
                String queryString = request.getQueryString();
                String returnUrl = currentUrl + (queryString != null ? "?" + queryString : "");

                String url = request.getContextPath() + "/index.jsp?workgroup=qna&work=qna_detail"
                    + "&qnaNo=" + qna.getQnaNo()
                    + "&pageNum=" + pageNum
                    + "&pageSize=" + pageSize
                    + "&returnUrl=" + URLEncoder.encode(returnUrl, "UTF-8");
                    
                %>
                
                <%-- 게시글 유형 출력 --%>
                <td class="subject"><%=qna.getQnaType() %></td>
                
                <%-- 게시글 제목 출력 --%>
                <td class="subject">
                    <a href="<%=url%>"><%=qna.getQnaTitle() %></a>
                </td>
                
                <%-- 게시글 내용 출력 --%>
                <td class="subject"><%=qna.getQnaContent() %></td>

                <%-- 게시글 작성자(회원이름) 출력 --%>
                <td><%=qna.getUsersName() %></td>
                
                <%-- 게시글 답변 상태 출력 --%>
                <td>
                    <% if(qna.getQnaStatus() == 2) { %>
                        답변완료
                    <% } else { %>
                        미답변
                    <% } %>
                </td>
                
                <%-- 게시글 작성일 출력 --%>
                <td>
                    <%-- 오늘 작성된 게시글인 경우 시간만 출력하고 아닌 경우 날짜와 시간 출력 --%>
                    <% if(currentDate.equals(qna.getQnaDate().substring(0, 10))) { %>
                        <%=qna.getQnaDate().substring(11) %>    
                    <% } else { %>
                        <%=qna.getQnaDate() %>    
                    <% } %>
                </td>
            </tr>
            <% 
                } 
            } 
            %>
        <% } %>
    </table>
       
    <%-- 페이지 번호 출력 --%>
    <%
        // 하나의 페이지블럭에 출력될 페이지번호의 갯수 설정
        int blockSize = 5;
    
        // 페이지블럭에 출력될 시작 페이지번호를 계산하여 저장
        int startPage = (pageNum-1)/blockSize*blockSize+1; 

        // 페이지블럭에 출력될 종료 페이지번호를 계산하여 저장
        int endPage = startPage+blockSize-1;
        
        // 종료 페이지번호가 페이지 총갯수보다 큰 경우 종료 페이지번호 변경
        if(endPage > totalPage) {
            endPage = totalPage;
        }
    %>
    <%
    String currentUrl = request.getRequestURI();
    String queryString = request.getQueryString();
    String returnUrl = currentUrl + (queryString != null ? "?" + queryString : "");
        String myUrl = request.getContextPath() + "/index.jsp?workgroup=qna&work=qna_list&pageSize=" + pageSize
        		 + "&returnUrl=" + URLEncoder.encode(returnUrl, "UTF-8");
    %>
    
    <div id="page_list">
        <%-- 이전 블럭을 출력할 수 있는 링크 제공 --%>
        <% if(startPage > blockSize) { %>
            <a href="<%=myUrl%>&pageNum=<%=startPage-blockSize%>">&laquo;</a>
        <% } else { %>
            <span>&laquo;</span>
        <% } %>
    
        <% for(int i = startPage ; i <= endPage ; i++) { %>
            <%-- 현재 처리중인 페이지 번호와 출력된 페이지 번호가 같지 않은 경우 링크 제공 --%>
            <% if(pageNum != i) { %>
                <a href="<%=myUrl%>&pageNum=<%=i%>"><%=i %></a>
            <%} else { %>
                <span class="current"><%=i %></span>
            <% } %>
        <% } %>

        <%-- 다음 블럭을 출력할 수 있는 링크 제공 --%>
        <% if(endPage != totalPage) { %>
            <a href="<%=myUrl%>&pageNum=<%=startPage+blockSize%>">&raquo;</a>
        <% } else { %>
            <span>&raquo;</span>
        <% } %>
    </div>
</div>
</div>
<script type="text/javascript">
// 게시글 갯수 변경 이벤트 처리
document.getElementById("pageSize").addEventListener("change", function() {
    location.href = "<%= request.getContextPath() %>/index.jsp?workgroup=qna&work=qna_list&pageNum=<%= pageNum %>&pageSize=" + this.value;
});
<%-- <%
String currentUrl = request.getRequestURI();
String queryString = request.getQueryString();
String returnUrl = currentUrl + (queryString != null ? "?" + queryString : "");

%> --%>
// 글쓰기 버튼 클릭 이벤트 처리
document.getElementById("writeBtn").addEventListener("click", function() {
    location.href = "<%= request.getContextPath() %>/index.jsp?workgroup=qna&work=qna_write_form"
    		+"&returnUrl=<%=URLEncoder.encode(returnUrl, "UTF-8")%>";
});


</script>
</body>
</html>
