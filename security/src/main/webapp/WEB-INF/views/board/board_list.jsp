<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
<style type="text/css">
#container {
	width: 1000px;
}

table {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}

.num { width: 150px; }
.writer { width: 200px; }
.subject { width: 450px; }
.regdate { width: 200px; }
</style>
</head>
<body>
	<h1>게시글 목록</h1>
	<hr>
	<div id="container">
		<sec:authorize access="isAuthenticated()">
			<div style="text-align: right; margin-bottom: 10px;">
				<button type="button" onclick="location.href='<c:url value="/board/register"/>';">글쓰기</button>
			</div>
		</sec:authorize>
		<table>
			<tr>
				<th class="num">글번호</th>
				<th class="writer">작성자</th>
				<th class="subject">제목</th>
				<th class="regdate">작성일</th>
			</tr>
			<c:choose>
				<c:when test="${empty resultMap.securityBoardList }">
					<tr>
						<td colspan="4">검색된 게시글이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="securityBoard" items="${resultMap.securityBoardList }">
						<tr>
							 <td>${securityBoard.num }</td>
							 <td>${securityBoard.name }</td>
							 <td style="text-align: left;">
							 	<a href="<c:url value="/board/detail"/>?num=${securityBoard.num}&pageNum=${resultMap.pager.pageNum}&pageSize=${resultMap.pager.pageSize }&column=${searchMap.column}&keyword=${searchMap.keyword}">
							 	${securityBoard.subject }
							 	</a>
							 </td>
							 <td>${securityBoard.regdate }</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</table>
		
		<div style="text-align: center;">
			<c:choose>
				<c:when test="${resultMap.pager.startPage > resultMap.pager.blockSize }">
					<a href="<c:url value="/board/list"/>?pageNum=${resultMap.pager.prevPage}&pageSize=${resultMap.pager.pageSize }&column=${searchMap.column}&keyword=${searchMap.keyword}">
						[이전]
					</a>
				</c:when>
				<c:otherwise>
					[이전]
				</c:otherwise>
			</c:choose>
				
			<c:forEach var="i" begin="${resultMap.pager.startPage }" end="${resultMap.pager.endPage }" step="1">
				<c:choose>
					<c:when test="${resultMap.pager.pageNum != i }">
						<a href="<c:url value="/board/list"/>?pageNum=${i}&pageSize=${resultMap.pager.pageSize }&column=${searchMap.column}&keyword=${searchMap.keyword}">
							[${i }]
						</a>
					</c:when>
					<c:otherwise>
						[${i }]
					</c:otherwise>
				</c:choose>				
			</c:forEach>
			
			<c:choose>
				<c:when test="${resultMap.pager.endPage != resultMap.pager.totalPage }">
					<a href="<c:url value="/board/list"/>?pageNum=${resultMap.pager.nextPage}&pageSize=${resultMap.pager.pageSize }&column=${searchMap.column}&keyword=${searchMap.keyword}">
						[다음]
					</a>
				</c:when>
				<c:otherwise>
					[다음]
				</c:otherwise>
			</c:choose>
		</div>
		
		<div style="text-align: center;">
			<form action="<c:url value="/board/list"/>" method="post">
				<select name="column">
					<option value="name">작성자</option>
					<option value="subject">제목</option>
					<option value="content">내용</option>
				</select>			
				<input type="text" name="keyword">
				<sec:csrfInput/>
				<button type="submit">검색</button>
			</form>
		</div>
	</div>
</body>
</html>













