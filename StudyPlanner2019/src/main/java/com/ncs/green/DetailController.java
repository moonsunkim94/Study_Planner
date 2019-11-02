package com.ncs.green;

/*
 * 멤버 디테일과 수정 컨트롤러
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import business.StudentService;
import vo.StudentVO;

@Controller
public class DetailController {

	@Autowired
	@Qualifier("student")
	private StudentService service;
	
	@RequestMapping(value="/studentDetail") //member Detail
	public ModelAndView studentDetail(HttpServletRequest request,
			ModelAndView mv, StudentVO vo) {
		
		String id = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			id = (String) session.getAttribute("id");
			if (id != null) {
				vo.setId(id);
				vo=service.selectOne(vo);
				mv.addObject("student", vo);
			} else
				System.out.println("**** loginID null ****");
		} else
			System.out.println("**** session null ****");
		
		if (vo != null && id != null) {
			mv.setViewName("detail/detailView"); //detailView
		} else { 
			mv.setViewName("detail/detailFail"); //detailView
		}
		return mv; 
	}
	
	@RequestMapping(value="/studentUpdate") //Update member
	public ModelAndView studentUpdate(ModelAndView mv, StudentVO vo) {
		
		int cnt =  service.update(vo);
		
		mv.addObject("student", vo);

		if (cnt > 0) { //Success
			mv.addObject("detailCheck","S");
			mv.setViewName("detail/detailView"); 
		} else { //fail
			mv.addObject("detailCheck","F");
			mv.setViewName("detail/detailView"); 
		} 
		return mv;
	} // studentUpdate
}
