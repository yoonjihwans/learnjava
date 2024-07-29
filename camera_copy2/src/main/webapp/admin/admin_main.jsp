<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Administrator</title>
<style>
 #welcome-wrap{
 		width:100%;
 		height:320px;
 		margin: 0 auto;
 }
 .welcome-message{
 	width:50%;
 	height: 200px;
 	margin: 0 auto;
 	text-align: center;
 	margin-top: 100px;
 }
 
 .welcome-message p{
  font-size: 50px;
  color: #333; /* 글자색 */
   animation: fadeIn 2s ease-in-out;
     transform: translate 1s;
      animation: fadeIn 2s ease-in-out;
      margin-top:10px; 
 }
    

    @keyframes fadeIn {
        0% {
            opacity: 0;
        }
        100% {
            opacity: 1;
        }
    } */
</style>
</head>
<body>

<div id="welcome-wrap">
    <div class="welcome-message">
        <p>관리자님 환영합니다.</p>
        <p>최고의 서비스를 제공하겠습니다.</p>
    </div>
  </div>
</body>
</html>