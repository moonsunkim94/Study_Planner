var ECheck = false;

$(function() {
    // password
   $('#find_email').focusout(function() {
      var find_email = $('#find_email').val();
      var FELength = find_email.length;
      var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
      if (exptext.test(id)==false){
     	 $('#EMessage').text('이메일형식이 올바르지 않습니다.');
          //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우            
          ECheck =false;
       } else {
          ECheck = true;
          $('#EMessage').text('');
       }
   }); // password

   // login 처리=============================================================이하 find_email에 맞게 수정
   $('#alogin').click(function() {
      if (iCheck == true && pCheck == true) {

         $.ajax({   
            type : 'Post',
            url : 'login',
            data : {
               id : $('#id').val(),
               password : $('#password').val()
            },
            
            success : function(result) {
               $('#resultArea').html(result);
            }
         });
      } else {
         alert('입력 자료에 오류가 있습니다.\n확인 후 전송 버튼을 누르세요..')
      }
   });
   //------------------------------------------------------------------------------------------------
   
   
   
   // 이메일 미입력시 로그인버튼 비활성화
   // 이메일 값이 change되면 loginDisabled의 id 값을 가진 버튼의 'disabled(비활성화)'속성을 제거한다.
	$('#find_email').change(function() {
		$('#find_emailDisabled').removeAttr("disabled");
	}); // 비밀번호 입력 change 끝
      
	// 비밀번호 찾기 a 태그 클릭시 패스워드 찾기화면으로 이동함.============================================이하 수정 필요
	$('#find_pw_btn').click(function(){
		location.href='find_pw_form';
	}); //비밀번호 클릭 끝
	//------------------------------------------------------------------------------------------------
	
	
	
}); // ready