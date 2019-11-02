/* loginForm.jsp에 대한 js 파일입니다. */
$(function(){
   $('#logo').click(function(){ // 이미지 logo 클릭시 사이트소개 페이지로 넘어가게 하는 js 코드입니다.
      open('siteIntro','_blank', /* 새로운 창으로 팝업하게 하고*/
            'toolbar=yes,menubar=yes,scrollbars=yes,resizeable=yes,width=500,height=700');
   }); // logo 클릭 끝
   
   
   var iCheck = false;
   var pCheck = false;

   $(function() {
      // id 확인
      // id는 4~10글자까지 입력 받음
      $('#id').focusout(function() {
         var id = $('#id').val();
         var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
         // id : 길이확인 (4이하 오류)
         if (id.length < 4 || id.length > 30) {
            $('#iMessage').text('아이디를 확인하세요. 등록되지 않았거나, 잘못 입력하셨습니다!.');
            $('#id').focus(); // 해당태그에 focus In
            iCheck = false;
         //} else if (id.replace(/[a-z.0-9]/gi, '').length > 0) {
           // $('#iMessage').text('아이디를 확인하세요. 등록되지 않았거나, 잘못 입력하셨습니다.');
           // $('#id').focus(); // 해당태그에 focus In
           // iCheck = false;
         } else if (exptext.test(id)==false){
       	 $('#iMessage').text('이메일형식이 올바르지 않습니다.');
            //이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우            
            iCheck =false;
         } else {
            iCheck = true;
            $('#iMessage').text('');
         }
      }); // id

      // password
      $('#password').focusout(function() {
         var password = $('#password').val();
         var pLength = password.length;
         var check=/^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,12}$/;
         if (password.length < 8 || password.length > 12) {
            $('#pMessage').text('비밀번호를 잘못 입력하셨습니다. 확인 후 다시 입력하세요!');
            $('#password').focus();
            pCheck = false;
         }else if(!check.test(password)){
   			$('#pMessage').text('Password를 영문,숫자,특수문자의 조합으로 입력바랍니다.');
   			$('#password').focus();
   			pCheck=false;
         }else if(/(\w)\1\1\1/.test(password)){
   			$('#pMessage').text('Password에 1111과 같이 같은 문자를 4번 이상 사용하실 수 없습니다.');
   			$('#password').focus();
   			pCheck=false;	
   	  } else {
            pCheck = true;
            $('#pMessage').text('');
         }
      }); // password

      // login 처리
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

      // 비밀번호 미입력시 로그인버튼 비활성화 처리..
      // 비밀번호에 값이 change되면 loginDisabled의 id 값을 가진 버튼의 'disabled(비활성화)'속성을 제거한다.
   	$('#password').change(function() {
   		$('#loginDisabled').removeAttr("disabled");
   	}); // 비밀번호 입력 change 끝
         
   	// 비밀번호 찾기 a 태그 클릭시 패스워드 찾기화면으로 이동함.
   	$('#find_pw_btn').click(function(){
   		location.href='find_pw_form';
   	}); //비밀번호 클릭 끝
   	
   	
   	
   }); // ready
   
   
   
}); // function