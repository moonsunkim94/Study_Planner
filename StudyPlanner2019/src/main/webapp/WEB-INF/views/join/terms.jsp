<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>
<body>
<header>
</header>
	<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
			<img src="resources/image/logo.png" height="100px" width="100px">
			</div></div>
<h3 align="center" class="text-center text-info">회원 약관</h3>
<hr style="border-style:dotted">
<div align="center">
<iframe src="termsDetail" 
	scrolling="yes" name="ce" width="800" height="600" frameborder="0" 
	style="border-width:0px;border-color:white; border-style:solid;"> 
</iframe>
<br><br>
<a href="joinf" class="btn btn-info btn-md">동의</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="loginf" class="btn btn-info btn-md">비동의</a><br><br><br>
<a href="javascript:history.go(-1)" class="text-info" id="find_pw_btn">뒤로가기</a>
</div>
</body>
</html>