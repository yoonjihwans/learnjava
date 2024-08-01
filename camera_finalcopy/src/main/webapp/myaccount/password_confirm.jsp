<%@page import="java.text.NumberFormat.Style"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>

		
		#account-box{width: 1100px; height: 600px; border: 1px solid green; margin: 0 auto;}
#acc1-box{width: 200px; height: 600px; border: 1px solid black; float: left;}
#acc2-box{width: 895px; height: 600px; border: 1px solid black; float: right;}

#acc1-box nav ul{border: 1px solid red; height: 400px; margin: 0 auto; margin-top: 100px;;}
#acc1-box nav ul li{border: 1px solid blue; width: 200px; text-align: center; height: 70px; }

#acc1-box nav ul li a{margin-top:20px; font-size: 23px; color: black;}
		
		#good { 
    color: #000;
    display:inline-block; 
    margin:0;
    text-transform:uppercase; }
    #good:after {
    display:block;
    content: '';
    border-bottom: solid 3px #BBDEF0;  
    transform: scaleX(0);  
    transition: transform 250ms ease-in-out;
  }
  #good:hover:after { transform: scaleX(1); }
  #good:after{ transform-origin:100% 50%; }
  #good:after{  transform-origin:  0% 50%; }
  
  #confirm-wrap {
  
  }
		
		
		
   </style>
<%-- 사용자로부터 비밀번호를 입력받기 위한 JSP 문서 --%>
<%-- => 로그인 사용자만 요청 가능한 JSP 문서 --%>
<%-- => [입력완료] 태그를 클릭한 경우 전달값에 따라 다른 JSP 문서를 요청하여 페이지 이동 - 입력값(비밀번호) 전달 --%>

<%@include file="/security/login_check.jspf" %>    
<%

	//전달값을 반환받아 저장
	String action=request.getParameter("action");

	//전달값이 없거나 비정상적인 값이 전달된 경우에 대한 응답 처리 - 비정상적인 요청
	if(action == null || !action.equals("modify") && !action.equals("remove")) {
		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
		return;	
	}
	// member_modify 만든 후에 추가된 코드 
	String message=(String)session.getAttribute("message");
	if(message == null) {
		message="";
	} else {
		session.removeAttribute("message");
	}
%>


 <div id="account-box">
 <div id="acc1-box">
            <nav>
                <ul>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct" id="good">회원정보</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_review" id="good">리뷰</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_qna" id="good">Q&A</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_orderlist" id="good">주문내역</a></li>
                </ul>

            </nav>

        </div>
        <div id="acc2-box">

<div id="confirm-wrap">
<% if(action.equals("modify")) { %>
	<p>회원정보변경을 위해 비밀번호를 입력해 주세요.</p>
<% } else { %>
	<p>회원탈퇴를 위해 비밀번호를 입력해 주세요.</p>
<% } %>


<form method="post" id="passwordForm">
	<input type="password" name="passwd" id="passwd">
	<button type="button" id="submitBtn">입력완료</button>
</form>
<p id="message" style="color: red;"><%=message %></p><%-- <%=message %> member_modify 만든 후에 추가된 코드  --%>
</div>

 </div>

</div>

<script type="text/javascript">
$("#passwd").focus();

$("#submitBtn").click(function() {
	if($("#passwd").val() == "") {
		$("#message").text("비밀번호를 입력해 주세요.");
		return;
	}
	
	//전달값에 따라 form 태그의 action 속성값을 다르게 설정
	<% if(action.equals("modify")) { %>
		$("#passwordForm").attr("action", "<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=myacct_modify")
	<% } else { %>
		$("#passwordForm").attr("action", "<%=request.getContextPath()%>/index.jsp?workgroup=myaccount&work=myacct_remove_action")
	<% } %>
	
	$("#passwordForm").submit();//form 태그 실행
});
</script>



