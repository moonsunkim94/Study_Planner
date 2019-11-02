package com.ncs.green;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class UserMailSendService {

	@Autowired
	private JavaMailSender mailSender;
	

	// 회원가입 발송 이메일(인증키 발송)
	public void mailSendWithUserKey(String id, String name, int seq,HttpServletRequest request) {  //id,name,seq를 받아온다
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>안녕하세요 2nd row 스터디플레너입니다!</h2><br><br>" 
				+ "<h3>" + name + "님</h3>" 
				+ "<p>인증하기 버튼을 누르시면 로그인을 하실 수 있습니다 : " 
				+ "<a href='http://localhost:9090" + request.getContextPath() // request.getContextPath() 값이 green(우리 주소)임
				+ "/emailJoinSuccess?id="+ id +"&seq="+seq //id랑 seq값 넘겨주기
				+"'>인증하기</a></p>"
				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";  //여기가 내용(html처럼 써야함)
		try {
			mail.setSubject("[2nd row] 스터디 플레너가 발송한 인증메일입니다", "utf-8"); //아마도 제목
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(id));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//요기는 저희도 긁어온거라...정확히는 모르겠어욤
	}
	
	
	// [2ndrow:left] ::임시비번메시지 발송----------------------------------------------┐
	public void findPasswordTemporary(String id, String name, String password, HttpServletRequest request) {  //id,name,난수(임시비번)를 받아온다
		//1.난수생성(대문자+소문자+숫자+특수문자 10글자 랜덤)함과 동시에 데이터베이스 비번 업데이트해야함.
		//	대문자+소문자+숫자+특수문자 20글자 랜덤
		//	SELECT DBMS_RANDOM.STRING('P', 10) STR FROM DUAL; 
		//		출처: https://oracle.tistory.com/400 [안나푸르나]
		System.out.println("null 값이 왜 나오는지...???");
		System.out.println("id는 "+id+"  ,password는 "+password+"  ,  name은  "+name+"  입니다.");
		//2.위의 1번에 관련된 쿼리문(mapper) 작성해야함.
		//3.쿼리문에 접근 및 실행하는 코드 작성.
		//4.쿼리문 접금 및 실행 후 생성된 난수 변수에 담아 아래의 코드중 삽입
		MimeMessage mail = mailSender.createMimeMessage();
		//tp=이곳에 난수생성된후 업데이트된 비밀번호 받아옴
		String htmlStr = "<h2>안녕하세요 2nd row 스터디플레너입니다!</h2><br><br>" 
				+ "<h3> 임시비밀번호가 요청에 의한 메일입니다.</h3>" 
				 
				+ "임시비밀번호는 "+ password+"입니다. 홈페이지에서 로그인 한 후 비밀번호를 수정해 주세요.";  
		try {
			mail.setSubject("[2nd row] 스터디 플레너가 발송한 인증메일입니다", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(id));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	//---------------------------------------------------------------------------┘
}