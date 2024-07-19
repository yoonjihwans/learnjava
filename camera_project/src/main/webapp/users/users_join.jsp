<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
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
            width: 100%;
            max-width: 700px;
            padding: 24px;
            border: 1px solid #ffffff;
            border-radius: 8px;
        }

        .error {
            color: red;
            font-size: 0.75em;
            margin-top: 0.2em;
            
        }        
    </style>
</head>
<body>
    <div class="custom-container">
        <div class="form-container">
            <h1 class="my-4 text-center">회원가입</h1>
            <form id="join" action="<%=request.getContextPath() %>/index.jsp?workgroup=users&work=users_join_action" method="post">
                <input type="hidden" id="idCheckResult" value="0">
				<div class="mb-3 row">
					<label for="id" class="col-sm-4 col-form-label">아이디</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="text" class="form-control" id="id" name="id"
								placeholder="아이디를 입력하세요">
							<button type="button" class="btn btn-secondary" id="idCheck">아이디 중복 검사</button>
						</div>
						<div id="idMsg" class="error">아이디를 입력해 주세요.</div>
						<div id="idRegMsg" class="error">아이디는 영문소문자/숫자, 4~16자로만 작성 가능합니다.</div>
						<div id="idCheckMsg" class="error">아이디 중복 검사를 반드시 실행해 주세요.</div>
					</div>
				</div>

				<div class="mb-3 row">
                    <label for="pw" class="col-sm-4 col-form-label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호를 입력하세요">
                        <div id="pwMsg" class="error">비밀번호를 입력해 주세요.</div>
                        <div id="pwRegMsg" class="error">비밀번호는 영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10~16자로만 작성 가능합니다.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="repw" class="col-sm-4 col-form-label">비밀번호 확인</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="repw" name="repw" placeholder="비밀번호를 다시 입력하세요">
                        <div id="repwMsg" class="error">비밀번호 확인을 입력해 주세요.</div>
                        <div id="repwMatchMsg" class="error">비밀번호와 비밀번호 확인이 서로 맞지 않습니다.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="name" class="col-sm-4 col-form-label">이름</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름을 입력하세요">
                        <div id="nameMsg" class="error">이름을 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="zipcode" class="col-sm-4 col-form-label">우편번호</label>
                    <div class="col-sm-8">
                        <div class="input-group">
                            <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" readonly>
                            <button type="button" class="btn btn-secondary" id="postSearch" onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
                        </div>
                        <div id="zipcodeMsg" class="error">우편번호를 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="address1" class="col-sm-4 col-form-label">기본주소</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="address1" name="address1" placeholder="주소" readonly>
                        <div id="address1Msg" class="error">기본주소를 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="address2" class="col-sm-4 col-form-label">상세주소</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="address2" name="address2" placeholder="상세주소를 입력하세요">
                        <div id="address2Msg" class="error">상세주소를 입력해 주세요.</div>
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
                        <div id="phoneMsg" class="error">전화번호를 입력해 주세요.</div>
                        <div id="phoneRegMsg" class="error">전화번호는 3~4 자리의 숫자로만 입력해 주세요.</div>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="email" class="col-sm-4 col-form-label">이메일</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" id="email" name="email" placeholder="example@example.com">
                        <div id="emailMsg" class="error">이메일을 입력해 주세요.</div>
                        <div id="emailRegMsg" class="error">입력한 이메일이 형식에 맞지 않습니다.</div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary w-100">가입하기</button>
            </form>
        </div>
    </div>        
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    
    $(".error").hide(); 

    $("#id").focus(); 

    $("#join").submit(function() {
        var submitResult = true;

       
        $(".error").hide();

        var idReg = /^[a-z0-9]{4,16}$/;
        if ($("#id").val() == "") {
            $("#idMsg").css("display", "block");
            submitResult = false;
        } else if (!idReg.test($("#id").val())) {
            $("#idRegMsg").css("display", "block");
            submitResult = false;
        } else if ($("#idCheckResult").val() == "0") {
            $("#idCheckMsg").css("display", "block");
            submitResult = false;
        }

        var pwReg = /^(?=.*[a-zA-Z])(?=.*[\d!@#$%^&*()_+{}|:"<>?])[a-zA-Z\d!@#$%^&*()_+{}|:"<>?]{10,16}$/;
     
        if ($("#pw").val() == "") {
            $("#pwMsg").css("display", "block");
            submitResult = false;
        } else if (!pwReg.test($("#pw").val())) {
            $("#pwRegMsg").css("display", "block");
            submitResult = false;
        }

        if ($("#repw").val() == "") {
            $("#repwMsg").css("display", "block");
            submitResult = false;
        } else if ($("#pw").val() != $("#repw").val()) {
            $("#repwMatchMsg").css("display", "block");
            submitResult = false;
        }

        if ($("#name").val() == "") {
            $("#nameMsg").css("display", "block");
            submitResult = false;
        }

        var emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if ($("#email").val() == "") {
            $("#emailMsg").css("display", "block");
            submitResult = false;
        } else if (!emailReg.test($("#email").val())) {
            $("#emailRegMsg").css("display", "block");
            submitResult = false;
        }

        var phone2Reg = /^\d{3,4}$/;
        var phone3Reg = /^\d{4}$/;
        if ($("#phone2").val() == "" || $("#phone3").val() == "") {
            $("#phoneMsg").css("display", "block");
            submitResult = false;
        } else if (!phone2Reg.test($("#phone2").val()) || !phone3Reg.test($("#phone3").val())) {
            $("#phoneRegMsg").css("display", "block");
            submitResult = false;
        }

        if ($("#zipcode").val() == "") {
            $("#zipcodeMsg").css("display", "block");
            submitResult = false;
        }

        if ($("#address1").val() == "") {
            $("#address1Msg").css("display", "block");
            submitResult = false;
        }

        if ($("#address2").val() == "") {
            $("#address2Msg").css("display", "block");
            submitResult = false;
        }

        return submitResult;
    });

    $("#idCheck").click(function() {
      
        $(".error").hide(); 

        var idReg = /^[a-z0-9]{4,16}$/;
        if ($("#id").val() == "") {
            $("#idMsg").css("display", "block");
            return;
        } else if (!idReg.test($("#id").val())) {
            $("#idRegMsg").css("display", "block");
            return;
        }

        window.open("<%=request.getContextPath()%>/users/id_check.jsp?id=" + $("#id").val(), "idCheck", "width=450, height=130, left=700, top=400");
    });

    $("#id").change(function() {
        $("#idCheckResult").val("0");
    });

    $("#postSearch").click(function() {
        new daum.Postcode({
            oncomplete: function(data) {
                $("#zipcode").val(data.zonecode);
                $("#address1").val(data.address);
            }
        }).open();
    });
});
</script>

</body>
</html>
