<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script>
	//3. 댓글 수정
	$("#btnReplyUpdate").click(function(){
		var detailReplytext = $("#detailReplytext").val();
		$.ajax({
			type: "put",
			url: "http://localhost:9090/green/reply/update/${vo.rno}",
			// 기본값 text/html을 json으로 변경
			headers: {
				"Content-Type":"application/json"
			},
			// 데이터를 json형태로 변환
			data: JSON.stringify({
				replytext : detailReplytext
			}),
			dataType: "text",
			success: function(result){
				if(result == "success"){
					$("#modifyReply").css("visibility", "hidden");
					// 댓글 목록 갱신
					listReplyRest("1");
				}
			}
		});
	});
	
	// 4. 댓글 상세화면 닫기
	$("#btnReplyClose").click(function(){
		$("#modifyReply").css("visibility", "hidden");
	});
	
	// 5. 댓글 삭제
	$("#btnReplyDelete").click(function(){
		if(confirm("삭제하시겠습니까?")){
			$.ajax({
				type: "delete",
				url: "http://localhost:9090/green/reply/delete/${vo.rno}",
				success: function(result){
					if(result == "success"){
						alert("삭제되었습니다.");
						$("#modifyReply").css("visibility", "hidden");
						listReplyRest("1");
					}
				}
			});
		}
	});
</script>


</head>

<body>
	댓글 번호 : ${vo.rno}<br>
	<textarea id="detailReplytext" rows="3" cols="80">${vo.replytext}</textarea>
	<br><br>
	<div style="text-align: center;">
		<!-- 본인 댓글만 수정, 삭제가 가능하도록 처리s -->
		<c:if test="${sessionScope.id == vo.replyer}">
			<button type="button" id="btnReplyUpdate" class="btn btn-info btn-md">수정</button>
			<button type="button" id="btnReplyDelete" class="btn btn-info btn-md">삭제</button>
		</c:if>
		<button type="button" id="btnReplyClose" class="btn btn-info btn-md">닫기</button>
	</div>
</body>
</html>