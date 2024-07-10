<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- STUDENT 테이블에 저장된 모든 행(학생정보)를 검색하여 HTML 태그에 포함하여 응답하는 JSP 문서 --%>
<%--  --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
h1 {
	margin: 0 auto;
	width: 850px; 
	text-align: center; 
}

div {
	margin: 10px auto;
	width: 850px;
	text-align: right;
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse; 	
}

th {
	background-color: black;
	color: white; 
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}

.no { width: 100px; }
.name { width: 100px; }
.phone { width: 150px; }
.address { width: 250px; }
.birthday { width: 150px; }
.remove { width: 50px; }
.modify { width: 50px; }
</style>
</head>
<body>
	<h1>학생목록</h1>
	<div>
		<button type="button">학생추가</button>
	</div>
	<table>
		<tr>
			<th class="no">학번</th>
			<th class="name">이름</th>
			<th class="phone">전화번호</th>
			<th class="address">주소</th>
			<th class="birthday">생년월일</th>
			<th class="remove">삭제</th>
			<th class="modify">변경</th>
		</tr>
		<tr align="center">
			<td>1000</td>				
			<td>홍길동</td>				
			<td>010-1234-5678</td>				
			<td>서울시 강남구</td>				
			<td>2000-01-01</td>				
			<td><button type="button">삭제</button></td>		
			<td><button type="button">변경</button></td>		
		</tr>	
	</table>
</body>
</html>