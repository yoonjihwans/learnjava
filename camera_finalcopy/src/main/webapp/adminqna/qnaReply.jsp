<%@page import="xyz.itwill.dao.AdminQnaDAO"%>
<%@page import="xyz.itwill.dto.AdminQnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	if(request.getParameter("no") == null) {
  		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
  		return;
  	}

  	//전달값을 반환받아 저장
  	int no=Integer.parseInt(request.getParameter("no"));
  	
  	AdminQnaDTO qna=AdminQnaDAO.getDAO().selectQnaNo(no);

  	if(qna ==null){
  		request.setAttribute("returnUrl", request.getContextPath()+"/index.jsp?workgroup=error&work=error_400");
  		return;	
  	}
  	qna.setQnaStatus(2);
  	AdminQnaDAO.getDAO().updateQna(qna);
  	

    // Set the return URL for redirection after showing the message
    String returnUrl = request.getContextPath() + "/index.jsp?workgroup=adminqna&work=qna";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이메일 전송</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        #resultMessage {
            font-size: 18px;
            color: #333;
            text-align: center;
        }
    </style>
    <script type="text/javascript">
        // Function to redirect to the specified URL after a delay
        function redirectToUrl() {
            setTimeout(function() {
                window.location.href = "<%= returnUrl %>";
            }, 3000); // Redirect after 1 second
        }

        // Display the message and start the redirection process
        window.onload = function() {
            document.getElementById('resultMessage').innerText = "이메일 전송 완료되었습니다.";
            redirectToUrl(); // Start the redirect process
        };
    </script>
</head>
<body>
    <div id="resultMessage"></div>
</body>
</html>