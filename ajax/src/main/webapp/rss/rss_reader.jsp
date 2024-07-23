<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- RSS 서비스를 제공하는 웹프로그램을 Ajax 엔진으로 요청하여 실행결과를 XML로 제공받아 
HTML 태그로 변환하여 응답하는 JSP 문서 --%>
<%-- RSS(Really Simple Syndication 또는 Rich Site Summary) : 블로그, 뉴스, 기업정보등과 같이
자주 업데이트 되는 사이트의 컨텐츠를 보다 쉽게 사용자에게 제공하기 위해 만들어진 서비스 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h1>RSS Reader</h1>
	<hr>
	<div id="display"></div>
	
	<script type="text/javascript">
	$.ajax({
		type: "get",
		//문제점)현재 실행중인 웹프로그램과 동일한 서버에 웹프로그램만 Ajax 엔진으로 요청해 실행
		//결과를 응답 받을 수 있지만 다른 서버의 웹프로그램은 Ajax 엔진으로 요청하면 에러 발생
		//url: "https://www.khan.co.kr/rss/rssdata/kh_sports.xml",
		//해결법)프록시 기능을 제공하는 웹프로그램을 사용해 다른 서버의 웹프로그램을 요청해 
		//실행결과 응답받아 처리 - Ajax 엔진으로 프록시 기능을 제공하는 웹프로그램 요청
		url: "<%=request.getContextPath()%>/rss/rss_proxy.jsp",
		dataType: "xml",
		success: function(xmlDoc) {
			var channelTitle=$(xmlDoc).find("channel").children("title").text();
			
			var html="<h2>"+channelTitle+"</h2>";
			html+="<ul>";
			$(xmlDoc).find("item").each(function() {
				var title=$(this).find("title").text();//뉴스제목
				var link=$(this).find("link").text();//URL 주소
				var date;//작성날짜
				if($(this).find("pubDate").length != 0) {
					date=$(this).find("pubDate").text();
				} else {
					date=$(this).find("dc\\:date").text();
				}
				html+="<li><a href='"+link+"'>"+title+"["+date+"]</a></li>";
			});
			html+="</ul>";
			$("#display").html(html);
		}, 
		error: function(xhr) {
			alert("에러코드 = "+xhr.status);
		}
	});
	</script>
</body>
</html>