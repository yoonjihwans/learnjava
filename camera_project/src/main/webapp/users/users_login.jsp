<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
    String url = request.getParameter("url");
    if(url == null) {
        url = "";
    }

    String message = (String) session.getAttribute("message");
    if(message == null) {
        message = "";
    } else {
        session.removeAttribute("message");
    }
    
    String id = (String) session.getAttribute("id");
    if(id == null) {
        id = "";
    } else {
        session.removeAttribute("id");
    }
%>    
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>로그인</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	
    <style>    	
        .custom-container {           
            display: flex;
            width: 100%;
            justify-content: center;
            align-items: center;
            height: auto;
            margin: 0 auto;
        }

        .form-container {
            max-width: 400px;
            width: 100%;
            padding: 20px;           
            border: 1px solid #dee2e6;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-signin h1 {
            text-align: center;
            font-size: 2.5rem;
            font-weight: bold;
        }

        .invalid-feedback {
            display: none;
        }

        .is-invalid ~ .invalid-feedback {
            display: block;
        }
    </style>
</head>
<body>
    <div class="custom-container">
        <div class="form-container">
            <h1 class="my-4 text-center">로그인</h1>
            <form id="login" name="loginForm" method="post" action="<%=request.getContextPath() %>/index.jsp?workgroup=users&work=users_login_action">
                <input type="hidden" name="url" value="<%=url%>">
                <div class="mb-3">
                    <label for="id" class="form-label">아이디</label>
                    <input type="text" class="form-control" id="id" name="id" value="<%=id%>" placeholder="아이디를 입력하세요" autofocus>
                    
                </div>
                <div class="mb-3">
                    <label for="pw" class="form-label">비밀번호</label>
                    <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요">
                    
                </div>
                <button class="btn btn-primary w-100 py-2" type="submit" id="login_btn">로그인</button>                
                <a href="index.jsp?workgroup=users&work=users_agreement" class="btn btn-secondary w-100 py-2 mt-2">회원가입</a>
                <div class="d-flex justify-content-center mt-2">
                    <a href="index.jsp?workgroup=users&work=id_find" class="text-decoration-none text-muted mx-2">아이디 찾기</a>
                    <span class="text-muted">|</span>
                    <a href="index.jsp?workgroup=users&work=pw_find" class="text-decoration-none text-muted mx-2">비밀번호 찾기</a>
                </div>                
                <div id="message" class="mt-3 text-danger"><%=message %></div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        $("#id").focus();

        $("#login_btn").click(function() {
            if($("#id").val() == "") {
                $("#message").text("아이디를 입력해 주세요. (영문소문자/숫자, 4~16자)");
                $("#id").focus();
                return;
            }
            
            if($("#pw").val() == "") {
                $("#message").text("비밀번호를 입력해 주세요. (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10~16자)");
                $("#pw").focus();
                return;
            }
            
            $("#login").submit();
        });
    </script>
</body>
</html>
