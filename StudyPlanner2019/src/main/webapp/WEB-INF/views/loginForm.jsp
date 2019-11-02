<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Study Planner</title>
<style type="text/css">
    @import url("csslib/login.css");
</style>
<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<script src="resources/jsLib/login.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- 이메일 기억하기 기능 테스트 중입니다. [09/10] -->
<script>
$(document).ready(function(){
 
    // 저장된 쿠키값을 가져와서 ID 칸에 넣어준다. 없으면 공백으로 들어감.
    var key = getCookie("key");
    $("#id").val(key); 
     
    if($("#id").val() != ""){ // 그 전에 ID를 저장해서 처음 페이지 로딩 시, 입력 칸에 저장된 ID가 표시된 상태라면,
        $("#remember-me").attr("checked", true); // ID 저장하기를 체크 상태로 두기.
    }
     
    $("#remember-me").change(function(){ // 체크박스에 변화가 있다면,
        if($("#remember-me").is(":checked")){ // ID 저장하기 체크했을 때,
            setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
        }else{ // ID 저장하기 체크 해제 시,
            deleteCookie("key");
        }
    });
     
    // ID 저장하기를 체크한 상태에서 ID를 입력하는 경우, 이럴 때도 쿠키 저장.
    $("#id").keyup(function(){ // ID 입력 칸에 ID를 입력할 때,
        if($("#remember-me").is(":checked")){ // ID 저장하기를 체크한 상태라면,
            setCookie("key", $("#id").val(), 7); // 7일 동안 쿠키 보관
        }
    });
});
 
function setCookie(cookieName, value, exdays){
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}
 
function deleteCookie(cookieName){
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}
 
function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if(start != -1){
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if(end == -1)end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}


</script>






</head>
<body>
<div id="login-row" class="row justify-content-center align-items-center">
<img src="resources/image/logo.png" height="100px" width="100px"></div>
	<div id="login">
		<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<form id="login-form" class="form" action="login" method="post">
							<h3 class="text-center text-info">Study Planner</h3>
							<div class="form-group">
								<!--          이메일 @도메인 처리하는 것에 대해 생각해봐야함                                     -->
								<!--          맵퍼 +                                      -->
								<label for="username" class="text-info">Email:</label><br>
								<input type="text" name="id" id="id" class="form-control">
								<tr><span id=iMessage class="text-info"></span></td>
							</div>
							<div class="form-group">
								<!--          비밀번호 처리하는 것에 대해 생각해봐야함                                     -->
								<label for="password" class="text-info">Password:</label><br>
								<input type="password" name="password" id="password" class="form-control">
								<tr><span id=pMessage class="text-info"></span></td>
							</div>
							<div class="form-group">
								<label for="remember-me" class="text-info">
									<span>이메일 기억하기</span>
									<span>
										<input id="remember-me" name="remember-me" type="checkbox">
									</span>
								</label><br>
								</div>
							
							<input type="submit" name="submit" disabled="disabled" id='loginDisabled' class="btn btn-info btn-md" value="로그인">
							<input type="button" class="btn btn-info btn-md" onclick="location.href='termsf'" value="회원가입">
							<div id="register-link" class="text-right">
								<a href="find_pw_form" class="text-info" id="find_pw_btn">비밀번호 찾기</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>