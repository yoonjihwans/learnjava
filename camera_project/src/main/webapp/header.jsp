<%@page import="xyz.itwill.dto.UsersDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    UsersDTO loginUsers=(UsersDTO)session.getAttribute("loginUsers");
    %>
            <div id="header-top">
                    <div id="profile">
                    <% if(loginUsers == null) { %>
                       <a href="index.jsp?workgroup=users&work=users_login">Login</a>
                       <a href="index.jsp?workgroup=users&work=users_join">Sing up</a>
                       <% } else { %>
                       <span> [<%=loginUsers.getUsersName() %>]님 환영합니다!</span>
                       <a href="index.jsp?workgroup=users&work=users_logout_action">Logout</a>
                       <a href="index.jsp?workgroup=users&work=users_mypage">My account</a>
                       <% if(loginUsers.getUsersStatus() == 9) {//로그인 사용자가 관리자인 경우 %>
                       <a href="index.jsp?workgroup=users&work=admin_main">Manager</a>
                       <% } %>
	               <% } %>
                    </div>
            </div>
            <div id="header-bottom">
                <div id="header-bottom-middle">
                <div id="logo" onclick="location.href='index.html'">Fili Camera</div>
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
                    <li><a href="#">Camera</a></li>
                    <li><a href="#">Filim</a></li>
                    <li><a href="#">Accesary</a></li>
                    <li><a href="#">Notice</a></li>
                    <li><a href="#">Cart</a></li>
                    <li><a href="#">Order List</a></li>
                </ul>
            </nav>
        </div>
        </div>
        </div>
       
