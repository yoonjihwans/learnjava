<%@page import="xyz.itwill.dto.StudentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 사용자로부터 학생정보를 입력받기 위한 JSP 문서 --%>
<%-- => [학생추가] 태그를 클릭한 경우 [insertStudent.jsp] 문서를 요청하여 페이지 이동 - 입력값(학생정보) 전달 --%>    
<%-- => [학생목록] 태그를 클릭한 경우 [displayStudent.jsp] 문서를 요청하여 페이지 이동 --%>
<%
	String message=(String)session.getAttribute("message");
	if(message == null) {
		message="";
	} else {
		session.removeAttribute("message");
	}
	
	StudentDTO student=(StudentDTO)session.getAttribute("student");
	if(student != null) {
		session.removeAttribute("student");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<style type="text/css">
h1 {
	margin: 0 auto;
	width: 300px; 
	text-align: center; 
}

table {
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse; 	
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}

.title { width: 100px; }
.input { width: 200px; }
</style>
</head>
<body>
	<h1>학생정보 입력</h1>
	<hr>
	<form name="studentForm" action="<%=request.getContextPath()%>/student/insertStudent.jsp" method="post">
	<table>
		<tr>
			<th class="title">학생번호</th>
			<td class="input">
				<input type="text" name="no" <% if(student != null) { %>value="<%=student.getNo()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">이름</th>
			<td class="input">
				<input type="text" name="name" <% if(student != null) { %>value="<%=student.getName()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">전화번호</th>
			<td class="input">
				<input type="text" name="phone" <% if(student != null) { %>value="<%=student.getPhone()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">주소</th>
			<td class="input">
				<input type="text" name="address" <% if(student != null) { %>value="<%=student.getAddress()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<th class="title">생년월일</th>
			<td class="input">
				<input type="text" name="birthday" <% if(student != null) { %>value="<%=student.getBirthday()%>"<% } %>>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">학생추가</button> 
				<button type="reset">초기화</button> 
				<button type="button" id="listBtn">학생목록</button> 
			</td>
		</tr>
	</table>
	</form>
	<p align="center" style="color: red;"><%=message %></p>
	
	<script type="text/javascript">
	studentForm.no.focus();

	studentForm.onsubmit=function() {
		if(studentForm.no.value=="") {
			alert("학생번호를 입력해 주세요.");
			studentForm.no.focus();
			return false;
		}
		
		var noReg=/\d{4}/g;
		if(!noReg.test(studentForm.no.value)) {
			alert("학생번호는 정수 4자리로 입력해주세요.");
			studentForm.no.focus();
			return false;
		}
		
		if(studentForm.name.value=="") {
			alert("이름을 입력해 주세요.");
			studentForm.name.focus();
			return false;
		}

		if(studentForm.phone.value=="") {
			alert("전화번호를 입력해 주세요.");
			studentForm.phone.focus();
			return false;
		}

		var phoneReg=/(01[016789])-\d{3,4}-\d{4}/g;
		if(!phoneReg.test(studentForm.phone.value)) {
			alert("전화번호를 형식에 맞게 입력해주세요.");
			studentForm.phone.focus();
			return false;
		}
		
		if(studentForm.address.value=="") {
			alert("주소를 입력해 주세요.");
			studentForm.address.focus();
			return false;
		}

		if(studentForm.birthday.value=="") {
			alert("생년월일을 입력해 주세요.");
			studentForm.birthday.focus();
			return false;
		}
		
		var birthdayReg=/(18|19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])/g;
		if(!birthdayReg.test(studentForm.birthday.value)) {
			alert("생년월일을 형식에 맞게 입력해주세요.");
			studentForm.birthday.focus();
			return false;
		}
	} 
	
	document.getElementById("listBtn").onclick=function() {
		location.href="<%=request.getContextPath()%>/student/displayStudent.jsp";	
	}
	</script>

</body>
</html>