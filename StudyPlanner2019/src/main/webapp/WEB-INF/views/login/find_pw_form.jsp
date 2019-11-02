<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** 비밀번호 찾기 **</title>
<style type="text/css">
    @import url("css/find_pw_form.css");
</style>

<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<script src="resources/jsLib/axTest.js"></script>
<script src="resources/jsLib/jsTest3.js"></script>
<script src="resources/jsLib/login.js"></script>
<script src="resources/jsLib/find_pw_form.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<div id="login">
		<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12"><br>
					 <p align="center"><img src="resources/image/13244.gif" id="logo1"><br></p>
					 	<form id="login-form" class="form" action="findpassword" method="post">
							<h3 class="text-center text-info">비밀번호를 까먹었다면?</h3><br><br>
							<div class="form-group">
								<label for="text" class="text-info">찾을 계정의 Email을 입력하세요 :</label><br>
								<input type="text" name="id" id="find_email" class="form-control">
								<tr><span id=EMessage></span></td>
							</div>
							<p align="right">
							<input type="submit" name="submit" disabled="disabled" id='find_emailDisabled' class="btn btn-info btn-md" value="임시비밀번호 전송">
							</p>
							</div>
							<a href="javascript:history.go(-1)" class="text-info" id="find_pw_btn">뒤로가기</a>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>