<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#account-box{width: 1100px; height: 600px; border: 1px solid green; margin: 0 auto;}
#acc1-box{width: 200px; height: 600px; border: 1px solid black; float: left;}
#acc2-box{width: 895px; height: 600px; border: 1px solid black; float: right;}

#acc1-box nav ul{border: 1px solid red; height: 400px; margin: 0 auto; margin-top: 100px;;}
#acc1-box nav ul li{border: 1px solid blue; width: 200px; text-align: center; height: 70px; }

#acc1-box nav ul li a{margin-top:20px; font-size: 23px; color: black;}

#good { 
    color: #000;
    display:inline-block; 
    margin:0;
    text-transform:uppercase; }
    #good:after {
    display:block;
    content: '';
    border-bottom: solid 3px #BBDEF0;  
    transform: scaleX(0);  
    transition: transform 250ms ease-in-out;
  }
  #good:hover:after { transform: scaleX(1); }
  #good:after{ transform-origin:100% 50%; }
  #good:after{  transform-origin:  0% 50%; }
</style>
</head>



<div id="account-box">
        <div id="acc1-box">
           <nav>
                <ul>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct" id="good">회원정보</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_review" id="good">리뷰</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_qna" id="good">Q&A</a></li>
                <li><a href="index.jsp?workgroup=myaccount&work=myacct_orderlist" id="good">주문내역</a></li>
                </ul>

            </nav>


        </div>
        <div id="acc2-box"></div>

    </div>


</html>