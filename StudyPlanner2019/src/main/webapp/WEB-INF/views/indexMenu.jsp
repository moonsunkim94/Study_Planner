<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset='utf-8'>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="resources/csslib/index.css">
   <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
   <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
   <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
   <script src="resources/jsLib/index.js"></script>
   <title>Study Planner</title>
</head>
<body>

<div id='logout' align="right">
		<div class="text-info">${id} 님이 로그인 중입니다</div>
		<!-- 
		<img id="logout" class="logout" name="logout" src="resources/image/logout.jpg" 
			weight="85px" height="35px" onclick="javascript:location.href='http://localhost:9090/green/logout';">
		 -->
		<!-- <a href='logout' style= "text-decorateion:'none' ; " id='logout' class='logout'>로그아웃</a> -->
</div>

<div id='cssmenu'>
<ul>
   <li><a href='http://localhost:9090/green/CalendarMain' target="section">캘린더</a></li>
   <li><a href='http://localhost:9090/green/GgraphMain' target="section">그래프</a></li>
   <li><a href='http://localhost:9090/green/studentDetail' target="section">내 정보</a></li>
   <li><a href='http://www.career.go.kr/cnet/iframe/MajorDic.do?apiKey=8b3617461e2979d61e0ab5749928e610&gubun=univDic' target="section">학과 정보</a></li>
   <li><a href='http://localhost:9090/green/board/list' target="section">게시판</a></li>
   <li><a href='http://localhost:9090/green/siteIntro' target="section">만든 사람들</a></li>
   <li style="float:right; color:white; cursor:pointer;"onclick="javascript:location.href='http://localhost:9090/green/logout';"><br>로그아웃&nbsp;&nbsp;<br></li>
</ul>
<br>
<br>
<br>
</div>
<div class=clear_both></div>
<div class=main>
	<iframe name=section id=section marginwidth="0" marginheight="0" height="900px" width="100%" frameborder="0"></iframe>
</div>
<div class=clear_both></div>
</body>
</html>
