<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Formatter - 날짜와 시간 변환 태그</h1>
	<hr>
	<%-- 서버 플렛폼의 현재 날짜와 시간이 저장된 Date 객체를 생성해 Scope 속성값으로 저장  --%>
	<c:set var="now" value="<%=new Date() %>"/>
	<%-- EL를 사용해 Scope 속성값(Date 객체)을 출력 처리하면 Date 클래스의 toString() 메소드를
	호출하여 Date 객체에 저장된 날짜와 시간을 문자열로 반환받아 출력 --%>
	<p>now = ${now }</p>
	
	<%-- formatDate 태그 : Date 객체에 저장된 날짜와 시간을 원하는 패턴의 문자열로 변환하는 태그 --%>
	<%-- => SimpleDateFormat 클래스의 format() 메소드와 유사한 기능 제공 --%>
	<%-- value 속성 : Date 객체를 속성값으로 설정 - EL를 사용해 Date 객체를 제공받아 속성값으로 설정 가능 --%>
	<%-- type 속성 : date(날짜) - 기본값, time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => type 속성값을 [date]로 설정한 경우 [yyyy.M.d.] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date"/></p>
	<%-- dateStyle 속성 : full 또는 short 중 하나를 속성값으로 설정 --%>
	<%-- => dateStyle 속성값을 [short]로 설정한 경우 [yy.M.d.] 패턴의 문자열로 변환되어 제공 --%>
	<%-- => dateStyle 속성값을 [full]로 설정한 경우 [yyyy년 M월 d일 E요일] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date" dateStyle="short"/></p>
	<p>now(날짜) = <fmt:formatDate value="${now }" type="date" dateStyle="full"/></p>

	<%-- type 속성 : date(날짜) - 기본값, time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => type 속성값을 [time]로 설정한 경우 [a h:mm:ss] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(시간) = <fmt:formatDate value="${now }" type="time"/></p>

	<%-- timeStyle 속성 : full 또는 short 중 하나를 속성값으로 설정 --%>
	<%-- => timeStyle 속성값을 [short]로 설정한 경우 [a h:mm] 패턴의 문자열로 변환되어 제공 --%>
	<%-- => timeStyle 속성값을 [full]로 설정한 경우 [a h시 mm분 ss초 z] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(시간) = <fmt:formatDate value="${now }" type="time" timeStyle="short"/></p>
	<p>now(시간) = <fmt:formatDate value="${now }" type="time" timeStyle="full"/></p>
	
	<%-- type 속성 : date(날짜) - 기본값, time(시간), both(날짜와 시간) 중 하나를 속성값으로 설정 --%>
	<%-- => type 속성값을 [both]로 설정한 경우 [yyyy.M.d. a h:mm:ss] 패턴의 문자열로 변환되어 제공 --%>
	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both"/></p>

	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/></p>
	<p>now(날짜와 시간) = <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/></p>
	
	<%-- pattern 속성 : 날짜와 시간을 변환하기 위한 패턴정보를 속성값으로 설정 --%>
	<p>now(패턴) = <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/>
	
	<%-- timeZone 태그 : 타임존(TimeZone)을 변경하는 태그 --%>
	<%-- value 속성 : 변경할 타임존(대륙/도시)을 속성값으로 설정 --%>
	<fmt:timeZone value="America/New_York">
		<p>뉴욕의 현재 날짜와 시간 : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	</fmt:timeZone>
	
	<fmt:timeZone value="Asia/Hong_Kong">
		<p>홍콩의 현재 날짜와 시간 : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
	</fmt:timeZone>
</body>
</html>