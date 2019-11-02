<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='resources/jsLib/fullcalendar.min.css' rel='stylesheet' />
<link href='resources/jsLib/fullcalendar.print.min.css' rel='stylesheet' media='print' />
<script src='resources/jsLib/moment.min.js'></script>
<script src='resources/jsLib/jquery-3.2.1.min.js'></script>
<script src='resources/jsLib/fullcalendar.min.js'></script>
<script src='resources/jsLib/ko.js'></script>
<script src='resources/jsLib/jquery.bpopup.min.js'></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
   
<script>
$(document).ready(function() {
	var test=[];
	
	for(var i=0, test=[]; i<${size}; i++){
		test.push(
			{title:$('#title'+i).val(),start:$('#start_date'+i).val(),end:$('#end_date'+i).val()}
		);
	}
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay,listWeek'
		},
//		defaultDate: '2019-09-02',
		navLinks: true, // can click day/week names to navigate views
		editable: true,
		eventLimit: true, // allow "more" link when too many events
		locale: "ko",
		//defaultView: "basicWeek",주간
		defaultView: "month",//월간->디폴트값
		//defaultView: "listWeek", 주간리스트
		events: test,
		
		//드래그 & 드롭
 		eventDrop: function(event, delta, revertFunc){
			$.ajax({
				type: 'POST',
				url: "CalendarUpdate",
				data: {caid: event.id, title: event.title, start_date: event.start.format(), end_date: event.end.format()},
				//cache: false;	//ajax로 통신 중 cache가 남아서 갱신된 데이터를 받아오지 못할 경우 사용
				//async: false;	//false로 선언시 ajax결과값이 끝난 다음에 함수가 진행됨
			})
		},	 
	
		//데이터 삭제
		eventClick: function(calEvent,jsEvent,view){
			if(!confirm("일정'"+calEvent.title+"'을 삭제하시겠습니까?")){
				return false;
			}
			$.ajax({
				type: 'POST',
				url: "CalendarDelete",
				data: {title: calEvent.title},
				success: function (result) {
					closeMessage('winAlert');
					alert('정상 처리되었습니다.');
					location.href = 'http://localhost:9090/green/CalendarMain';
				}
			})
		}
    });
});

function addSchedule(){
	var scheduleContents="";
	scheduleContents +="<div style='width:100%; height:30px;'><div style='width:30%; float:left; padding-left:30px'>일정 명칭</div><div style='width:60%; float:right'><input type='text' id='calendar_title' value=''></div></div>";
	scheduleContents +="<div style='width:100%; height:30px;'><div style='width:30%; float:left; padding-left:30px'>시작 날짜</div><div style='width:60%; float:right'><input type='datetime-local' id='calendar_start_date' value=''></div></div>";
	scheduleContents +="<div style='width:100%; height:30px;'><div style='width:30%; float:left; padding-left:30px'>마침 날짜</div><div style='width:60%; float:right'><input type='datetime-local' id='calendar_end_date' value=''></div></div>";
	scheduleContents +="<div style='width:100%; text-align:center; height:30px; margin-bottom:15px; margin-top:10px'><button onclick= saveSchedule();>저장하기</button></div>";
	openPopup("일정 등록", scheduleContents, 400);
};

function openPopup(subject,contents,widths){
	$("#alert_subject").html(subject);
	$("#alert_contents").html(contents);
	openMessage('winAlert',widths)
};

function saveSchedule(){
	var calendar_title = $("#calendar_title").val();
	var calendar_start_date = $("#calendar_start_date").val();
	var calendar_end_date = $("#calendar_end_date").val();

	if(!calendar_title){
		alert("일정 명칭을 입력해 주세요.");
		return false;
	};
	
	if(!calendar_start_date){
		
		alert("시작 날짜를 입력해 주세요.");
		return false;
	};
	
	if(!calendar_end_date){
		alert("종료 날짜를 입력해 주세요.");
		return false;
	};
	
	$.ajax({
		type: 'POST',
		url: "CalendarInsert", //저장하기버튼 눌렀을시 이동하는 컨트롤러 주소
		data: {title: calendar_title, start_date: calendar_start_date, end_date: calendar_end_date},
		success: function (result) {
			closeMessage('winAlert');
			alert('정상 처리되었습니다.');
			location.href = 'http://localhost:9090/green/CalendarMain';
		}
	})
}; 

function openMessage(IDS,widths){
	$('#'+IDS).css("width",widths+"px");
	$('#'+IDS).bPopup();
};

function closeMessage(IDS){
	$('#'+IDS).bPopup().close();
};
</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	font-size: 14px;
}
#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>
<!-- <div id='script-warning'>
	실행 오류!
</div> -->
<div id='calendar'>
	<div style='float:right'>
		<button onclick="addSchedule()" class="btn btn-info btn-md">일정등록</button>
	</div>
</div>
<div class="box box-success" style="width:500px; display:none; background-color:white" id="winAlert">
	<div class="box-header with-border" style="background-color:white; padding-left:15px">
		<h3 class="box-title" id="alert_subject" style="background-color:white"></h3>
	</div>
	<div class="box-body" id="alert_contents" style="font-size:15px; background-color:white"></div>
</div>
<div>

<c:forEach var="kkkk" items="${loginIDcalendar}" varStatus="vs">
	<input type="hidden" id="title${vs.index}" value="${kkkk.title}">
	<input type="hidden" id="start_date${vs.index}" value="${kkkk.start_date}">
	<input type="hidden" id="end_date${vs.index}" value="${kkkk.end_date}">	
</c:forEach>
</div>
</body>
</html>