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

h1, h2 {
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

.clientAddressCheckbox, .newAddressCheckbox {
    font-size: 12px; 
    font-weight: bold;
    cursor: pointer;
    margin-left: 10px;
    padding: 2px 10px;
    border: 1px solid black;
}

#newAddressForm {
    margin-top: 20px;
    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0,0,0,0.1);
    padding: 15px;
}

#postSearch {
 	font-size: 12px;
	font-weight: bold;
	cursor: pointer;
	margin-left: 10px;
	padding: 2px 10px;
	border: 1px solid black;
    transition: transform 0.1s ease, background-color 0.1s ease;
}

#postSearch:active {
    transform: translateY(2px); /* 클릭 시 약간 아래로 내리는 효과 */
    background-color: #ddd; /* 클릭 시 배경색 변화 */
}

</style>
<title>구매정보창</title>
</head>
<body>

<h1>결제정보</h1>

<div id="detail">
	<p>주문자 정보</p>
	<hr class="hr-line"> 
	<%-- 
    <p>아이디 : <%=loginMember.getMemberId()%></p>
    <p>이름 : <%=loginMember.getMemberName()%></p>
    <p>이메일 : <%=loginMember.getMemberEmail()%></p>
    <p>전화번호 : <%=loginMember.getMemberMobile()%></p>
    <p>주소 : [<%=loginMember.getMemberZipcode()%>] <%=loginMember.getMemberAddress1()%> <%=loginMember.getMemberAddress2()%></p>
--%>
</div>
<hr class="hr-line">

<h2>배송지 작성</h2>
<div class="checkboxList">
    <input type="checkbox" name="clientAddressCheckbox" class="clientaddressCheckbox" id="clientAddressCheckbox" checked>주문자 정보와 동일&nbsp;&nbsp;
    <input type="checkbox" name="newAddressCheckbox" class="newAddressCheckbox" id="newAddressCheckbox">새로운 배송지 작성&nbsp;&nbsp;
</div>
<div id="newAddressForm">
<form id="join" action="<%=request.getContextPath()%>/order_process.jsp" method="post">
    <input type="hidden" name="num" <%-- %>value="<%=loginMember.getMemberNum()%>" --%>>

    <fieldset>
        <legend>배송지 정보</legend>
        <ul>
            <li>
                <label for="id">아이디</label>
                <input type="text" name="id" id="id" readonly="readonly">	
            </li>
            <li>
                <label for="name">이름</label>
                <input type="text" name="name" id="name">
            </li>
            <li>
                <label for="email">이메일</label>
                <input type="text" name="email" id="email">
            </li>
            <li>
                <label>우편번호</label>
                <input type="text" name="zipcode" id="zipcode" size="7" readonly="readonly">
                <span id="postSearch">우편번호 검색</span>
            </li>
            <li>
                <label for="address1">기본주소</label>
                <input type="text" name="address1" id="address1" size="50" readonly="readonly">
            </li>
            <li>
                <label for="address2">상세주소</label>
                <input type="text" name="address2" id="address2" size="50">		
            </li>
        </ul>
    </fieldset>
</form>
</div>

<hr class="hr-line">

<script type="text/javascript">
$(document).ready(function() {
    // 초기에 주문자 정보로 자동 입력
    <%--
    $("#id").val("<%=loginMember.getMemberId()%>");
    $("#name").val("<%=loginMember.getMemberName()%>");
    $("#email").val("<%=loginMember.getMemberEmail()%>");
    $("#zipcode").val("<%=loginMember.getMemberZipcode()%>");
    $("#address1").val("<%=loginMember.getMemberAddress1()%>");
    $("#address2").val("<%=loginMember.getMemberAddress2()%>");
	--%>
    // 체크박스 상태에 따라 배송지 입력 폼 표시/숨김 및 선택 제한
    // clientaddressCheckbox 체크박스에 대한 change 이벤트 핸들러
$('#clientAddressCheckbox').change(function() {
    var clientAddressChecked = $(this).prop("checked");
    if (clientAddressChecked) {
        // 주문자 정보로 자동 입력
       <%--
        $("#name").val("<%=loginMember.getMemberName()%>");
        $("#email").val("<%=loginMember.getMemberEmail()%>");
        $("#zipcode").val("<%=loginMember.getMemberZipcode()%>");
        $("#address1").val("<%=loginMember.getMemberAddress1()%>");
        $("#address2").val("<%=loginMember.getMemberAddress2()%>");
        --%>

        // 입력 필드 읽기 전용으로 변경
        $("#name, #email, #zipcode, #address1, #address2").prop("readonly", true);

        // newAddressForm 보이기
        $("#newAddressForm").show();
    } else {
        // 입력 필드 읽기/쓰기 가능으로 변경
        $("#name, #email, #zipcode, #address1, #address2").prop("readonly", false);

        // newAddressForm 숨기기
        $("#newAddressForm").hide();
    }
});

// newaddressCheckbox 체크박스에 대한 change 이벤트 핸들러
$('#newAddressCheckbox').change(function() {
    var newAddressChecked = $(this).prop("checked");
    if (newAddressChecked) {
        // 입력 필드 읽기/쓰기 가능으로 변경
        $("#name, #email, #zipcode, #address1, #address2").prop("readonly", false);

        // newAddressForm 보이기
        $("#newAddressForm").show();
    } else {
        // newAddressForm 숨기기
        $("#newAddressForm").hide();
    }
});

    // 우편번호 검색 버튼 클릭 이벤트
    $("#postSearch").click(function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $("#zipcode").val(data.zonecode);
                $("#address1").val(data.address);
            }
        }).open();
    });

    // 한 체크박스만 선택되도록 설정
    $("#clientAddressCheckbox, #newAddressCheckbox").click(function() {
        if ($(this).prop("checked")) {
            $(this).siblings("input[type=checkbox]").prop("checked", false);
        }
    });
});
</script>

</body>
</html>