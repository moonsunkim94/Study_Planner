<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
<style type="text/css">
    @import url("csslib/login.css");
</style>
<script src="resources/jsLib/jquery-3.2.1.min.js"></script>
<script src="resources/jsLib/inCheck1.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<header>

</header>

<c:if test="${detailCheck=='S'}">
	<script>
		alert("정보 수정에 성공했습니다!")	
	</script>
</c:if>
<c:if test="${detailCheck=='F'}">
	<script>
		alert("정보 수정에 실패했습니다")	
	</script>
</c:if>
<p>
<br>
<h3 class="text-center text-info">회원 정보 수정</h3>
<br>
</p>
	<div id="login">
		<div class="container">
			<div id="login-row" class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
					
<form action="studentUpdate" method="post">
<table style="margin-left: auto; margin-right: auto;">


<tr height="40"><td class="text-info">Email: </td>
	<td>
	<div>${student.id}</div>
	<input type="hidden" name="id" value="${student.id}" readonly="readonly" class="form-control">
	<span></span>
	</td></tr>

<tr height="40"><td class="text-info">인증여부: </td>
	<td>
	<c:choose>
		<c:when test="${student.seq==1}">
			<div> 인증완료</div>
			<input type="hidden" name="seq" value="${student.seq}" readonly="readonly">
		</c:when>
		<c:otherwise>
			<div> 인증 미완료</div>
			<input type="hidden" name="seq" value="${student.seq}" readonly="readonly">
		</c:otherwise>
	</c:choose>
	<span></span>
	</td>
</tr>

	
<tr height="40"><td class="text-info">Password: </td>
	<td><input type="password" name="password" id="password" value="${student.password}" class="form-control"><br>
	<span id="pMessage" class="text-info"></span></td>
</tr>

<tr height="40"><td class="text-info">Password 확인: </td>
	<td><input type="password" name="password2" id="password2" value="${student.password}" class="form-control"><br>
	<span id="p2Message" class="text-info"></span></td>
</tr>

	
<tr height="40"><td class="text-info">이름: </td>
	<td><input type="text" name="name" value="${student.name}" class="form-control"><span></span></td></tr>
	
<tr height="40"><td class="text-info">회원: </td>
	<td class="text-info">
	<c:choose>
		<c:when test="${student.lev=='A'}">
			<select name="lev" class="form-control" id="exampleFormControlSelect1">
				<option value="A" selected="selected">공시생</option>
				<option value="B">대학생</option>
				<option value="C">중고생</option>
			</select>
		</c:when>
		<c:when test="${student.lev=='B'}">
			<select name="lev" class="form-control" id="exampleFormControlSelect1">
				<option value="A">공시생</option>
				<option value="B" selected="selected">대학생</option>
				<option value="C">중고생</option>
			</select>
		</c:when>
		<c:when test="${student.lev=='C'}">
			<select name="lev" class="form-control" id="exampleFormControlSelect1">
				<option value="A">공시생</option>
				<option value="B">대학생</option>
				<option value="C" selected="selected">중고생</option>
			</select>
		</c:when>
	</c:choose>
	<span></span>
	</td>
</tr>

<tr><td></td>
	<td><br><input type="submit" value="수정하기" class="btn btn-info btn-md">
            <input type="reset" value="Reset" class="btn btn-info btn-md"></td>	
</table>
</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<br>
     
</body>
</html>