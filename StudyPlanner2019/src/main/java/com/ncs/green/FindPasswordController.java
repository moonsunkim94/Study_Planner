package com.ncs.green;
/*
 *  [2ndrow::left] 비번찾기에 대한 내용들을 정리하고 추가한 controller입니다.
 *
 * */
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import business.StudentService;
import vo.StudentVO;

@Controller
public class FindPasswordController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("student")
	private StudentService service;
	
	@Autowired
	public UserMailSendService mailsender;

	// 비밀번호 찾기를 진행할 메소드(비번찾기 페이지로 이동하게함...) 
	@RequestMapping(value = "/find_pw_form")
	public String find_pw_form() throws Exception {
		return "/login/find_pw_form";
	}

	// 임시비번 변경하고 메일로 보내주는??? 그런 거..
	@RequestMapping(value = "/findpassword")
	public ModelAndView findpassword(HttpServletRequest request, ModelAndView mv, StudentVO vo) {
		
		System.out.println("이메일꼐정떼스뜌 " + vo); // vo값이 제대로 들어가있는지 테스트!!
		vo = service.selectPassword(vo); // 비밀번호 찾을 계정이 student 테이블에 있는지 검색!

		if (vo != null) { // 찾을계정이 디비에 있다!!!
			// 비밀번호 난수 생성
			int cnt = service.updateTemPassword(vo);

			if (cnt > 0) { // 임시비밀번호 난수로 변경 성공
				vo.setPassword(service.selectAfterPW(vo).getPassword());
				System.out.println("임시비번테스튜 성공"); // vo값이 제대로 들어가있는지 테스트!!

				// 그리고 해당 비밀번호를 전송해야합니다....
				mailsender.findPasswordTemporary(vo.getId(), vo.getName(), vo.getPassword(), request);

				System.out.println("**********임시비번 성공**********");

			} else { // 임시비번 생성 실패한 경우는 없어야되.....끄아아ㅏ.. 없어야합니다.......;;;;;
				System.out.println("난수생성 실패" + vo);
			}
			mv.setViewName("login/pwyes"); // 디비에 있다면 해당 화면에 있다고 출력하고 난수생성....하고 메일..

		} else { // 찾을 계정이 디비에 없다!!
			mv.setViewName("login/pwno"); // 비밀번호찾을 이메일이 디비에 없다...없다는 화면 출력
		}
		return mv;
	} // findpassword

}
