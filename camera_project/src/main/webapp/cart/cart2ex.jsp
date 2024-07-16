<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                   <a href="./cartorder.jsp?cartId={cartId}" class="btn btn-success">주문하기</a>
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
               <!-- 예시 데이터로 2개의 상품 행을 생성 -->
               <tr>
                   <td><input type="checkbox" class="chk" onclick="updateAllCheckbox()"></td>
                   <td>상품 ID - 상품 이름 1</td>
                   <td class="price">1000</td> <!-- 예시로 가격 설정, 실제로는 동적으로 가져와야 함 -->
                   <td>
                       <button onclick="adjustQuantity(this, -1)">-</button>
                       <input type="number" min="1" value="1" onchange="updateSubtotal(this)">
                       <button onclick="adjustQuantity(this, 1)">+</button>
                   </td>
                   <td class="subtotal">1000</td> <!-- 예시로 초기 소계 설정 -->
                   <td><a href="./removeCart.jsp?id={productlistId}">삭제</a></td>
               </tr>
               
               <tr>
                   <td><input type="checkbox" class="chk" onclick="updateAllCheckbox()"></td>
                   <td>상품 ID - 상품 이름 1</td>
                   <td class="price">1000</td> <!-- 예시로 가격 설정, 실제로는 동적으로 가져와야 함 -->
                   <td>
                       <button onclick="adjustQuantity(this, -1)">-</button>
                       <input type="number" min="1" value="1" onchange="updateSubtotal(this)">
                       <button onclick="adjustQuantity(this, 1)">+</button>
                   </td>
                   <td class="subtotal">1000</td> <!-- 예시로 초기 소계 설정 -->
                   <td><a href="./removeCart.jsp?id={productlistId}">삭제</a></td>
               </tr>
           
               <!-- 추가적인 상품 행들을 여기에 추가 -->
               <tr class="total-row">
                   <td></td>
                   <td></td>
                   <td>총액</td>
                   <td colspan="2" id="totalAmount"></td> <!-- 초기 총액을 예시로 설정 -->
                   <td></td>
               </tr>
           </tbody>
       </table>
       <div class="continue-shopping">
           <a href="./main.jsp">쇼핑 계속하기</a>
       </div>
   </div>

   <script type="text/javascript">
       // 수량 변경 시 호출되는 함수
       function adjustQuantity(button, change) {
           var input = button.parentNode.querySelector('input[type="number"]');
           var currentValue = parseInt(input.value);
           var newValue = currentValue + change;
           if (newValue >= 1) {
               input.value = newValue;
               updateSubtotal(input); // 수량이 변경될 때 소계를 업데이트
           }
       }

       // 소계를 업데이트하는 함수
       function updateSubtotal(input) {
           var quantity = parseInt(input.value);
           var row = input.parentNode.parentNode; // 현재 행(tr) 가져오기
           var unitPrice = parseInt(row.querySelector('.price').textContent); // 상품 가격 가져오기
           var subtotal = quantity * unitPrice; // 수량 * 가격 계산
           row.querySelector('.subtotal').textContent = subtotal; // 소계 업데이트
           updateTotalAmount(); // 전체 총액 업데이트
       }

       // 전체 총액을 업데이트하는 함수
       function updateTotalAmount() {
           var total = 0;
           var subtotals = document.querySelectorAll('.subtotal');
           subtotals.forEach(function(element) {
               total += parseInt(element.textContent); // 각 상품의 소계를 총액에 추가
           });
           document.getElementById('totalAmount').textContent = total; // 총액 업데이트
       }

       // 전체 선택 체크박스를 클릭했을 때 동작하는 함수
       function toggleAll(source) {
           var checkboxes = document.querySelectorAll('.chk');
           checkboxes.forEach(function(checkbox) {
               checkbox.checked = source.checked;
           });
       }

       // 개별 체크박스가 클릭되었을 때 전체 선택 체크박스 상태를 업데이트하는 함수
       function updateAllCheckbox() {
           var checkboxes = document.querySelectorAll('.chk');
           var allChecked = true;
           checkboxes.forEach(function(checkbox) {
               if (!checkbox.checked) {
                   allChecked = false;
               }
           });
           document.getElementById('allChk').checked = allChecked;
       }

       // 페이지 로드 시 총액 계산
       window.onload = function() {
           updateTotalAmount(); // 초기 총액 계산
       };
   </script>
</body>
</html>