<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<script src="resources/jsLib/inCheck1.js"></script>
<script>

function check(){
	
	if (pCheck== true && p2Check==true && idcheck==true){
		return true;
	} else {
		alert('아이디와 패스워드를 확인해 주세요!');
		return false;
	}	
}

function idDupCheck() {
	
	if (idcheck==false) return ;
	var url='idcheck?id='+$('#id').val();
	window.open(url,"_blank"
		,"toolbar=no,menubar=yes,scrollbars=yes,resizable=yes,width=450,height=300");
};

//???

$(function() {
	$('#id').change(function() {
		document.getElementById('idDupcheck').disabled="disabled";
	})
});

</script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<div id="login-row" class="row justify-content-center align-items-center">
<img src="resources/image/logo.png" height="100px" width="100px"></div>
<h3 align="center" class="text-center text-info">회원가입</h3>
<hr style="border-style:dotted">
	<div id="login">
		<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
<form action="studentInsert" method="post">
<table>
	<tr>
		<td class="text-info">ID</td>
		<td><input type="text" id="id" name="id" placeholder="email@address.com" class="form-control">
		</td>
		<td>
		<input type="button" value="ID 중복확인" onclick="idDupCheck()" class="btn btn-info btn-md"><br>
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2">
		<span id="idMessage" class="text-info"></span>
		</td>
	</tr>
	<tr>
		<td class="text-info">password</td>
		<td><input type="password" id="password" name="password" class="form-control">
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2">
		<span id="pMessage" class="text-info"></span>
		</td>
	</tr>
	<tr>
		<td class="text-info">password 확인</td>
		<td><input type="password" id="password2" name="password2" class="form-control">
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2">
		<span id="p2Message" class="text-info"></span>
		</td>
	</tr>
	<tr>
		<td class="text-info">name</td>
		<td><input type="text" id="name" name="name" value="홍길동" class="form-control">
		</td>
	</tr>
	<tr>
		<td class="text-info">학생타입</td>
		<td>
			<select name="lev" class="form-control" id="exampleFormControlSelect1">
				<option value="A">공시생</option>
				<option value="B">대학생</option>
				<option value="C">중·고생</option>
			</select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="reset" value="재작성" class="btn btn-info btn-md">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="submit" value="가입하기" disabled="disabled" id="idDupcheck" onclick="return check()" class="btn btn-info btn-md">
		</td>
	</tr>
	<tr><td><br></td></tr>
	<tr>
		<td></td>
		<td></td>
		<td>
			<input type="button" value="홈으로" onclick="location.href='loginf'" class="btn btn-info btn-md">
		</td>
	</tr>
</table>
</form>
</div>
</div>
</div>
</div>
</div>
</body>
</html>