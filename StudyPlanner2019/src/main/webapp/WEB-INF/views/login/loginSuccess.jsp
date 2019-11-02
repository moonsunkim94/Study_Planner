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
<script src="resources/jsLib/axTest.js"></script>
<script src="resources/jsLib/jsTest3.js"></script>
</head>
<body>
	${id}님이 로그인했습니다. <br>
	
	</table>

	<a href="studentDetail">[회원정보 보기]</a>
	
	<br>
	<a href="logout" id="logout" name="logout">[로그아웃]</a>
</body>
</html>