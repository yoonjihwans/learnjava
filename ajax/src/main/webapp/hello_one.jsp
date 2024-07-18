<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<style type="text/css">
#display {
	width: 50%;
	padding: 5px;
	margin: 10px;
	font-size: 30px;
	border: 1px solid red;
}
</style>
</head>
<body>	
	<h1>XMLHttpRequest 객체</h1>
	<hr>
	<div id="display">Hello, Ajax!!!</div>
	<div>
		<button type="button" id="btn1">번역하기</button>
		<button type="button" id="btn2">배경색 바꾸기</button>
	</div>
	
	<script type="text/javascript">
	/*
	//태그를 검색해 클릭 이벤트가 발생될 경우 호출될 이벤트 처리 함수 저장
	document.getElementById("btn1").onclick=function() {
		//태그를 검색해 검색된 태그의 내용 변경 - DHTML
		// => 현재 요청 웹프로그램의 실행결과를 이용하여 태그내용 변경
		document.getElementById("display").innerHTML="안녕하세요. 에이젝스!!!";
	}
	*/
	
	//AJAX(Asynchronous Javascript + XML) : XMLHttpRequest 객체를 사용하여 비동기식으로
	//웹프로그램을 요청하여 실행결과를 XML 문서로 응답받아 태그를 변경하는 기능 
	//XMLHttpRequest 객체(Ajax Engine) : 비동기식 방식으로 웹프로그램을 요청하여 실행결과를
	//응답받아 처리하기 위한 Javascript 객체
	
	//XMLHttpRequest 객체를 저장하기 위한 전역변수 선언
	var xhr=null;

	document.getElementById("btn1").onclick=function() {
		//XMLHttpRequest 객체를 생성하여 전역변수에 저장
		// => XMLHttpRequest 객체의 readyState 멤버변수에 자동으로 [0] 저장 
		xhr=new XMLHttpRequest();
		
		//XMLHttpRequest 객체의 준비상태(ReadyState)가 변경되는 이벤트가 발생될 경우 호출될
		//이벤트 처리 함수 저장
		//XMLHttpRequest.onreadystatechange : XMLHttpRequest 객체의 준비상태(ReadyState)가 
		//변경되는 이벤트에 대한 이벤트 리스너 속성
		//XMLHttpRequest.readyState : XMLHttpRequest 객체의 준비상태가 저장된 멤버변수(Property)
		// => XMLHttpRequest 객체의 준비상태는 [0]부터 [4]까지 순차적으로 변경
		// => 0 : XMLHttpRequest 객체 생성
		// => 1 : XMLHttpRequest 객체로 open() 멤버함수 호출 - 웹프로그램 요청 설정
		// => 2 : XMLHttpRequest 객체로 send() 멤버함수 호출 - 웹프로그램 요청
		// => 3 : 요청 웹프로그램 실행
		// => 4 : 웹프로그램의 실행결과 응답
		xhr.onreadystatechange=changeHTML;
		
		//XMLHttpRequest 객체로 open() 멤버함수 호출 - 준비상태가 자동으로 [1]로 변경
		//XMLHttpRequest.open(method, url[, async]) : XMLHttpRequest 객체에 웹프로그램을 요청하기
		//위한 정보를 저장하는 멤버함수
		// => method : 요청방식 전달 - GET(검색), POST(삽입), PUT(전체 변경), PATCH(부분 변경)
		//, DELETE(삭제) 등
		// => url : 요청 웹프로그램의 URL 주소 전달 - 현재 서버의 웹프로그램만 요청 가능
		// => async : false(동기식 통신) 또는 true(비동기식 통신 - 기본값) 중 하나를 전달
		//비동기식 통신 : 요청에 대한 응답 기다림 미발생 - 다른 작업 실행 가능 
		xhr.open("get", "hello_two.jsp", true);
		//동기식 통신 : 요청에 대한 응답 기다림 발생 - 다른 작업 실행 불가능
		//xhr.open("get", "hello_two.jsp", false);
		
		//XMLHttpRequest 객체로 send() 멤버함수 호출 - 준비상태가 자동으로 [2]로 변경
		//XMLHttpRequest.send(data) : XMLHttpRequest 객체에 저장된 정보를 사용해 웹프로그램을
		//요청하는 멤버함수
		// => data : 웹프로그램 요청시 [이름=값&이름=값&...] 형식으로 값 전달
		// => 리퀘스트 메세지 몸체부에 값이 저장되어 전달 - POST 방식으로 요청한 경우에만 값 전달 가능
		// => 전달값이 없거나 GET 방식으로 웹프로그램을 요청한 경우 매개변수에 null 전달
		xhr.send(null);//웹프로그램 요청 후 준비상태는 자동으로 [3]과 [4]로 변경
	}
	
	//XMLHttpRequest 객체의 준비상태(ReadyState)가 변경될 때만다 호출되는 이벤트 처리 함수 - 4번 호출
	// => 요청한 웹프로그램의 실행결과를 응답받아 처리하기 위한 명령 작성
	function changeHTML() {
		/*
		if(xhr.readyState == 1) {
			alert("웹프로그램 요청에 대한 환경설정");
		} else if(xhr.readyState == 2) {
			alert("웹프로그램 요청");
		} else if(xhr.readyState == 3) {
			alert("요청 웹프로그램 실행");
		} else if(xhr.readyState == 4) {
			alert("웹프로그램 실행결과 응답 완료");
		}
		*/
		
		if(xhr.readyState == 4) {
			//XMLHttpRequest.status : 웹프로그램 요청에 대한 상태코드(StatusCode)가 저장된 멤버변수
			if(xhr.status == 200) {//정상적인 실행결과를 응답 받은 경우
				//XMLHttpRequest.responseText : 웹프로그램 요청에 대한 응답결과(Text or HTML)가
				//저장된 멤버변수
				//alert(xhr.responseText);
				
				//요청 웹프로그램의 응답결과를 사용해 HTML 태그의 내용 변경
				document.getElementById("display").innerHTML=xhr.responseText;
			} else {//비정상적인 실행결과를 응답 받은 경우
				alert("에러코드 = "+xhr.status);
			}
		} else {
			document.getElementById("display").innerHTML="<img src='images/loading.gif' width='50'>";
		}
	}
	
	document.getElementById("btn2").onclick=function() {
		document.getElementById("display").style="background: green;";
	}
	</script>
</body>
</html>