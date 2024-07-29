<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@page import="xyz.itwill.dao.ProductDAO"%>
<%@page import="xyz.itwill.dao.CartDAO"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.CartDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page language="java" contentType="text/html; charset=UTF-8"
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<title>Cart</title>

<style type="text/css">
/* CSS 작성 */
.titleArea h1 {
	margin: 0 0 30px;
	text-align: center;
}

.basketinfo .menu {
	text-align: center;
	border-bottom: 0.3px solid rgb(207, 207, 207);
	max-width: 180px;
	margin: 0 auto;
}

.basketinfo .menu p {
	font-size: 18px;
	margin: 20px 20px 12px;
	padding: 13px 10px 0px;
}

.removeBtn {
	text-align: center;
}

.removeBtn .textAndBtn {
	margin-right: 100px;
}
.continue-shopping {
            margin-top: 10px;
            text-align: center;
            margin-right: 800px;
}
.continue-shopping a {
            text-decoration: none;
            color: black;
            padding: 10px 20px;
            background-color: #f7f7f7;
            border: 1px solid #ccc;
            border-radius: 5px;
}
.continue-shopping a:hover {
            background-color: #e3e3e3;
}
.basket_btn {
	margin: 0 auto;
	width: 400px;
	height: 50px;
	font-size: 1.2em;
	cursor: pointer;
	padding: 12px 0;
	text-align: center;
	margin-top: 10px;
}

.basket_btn .submitAllBtn {
			text-decoration: none;
            color: black;
            padding: 10px 20px;
            background-color: #f7f7f7;
            border: 1px solid #ccc;
            border-radius: 5px;
}

.basket_btn .submitAllBtn:hover {
	 background-color: #e3e3e3;
}

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

.count #down .downBtn {
	position: static
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
.removeBtn{
width:900px;
border:1px solid white;
margin: 0 auto;
}

.removeBtn #removeSelectBtn {
	background-color: #ffc107;
	cursor: pointer;
	
	padding: 6px 12px;
}
.removeBtn #removeSelectBtn:hover {
	background-color: #45a049;
	border: 1px solid rgb(207, 207, 207);
	
}

.quantity .upBtn {
	border: none;
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
  .upBtn, .downBtn {
        width: 20px;
        height: 20px;
        border: none;
        background-color: #ccc;
        cursor: pointer;
        position: relative;
        font-size: 18px; /* 폰트 크기 조정으로 기호 크기 조절 가능 */
    }

    .upBtn::before, .downBtn::before {
        content: '+';
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }

    .downBtn::before {
        content: '-';
    }
</style>
</head>
<body>
<div id="width: 100%">
	
	<div class="basketinfo">
		<div class="menu">
			<p>국내배송상품</p>
		</div>
		 <div class="removeBtn" style="text-align: right;">
		 	<div id="wrap">
   			<button type="button" id="removeSelectBtn" >선택상품 삭제하기</button>
   			</div>
		</div>
		
	</div>
	<br>
	<%-- 있으면 목록 출력 없으면 비어있음 표시--%>
	<% if (cartList.isEmpty()) { %>
		<div class="empty">
		<p>Your cart is empty</p>
		</div>
	<%} else {%>
	<form id = "cartForm" class="cartTable" method="post" action = "<%=request.getContextPath()%>/index.jsp?workgroup=cart&work=cart_submit">
		<div class="cartlist">
			
				<table>
					<tr>
						<th><input type="checkbox" id="allCheck" name="allcheck"></th>
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
							<input type="checkbox" name="checkP" value="<%=cartList.get(i).getCartNo()%>" class="check">
							<input type="hidden" name="prodNo" value="<%=productList.get(i).getProdNo()%>">
						</td>
						<td>
							<input type="button" id="name<%=i%>" class="name" name="name" value="<%=productList.get(i).getProdName()%>" >
						</td>
						<td id="quantity<%=i%>" class="quantity">
							<span class="count count-box"> 
								<button type="button" name="countBtn" class="upBtn">
									
								</button>
								<input type="text" class="countInput" id = "quantity"<%=i %>
									name="countInput" value="<%=qtylist.get(i) %>" readonly="readonly"
									style="width: 20px; border: none;">
								<button type="button" name="countBtn" class="downBtn">
									
								</button>
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
						<td><input id="selectedTotal" readonly="readonly" value="0"></td>
					</tr>
				</table>
				
				<div class="basket_btn" style="text-align: center;">
    	 			<input type="submit" id="submitAllBtn" class="submitAllBtn" value="주문하기">
   				</div>
 				<div class="continue-shopping">
          		 <a href="./index.jsp">쇼핑 계속하기</a>
       			</div>
       			
       			
		</div>

		</form>
		<%}%>
</div>	

<script type="text/javascript">
	$("document").ready(function() {
	  //selectedTotal
		var total=Number(0);
		  <%
		  for(int j=0;j<productList.size();j++){%>
			total += Number(document.getElementsByName("total")[<%=j%>].value);
		  <%}%>
		  $('#selectedTotal').val(total);
	});
	
	$("document").ready(function() {
      //count button 
      $(document).on('click','button[name="countBtn"]',function(e){
   		 e.stopPropagation();
   	     e.preventDefault();
    	 let countBox = $(this).closest('.count-box');
    	 let row = countBox.closest('tr');
    	 let countInput = countBox.find('input[name=countInput]');
    	 let count = parseInt(countInput.val());
    	 let price = row.find('input[name=price]').val(); 
    	 let totalInput = row.find('input[name=total]');
    	
    	 //upBtn일 경우
    	 if($(this).hasClass("upBtn")){
    		 count++;
    		
    	 //downBtn일 경우
    	 } else {
    		 count--;
    		 if(count < 1) return;
    	 }
    	 countInput.val(count);
    	 totalInput.val(count * price); 
    	 var total=Number(0);
		  <%
		  for(int j=0;j<productList.size();j++){%>
			total += Number(document.getElementsByName("total")[<%=j%>].value);
		  <%}%>
		  $('#selectedTotal').val(total);
      });
      
      //전체 체크
      $(document).on('change','#allCheck',function(e){
    	  let checkItem = $("input[name=checkP]");
          if($(this).prop("checked")) { 
        	  checkItem.prop("checked",true); 
          } else {
        	  checkItem.prop("checked",false);
          }
      });
      let checkItem = $("input[name=checkP]");
      $("#allCheck").prop("checked",true);
      checkItem.prop("checked",true); 
      
      //개별 체크
      $(document).on('change','input[name=checkP]',function(e){
         let totalPrice = $("#selectedTotal");
         let countInput = countBox.find('input[name=countInput]');
         let count = countInput.val();
         totalPrice = parseInt(document.getElementById("sum").val(count)); 
         let val = document.getElementById('input[name="checkP"]').checked;
         
         if($(this).prop("checked")) {
            totalPrice+=result;
         } else {
         totalPrice-=result;
      }
         document.getElementById("sum").value = totalPrice;
         
         totalPrice.empty();
         totalPrice.html(val);  
      }); 
      
     
    //개별 선택 삭제
     $("#removeSelectBtn").click(function() {
    	 let usersId =  '<%= usersId %>';
		
    	 
    	 let selectedItemArr = $('input[name="checkP"]:checked').map(function () {
    		    return this.value; 
    	 }).get();  
    	 
    	 console.log(usersId);
    	 console.log(selectedItemArr);
    	 
         if(window.confirm("선택 상품을 삭제하시겠습니까?")) {
            location.href="<%=request.getContextPath()%>/index.jsp?workgroup=cart&work=cart_delete&usersId="+usersId+"&checkP="+selectedItemArr;
         }
    });   
    
    
});
</script>
</body>
</html>