<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/security/login_url.jspf" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            font-size: 1.5em;
        }
        .cart-header {
            background-color: #f7f7f7;
            border-radius: 20px;
            padding: 10px;
            margin-bottom: 20px;
        }
        .cart-table {
            width: 100%;
            margin: auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-collapse: collapse;
        }
        .cart-table th, .cart-table td {
            padding: 10px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        .cart-table th {
            background-color: #f2f2f2;
        }
        .cart-table .chk {
            width: 20px;
        }
        .total-row {
            font-weight: bold;
        }
        .continue-shopping {
            margin-top: 20px;
            text-align: center;
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
        .btn-success, .btn-delete {
            text-decoration: none;
            color: black;
            padding: 10px 20px;
            background-color: #f7f7f7;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
    </style>
</head>
<body>
 <div class="cart-header">
       <h1>장바구니</h1>
       <table width="100%">
           <tr>
               <td style="padding-left: 5px;">
                   <input type="checkbox" id="allChk" onclick="toggleAll(this)">
                   &nbsp;<b>전체</b>
               </td>
               <td></td>
               <td align="right" style="padding-right: 10px;">
                   <a href="./deleteCart.jsp?cartId={cartId}" class="btn btn-delete">삭제하기</a>
                   <a href="./shippingInfo.jsp?cartId={cartId}" class="btn btn-success">주문하기</a>
               </td>
           </tr>
       </table>
   </div>
   <div style="width: 100%; margin: auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
       <table class="cart-table">
           <thead>
               <tr>
                   <th></th>
                   <th>상품</th>
                   <th>가격</th>
                   <th>수량</th>
                   <th>소계</th>
                   <th>비고</th>
               </tr>
           </thead>
           <tbody>
           <%--				
					int sum = 0;
					ArrayList<Product> cartList = (ArrayList<Product>) session.getAttribute("cartlist");
					if (cartList == null)
						cartList = new ArrayList<Product>();

					for (int i = 0; i < cartList.size(); i++) { // 상품리스트 하나씩 출력하기
						Product camera = cartList.get(i);
						int total = camera.getUnitPrice() * camera.getQuantity();
						sum = sum + total;
			--%>
               <tr>
                   <td><input type="checkbox" class="chk" onclick="updateAllCheckbox()"></td>
                   <td>상품 ID - 상품 이름</td>
                   <td>가격불러오기</td>  <%--  <%=productlist.getPrice()%>--%>
                   <td>수량불러오기</td>
                   <td>소계불러오기</td>
                   <td><a href="./removeCart.jsp?id={productlistId}">&nbsp;삭제</a></td>
               </tr>
             <%-- } --%>
               <!-- 추가적인 상품 행들 -->
               <tr class="total-row">
                   <td></td>
                   <td></td>
                   <td>총액</td>
                   <td><%--= sum --%></td>
                   <td></td>
                   <td></td>
               </tr>
           </tbody>
       </table>
       <div class="continue-shopping">
           <a href="./main.jsp">&laquo; 쇼핑 계속하기</a>
       </div>
   </div>
</body>
</html>
   
   <script type="text/javascript">
   function toggleAll(source) {
       var checkboxes = document.querySelectorAll('.chk');
       for (var i = 0; i < checkboxes.length; i++) {
           checkboxes[i].checked = source.checked;
       }
   }

   function updateAllCheckbox() {
       var checkboxes = document.querySelectorAll('.chk');
       var allChecked = true;
       for (var i = 0; i < checkboxes.length; i++) {
           if (!checkboxes[i].checked) {
               allChecked = false;
               break;
           }
       }
       document.getElementById('allChk').checked = allChecked;
   }
   </script>
</body>
</html>