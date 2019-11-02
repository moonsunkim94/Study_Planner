<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>그래프 보기</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src='resources/jsLib/jquery-3.2.1.min.js'></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  </head>
  
  <body>
  
<form action="Ggraph" method="post" target="graph">
  <div class="form-row align-items-center">
    <div class="col-auto my-1">
      <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Preference</label>
      <select class="form-control" id="exam_subject" name="exam_subject" style="width:300px;">
<c:forEach items="${subarr}" var="sub">
    <option value="${sub}">${sub}</option>
</c:forEach>
</select>
    </div>
    <div class="col-auto my-1">
      <input type="submit" value="확인" class="btn btn-info btn-md">
    </div>
    <div align="right" style="right: 30%;">
    <a a href="http://localhost:9090/green/GgraphDetail">
<button type="button" class="btn btn-info btn-md">점수 입력하기</button>
    </a>
    </div>
  </div>
</form>

  
<iframe name="graph" id="graph" style="display:block; 
width:100vw; height: 100vh" frameborder=0 framespacing=0 marginheight=0 marginwidth=0 scrolling=no vspace=0>
</iframe>
 
</body>
</html>


