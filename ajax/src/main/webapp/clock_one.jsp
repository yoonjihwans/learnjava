<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<style type="text/css">
div {
	font-size: 20px;
	margin: 10px;
}
</style>
</head>
<body>
	<h1>브라우저 캐싱</h1>
	<hr>
	<div>클라이언트 플렛폼의 현재 날짜와 시간은 <span id="clientTime">
		2024년 7월 18일 16시 5분 20초</span>입니다.</div>
	<div>서버 플렛폼의 현재 날짜와 시간은 <span id="serverTime">
		2024년 7월 18일 16시 5분 20초</span>입니다.</div>	
		
	<script type="text/javascript">
	//클라이언트 플렛폼의 현재 날짜와 시간을 제공받아 태그내용을 변경하는 함수
	// => 브라우저를 실행하는 클라이언트 플렛폼에 따라 서로 다른 응답결과 제공
	function displayClientTime() {
		//클라이언트 플렛폼의 현재 날짜와 시간이 저장된 Date 객체를 생성하여 저장
		var now=new Date();
		
		//Date 객체에 저장된 날짜와 시간을 사용해 출력결과를 생성하여 저장
		var displayTime=now.getFullYear()+"년 "+(now.getMonth()+1)+"월 "+now.getDate()+"일 "
			+now.getHours()+"시 "+now.getMinutes()+"분 "+now.getSeconds()+"초";
		
		//태그를 검색해 태그내용 변경
		document.getElementById("clientTime").innerHTML=displayTime;
	}
	
	displayClientTime();
	setInterval(displayClientTime, 1000);
	
	//웹프로그램을 실행하는 서버 플렛폼의 현재 날짜와 시간을 제공받아 태그내용을 변경하는 함수
	// => 서버 플렛폼의 현재 날짜와 시간을 제공받아 사용하므로 모든 클라이언트에게 동일한 응답결과 제공
	// => Ajax 엔진을 사용해 서버 서버 플렛폼의 현재 날짜와 시간을 제공하는 웹프로그램을 요청
	//하여 응답결과를 제공받아 태그내용 변경
	//문제점)일부 브라우저에서는 동일한 웹프로그램을 지속적으로 요청한 경우 웹프로그램의 응답결과를
	//제공받아 출력하지 않고 브라우저 캐싱 기능에 의해 저장된 기존 응답결과를 제공받아 출력 처리
	//해결법-1)요청 웹프로그램의 URL 주소를 질의문자열로 계속 변경하여 요청
	//해결법-2)요청 웹프로그램의 응답결과가 브라우저 캐싱 기능에 의해 저장되지 않도록 무효화 처리
	function displayServerTime() {
		var xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function() {
			if(xhr.readyState == 4) {
				if(xhr.status == 200) {
					document.getElementById("serverTime").innerHTML=xhr.responseText;
				} else {
					alert("에러코드 = "+xhr.status);
				}
			}
		}
		xhr.open("get", "clock_two.jsp");
		//xhr.open("get", "clock_two.jsp?dummy="+new Date().getTime());
		xhr.send(null);
	}
	
	displayServerTime();
	setInterval(displayServerTime, 1000);
	</script>	
</body>
</html>