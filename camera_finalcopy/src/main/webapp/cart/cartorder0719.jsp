<%@page import="xyz.itwill.dao.ProductDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="xyz.itwill.dao.CartDAO"%>
<%@page import="xyz.itwill.dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="java.util.Date"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   
  <%
  UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");
  String usersId = ""; // 초기화

  if (loginUsers != null) {
      usersId = loginUsers.getUsersId(); // 사용자 아이디 가져오기
  }

  if(loginUsers == null) {
  	request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
  	return;	
  }

  List<CartDTO> cartList = CartDAO.getDAO().selectAllCartList(loginUsers.getUsersId());
  List<ProductDTO> productList = new ArrayList<ProductDTO>();
  List<Integer> qtylist =  new ArrayList<Integer>();

  for (CartDTO cart : cartList) {
     
     ProductDTO product = ProductDAO.getDAO().selectProductByNo(cart.getCartproductNo());
     productList.add(product);
     qtylist.add(cart.getCartQuantity());
  
	

  }

 	
  
  

 
 
%>
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

.buttonpay {
    background-color: #4CAF50; /* Green background */
    color: white;
    padding: 10px 20px;
    border: 1px solid #4CAF50; /* Green border */
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    transition: background-color 0.3s, color 0.3s, border-color 0.3s;
}

.buttonpay:hover {
    background-color: #45a049; /* Darker green on hover */
    border-color: #45a049; /* Darker green border on hover */
}
.button-container {
    text-align: center;
  }
  
/* CART CSS 작성 */


table {
	margin: 5px auto;
	border: 0.5px solid rgb(207, 207, 207);
	border-collapse: collapse;
	width: 900px;
}

th {
	height: 40px;
	border: 0.5px solid rgb(207, 207, 207);
	text-align: center;
}

td {
	height: 40px;
	border: 0.5px solid rgb(207, 207, 207);
	text-align: center;
}

.price .count {
	display: inline-block;
	position: relative;
	vertical-align: top;
}


.cartTable table {
	font-size: 13px;
}

.cartTable table input {
	text-align: center;
}

.name {
	cursor: pointer;
}

.name:hover {
	border-bottom: 1px solid rgb(207, 207, 207)
}

.cartTable .quantity button {
	padding: 4px 8px;
	margin: 8px 5px 8px;
	border : none;
}

.name, .price, .total {
	border: none;
	font-size: 15px;
}

.name, .price, .total:focus {
	outline: none;
}


}

.empty { 
	text-align: center;
}

/* cart만 고정 */
body {
	width: 100;
	margin: 0 auto;   
	height: 800px;  
}
#width {
	width: 1000px;
    margin: 0 auto; 
    text-align: center;
}
#selectedTotal{
	font-size: 15px;
	border:none;
	padding-left: 4px;
	line-height: 20px;
	cursor: text;
}
   
</style>
<title>구매정보창</title>
</head>
<body>

<h1>결제정보</h1>
<form id="joinForm" action="<%=request.getContextPath()%>/index.jsp?workgroup=cart&work=orders_action3" method="post">
<div id="detail">
	<p>주문자 정보</p>
	<hr class="hr-line"> 
 
    <p>아이디 : <%=loginUsers.getUsersId()%></p>
    <p>이름 : <%=loginUsers.getUsersName()%></p>
    <p>이메일 : <%=loginUsers.getUsersEmail()%></p>
    <p>전화번호 : <%=loginUsers.getUsersPhone()%></p>
    

</div>
<hr class="hr-line">
<div id="newAddressForm">
	<fieldset>
	
		<table>
					<tr>
						<th><input type="hidden" id="allCheck" name="allcheck"></th>
						<th>Item</th>
						<th>Qty</th>
						<th>Price</th>
						<th>Delivery</th>
						<th>Total</th>
					</tr>
					<%
		            int i = 0;
		            for (; i < productList.size(); i++) {
		            %>
					<tr>
						<td>
							<input type="hidden" name="checkP" value="<%=cartList.get(i).getCartNo()%>" class="check">
							<input type="hidden" name="prodNo" value="<%=productList.get(i).getProdNo()%>">
						</td>
						<td>
							<input type="button" id="name<%=i%>" class="name" name="name" value="<%=productList.get(i).getProdName()%>" >
						</td>
						<td id="quantity<%=i%>" class="quantity">
							<span class="count count-box"> 
								
								<input type="text" class="countInput" id = "quantity"<%=i %>
									name="countInput" value="<%=qtylist.get(i) %>" readonly="readonly"
									style="width: 20px; border: none;">
								
							</span>
						</td>
		
						<td>
							<input id="price<%=i%>" class="price" name="price" value="<%=productList.get(i).getProdPrice()%>" readonly="readonly">
						</td>
						<td>기본배송</td>
						<td>
							<input id="total<%=i%>" class="total"
							value="<%=(productList.get(i).getProdPrice()*qtylist.get(i))%>" name="total" readonly="readonly">
							
						</td>
						
					</tr>
					<%}%>
					<tr>
						<td colspan="5">결제예정금액</td>
						<td><input id="selectedTotal" name="selectedTotal" readonly="readonly" value="0"></td>
					</tr>
				</table>
	</fieldset>
</div>

<div id="newAddressForm">
<h2>배송지 작성</h2>


<div class="checkboxList">
    <input type="checkbox" name="clientAddressCheckbox" class="clientaddressCheckbox" id="clientAddressCheckbox" checked>주문자 정보와 동일&nbsp;&nbsp;
    <input type="checkbox" name="newAddressCheckbox" class="newAddressCheckbox" id="newAddressCheckbox">새로운 배송지 작성&nbsp;&nbsp;
</div>

	

    <fieldset>
        <legend>배송지 정보</legend>
        <ul>
        	  
            
            
            <li>
                <label for="name">받는사람</label>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="name" id="name" readonly="readonly">
            </li>
            <li>
                <label for="phone">전화번호</label>&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="text" name="phone" id="phone" readonly="readonly">
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
                <input type="text" name="address2" id="address2" size="50" readonly="readonly">		
            </li>
            <li>
                <label for="ordersrequest">배송 요청 사항</label>
                <br>
                <textarea name="ordersrequest" id="ordersrequest" rows="4" cols="50" placeholder="배송 요청 사항을 입력해주세요."></textarea>
            </li>
        </ul>
    </fieldset>
 
</div>

 	<hr class="hr-line">
 <div id="newAddressForm">

    <fieldset>
        <legend>결제 수단</legend>
    
     <div style="display: flex; flex-direction: column">
        
        <label>
            <input type="radio" name="payment" value="paytoss" onclick="setPayment(this)"> 토스
        </label>
        <label>
            <input type="radio" name="payment" value="paynaver" onclick="setPayment(this)"> 네이버페이
        </label>
        <label>
            <input type="radio" name="payment" value="paykakao" onclick="setPayment(this)"> 카카오페이
        </label>
        <label>
            <input type="radio" name="payment" value="paymoney" onclick="setPayment(this)"> 계좌이체
        </label>
         <label>
            <input type="radio" name="payment" value="paycard" onclick="setPayment(this)"> 카드결제
        </label>
         <label>
            <input type="radio" name="payment" value="payphone" onclick="setPayment(this)"> 휴대폰결제
        </label>
    </div>

    </fieldset>	
 	</div>
 	
        <div class="button-container">
    		<button type="button"  class="buttonpay"><a href="index.jsp?workgroup=cart&work=cart" style="color:white;">되돌아가기 </a></button>
    		<button type="submit" class="buttonpay">결제하기</button>
		</div>
</form>

<script type="text/javascript">
$(document).ready(function() {
    // 초기에 주문자 정보로 자동 입력

	 <%-- $("#id").val("<%=loginMember.getMemberId()%>"); --%>
    $("#name").val("<%=loginUsers.getUsersName()%>");
    $("#phone").val("<%=loginUsers.getUsersPhone()%>");
    $("#zipcode").val("<%=loginUsers.getUsersZipcode()%>");
    $("#address1").val("<%=loginUsers.getUsersAddress1()%>");
    $("#address2").val("<%=loginUsers.getUsersAddress2()%>");

    // 체크박스 상태에 따라 배송지 입력 폼 표시/숨김 및 선택 제한
    // clientaddressCheckbox 체크박스에 대한 change 이벤트 핸들러
$('#clientAddressCheckbox').change(function() {
    var clientAddressChecked = $(this).prop("checked");
    if (clientAddressChecked) {
        // 주문자 정보로 자동 입력
 	 <%--	$("#id").val("<%=loginMember.getMemberId()%>"); --%>
        $("#name").val("<%=loginUsers.getUsersName()%>");
        $("#phone").val("<%=loginUsers.getUsersPhone()%>");
        $("#zipcode").val("<%=loginUsers.getUsersZipcode()%>");
        $("#address1").val("<%=loginUsers.getUsersAddress1()%>");
        $("#address2").val("<%=loginUsers.getUsersAddress2()%>");


        // 입력 필드 읽기 전용으로 변경
        $("  #name, #phone, #zipcode, #address1, #address2").prop("readonly", true);
        $(" #ordersrequest").prop("readonly", false);
       
        // newAddressForm 보이기
        $("#newAddressForm").show();
    } else {
         // newAddressForm 숨기기
        $("#newAddressForm").hide();
    }
});

// newaddressCheckbox 체크박스에 대한 change 이벤트 핸들러
$('#newAddressCheckbox').change(function() {
    var newAddressChecked = $(this).prop("checked");
    
    if (newAddressChecked) {

    	 $("#id").val("");
         $("#name").val("");
         $("#phone").val("");
         $("#zipcode").val("");
         $("#address1").val("");
         $("#address2").val("");
         $("#request").val("");
         
        // 입력 필드 읽기/쓰기 가능으로 변경
        $("#name, #phone, #zipcode, #address1, #address2,#ordersrequest").prop("readonly", false);

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
	
	function setPayment(radio) {
    	if (radio.checked) {
        	payment = radio.value;
   		 }
	}
	
	
	var total=0;
	  <%
	  for(int j=0;j<productList.size();j++){%>
		total += Number(document.getElementsByName("total")[<%=j%>].value);
	  <%}%>
	  $('#selectedTotal').val(total);
	  
	  

</script>

</body>
</html>