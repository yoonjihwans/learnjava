<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</head>
<body>
	<h1>결재페이지</h1>
	<hr>
	<h3>결재할 제품정보 출력</h3>
	<hr>
	<button type="button" id="html5_inicis" class="pay">일반결재(KG이니시스)</button>
	<button type="button" id="kakaopay" class="pay">간편결재(카카오페이)</button>
	
	<script type="text/javascript">
	$(".pay").click(function() {
		var pg=$(this).attr("id");
		
		var IMP=window.IMP;
		//IMP 객체 초기화 - 가맹점 식별자 코드 
		IMP.init("imp37020058");
		
		//주문번호를 생성하여 저장
		var merchantUid="merchant_"+new Date().getTime();
		//태그로 출력된 값을 제공받아 결재금액을 계산하여 저장
		var amount=10;
		
		//결재 전 주문번호와 결재금액을 세션에 저장하기 위한 페이지를 비동기식 방식으로 요청
		$.ajax({
			type: "post",
			url: "<c:url value="/pay/payment"/>",
			contentType: "application/json",
			data: JSON.stringify({"merchantUid":merchantUid, "amount":amount}),
			dataType: "text",
			success: function(result) {
				if(result == "ok") {
					//결재를 요청하는 IMP 객체의 메소드 호출
					IMP.request_pay({
						//pg(결재 대행사) : kakaopay, html_inicis, nise, jtnet, uplus, danal, payco 등
						pg : pg,
						//pay_method(결재방식) : card, sumsung(삼성페이), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결재) 등
						pay_method : "card",
						//merchant_uid(주문번호)
						merchant_uid : merchantUid,
						//amount(결재금액)
						amount : amount,
						//name(결제창에 보여질 제품명)
						name : "컴퓨터",
						//buyer_email(결제창에 보여질 결재 사용자의 이메일 주소)
						buyer_email : "ocj1778@hanmail.net",
						//buyer_tel(결제창에 보여질 결재 사용자의 전화번호)
						buyer_tel : "010-1234-5678",
						//buyer_postcode(결제창에 보여질 우편번호)
						buyer_postcode : "123-456",
						//buyer_addr(결제창에 보여질 주소)
						buyer_addr : "서울시 강남구 역삼동 내빌딩 4층 3강의실",
					}, function(response) {//결제정보를 제공받아 처리하기 위한 콜백함수
						//response : 응답결과를 저장한 Object 객체
						if(response.success) {//결제가 성공한 경우
							alert(response.imp_uid);
							
							//결제금액을 검증하기 위한 페이지를 비동기식 방식으로 요청
							$.ajax({
								type: "post",
								url: "<c:url value="/pay/complate"/>",
								contentType: "application/json",
								data: JSON.stringify({"impUid":response.imp_uid, 
									"merchantUid":response.merchant_uid}),
								dataType: "text",
								success: function(result) {
									if(result == "success") {
										//결제 성공 페이지로 이동
										alert("결제 성공");
									} else {
										//결제 실패 페이지로 이동
										alert("결제 취소");
									}
								},
								error: function(xhr) {
									alert("에러코드 = "+xhr.status);
								}
							});
							
						}  
					});					
				}
			},
			error: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		})
	});
	</script>
</body>
</html>










