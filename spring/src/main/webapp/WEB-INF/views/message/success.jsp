<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
</head>
<body>
	<h1>제품확인</h1>
	<hr>
	<%-- 
	<p>제품코드 = ${product.productCode }</p>
	<p>제품이름 = ${product.productName }</p>
	<p>제품수량 = ${product.productQuantity }</p>
	--%>
	<p><spring:message code="product.code"/> = ${product.productCode }</p>
	<p><spring:message code="product.name"/> = ${product.productName }</p>
	<p><spring:message code="product.qty"/> = ${product.productQuantity }</p>
	<hr>
	<%-- Properties 파일에 저장된 메세지에 값을 전달하여 사용 가능 --%>
	<%-- => Properties 파일에 저장된 메세지에서는 {정수값} 형식으로 표현하여 전달된 값을
	메세지에 포함해 작성 가능 - 정수값은 0부터 1씩 증가 --%>
	<%-- arguments 속성 : Properties 파일에 저장된 메세지에게 전달할 값을 속성값으로 설정 --%>
	<%-- => 다수의 값을 전달할 경우 [,] 기호를 사용해 구분하여 전달 --%>
	<h3><spring:message code="product.success" 
		arguments="${product.productName}, ${product.productCode }"/></h3>
</body>
</html>













