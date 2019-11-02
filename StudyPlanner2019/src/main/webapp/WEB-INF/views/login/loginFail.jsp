<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Login Form **</title>
<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<br>
<br>
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
	<h2 class="text-info">로그인 실패!</h2><br>
	</div>
	</div>
	<br>
<c:if test="${Check=='NoEmail'}">
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
<h3 class="text-info">이메일 인증을 먼저 해주세요!</h3><br>
</div></div><br><br><br>
</c:if>
<c:if test="${Check=='Fail'}">
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
<h3 class="text-info">로그인에 실패했습니다!</h3><br>
</div></div><br><br>
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
<span class="text-info">회원가입 하셨나요?&nbsp;&nbsp;&nbsp;</span>
<input type="button" value="회원가입" onclick="termsf" class="btn btn-info btn-md"><br>
</div></div><br><br>
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
<span class="text-info">비밀번호를 잊으셨나요?&nbsp;&nbsp;&nbsp;</span><br>
<input type="button" value="비밀번호 찾기" onclick="find_pw_form" class="btn btn-info btn-md"><br>
</div></div><br><br>
</c:if>
<br>
<div class="container">
	<div id="login-row" class="row justify-content-center align-items-center">
<a href="javascript:history.go(-1)" class="text-info" id="find_pw_btn">뒤로가기</a>
</div></div>
</body>
</html>