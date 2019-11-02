var idcheck=false;
var pCheck=false;
var p2Check=false;

$(function(){

	$('#id').focusout(function(){
		var id=$('#id').val();
		var check=/^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(!check.test(id)){
			$('#idMessage').text('이메일 형식으로 입력바랍니다.');
			$('#id').focus();
		} else{
			idcheck=true;
			$('#idMessage').text('');
		}
		

	})
	
	$('#password').focusout(function(){
		var password=$('#password').val();
		var password2=$('#password2').val();
		var pLength=password.length;
		var check=/^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,12}$/;
		if(password.length<8 || password.length>12 ){
			$('#pMessage').text('Password를 8자리 이상 12자리 이하로 입력바랍니다.');
			$('#password').focus();
			pCheck=false;
		}else if(!check.test(password)){
			$('#pMessage').text('Password를 영문,숫자,특수문자의 조합으로 입력바랍니다.');
			$('#password').focus();
			pCheck=false;
		}else if(/(\w)\1\1\1/.test(password)){
			$('#pMessage').text('Password에 1111과 같이 같은 문자를 4번 이상 사용하실 수 없습니다.');
			$('#password').focus();
			pCheck=false;
		}else{
			pCheck=true;
			$('#pMessage').text('');
		}
	}); //password
	
	$('#password2').focusout(function(){
		var password=$('#password').val();
		var password2=$('#password2').val();
		if(password!=password2){
			$('#p2Message').text('Password와 Password확인 이 다릅니다. 정확하게 입력해세요.');
			return false;
		} else {
			p2Check=true;
			$('#p2Message').text('');
		}
	})
	
	
	
}); //ready

