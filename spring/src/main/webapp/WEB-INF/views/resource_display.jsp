<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
<%-- <link href="/spring/css/style.css" type="text/css" rel="stylesheet"> --%>
<link href="<c:url value="/css/style.css"/>" type="text/css" rel="stylesheet">
<%-- <script type="text/javascript" src="/spring/js/jquery-3.7.1.min.js"></script> --%>
<script type="text/javascript" src="<c:url value="/js/jquery-3.7.1.min.js"/>"></script>
</head>
<body>
	<h1 class="text">리소스 파일</h1>
	<hr>
	<%-- 리소스 파일(Resource File) : 클라이언트에서 HTML 문서를 해석하여 실행하기 위해 필요한 
	정보를 URL 주소로 제공받기 위해 서버에 저장된 파일(웹자원) --%>
	<%-- => CSS 파일, JavaScript 파일, 멀티미디어 파일(그림파일, 소리파일, 동영상 파일) 등 --%>
	<%-- => 클라이언트에서 HTML 문서를 해석해 실행할 때 URL 주소의 리소스 파일이 서버에 없는 
	경우 404 에러코드가 발생되러 비정상적인 출력 결과 발생 --%>
	
	<!-- 문제점 : 리소스 파일이 [WEB-INF] 폴더에 저장된 경우 클라이언트 요청에 의해 응답 불가능 -->
	<!-- => [WEB-INF] 폴더는 서버에서 서블릿으로만 접근 가능하고 클라이언트 접근이 불가능한
	은닉화 처리된 폴더이므로 클라이언트 요청에 의해 응답 불가능 -->
	<!-- 해결법 : [WEB-INF] 폴더가 아닌 웹자원으로 제공될 [webapp] 폴더에 리소스 파일 저장  -->
	<%-- <img alt="코알라" src="/spring/WEB-INF/views/images/Koala.jpg" width="200"> --%>
	
	<!-- 문제점 : Front Controller가 클라이언트의 모든 요청을 전달받아 처리되도록 설정된 경우 
	리소스 파일을 클라이언트가 요청한 경우에도 Front Controller가 요청 처리 메소드 호출하여 처리 -->
	<!-- => 리소스 파일의 URL 주소에 대한 요청 처리 메소드가 없으므로 404 에러코드 발생 -->
	<!-- 해결법 : 클라이언트가 리소스 파일을 요청한 경우 Front Controller가 요청 처리 메소드를
	호출하지 않고 직접 응답처리 되도록 Spring Bean Configuration File(servlet-context.xml)에 
	resources 엘리먼트를 사용해 환경 설정 -->
	<%-- <img alt="코알라" src="/spring/images/Koala.jpg" width="200"> --%>
	<%-- <img alt="코알라" src="/spring/resources/images/Koala.jpg" width="200"> --%>
	<img alt="코알라" src="/spring/images/Koala.jpg" width="200">
	
	<%-- 웹자원의 경로는 반드시 절대경로로 표현하여 요청 --%>
	<%-- => 컨텍스트 폴더의 이름이 변경되면 URL 주소도 변경되어 404 에러코드 발생 가능 --%>
	<%-- => 컨텍스트 폴더의 이름이 변경되면 URL 주소도 자동 변경되도록 컨텍스트 폴더의 경로를 제공받아 작성 --%>
		
	<%-- 1. EL 표현식에 pageContext 내장객체를 사용해 컨텍스트 폴더의 경로를 제공받아 URL 주소로 사용 --%>
	<img alt="코알라" src="${pageContext.request.contextPath }/images/Koala.jpg" width="200">
	
	<%-- 2. core 태그 라이브러리의 url 태그를 사용해 웹자원의 URL 주소를 제공받아 사용 --%>
	<img alt="코알라" src="<c:url value="/images/Koala.jpg"/>" width="200">
	
	<%-- 3. spring 태그 라이브러리의 url 태그를 사용해 웹자원의 URL 주소를 제공받아 사용 --%>
	<img alt="코알라" src="<spring:url value="/images/Koala.jpg"/>" width="200">

	<script type="text/javascript">
	$(".text").css("text-align","center");
	</script>
</body>
</html>



