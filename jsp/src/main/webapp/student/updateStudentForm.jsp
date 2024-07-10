<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>학생정보 변경</h1>
	<hr>
	<form name="studentForm">
	<table>
		<tr>
			<th class="title">학생번호</th>
			<td class="input">
				<input type="text" name="no" value="1000" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th class="title">이름</th>
			<td class="input">
				<input type="text" name="name" value="홍길동">
			</td>
		</tr>
		<tr>
			<th class="title">전화번호</th>
			<td class="input">
				<input type="text" name="phone" value="010-1234-5678">
			</td>
		</tr>
		<tr>
			<th class="title">주소</th>
			<td class="input">
				<input type="text" name="address" value="서울시 강남구">
			</td>
		</tr>
		<tr>
			<th class="title">생년월일</th>
			<td class="input">
				<input type="text" name="birthday" value="2000-01-01">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button">학생변경</button> 
				<button type="reset">초기화</button> 
				<button type="button">학생목록</button> 
			</td>
		</tr>
	</table>
	</form>
	<p align="center" style="color: red;"></p>
	
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
	</script>

</body>
</html>