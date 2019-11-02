<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>* 게시글 작성 *</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function(){
	
	
	/* --------------- 게시글 관련 --------------- */
	// 1. 게시글 수정
	$("#btnUpdete").click(function(){
		var title = $("#title").val();
		var content = $("#content").val();
		if(title == ""){
			alert("제목을 입력하세요");
			document.form1.title.focus();
			return;
		}
		if(content == ""){
			alert("내용을 입력하세요");
			document.form1.content.focus();
			return;
		}
	
		document.form1.action="update"
		// 폼에 입력한 데이터를 서버로 전송
		document.form1.submit();
		
		// 폼에 입력한 데이터를 서버로 전송
		document.form1.submit();
	});
	
	// 2. 게시글 삭제
	$("#btnDelete").click(function(){
		// 댓글이 존재하는 게시물의 삭제처리 방지
		/* var count = "${count}";
		if(count > 0) {
			alert("댓글이 있는 게시물은 삭제할 수 없습니다.")
			return;
		} */
		if(confirm("삭제하시겠습니까?")){
			document.form1.action = "delete";
			document.form1.submit();
		}
	});
	
	// 3. 게시글 목록으로 이동 - 버튼 클릭시 상세보기화면에 있던 페이지, 검색옵션, 키워드 값을 가지로 목록으로 이동
	$("#btnList").click(function(){
		location.href="http://localhost:9090/green/board/list?curPage=${curPage}&searchOption=${searchOption}&keyword=${keyword}";
	});
	
	
	// ㅋㅋㅋㅋ댓글목록 바로 보여지게 하는거를...ㅋㅋㅋㅋ경로문제로 인식헤서..;;;;;;//나는 바보입니다..ㅋㅋㅋ
	listReplyRest("1");
	
	
		
	/* --------------- 댓글 관련---------------- */
	// 1. 댓글 입력-------------------------------
	$("#btnReply").click(function(){
		replyJson(); // json 형식으로 입력
	});
	

	
});

/* --------------- 댓글 관련 -------------- */

// 1_2. 댓글 입력 함수(json방식)
function replyJson(){
	var replytext=$("#replytext").val();
	var bno="${dto.bno}";
	// 비밀댓글 체크여부
	var secretReply = "n";
	// 태그.is(":속성") 체크여부 true/false
	if( $("#secretReply").is(":checked") ){
		secretReply = "y";
	}
	$.ajax({				
		type: "post",
		url: "http://localhost:9090/green/reply/insertRest",
		headers: {
			"Content-Type" : "application/json"
		},
		dateType: "text",
		// param형식보다 간편
		data: JSON.stringify({
			bno : bno,
			replytext : replytext,
			secretReply : secretReply
		}),
		success: function(){
			alert("댓글이 등록되었습니다.");
			// 댓글 입력 완료후 댓글 목록 불러오기 함수 호출
			listReplyRest("1"); // Rest 방식
		}
	});
	
}

// 2_2. 댓글 목록 - 날짜 형식 변환 함수 작성
function changeDate(date){
	date = new Date(parseInt(date));
	year = date.getFullYear();
	month = date.getMonth();
	day = date.getDate();
	hour = date.getHours();
	minute = date.getMinutes();
	second = date.getSeconds();
	strDate = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
	return strDate;
}

// 2_3. 댓글 목록 - Rest방식
function listReplyRest(num){
	$.ajax({
		type: "get",
		url: "http://localhost:9090/green/reply/list/${dto.bno}/"+num,
		success: function(result){
		// responseText가 result에 저장됨.
			$("#listReply").html(result);
		}
	});
}	


// listReplyRest22
function listReplyRest2(num){
	$.ajax({
		type: "get",
		url: "http://localhost:9090/green/reply/list/${dto.bno}/"+num,
		success: function(result){
		// responseText가 result에 저장됨.
			$("#listReply").html(result);
		}
	});
}





// 댓글 수정화면 생성 함수
function showReplyModify(rno){
	$.ajax({
		type: "get",
		url: "http://localhost:9090/green/reply/detail/"+rno,
		success: function(result){
			$("#modifyReply").html(result);
			// 태그.css("속성", "값")
			$("#modifyReply").css("visibility", "visible");
		}
	})
}
</script>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

<style>
#modifyReply {
	width: 600px;
	height: 130px;
	background-color: gray;
	padding: 10px;
	z-index: 10;
	visibility: hidden;
}
.center {
	margin: auto;
	width:46%;
	/* border: 3px solid #73AD21;  */
	padding: 10px;
}
label[for="board_content"] {
   position:absolute;
}

label {
	font-size: 20px;
}
</style>
</head>

<body>
	
	<div class="center">	
	<h3><b>게시글 보기</b></h3><br>
		<!-- show가 y면 -->	
			<!-- 게시물 상세보기 영역 -->
			<form name="form1" id="form1" method="post" >
				<div>		<!-- 원하는 날짜형식으로 출력하기 위해 fmt태그 사용 -->
					<label for="board_date">작성일자 :&nbsp;</label><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd a HH:mm:ss"/>
							<!-- 날짜 형식 => yyyy 4자리연도, MM 월, dd 일, a 오전/오후, HH 24시간제, hh 12시간제, mm 분, ss 초 -->
				</div>
				<div>
					<label for="board_view">조회수 :&nbsp;&nbsp;</label>  ${dto.viewcnt}
				</div>
				<div>
					<label for="board_title">제목</label>&nbsp;&nbsp;
					<c:if test="${sessionScope.id == dto.writer}"><!-- 작성자와 로그인한 사람이 같으면 readonly 설정 x -->
						<input name="title" id="title" size="80" value="${dto.title}" placeholder="제목을 입력해주세요">
					</c:if>
					<c:if test="${sessionScope.id != dto.writer}">
						<input name="title" id="title" size="80" value="${dto.title}" placeholder="제목을 입력해주세요"  readonly="readonly">
					</c:if>
				</div>
				<div>
					<label for="board_content">내용</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${sessionScope.id == dto.writer}"><!-- 작성자와 로그인한 사람이 같으면 readonly 설정 x -->
						<textarea name="content" id="content" rows="4" cols="82" style="resize: none;">${dto.content}</textarea>
					</c:if>
					<c:if test="${sessionScope.id != dto.writer}">
						<textarea name="content" id="content" rows="4" cols="80" style="resize: none;" readonly="readonly">${dto.content}</textarea>
					</c:if>
				</div>
				<div>
					<label for="board_name">이름&nbsp;&nbsp;</label>
					<input name="writer" id="writer" value="${dto.userName}" placeholder="이름을 입력해주세요"> 
				</div><br>
				<div style="width:650px; text-align: center;">
					<!-- 게시물번호를 hidden으로 처리 -->
					<input type="hidden" name="bno" value="${dto.bno}">
				<!-- 본인이 쓴 게시물만 수정, 삭제가 가능하도록 처리 -->
				<c:if test="${sessionScope.id == dto.writer}">
					<button type="button" id="btnUpdete" class="btn btn-info btn-md">수정</button>
					<button type="button" id="btnDelete" class="btn btn-info btn-md">삭제</button>
				</c:if>
					<!-- 상세보기 화면에서 게시글 목록화면으로 이동 -->
					<button type="button" id="btnList" class="btn btn-info btn-md">목록</button>
				</div>
			</form>
			<!-- 게시물 상세보기 영역 -->
			
			<!-- 댓글 작성 영역 -->	
			<div style="width:650px; text-align: center;">
				<br>
				<!-- 로그인 한 회원에게만 댓글 작성폼이 보이게 처리 -->
				<c:if test="${sessionScope.id != null}">	
					<hr>
					<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성해주세요"></textarea>
					<br>
					<!-- 비밀댓글 체크박스 -->
					<input type="checkbox" id="secretReply">비밀 댓글
					<button type="button" id="btnReply" class="btn btn-info btn-md">댓글 작성</button>
				</c:if>
				<hr>
			</div>
			<!-- 댓글 작성 영역 -->
			
		

	<!-- 댓글 목록 영역 -->
	<div id="listReply"></div>
	<!-- 댓글 목록 영역 -->
	</div>
</body>
</html>












