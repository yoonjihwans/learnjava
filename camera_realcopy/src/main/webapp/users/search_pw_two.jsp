<%@page import="xyz.itwill.util.Utility"%>
<%@ page import="xyz.itwill.dao.UsersDAO" %>
<%@ page import="xyz.itwill.dto.UsersDTO" %>
<%@ page import="xyz.itwill.util.NewPasswordApp" %>
<%@ page contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (request.getMethod().equals("GET")) {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return;
    }

    request.setCharacterEncoding("UTF-8");

    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    UsersDTO users = new UsersDTO();
    users.setUsersId(id);
    users.setUsersName(name);
    users.setUsersEmail(email);

    String no = UsersDAO.getDAO().selectUsersNo(users);

%>
<result>
	<% if (no != null) {
        String tempPassword = NewPasswordApp.getPasswordOne();
        String encryptedPassword = Utility.encrypt(tempPassword);
        int result = UsersDAO.getDAO().updateNewPassword(id, encryptedPassword);

        if (result > 0) { %>        
       <code>success</code>
       <pw><%= tempPassword %></pw>
	<% } else {%>
       <code>error</code>
	<% }
    } else { %>
       <code>empty</code>
	<% } %>
</result>
