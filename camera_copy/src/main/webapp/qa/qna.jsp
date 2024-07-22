<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/q&a.css">
    <title>Q&A 게시판</title>
   
</head>

<style  type="text/css">
body {
    font-family: 'Arial', sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}
.container {
    width: 80%;
    max-width: 1200px;
    margin-top: 50px;
}
h1 {
    text-align: center;
    color: #343a40;
}
.board {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
.board th, .board td {
    border: 1px solid #dee2e6;
    padding: 12px;
    text-align: center;
}
.board th {
    background-color: #ffc107;
    color: white;
    font-weight: bold;
}
.board tr:nth-child(even) {
    background-color: #f2f2f2;
}
.board tr:hover {
    background-color: #e9ecef;
}
.write-btn {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #ffc107;
    color: white;
    text-align: center;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
}
.write-btn:hover {
    background-color: #ffc107;
}
.delete-btn {
    background-color: #dc3545;
    color: white;
    border: none;
    padding: 5px 10px;
    border-radius: 5px;
    cursor: pointer;
    font-weight: bold;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
}
.delete-btn:hover {
    background-color: #c82333;
}

</style>
<body>
    <div class="container">
        <h1>Q&A 게시판</h1>
        <table class="board">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>내용</th>
                    <th>아이디</th>
                    <th>작성일</th>
                    <th>문의답변</th>
                    <th>상품번호</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody id="board-contents">
                <!-- JavaScript를 사용하여 Q&A 항목을 추가할 수 있습니다 -->
            </tbody>
        </table>
        <a href="q&awrite.html" class="write-btn">글쓰기</a>

    </div>

   
</body>
</html>