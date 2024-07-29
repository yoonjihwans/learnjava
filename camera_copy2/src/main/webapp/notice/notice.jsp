<%@page import="xyz.itwill.dto.NoticeDTO"%>
<%@page import="xyz.itwill.dao.NoticeDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>


<%

// 페이징 처리에 필요한 전달값(페이지번호와 게시글갯수)을 반환받아 저장
int pageNum = 1; // 페이지번호 - 전달값이 없는 경우 사용할 기본값 저장
if (request.getParameter("pageNum") != null) { // 전달값이 있는 경우
    pageNum = Integer.parseInt(request.getParameter("pageNum"));
}

int pageSize = 10; // 게시글갯수 - 전달값이 없는 경우 사용할 기본값 저장
if (request.getParameter("pageSize") != null) { // 전달값이 있는 경우
    pageSize = Integer.parseInt(request.getParameter("pageSize"));
}

// 모든 공지사항을 가져와 상태가 1인 공지사항만 필터링
List<NoticeDTO> allNotices = NoticeDAO.getDAO().selectNoticeList(0, Integer.MAX_VALUE);
List<NoticeDTO> activeNoticesList = new ArrayList<>();

for (NoticeDTO notice : allNotices) {
    if (notice.getNoticeStatus() == 1) {
        activeNoticesList.add(notice);
    }
}

int totalNotice = activeNoticesList.size(); // 활성화된 게시글의 총갯수

// 페이지의 총갯수를 계산하여 저장
int totalPage = (int) Math.ceil((double) totalNotice / pageSize);

// 전달받은 페이지번호가 비정상적인 경우 첫번째 페이지를 요청할 수 있는 기본값 저장
if (pageNum <= 0 || pageNum > totalPage) {
    pageNum = 1;
}

// 페이지번호에 대한 게시글의 시작 행번호를 계산하여 저장
int startRow = (pageNum - 1) * pageSize;
int endRow = startRow + pageSize;

// 마지막 페이지의 게시글의 종료 행번호가 게시글의 총갯수보다 많은 경우 종료 행번호 변경
if (endRow > totalNotice) {
    endRow = totalNotice;
}

// 시작 행번호와 종료 행번호에 해당하는 공지사항 목록을 추출
List<NoticeDTO> noticeList = activeNoticesList.subList(startRow, endRow);

// 서버의 현재 날짜와 시간이 저장된 Date 객체를 생성하여 SimpleDateFormat 객체에 저장된
// 패턴의 문자열로 변환하여 저장
String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

// 최신순으로 글번호를 부여하기 위해
int displayNum = totalNotice - startRow;
%>

<style type="text/css">
*{ font-family: 'Jua', sans-serif;}

#review_title{
    font-size:30px;
}

#review_list {
    width: 1000px;
    margin: 0 auto;
    text-align: center;
    margin-top:40px;
    margin-bottom:50px;
}

.board {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    font-size:17px;
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
    background: black;
    color: white;
    font-size: 14px;
    border: 1px solid black;
    border-radius: 4px;
}

#page_list {
    font-size: 1.5em;
    margin: 10px;
}
</style>

<div id="review_list">
    <div id="review_title">Notice(<%=totalNotice %>)</div>
    <table class="board">
        <thead>
            <tr>
                <th width="100">글번호</th>
                <th width="500">제목</th>
                <th width="100">작성자</th>
                <th width="200">작성일</th>
            </tr>
        </thead>
        
        <%
        // 게시글에 출력될 일련번호 시작값을 계산하여 저장
        for (NoticeDTO notice : noticeList) {
        %>
        <tr>
            <td><%= displayNum %></td>
            <td class="subject">
                <%
                    String url = request.getContextPath() + "/index.jsp?workgroup=notice&work=notice_detail"
                            + "&noticeNo=" + notice.getNoticeNo() + "&pageNum=" + pageNum + "&pageSize=" + pageSize;
                %>
                <a href="<%=url%>"><%=notice.getNoticeTitle() %></a>
            </td>
            <td>관리자</td>
            <td>
                <% if (currentDate.equals(notice.getNoticeDate().substring(0, 10))) { %>
                    <%=notice.getNoticeDate().substring(11) %>   
                <% } else { %>
                    <%=notice.getNoticeDate() %>   
                <% } %>
            </td>
        </tr>
        <%
                displayNum--;
        }
        %>
    </table>
    
    <div id="page_list">
        <% String myUrl = request.getContextPath() + "/index.jsp?workgroup=notice&work=notice" + "&pageSize=" + pageSize; %>
        <% for (int i = 1; i <= totalPage; i++) { %>
            <% if (pageNum != i) { %>
                <a href="<%=myUrl%>&pageNum=<%=i%>">[<%=i %>]</a>
            <% } else { %>
                [<%=i %>]
            <% } %>
        <% } %>
    </div>
</div>

<script type="text/javascript">
// 입력태그(게시글갯수)의 입력값을 변경한 경우 호출되는 이벤트 처리 함수 등록
$("#pageSize").change(function() {    
    location.href="<%=request.getContextPath()%>/index.jsp?workgroup=notice&work=notice"
        +"&pageNum=<%=pageNum%>&pageSize=<%=pageSize%>";
});
</script>

</html>
