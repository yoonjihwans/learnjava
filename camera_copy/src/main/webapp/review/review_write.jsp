<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/write.css">
    <title>리뷰 작성하기</title>
   
</head>
<style type="text/css">
body {
    font-family: 'Arial', sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}
.container {
    width: 90%;
    max-width: 600px;
    background: white;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
}
h2 {
    text-align: center;
    color: #343a40;
}
form {
    display: flex;
    flex-direction: column;
}
label {
    margin-top: 10px;
    font-weight: bold;
}
input, textarea, button {
    margin-top: 5px;
    padding: 10px;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    font-size: 16px;
}
button {
    margin-top: 20px;
    background-color: #ffc107;
    color: white;
    border: none;
    cursor: pointer;
    font-weight: bold;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease;
}
button:hover {
    background-color: #e0a800;
}
</style>
<body>
    <div class="container">
        <h2>리뷰 작성하기</h2>
        <form id="review-form" onsubmit="submitReview(event)">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required>
            <label for="content">내용:</label>
            <textarea id="content" name="content" required></textarea>
            <label for="userid">아이디:</label>
            <input type="text" id="userid" name="userid" required>
            <label for="orderid">주문번호:</label>
            <input type="text" id="orderid" name="orderid" required>
            <button type="submit">제출</button>
        </form>
    </div>

    <script>
        function submitReview(event) {
            event.preventDefault();
            const title = document.getElementById('title').value;
            const content = document.getElementById('content').value;
            const userid = document.getElementById('userid').value;
            const orderid = document.getElementById('orderid').value;

            const reviews = JSON.parse(localStorage.getItem('reviews') || '[]');
            reviews.push({ title, content, userid, orderid });
            localStorage.setItem('reviews', JSON.stringify(reviews));

            window.location.href = 'review.html';
        }
    </script>
</body>
</html>