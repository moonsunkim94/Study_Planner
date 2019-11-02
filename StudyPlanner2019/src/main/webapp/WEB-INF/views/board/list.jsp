<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>** 게시판 화면입니다 **</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	// 원하는 페이지로 이동시 검색조건, 키워드 값을 유지하기 위해 
	function list(page) {
		location.href = "http://localhost:9090/green/board/list?curPage="
				+ page + "&searchOption=${map.searchOption}"
				+ "&keyword=${map.keyword}";
	}
</script>

<script>
	$(document).ready(function() {
		$("#btnWrite").click(function() {
			location.href = "http://localhost:9090/green/board/write";
		});

	});
</script>

<style>
.center {
	margin: auto;
	width:46%;
	/* border: 3px solid #73AD21;  */
	padding: 10px;
}

#board_title {
	background-color: #17a2b8;
	color: white;
	font-size: 25px;
}

#board_content {
	font-size: 25px;
}

#paging {
	padding-top: 25px;
}

#number {
	padding:3px;
	margin:3px;
}

#number a{
	padding: 5px 8px 4px 8px;
	/*margin: 2px;*/
	border: #ccc 1px solid;
	color: #999;
	text-decoration: none;
}

#number a:hover {BORDER:#999 1px solid; COLOR: #666;}
#number a:active {BORDER:#999 1px solid; COLOR: #666;}
#number .current {BORDER: #117bff 1px solid; padding:5px 8px 4px 8px; FONT-WEIGHT: bold; MARGIN: 2px; COLOR: #036cb4;}
#number .disabled {BORDER: #eee 1px solid; padding:5px 8px 4px 8px; MARGIN: 2px; COLOR: #ddd;}

</style>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</head>

<body>
<div class="center">
	<h3><b>게시판</b></h3><br>
	<div id="search_list" align="right">
		<form action="list" method="post" name="form1">
			<select name="searchOption">
				<!-- 검색 조건을 검색처리 후 결과화면에 보여주기 위해 c:out 출력태그 사용,삼항연산자 -->
				<option value="all" <c:out value="${map.searchOption=='all'?'selected':''}"/>>전체</option>
				<option value="name" <c:out value="${map.searchOption=='name'?'selected':''}"/>>이름</option>
				<option value="content" <c:out value="${map.searchOption=='content'?'selected':''}"/>>내용</option>
				<option value="title" <c:out value="${map.searchOption=='title'?'selected':''}"/>>제목</option>
			</select> 
			<input name="keyword" value="${map.keyword}"> 
			<input type="submit" class="btn btn-info btn-md" value="조회">
		</form>
	</div>
	
	<!-- 레코드 갯수를 출력 -->
	<div id="count_list">>> ${map.count}개의 게시물이 있습니다.</div>
		<table style="width:850px;">
		<!-- 테이블 제목 비율로 조정해서 클릭. -->
		<colgroup>
			<col width="10%"/>
			<col width="*"/>
			<col width="15%"/>
			<col width="35%"/>
			<col width="10%"/>			
		</colgroup>
			<tr align="center" id="board_title" class="board_title">
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>

			<c:forEach var="row" items="${map.list}">
			<tr id="board_content" class="board_content">
				<td align="center">${row.bno}</td>
				<td><a href="http://localhost:9090/green/board/view?bno=${row.bno}&writer=${row.writer}
						&curPage=${map.boardPager.curPage}&searchOption=${map.searchOption}&keyword=${map.keyword}">
					<!--  페이지마다 게시판 크기 고정을 위해 글제목이 길 경우 글자를 잘라서 일부만 표시 -->
					<c:choose>
						<c:when test="${fn:length(row.title)>9}">
							<c:out value="${fn:substring(row.title,0,8)}"/>...
						</c:when>
						<c:otherwise>
							<c:out value="${row.title}"/>
						</c:otherwise>
					</c:choose>

		
					<!-- ** 댓글이 있으면 게시글 이름 옆에 출력하기 --> 
					<c:if test="${row.recnt > 0}">
						<span style="color: red;">(${row.recnt}) </span>
					</c:if>
					</a>
				</td>
				<td align="center">${row.userName}</td>
				<td align="center">
					<!-- 원하는 날짜형식으로 출력하기 위해 fmt 태그 사용 --> 
					<fmt:formatDate value="${row.regdate}" pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td align="center">${row.viewcnt}
				</td>	
			</tr>
			</c:forEach>
		</table>
		<br>
		
		<div class="paging" id="paging" align="center">
		
			<!-- 페이징 -->
			<div class="number" id="number">
					<c:if test="${map.boardPager.curBlock > 1}">
					<!-- 처음페이지로 이동 : 현재 페이지가 1보다 크면  [처음]하이퍼링크를 화면에 출력--> 
						<a href="javascript:list('1')">처음</a>
					</c:if>
					<!-- 이전페이지 블록으로 이동 : 현재 페이지 블럭이 1보다 크면 [이전]하이퍼링크를 화면에 출력 --> 
					<c:if test="${map.boardPager.curBlock > 1}">
						<a href="javascript:list('${map.boardPager.prevPage}')">이전</a>
					</c:if> 
					<!-- **하나의 블럭 시작페이지부터 끝페이지까지 반복문 실행 --> 
					<c:forEach var="num" begin="${map.boardPager.blockBegin}" end="${map.boardPager.blockEnd}">
						<!-- 현재페이지이면 하이퍼링크 제거 -->
						<c:choose>
							<c:when test="${num == map.boardPager.curPage}">
								<span class="current">${num}</span>&nbsp;
						</c:when>
							<c:otherwise>
								<a href="javascript:list('${num}')">${num}</a>&nbsp;
						</c:otherwise>
						</c:choose>
					</c:forEach> 
					<!-- 다음페이지 블록으로 이동 : 현재 페이지 블럭이 전체 페이지 블럭보다 작거나 같으면 [다음]하이퍼링크를 화면에 출력 -->
					<c:if test="${map.boardPager.curBlock <= map.boardPager.totBlock}">
						<a href="javascript:list('${map.boardPager.nextPage}')">다음</a>
					</c:if> 
					<!-- 끝페이지로 이동 : 현재 페이지가 전체 페이지보다 작거나 같으면 [끝]하이퍼링크를 화면에 출력 --> 
					<c:if test="${map.boardPager.curPage <= map.boardPager.totPage}">
						<a href="javascript:list('${map.boardPager.totPage}')">끝</a>
					</c:if>
			</div>
			<!-- 페이징  끝-->
		</div>
		
		
		
		
		
		
		
		
		
		
		<br>
		<div id="board_write" align="right">
			<c:if test="${sessionScope.id!=null}">
				<button type="button" id="btnWrite" class="btn btn-info btn-md">글쓰기</button>
			</c:if>
		</div>
	</div>
	<!-- 전체 가운데 정렬 -->
</body>
</html>

































