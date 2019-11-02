<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** ID 중복확인 **</title>
<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<script src="resources/jsLib/inCheck1.js"></script>
<script>
	function idOk() {
		// joinForm(parent window : opener) 으로 id를 전달하고
		// 현재의 창은 닫는다.
		opener.document.getElementById('id').value="${id}";
		opener.document.getElementById('idDupcheck').disabled="";
		self.close();
	} // idOk
</script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<div id=wrap>
<br>
<h3 class="text-center text-info">** ID 중복확인 **</h3>
<br>
<form action="idcheck" method="get">
&nbsp;&nbsp;User ID :
<input type="text" id="id" name="id" value="">
<input type="submit" value="ID 중복확인" onclick="return idCheck()" class="btn btn-info btn-md">
<br><br><hr>
<div class="text-info">
	<c:if test="${idCheck=='T'}">        
		&nbsp;&nbsp;${id} 는 사용가능한 ID 입니다. 
		<input type="button" value="ID사용하기" onclick="idOk()" class="btn btn-info btn-md">
	</c:if>
	<c:if test="${idCheck!='T'}"> 
		&nbsp;&nbsp;${id} 는 사용 불가능한 ID 입니다.<br>
		다시 선택 하세요 ~~<br>
		<script>
		opener.document.getElementById('id').value="";
		</script>
	</c:if>
</div>
</form></div>
</body>
</html>