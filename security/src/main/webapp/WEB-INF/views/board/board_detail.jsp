<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SECURITY</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	text-align: center;
	padding: 5px;	
}
</style>
</head>
<body>
	<h1>게시글 상세</h1>
	<hr>
	<table>
		<tr>
			<td width="200">글번호</td>
			<td width="500">${securityBoard.num }</td>
		</tr>
		<tr>
			<td width="200">작성자</td>
			<td width="500">${securityBoard.writer }</td>
		</tr>
		<tr>
			<td width="200">제목</td>
			<td width="500" style="text-align: left;">${securityBoard.subject }</td>
		</tr>
		<tr>
			<td width="200">내용</td>
			<td width="500" style="font-size: 16px; text-align: left;">
				<pre>${securityBoard.content }</pre>
			</td>
		</tr>
		<tr>
			<td width="200">작성일</td>
			<td width="500">${securityBoard.regdate }</td>
		</tr>
	</table>
	
	<div style="margin-top: 10px;">
		<form method="get" id="linkForm">
			<input type="hidden" name="num" value="${securityBoard.num }">
			<%-- 권한 설정을 위해 게시글 작성자 전달 --%>
			<input type="hidden" name="writer" value="${securityBoard.writer }">
			<input type="hidden" name="pageNum" value="${searchMap.pageNum }">
			<input type="hidden" name="pageSize" value="${searchMap.pageSize }">
			<input type="hidden" name="column" value="${searchMap.column }">
			<input type="hidden" name="keyword" value="${searchMap.keyword }">
			
			<button type="button" id="listBtn">글목록</button>
			<sec:authorize access="isAuthenticated()">
				<%-- authorize 태그의 access 속성값으로 설정된 권한이 있는 경우 var 속성값으로 
				작성된 Scope 속성명에 [true] 저장 --%>
				<sec:authorize access="hasRole('ROLE_ADMIN')" var="adminRole"/>
				<%-- authentication 태그로 인증된 사용자 정보를 제공받아 var 속성값으로 작성된
				Scope 속성명에 인증된 사용자 정보 저장 --%>
				<sec:authentication property="principal" var="pinfo"/>
			
				<c:if test="${adminRole || pinfo.userid eq securityBoard.writer }">
					<button type="button" id="modifyBtn">글변경</button>
					<button type="button" id="removeBtn">글삭제</button>
				</c:if>
			</sec:authorize>
		</form>
	</div>
	<hr>
	
	<%-- 댓글을 입력받거나 댓글 목록을 출력하는 태그 - 로그인 사용자에게만 제공 --%>
	<sec:authorize access="isAuthenticated()">
		<input type="hidden" id="writer" value="<sec:authentication property="principal.userid"/>">
		<div>
			<textarea rows="3" cols="60" id="content"></textarea>
			<button type="button" id="addBtn">댓글쓰기</button>
		</div>
	</sec:authorize>
	<div id="replyList"></div>
	
	<script type="text/javascript">
	$("#listBtn").click(function() {
		$("#linkForm").attr("action","<c:url value="/board/list"/>").submit();
	});
	
	$("#modifyBtn").click(function() {
		$("#linkForm").attr("action","<c:url value="/board/modify"/>").submit();
	});
		
	$("#removeBtn").click(function() {
		$("#linkForm").attr("action","<c:url value="/board/remove"/>").submit();
	});
	
	function replyListDisplay() {
		$.ajax({
			type: "get",
			url: "<c:url value="/reply/list"/>/"+${securityBoard.num},
			dataType: "json",
			success: function(result) {
				if(result.length == 0) {
					var html="<div style='width: 600px; border-bottom: 1px solid black;'>";
					html+="댓글이 하나도 없습니다.";
					html+="</div>";
					$("#replyList").html(html);
					return;
				}
				
				var html="";
				$(result).each(function() {
					html+="<div style='width: 600px; border-bottom: 1px solid black;'>";
					html+="["+this.num+"]"+this.name+"<br>";
					html+="<pre>"+this.content+"</pre>("+this.regdate+")";
					html+="</div>";
				});
				$("#replyList").html(html);
			},
			error: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		});
	}
	
	replyListDisplay();
	
	//ajaxSend() 메소드를 호출하여 페이지를 Ajax 기능으로 페이지를 요청할 경우 무조건 CSRF 토큰 전달
	$(document).ajaxSend(function(event, xhr) {
		xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
	});
	
	$("#addBtn").click(function() {
		/*
		//자바스크립트에서 Spring Security 태그 사용 가능
		<sec:authorize access="isAuthenticated()">
			var writer="<sec:authentication property="principal.userid"/>";
		</sec:authorize>
		*/
		var writer=$("#writer").val();
		var content=$("#content").val();		
		/*
		if(content == "") {
			alert("댓글내용을 입력해 주세요.");
			return;			
		}
		*/
		$("#content").val("");
		
		$.ajax({
			type: "post",
			url: "<c:url value="/reply/register"/>",
			contentType: "application/json",
			data: JSON.stringify({"writer":writer, "content":content, "boardNum":${securityBoard.num}}),
			//beforeSend 속성 : 페이지 요청전에 실행될 명령이 작성된 함수를 속성값으로 설정
			// => XMLHttpRequest 객체를 함수의 매개변수로 제공받아 사용 가능  
			/*
			beforeSend: function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}","${_csrf.token}");
			},
			*/
			dataType: "text",
			success: function(result) {
				if(result == "success") {
					replyListDisplay();
				}
			},
			error: function(xhr) {
				alert("에러코드 = "+xhr.status);
			}
		});
	});
	</script>
</body>
</html>
