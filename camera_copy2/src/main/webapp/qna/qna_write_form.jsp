<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QnA 작성</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;500&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {            
            font-family: 'Do Hyeon', sans-serif; 
            color: #333; 
        }
        
        .do-hyeon-regular {
            font-family: "Do Hyeon", sans-serif;
            font-weight: 400;
            font-style: normal;
        }
        
        .container {
            width: 70%; 
            margin-top: 50px;
        }
        .form-control {
            height: auto; 
            border: 1px solid #333;
        }
        .form-control:focus {
            border-color: #f6d365; 
            box-shadow: 0 0 0 0.25rem rgba(246, 211, 101, 0.25); 
        }       
        .container form {
            width: 70%;
            margin: 0 auto;
        }
        .form-label {
            font-weight: bold;          
        }        
        .form-control {
            border-radius: 0;
        }
        
        .buttons {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .btn-btn1 {
            background-color: #f6d365;
            color: #000;
            width: 150px;
            height: 50px;
            font-size: 18px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-align: center;
            line-height: 50px;
            font-family: 'Do Hyeon', sans-serif;
            font-weight: 500;
        }

        .btn-btn1:active,
        .btn-btn1:focus,
        .btn-btn1:hover {
            background-color: #f6d365;
            color: #000;
            outline: none;
            box-shadow: none;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <form action="qna_write_action.jsp" method="post" id="qnaForm">
        <h1 class="text-center">QnA 작성</h1>
        <div class="mb-3">
            <label for="qnaType" class="form-label">문의 유형</label>
            <select class="form-control" id="qnaType" name="qnaType">
                <option value="">문의 유형</option>
                <option value="회원정보 문의">회원정보 문의</option>
                <option value="주문/결제 관련 문의">주문/결제 관련 문의</option>
                <option value="취소/환불 관련 문의">취소/환불 관련 문의</option>
                <option value="배송 관련 문의">배송 관련 문의</option>
                <option value="주문 전 상품 정보 문의">주문 전 상품 정보 문의</option>
                <option value="서비스 개선 제안">서비스 개선 제안</option>
                <option value="시스템 오류 제보">시스템 오류 제보</option>
                <option value="불편 신고">불편 신고</option>
                <option value="기타문의">기타문의</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" name="content" rows="10" placeholder="내용을 입력하세요"></textarea>
        </div>
        <div class="buttons mt-4">
            <button type="button" class="btn-btn1" onclick="location.href='index.jsp?workgroup=qna&work=qna_list'">취소</button>
            <button type="submit" class="btn-btn1">등록</button>
        </div>
    </form>
</div>
<div id="message" style="color: red;"></div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$("#qnaForm").submit(function(event) {
    var isValid = true;
    $("#message").text("");

    if ($("#title").val().trim() == "") {
        $("#message").text("제목을 입력해 주세요.");
        $("#title").focus();
        isValid = false;
    }
    
    if ($("#content").val().trim() == "") {
        $("#message").text("내용을 입력해 주세요.");
        $("#content").focus();
        isValid = false;
    }

    if (isValid) {
        // form 제출
        this.submit();
    } else {
        event.preventDefault();
    }
});
</script>
</body>
</html>
