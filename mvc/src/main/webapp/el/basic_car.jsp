<%@page import="xyz.itwill.el.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("car", new Car("싼타페", "하얀색"));
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL(Expression Language)</h1>
	<hr>
	<h2>EL 미사용</h2>
	<%
		Car car=(Car)pageContext.getAttribute("car");
		//Car car=(Car)pageContext.getAttribute("pageCar");//참조변수에 [null] 저장
	%>
	<%-- 참조변수를 출력할 경우 Object 클래스의 toString() 메소드가 자동 호출되어 객체의 메모리
	주소(HashCode)를 문자열로 반환받아 출력 처리 --%>
	<p>자동차 = <%=car %></p>
	<%-- 클래스의 Getter 메소드를 호출하여 객체의 필드값을 반환받아 출력 처리 --%>
	<%-- => [null]이 저장된 참조변수로 메소드를 호출할 경우 NullPointerException 발생 --%>
	<p>자동차(모델명) : <%=car.getModelName() %></p>
	<p>자동차(색상) : <%=car.getCarColor() %></p>
	<hr>
	<h2>EL 사용</h2>
	<p>자동차 = ${car }</p>
	<%-- EL 표현식에서 속성값을 객체로 제공받아 메소드 호출 가능 --%>
	<p>자동차(모델명) = ${car.getModelName() }</p>
	<p>자동차(색상) = ${car.getCarColor() }</p>
	<hr>
	<%-- ${속성명.필드명} 형식으로 EL 표현식을 사용해 속성값으로 객체를 제공받아 . 연산자로
	객체의 필드명으로 Getter 메소드를 호출하여 필드값을 반환받아 출력 처리 가능 --%>
	<%-- => 클래스의 Getter 메소드를 호출하지 않아도 객체의 필드값을 반환받아 출력 처리 --%>
	<%-- => Getter 메소드를 작성 규칙에 맞지 않게 작성하거나 Getter 메소드가 없는 경우 에러 발생 --%>
	<p>자동차(모델명) = ${car.modelName }</p>
	<p>자동차(색상) = ${car.carColor }</p>
	<hr>
	<%-- . 연산자 대신 [] 연산자를 사용해 객체의 필드값을 제공받아 출력 처리 가능 --%>
	<%-- => [] 연산자 내부에 문자열로 필드명을 사용해 Getter 메소드로 필드값을 반환받아 출력 처리 --%>
	<p>자동차(모델명) = ${car["modelName"] }</p>
	<p>자동차(색상) = ${car["carColor"] }</p>
	<hr>
	<%-- EL 표현식으로 제공받은 객체가 없는 경우 EL 미실행 - NullPointerException 미발생 --%>
	<p>자동차(모델명) = ${pageCar.modelName }</p>
</body>
</html>