<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시글 작성</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
	/* 	// 게시판 예쁘게 불러오기
		 $('#summernote').summernote({
          placeholder: 'Write contents',
          height: 400,
          minHeight: null,             // set minimum height of editor
          maxHeight: null,             // set maximum height of editor
        }); */
		
		// ** 게시글 목록으로 이동 - 버튼 클릭시 상세보기화면에 있던 페이지, 검색옵션, 키워드 값을 가지로 목록으로 이동
	/* 	$("#btnList").click(function(){
			location.href="http://localhost:9090/green/board/list?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
		}); */
		
		$("#btnSave").click(function(){
			if(title==""){
				alert("제목을 입력하세요");
				document.form1.title.focus();
				return;
			}
			if(content==""){
				alert("내용을 입력하세요");
				document.form1.content.focus();
				return;
			}
			document.form1.submit();
		});
	});
</script>
<script>
function goWrite(frm) {
	var title = frm.title.value;
	var writer = frm.writer.value;
	var content = frm.content.value;
	
	if (title.trim() == ''){
		alert("제목을 입력해주세요");
		return false;
	}
	if (writer.trim() == ''){
		alert("작성자를 입력해주세요");
		return false;
	}
	if (content.trim() == ''){
		alert("내용을 입력해주세요");
		return false;
	}
	frm.submit();
}

function movePre(){
	history.back();	
}
</script>	
<style>
.center {
	margin: auto;
	width: 46%;
	/*border: 3px solid #73AD21;*/
	padding: 10px;
}

label[for="board_content"] {
   position:absolute;
}

label {
	font-size: 20px;
}

#btn{
	margin-left: 280px;
}
</style>



</head>
<body>
<div class="center">
	<h3><b>글쓰기</b></h3><br><br>
	<form name="form1" method="post" action="insert">
		<div>
			<label for="board_username">이름 </label>&nbsp;&nbsp;&nbsp;
			<input name=writer id="writer" value="${dto.userName}" readonly="readonly">
		</div>
		<div>
			<label for="board_title">제목</label>&nbsp;&nbsp;&nbsp;
			<input name="title" id="title" size="80" width="600">
		</div>
		<div>
			<label for="board_content">내용</label>&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<textarea name="content" id="content" rows="10" cols="81" style="resize: none;"></textarea> 
			 
		</div>
		<br>
		<hr width="680" align="left">
		<br>
		<div id="btn" class="btn">
			<!-- 게시물번호를 hidden으로 처리 -->
			<input type="submit" value="등록" class="btn btn-info btn-md">

			<button type="button" id="btnList" onclick="movePre()" class="btn btn-info btn-md">목록</button>
			<button type="reset" class="btn btn-info btn-md">취소</button>
		</div>
	</form>	
</div>
</body>
</html>








