<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="camera_project/main/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
 <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Frank+Ruhl+Libre:wght@300..900&display=swap" rel="stylesheet">
    <title>Camera</title> 
    
    <style type="text/css">
    @charset "UTF-8";
*{margin: 0; padding: 0; list-style: none; box-sizing: border-box; text-decoration: none;  font-family: "Frank Ruhl Libre", serif; }
html{font-size: 62.5%;}/* 15px는 1.5rem!! */
.main-header{width: 100%;height: 22.0rem; ; position: fixed; top: 0; left: 0; right: 0;  z-index: 100;} 
#main-header-middle{width: 150rem; height: 22rem;  margin: 0 auto;}
#account-box{width: 52rem; height: 12rem;  float: right;  }
#account-box nav{width: 52rem; height: 8rem; }
#account-box ul {width: 50rem; height: 5.5rem;  float: right;  margin-top: 10px; }
#account-box ul li{ float: left; width: 15rem;  font-size: 2.1rem;  text-align: center; margin-top: 20px; padding-left: 10px; }
#logo-box{width: 65rem; height: 14.7rem;  margin: 0 auto; position: relative; top: 70px;}
#logo-img{width: 30rem; height: 9rem;  margin: 0 auto; font-size: 2rem; text-align: center; font-family: "Frank Ruhl Libre", serif;}
#nav-bar{width: 70rem; height: 4rem;  margin: 0 auto;}
#nav-bar ul{width: 552px; height: 4rem;  margin: 0 auto;   display: flex; position: relative; left: 70px;} 
/* #nav-bar ul::after{display:block;clear:both;content: '';} */
#nav-bar ul li{ font-size: 1.9rem; width: 15rem;   text-align: center; margin-top: 5px;}
#nav-bar ul li:nth-child(1):hover ul,
#nav-bar ul li:nth-child(2):hover ul {display: block;}
#nav-bar ul li:nth-child(1):hover ul li,
#nav-bar ul li:nth-child(2):hover ul li {display: block;}
#nav-bar ul li ul { display: none; position: absolute; background-color: #FFF7D9;  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); z-index: 200; width: 140px;  height: 100px; padding: 10px 0;}
#nav-bar ul li ul li {text-align: center; padding: 10px 0; padding-right: 25.5px;}
#nav-bar ul li ul li:last-child { border-bottom: none;}

#welcome{width: 40rem; height: 4rem;  margin-left: 20px;   margin: 0 auto;}
#welcome span{font-size: 20px; font-family: "Noto Sans KR", sans-serif;}
#welcome a{ font-size: 20px; color: black; }
#welcomeleft{width: 20rem; height: 4rem;  float: left; float: right; }




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


.main-body{width: 100%; height: 200rem;  padding-top: 220.4px;}
#mylogo{width: 40rem; height: 5rem;  float: right; }
#mylogobox{ width: 40rem; height: 5rem;  margin: 0 auto; }
#searchicon{width: 200px; height: 35px; font-weight: bold; font-family: "Noto Sans KR", sans-serif; color: black; position: relative; bottom: 25px;}

section{width: 100%; height: 325.2rem;  padding-top: 220px; }
#img-box{width: 145rem; height: 63rem;  text-align: center;  margin: 0 auto; overflow: hidden; position: relative;  }
#img-slider{display: flex; transition: transform 0.5s ease;}
.img-slide{min-width: 750px; flex: 0 0 auto;}
.productmain{width: 100%; height: 90rem; }
#product{width: 100%; height: 200rem; }

#pdbox{width: 100%; height: 220rem;  }

#product{width: 170rem; height: 60rem;  margin: 0 auto; position: relative; top: 200px; display: flex; flex-direction: row; flex-wrap: wrap; justify-content:  center;}
#product1{width: 170rem; height: 60rem;  margin: 0 auto; position: relative; top: 200px; display: flex; flex-direction: row; flex-wrap: wrap; justify-content:  center; margin-top: 100px;}
#product-title{width: 160rem; height: 10rem;  margin: 0 auto;}
#product-box{width: 40rem; height: 50rem; }
#add-box{width: 50%; height: 3.9rem;  text-align: center; font-size: 18px; color: white; margin-top: 10px; margin: 0 auto;}
#add-box:hover h1{background-color: #32343E; transition: 1s;}
#add-box h1 a:hover{ transition: 1s; color: white;}
#productimg{width: 40rem; height: 35rem;} 
#textbox{width: 40rem; height: 10rem; text-align: center;}
#textbox h1{font-size: 20px;}
#textbox p{font-size: 20px; font-family: "Noto Sans KR", sans-serif;}
.seeimgbox-main{width: 100%; height: 125rem;   margin: 0 auto;}
#imgbox{width: 100rem; height: 100rem; margin: 0 auto;}
#seeimg{width: 70rem; height: 98.5rem;   margin: 0 auto;} 
#font-box{width: 70rem; height: 13rem; background-color: #C9AD8D; margin: 0 auto; position: relative; top: 70px; text-align: center; border: 1px solid black;}
#font-box-wrap{width: 50rem; height: 13rem; border: 1px solid #D0AC88; margin: 0 auto;}
#font-box h1{font-size: 26px; margin-top: 20px; font-family: "Noto Sans KR", sans-serif;}
#font-box p{font-size: 15px; font-family: "Noto Sans KR", sans-serif;}
#font-box a{font-size: 25px; margin-top: 15px; border-radius: 1px solid #D0AC88; position: relative; top:12px; color: black;}


footer{width: 100%; height: 60rem;  display: flex; justify-content: center;  align-items: center; } 
.footer-main{width: 160rem; height: 40rem; }
#info-left{width: 65rem; height: 40rem;  float: left;}
#info-right{width: 65rem; height: 40rem;  float: right;}

#productimglist1{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist2 img{ position: relative; top: -97px;}
#productimglist3 img{ position: relative; top: -130px;}
#productimglist4 img{ position: relative; top: 7px;}
#productimglist2{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist3{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist4{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}

#productimglist1-1{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist1-1 img{position: relative; top: -40px;}
#productimglist2-2 img{ position: relative; top: -30px;}
#productimglist3-3 img{ position: relative; top: -60px;}
#productimglist4-4 img{ position: relative; top: -1px;}
#productimglist2-2{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist3-3{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#productimglist4-4{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}

#flimimglist1{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#flimimglist1 img{position: relative; top: 25px;}
#flimimglist2 img{ position: relative; top: 20px; left: 30px;}
#flimimglist3 img{ position: relative; top: -3px;}
#flimimglist4 img{ position: relative; top: -26px;}
#flimimglist2{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#flimimglist3{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}
#flimimglist4{width: 36rem; height: 27rem; border: 1px solid #D0AC88; margin: 0 auto; position: relative; top: 40px; text-align: center;}




#title-box{width: 50rem; height: 10rem; margin: 0 auto; text-align: center; } 
#title-box h1{font-size: 45px;  border-top: 3px solid #D0AC88; } 
#title-box p{font-size: 15px; font-family: "Noto Sans KR", sans-serif;}



#info-left-box1{width: 65rem; height: 10rem; }
#info-left-box1 h1{font-size: 40px;}
#info-left-box2{width: 65rem; height: 30rem;  }
#divtitle-1{width: 65rem; height: 5rem;  font-size: 15.5px; font-weight: bold;}
#divtitle-1 span{font-family: "Noto Sans KR", sans-serif;}
#box1{width: 10rem; height: 3rem;  }
#box1 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box2{width: 10rem; height: 3rem; }
#box2 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box3{width: 30rem; height: 3rem;  }
#box3 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box4{width: 30rem; height: 3rem;  }
#box4 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box5{width: 30rem; height: 3rem; }
#box5 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box6{width: 30rem; height: 3rem;  }
#box6 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#box7{width: 30rem; height: 3rem; }
#box7 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#block{width: 65rem; height:10rem;}
#right-box1{width: 25rem; height: 29.8rem; }
#right-box2{width: 25rem; height: 29.8rem;   float: left;}
#right-box1{width: 25rem; height: 29.8rem; float: left;}
#right-box3{width: 14.8rem; height: 29.8rem;  float: left;}
#rbox1{width: 10rem; height: 3rem;  }
#rbox1 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rbox2{width: 20rem; height: 3rem;  }
#rbox2 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rbox3{width: 20rem; height: 3rem; }
#rbox3 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rbox4{width: 20rem; height: 3rem;  }
#rbox4 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rbox5{width: 10rem; height: 3rem; }
#rbox5 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rbox6{width: 30rem; height: 3rem; }
#rbox6 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rrbox1{width: 10rem; height: 3rem;  }
#rrbox1 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rrbox2{width: 15rem; height: 3rem;  }
#rrbox2 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#rrbox3{width: 25rem; height: 3rem; }
#rrbox3 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns1{width: 10rem; height: 3rem;  }
#sns1 span{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns2{width: 10rem; height: 3rem; }
#sns2 a{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns3{width: 10rem; height: 3rem;  }
#sns3 a{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns4{width: 10rem; height: 3rem; }
#sns4 a{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns5{width: 10rem; height: 3rem; }
#sns5 a{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}
#sns6{width: 10rem; height: 3rem;  }
#sns6 a{font-family: "Noto Sans KR", sans-serif; font-size: 15.5px; font-weight: bold;}


    
    </style>   
</head>


<body>
    <header>
        <div class="main-header">
            <div id="main-header-middle">
                 <div id="account-box">
                    <nav>
                        <ul>
                            <li><a href=""  id="good">Sing in</a></li>
                            <li><a href=""  id="good">Sing up</a></li>
                            <li><a href=""  id="good">My account</a></li>
                        </ul>
                    </nav>

                    <div id="welcome">
                       <div id="welcomeleft"><span>누구누구님 환영합니다!</span></div>
                      
                    </div>
                  
                 </div>
                 
                 <div id="logo-box">
                    <div id="logo-img"><h1><a href="">FILLI CAMERA</a></h1></div>
                    <div id="nav-bar">
                        <nav>
                            <ul>
                                <li><a href="#" id="good">Camera</a></li>
                                <li><a href="#" id="good">Filim</a></li>
                                <li><a href="#" id="good">Acc</a></li>
                                <li><a href="#" id="good">Notice</a></li>
                                <li><a href="#" id="good">Order List</a></li>
                            </ul>
                        </nav>
                    </div>
                 </div>
                 <div id="mylogo">
                         <div id="mylogobox">
                            <form action="" method="post">
                            <input type="text" name="search" id="searchicon" placeholder="입력해주세요!" style="position: relative; left: 45px; ;">
                           <a href=""><img src="img/search.svg" alt="" width="55px" style="margin-top: 5px; position: relative; left: 40px; bottom: 2px;" ></a>
                           <a href=""><img src="img/market.svg" alt="" width="55px" style="margin-left: 57px;"></a>
                        </form>
                            </div>
                 </div>
            </div>
        </div>
    </header>
    <section>
        <div id="img-box">
            <div id="img-slider">
                <div class="img-slide"><img src="img/main1.jpg" alt="" ></div>
                <div class="img-slide"><img src="img/main2.jpg" alt="" ></div>
                <div class="img-slide"><img src="img/main3.jpg" alt=""></div>
            </div>
        </div>
       
       
        <div class="productmain">
            <div id="pdbox">
        <div id="product">
            <div id="product-title">
                <div id="title-box">
                <h1>New Film Camera</h1>
                <p>새롭게 입고된 상품들을 만나보세요</p>
            </div>
            </div>
                <div id="product-box">
                    <div id="productimg">
                        <div id="productimglist1"><img src="img/productimg1.png" alt="" width="280px"></div>
                    </div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist2"><img src="img/productimg2.png" alt="" width="330px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                       
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist3"><img src="img/productimg3.png" alt="" width="330px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist4"><img src="img/productuimg4.png" alt=""  width="277px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
        </div>

        <div id="product">
            
            <div id="product-title">
                <div id="title-box">
               
            </div>
            </div>
                <div id="product-box">
                    <div id="productimg">
                        <div id="productimglist1-1"><img src="img/productimg5.png" alt="" width="350px"></div>
                    </div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist2-2"><img src="img/productimg6.png" alt="" width="330px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                       
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist3-3"><img src="img/productimg7.png" alt="" width="330px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="productimglist4-4"><img src="img/productimg8.png" alt=""  width="277px"></div></div>
                    <div id="textbox">
                        <h1>Yellow Camera</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
        </div>

        <div id="product1">
            <div id="product-title">
                <div id="title-box">
                <h1>New Film</h1>
                <p>새롭게 입고된 상품들을 만나보세요</p>
            </div>
            </div>
                <div id="product-box">
                    <div id="productimg">
                        <div id="flimimglist1"><img src="img/flimimg1.png" alt="" width="217px"></div>
                    </div>
                    <div id="textbox">
                        <h1>Flim</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="flimimglist2"><img src="img/flimimg2.png" alt="" width="220px"></div></div>
                    <div id="textbox">
                        <h1>Flim</h1>
                        <P>10000000원</P>
                       
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="flimimglist3"><img src="img/flimimg3.png" alt="" width="200px"></div></div>
                    <div id="textbox">
                        <h1>Flim</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
                <div id="product-box">
                    <div id="productimg"><div id="flimimglist4"><img src="img/flimimg4.png" alt=""  width="210px" ></div></div>
                    <div id="textbox">
                        <h1>Flim</h1>
                        <P>10000000원</P>
                    </div>
                    <div id="add-box"><h1><a href="">More +</a></h1></div>
                </div>
        </div>


    
    </div>

    <div class="lastbox">
        <div id="font-box">
            <div id="font-box-wrap">
            <h1>새로운 상품들을 만나보세요!</h1>
            <p>우리 매장만의 유니크하고 다양한 상품들을 만나볼 수 있습니다.</p>
            <a href="" > Go Products</a>
        </div>
        </div>
</div>

        </div>

    </section>
    <footer>
        <div class="footer-main">
            <div id="info-left">
                <div id="info-left-box1">
                    <h1>FILI CAMERA</h1>
                </div>
                <div id="info-left-box2">
                   <div id="divtitle-1"><span>쇼핑몰 기본정보</span></div>
                   <div id="box1"><span>상호명 </span></div>
                   <div id="box2"><span>대표자명 OOO</span></div>
                   <div id="box3"><span>사업장 주소 서울특별시 강남구 아이티윌</span></div>
                   <div id="box4"><span>대표 전화 0000-0000</span></div>
                   <div id="box5"><span>사업자 등록번호 000-00-00000</span></div>
                   <div id="box6"><span>통신판매업 신고번호 기타</span></div>
                   <div id="box7"><span>개인정보보호책임자 OOO</span></div>
                </div>
            </div>
            <div id="info-right">
                <div id="block"></div>
                <div id="right-box1">
                    <div id="rbox1"><span>고객센터 정보</span></div>
                    <div id="rbox2"><span>상담/주문전화0000-0000</span></div>
                    <div id="rbox3"><span>상담/주문 이메일</span></div>
                    <div id="rbox4"><span>test@test.com</span></div>
                    <div id="rbox5"><span>CS운영시간</span></div>
                    <div id="rbox6"><span>AM 09:00 - PM 06:00 (토,일,공휴일 휴무)</span></div>
                </div>
                <div id="right-box2">
                    <div id="rrbox1"><span>결제 정보</span></div>
                    <div id="rrbox2"><span>무통장 계좌정보</span></div>
                    <div id="rrbox3"><span>은행 0000-000-00000 예금주</span></div>
                </div>
                <div id="right-box3">
                    <div id="sns1"><span>sns</span></div>
                    <div id="sns2"><a href="https://www.instagram.com/">Instagram</a></div>
                    <div id="sns3"><a href="https://www.youtube.com/">YouTube</a></div>
                    <div id="sns4"><a href="https://www.facebook.com/">Facebook</a></div>
                    <div id="sns5"><a href="https://www.kakaocorp.com/page/">Kakao</a></div>
                    <div id="sns6"><a href="https://x.com/">Twitter</a></div>
                </div>

            </div>
        </div>
    </footer>



    <script>
         document.addEventListener("DOMContentLoaded", function() {
            const imgSlider = document.getElementById('img-slider');
            const imgSlides = document.querySelectorAll('.img-slide');
            const totalSlides = imgSlides.length;
            let currentIndex = 0;

            function nextSlide() {
                currentIndex++;
                if (currentIndex >= totalSlides) {
                    currentIndex = 0; // 마지막 슬라이드에서 첫 번째 슬라이드로 이동
                }
                const offset = -currentIndex * imgSlides[0].offsetWidth;
                imgSlider.style.transform = `translateX(${offset}px)`;
            }

            // 이미지 전환 간격 (ms)
            const slideInterval = 3000;
            setInterval(nextSlide, slideInterval);
        });
    </script>
  
</body>
</html>