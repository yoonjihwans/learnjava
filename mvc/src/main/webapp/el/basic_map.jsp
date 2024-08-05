<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//HashMap 객체(Map 객체)를 생성하여 저장
	Map<String, String> carMap=new HashMap<String, String>();

	//HashMap 객체(Map 객체)에 엔트리를 추가하여 저장
	carMap.put("modelName", "싼타페");
	carMap.put("carColor", "하얀색");
	
	pageContext.setAttribute("carMap", carMap);
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
	<%
		//@SuppressWarnings : 프로그램에서 발생되는 경고를 제거하는 어노테이션
		// => value 속성값으로 제거할 경고의 종류 설정 - 다른 속성이 없는 경우 속성값만 설정 가능
		@SuppressWarnings("unchecked")
		Map<String, String> car=(Map<String, String>)pageContext.getAttribute("carMap");	
	%>
	<%-- Map 객체가 저장된 참조변수를 출력할 경우 Map 객체의 toString() 메소드가 호출되어
	Map 객체에 저장된 모든 엔트리를 문자열로 반환받아 출력 처리 --%>
	<p>자동차 = <%=car %></p>
	<!-- Map 객체에 저장된 엔트리에서 맵키(MapKey)를 사용해 맵값(MapValue)을 반환받아 출력 처리 -->
	<p>자동차(모델명) = <%=car.get("modelName") %></p>
	<p>자동차(색상) = <%=car.get("carColor") %></p>
	<hr>
	<h2>EL 사용</h2>
	<p>자동차 = ${carMap }</p>
	<%-- ${속성명.맵키} 형식으로 EL 표현식을 사용해 속성값으로 Map 객체를 제공받아 . 연산자로
	맵키로 get() 메소드를 호출하여 맵값을 반환받아 출력 처리 가능 --%>
	<%-- => Map 객체의 get() 메소드를 호출하지 않아도 Map 객체의 맵값을 반환받아 출력 처리 --%>
	<p>자동차(모델명) = ${carMap.modelName }</p>
	<p>자동차(색상) = ${carMap.carColor }</p>
	<hr>
	<p>자동차(모델명) = ${carMap["modelName"] }</p>
	<p>자동차(색상) = ${carMap["carColor"] }</p>
</body>
</html>






