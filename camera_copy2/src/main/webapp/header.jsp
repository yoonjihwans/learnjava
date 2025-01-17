<%@page import="xyz.itwill.dao.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="xyz.itwill.dto.ProductDTO"%>
<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UsersDTO loginUsers = (UsersDTO) session.getAttribute("loginUsers");

String search=request.getParameter("search");//조회대상
if(search == null) {//전달값이 없는 경우 - 조회기능을 사용하지 않은 경우
	search="";
}

String keyword=request.getParameter("keyword");//조회단어
if(keyword == null) {
	keyword="";
}



%>
<div id="header-top">
	<div id="profile">
		<%
		if (loginUsers == null) {
		%>
		<a href="index.jsp?workgroup=users&work=users_login">Login</a> <a
			href="index.jsp?workgroup=users&work=users_agreement">Signup</a>
		<%
		} else {
		%>
		<span> [<%=loginUsers.getUsersName()%>]님, 환영합니다!
		</span> <a href="index.jsp?workgroup=users&work=users_logout_action">Logout</a>
		<a href="index.jsp?workgroup=myaccount&work=myacct">My account</a>
		<%
		if (loginUsers.getUsersStatus() == 9) {//로그인 사용자가 관리자인 경우
		%>
		<a href="index.jsp?workgroup=admin&work=admin_main">Manager</a>
		<%
		}
		%>
		<%
		}
		%>
	</div>
</div>
<div id="header-bottom">
	<div id="header-bottom-middle">
		<div id="logo">
			<a href="index.jsp" style="color: orange;"><img alt="" src="img/title1.jpg"" width="330px"; style="margin-top: 10px;"></a>
		</div>
		<div id="search_button">
			<div id="search_middle"> 
   <form action="<%= request.getContextPath()%>/index.jsp?workgroup=product&work=product_list" method="post" id="form">

    <select  name="search" id="searchInput">
    <option id="searchInput" value="prod_name" <% if(search.equals("prod_name")) { %>selected<% } %>>&nbsp;name&nbsp;</option>
    </select>
    <input type="text" name="keyword"  value="<%=keyword%>" id="keyword" placeholder="search">
    <button type="submit" id="searchButton">검색</button>
</form>
</div>
		</div> 
		<div id="nav-bar">
			  <nav>
                <ul>
                    <li><a href="index.jsp?workgroup=product&work=camera_list" id="good">Camera</a></li>
                    <li><a href="index.jsp?workgroup=product&work=film_list" id="good">Film</a></li>
                    <li><a href="index.jsp?workgroup=product&work=acc_list" id="good" style="margin-right:10px;">Acc</a></li>
                    <li class="dropdown-item1">
                        <a href="#" style="margin-right:20px;">COMMUNITY</a>
                        <div class="dropdown-content">
                            <a href="index.jsp?workgroup=notice&work=notice" id="good">Notice</a>
                            <a href="index.jsp?workgroup=qna&work=qna_list" id="good">Q&A</a>
                        </div>
                    </li>
                    <li class="dropdown-item2">
                        <a href="#" style="margin-left:30px;">SHOP</a>
                        <div class="dropdown-content">
                            <a href="index.jsp?workgroup=cart&work=cart" id="good">Cart</a>
                            <a href="index.jsp?workgroup=order&work=order_list" id="good">Order List</a>
                        </div>
                    </li>
                  
                </ul>
            </nav>
		</div>
	</div>
</div>


