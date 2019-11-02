$(function() {
	$.fn.serializeObject = function() {

		  var result = {}
		  var extend = function(i, element) {
		    var node = result[element.name]
		    if ("undefined" !== typeof node && node !== null) {
		      if ($.isArray(node)) {
		        node.push(element.value)
		      } else {
		        result[element.name] = [node, element.value]
		      }
		    } else {
		      result[element.name] = element.value
		    }
		  }

		  $.each(this.serializeArray(), extend)
		  return result
		}
	$("#add").click(function () { 

		var row = '<tr><td></td><td><input type="date" name="exam_date" class="form-control"></td>'+
		 '<td><input type="text" name="exam_name" class="form-control" width="60px" placeholder="시험 이름"></td>'+
		 '<td><input type="text" name="exam_subject" class="form-control" placeholder="시험 과목"></td>'+
		  '<td><input type="text" name="exam_grade" class="form-control" width="20px" placeholder="점수·등급"></td>'+
		  '<td><button class="btn btn-default" name="delStaff">삭제</button></td></tr>'; 
	
		  $("#addTable").append(row); 
		
	}); 
	
	$(document).on("click","button[name=delStaff]",function(){
        
        var trHtml = $(this).parent().parent();
         
        trHtml.remove(); //tr 테그 삭제
         
    });
	
}); //$(function() { 끝

function submitForm() {
	var formData = $("#Ggraphform").serialize();
	
	$.ajax({
		type: 'POST',
		url: "Ggraphupdate",
		data: formData,
		success: function (result) {
			alert('정상 처리되었습니다.');
			location.href = 'redirect:/GgraphDetail';
		}
	}) //ajax 끝	
} //함수 끝

function deletedata(i) {
	
	if(!confirm("삭제하시겠습니까?"+$("#seq"+i).val)){
		return false;
	}
	var trHtml = $(this).parent().parent();
    trHtml.remove(); 
	$.ajax({
		type: 'POST',
		url: "GgraphDelete",
		data: {
			seq : $("#seq"+i).val()
		},
		success: function (result) {
			alert('정상 처리되었습니다.');
			location.href = 'redirect:/GgraphDetail';
		}
	}) //ajax끝
	
	}//deletedata의 끝

	
