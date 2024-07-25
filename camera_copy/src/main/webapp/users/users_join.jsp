<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        a {text-decoration: none !important}
    
        .custom-container {        	
            display: flex;
            justify-content: center;
            align-items: center;
            height: auto;
            margin: 100px; 
        }

        .form-container {
            max-width: 550px;
            width: 100%;
            padding: 24px;
            border: 1px solid #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);          
        }

        .msg {
            color: red;
            font-size: small;
            margin-top: 0.5em;
            display: none; 
        }    
        .is-invalid {
            border-color: red !important;
        }
        .form-label {   			
    		font-size: medium;
		}
		.my-4 {    	
   			font-size: xx-large;
		}
		.btn-warning {
			 font-size: 14.5px;
		}
		.form-control {
			font-size: small;
		}
		.col-form-label {
			font-size: medium;
		}
		.btn-secondary {
			font-size: small;
		}
		.form-select {
			font-size: medium;
		}				
                   
    </style>
</head>
<body>
    <div class="custom-container">
        <div class="form-container">
            <h1 class="my-4 text-center">회원가입</h1>
         	<form id="joinForm" action="<%=request.getContextPath() %>/index.jsp?workgroup=users&work=users_join_action" method="post">
                <input type="hidden" id="idCheckResult" value="0">
				<div class="mb-3 row">
					<label for="id" class="col-sm-4 col-form-label">아이디</label>
					<div class="col-sm-8">						
						<input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요">							
						<div id="idNullMsg" class="msg idMsg">아이디를 입력해 주세요.</div>
						<div id="idValidMsg" class="msg idMsg">아이디는 영문소문자/숫자, 4~16자로만 작성 가능합니다.</div>
						<div id="idDuplMsg" class="msg idMsg">이미 사용중인 아이디입니다.</div>
					</div>
				</div>

				<div class="mb-3 row">
                    <label for="pw" class="col-sm-4 col-form-label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요">
                        <div id="pwNullMsg" class="msg">비밀번호를 입력해 주세요.</div>
                        <div id="pwValidMsg" class="msg">비밀번호는 영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 6~16자로만 작성 가능합니다.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="repw" class="col-sm-4 col-form-label">비밀번호 확인</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="repw" name="repw" placeholder="비밀번호를 다시 입력하세요">
                        <div id="repwNullMsg" class="msg">비밀번호 확인을 입력해 주세요.</div>
                        <div id="repwValidMsg" class="msg">비밀번호와 비밀번호 확인이 서로 맞지 않습니다.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="name" class="col-sm-4 col-form-label">이름</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요">
                        <div id="nameNullMsg" class="msg">이름을 입력해 주세요.</div>
                        <div id="nameValidMsg" class="msg">이름을 형식에 맞게 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="zipcode" class="col-sm-4 col-form-label">우편번호</label>
                    <div class="col-sm-8">
                        <div class="input-group">
                            <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
                            <button type="button" class="btn btn-secondary" id="postSearch" onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
                        </div>
                        <div id="zipcodeMsg" class="msg">우편번호를 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="address1" class="col-sm-4 col-form-label">기본주소</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="address1" name="address1" placeholder="주소" readonly>
                        <div id="address1Msg" class="msg">기본주소를 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="address2" class="col-sm-4 col-form-label">상세주소</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="address2" name="address2" placeholder="상세주소를 입력하세요">
                        <div id="address2Msg" class="msg">상세주소를 입력해 주세요.</div>
                    </div>
                </div>                
                <div class="mb-3 row">
                    <label for="phone2" class="col-sm-4 col-form-label">전화번호</label>
                    <div class="col-sm-8">
                        <div class="input-group">
                            <select class="form-select" id="phone1" name="phone1">
                                <option value="010" selected>010</option>
                                <option value="011">011</option>
                                <option value="016">016</option>
                                <option value="017">017</option>
                                <option value="018">018</option>
                                <option value="019">019</option>
                            </select>
                            <input type="text" class="form-control" id="phone2" name="phone2">
                            <input type="text" class="form-control" id="phone3" name="phone3">
                        </div>
                        <div id="phoneNullMsg" class="msg">전화번호를 입력해 주세요.</div>
                        <div id="phoneValidMsg" class="msg">전화번호는 3~4 자리의 숫자로만 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="email" class="col-sm-4 col-form-label">이메일</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com">
                        <div id="emailNullMsg" class="msg">이메일을 입력해 주세요.</div>
                        <div id="emailValidMsg" class="msg">이메일을 형식에 맞게 입력해 주세요.</div>
                    </div>
                </div>
                <button type="submit" class="btn btn-warning w-100">가입하기</button>
            </form>
        </div>
    </div>        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
var idCheckResult=false;

$("#id").focus();

$("#joinForm").submit(function() {
	$(".msg").hide();
    $(".is-invalid").removeClass("is-invalid");

    var validResult = true;

    var id = $("#id").val();
    var idReg = /^[a-z0-9]{4,16}$/g;
    if (id == "") {
        $("#id").addClass("is-invalid");
        $("#idNullMsg").show();
        validResult = false;
    } else if (!idReg.test(id)) {
        $("#id").addClass("is-invalid");
        $("#idValidMsg").show();
        validResult = false;
    } else if (!idCheckResult) {
        $("#id").addClass("is-invalid");
        $("#idDuplMsg").show();
        validResult = false;
    }

    var passwd = $("#pw").val();
    var passwdReg = /^[A-Za-z\d!@#$%^&*()_+\[\]{};':",.<>\/?\\|`~\-]{6,16}$/g;

    if (passwd == "") {
        $("#pw").addClass("is-invalid");
        $("#pwNullMsg").show();
        validResult = false;
    } else if (!passwdReg.test(passwd)) {
        $("#pw").addClass("is-invalid");
        $("#pwValidMsg").show();
        validResult = false;
    }

    if ($("#repw").val() == "") {
        $("#repw").addClass("is-invalid");
        $("#repwNullMsg").show();
        validResult = false;
    } else if ($("#pw").val() != $("#repw").val()) {
        $("#repw").addClass("is-invalid");
        $("#repwValidMsg").show();
        validResult = false;
    }

    var name = $("#name").val();
    var nameReg = /^[가-힣]{2,10}$/g;
    if (name == "") {
        $("#name").addClass("is-invalid");
        $("#nameNullMsg").show();
        validResult = false;
    } else if (!nameReg.test(name)) {
        $("#name").addClass("is-invalid");
        $("#nameValidMsg").show();
        validResult = false;
    }

    var email = $("#email").val();
    var emailReg = /^([a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z0-9]{2,6})$/g;
    if (email == "") {
        $("#email").addClass("is-invalid");
        $("#emailNullMsg").show();
        validResult = false;
    } else if (!emailReg.test(email)) {
        $("#email").addClass("is-invalid");
        $("#emailValidMsg").show();
        validResult = false;
    }

    var phone2Reg = /^\d{3,4}$/;
    var phone3Reg = /^\d{4}$/;
    if ($("#phone2").val() == "" || $("#phone3").val() == "") {
        $("#phone2").addClass("is-invalid");
        $("#phone3").addClass("is-invalid");
        $("#phoneNullMsg").show();
        validResult = false;
    } else if (!phone2Reg.test($("#phone2").val()) || !phone3Reg.test($("#phone3").val())) {
        $("#phone2").addClass("is-invalid");
        $("#phone3").addClass("is-invalid");
        $("#phoneValidMsg").show();
        validResult = false;
    }

    if ($("#zipcode").val() == "") {
        $("#zipcode").addClass("is-invalid");
        $("#zipcodeMsg").show();
        validResult = false;
    }

    if ($("#address1").val() == "") {
        $("#address1").addClass("is-invalid");
        $("#address1Msg").show();
        validResult = false;
    }

    if ($("#address2").val() == "") {
        $("#address2").addClass("is-invalid");
        $("#address2Msg").show();
        validResult = false;
    }

    return validResult;
});

$("#id").keyup(function() {
    idCheckResult = false;

    var id = $(this).val();
    if (id.length < 4) return;

    $.ajax({
        type: "get",
        url: "<%=request.getContextPath()%>/users/id_check.jsp",
        data: "id=" + id,
        dataType: "xml",
        success: function(xmlDoc) {
            var code = $(xmlDoc).find("code").text();
            if (code == "possible") {
                idCheckResult = true;
            }
        },
        error: function(xhr) {
            alert("에러코드 = " + xhr.status);
        }
    });
});

$("#postSearch").click(function() {
    new daum.Postcode({
        oncomplete: function(data) {
            $("#zipcode").val(data.zonecode);
            $("#address1").val(data.address);
        }
    }).open();
});
</script>
</body>
</html>
