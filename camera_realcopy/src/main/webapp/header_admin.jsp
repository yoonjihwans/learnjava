<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<form action="<%=request.getContextPath()%>/index.jsp?workgroup=review&work=review_write_action"
	method="post" id="reviewForm">
<div id="header-top">
	<div id="profile">
		<span style="font-weight: bold;">관리자님, 환영합니다.</span>&nbsp;&nbsp;
	<a href="index.jsp?workgroup=users&work=users_logout_action">Logout</a>
	
	<a href="index.jsp?workgroup=main&work=main_page">메인페이지로</a>&nbsp;&nbsp;
	</div>
</div>
<div id="header-bottom">
	<div id="header-bottom-middle">
		<div id="logo">
			<a href="index.jsp" style="color: orange;">Filli Camera</a>
		</div>
		<div id="search_button">
			<div id="search-middle">
				<form action="" method="get">
					<input id="search" type="text" name="search" placeholder="Search">
				</form>
			</div>
		</div>
		<div id="nav-bar">
			  <nav>
                <ul>
                    <li><a href="index.jsp?workgroup=adminusers&work=users" id="good">회원관리</a></li>
                    <li><a href="index.jsp?workgroup=adminnotice&work=notice" id="good">공지관리</a></li>
                    <li><a href="index.jsp?workgroup=adminqna&work=qna" id="good">QNA관리</a></li>
                    <li><a href="index.jsp?workgroup=adminproduct&work=product" id="good">제품관리</a></li>
                    <li><a href="index.jsp?workgroup=adminorders&work=orders" id="good">주문관리</a></li>
                </ul>
            </nav>
		</div>
	</div>
</div>
</form>
