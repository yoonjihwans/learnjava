<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 10px;
}

h1,h2 {
	font-size: 1em;
	font-weight: bold;
}

#detail {
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    padding: 15px;
    margin-top: 20px;
}

#detail p {
    margin: 0 auto;
    line-height: 1.5;
}

#detail p:first-child {
    font-weight: bold;
    font-size: 1em;
    color: black;
}

#detail p:nth-child(even) {
    background-color: #f9f9f9;
    padding: 5px 10px;
    border-radius: 3px;
}

#detail p:nth-child(odd) {
    background-color: white;
    padding: 5px 10px;
    border-radius: 3px;
}

.hr-line {
    border-top: 1px solid #ccc;
    margin-top: 10px;
    margin-bottom: 10px;
}
.clientAddress, .newAddress {
    font-size: 0.1em; 
}

 #postSearch {
	font-size: 12px;
	font-weight: bold;
	cursor: pointer;
	margin-left: 10px;
	padding: 2px 10px;
	border: 1px solid black;
}

</style>
<title>구매정보창</title>
</head>
<body>

<h1>결제정보</h1>

<div id="detail">
	<p>주문자 정보</p>
	<hr class="hr-line">
    <p>아이디	:<%--  <%=loginMember.getMemberId() %>--%></p>
    <p>이름	:<%-- <%=loginMember.getMemberName() %>--%></p>
    <p>이메일	:<%-- <%=loginMember.getMemberEmail() %>--%></p>
    <p>전화번호	:<%-- <%=loginMember.getMemberMobile() %>--%></p>
    <p>주소	:<%--  [ <%=loginMember.getMemberZipcode() %>]<%=loginMember.getMemberAddress1() %>
    	 <%=loginMember.getMemberAddress2() %>--%></p>
</div>
<hr class="hr-line">

<h2>배송지 작성</h2>
<div class="checkboxList">
<input type="checkbox" name="clientAddress" class="clientAddress" checked >주문자 정보와 동일&nbsp;&nbsp;
<input type="checkbox" name="newAddress" class="newAddress">새로운 배송지 작성&nbsp;&nbsp;
</div>
<div id="detail">
		
<form id="join" action="<%=request.getContextPath() %>/index.jsp?workgroup=member&work=member_modify_action" method="post">
<input type="hidden" name="num" <%-- value="<%=loginMember.getMemberNum()%> --%>">

<fieldset>
	<legend>배송지 정보</legend>
	<ul>
		<li>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" <%-- value="<%=loginMember.getMemberId()%>" --%> readonly="readonly">	
		</li>
		<li>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" <%--value="<%=loginMember.getMemberName()%> --%>">

		</li>
		<li>
			<label for="email">이메일</label>
			<input type="text" name="email" id="email" <%--value="<%=loginMember.getMemberEmail()%> --%>">

		</li>
		
		<li>
			<label>우편번호</label>
			<input type="text" name="zipcode" id="zipcode" size="7" readonly="readonly" <%--value="<%=loginMember.getMemberZipcode()%>"--%>>
			<span id="postSearch">우편번호 검색</span>
			
		</li>
		<li>
			<label for="address1">기본주소</label>
			<input type="text" name="address1" id="address1" size="50" readonly="readonly" <%--value="<%=loginMember.getMemberAddress1()%>"--%>>
			
		</li>
		<li>
			<label for="address2">상세주소</label>
			<input type="text" name="address2" id="address2" size="50" <%--value="<%=loginMember.getMemberAddress2()%>"--%>>		
		</li>
	</ul>
</fieldset>
	
<%--
	if(체크박스 주문자정보동일 체크시){
	 
 --%>
</div>

<hr class="hr-line">

<script type="text/javascript">
$(document).ready(function() {
    $("#postSearch").click(function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $("#zipcode").val(data.zonecode);
                $("#address1").val(data.address);
            }
        }).open();
    });
});
</script>

</body>
</html>