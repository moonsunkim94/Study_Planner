package com.ncs.green;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import business.CalendarService;
import vo.CalendarVO;

@Controller
public class CalendarController {

	@Autowired
	@Qualifier("calendar")
	private CalendarService service;
	
	@RequestMapping(value="/CalendarMain") //member Detail
	public ModelAndView CalendarMain(HttpServletRequest request, 
			ModelAndView mv, CalendarVO vo, ArrayList<CalendarVO> avo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setCaid(id);
				avo=service.selectList(vo);
				int size=service.listCount(vo);
				mv.addObject("size",size);
				mv.addObject("loginIDcalendar",avo);
			}else {
				System.out.println("***** loginID null *****");
			}
		}else {
			System.out.println("***** session null *****");
		}
		mv.setViewName("calendar/mainCalendar");
		return mv;
	}
	
	@RequestMapping(value="/CalendarUpdate")
	public ModelAndView CalendarUpdate(HttpServletRequest request, ModelAndView mv, CalendarVO vo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setCaid(id);
			}
		}
		int cnt=0;
		cnt=service.update(vo);
		if(cnt>0) {
			System.out.println("*****calendar update success*****");
			mv.setViewName("calendar/mainCalendar");
		}else {
			System.out.println("*****calendar updatee fail*****");
			mv.setViewName("calendar/mainCalendar");
		}
		return mv;
	}
	
	@RequestMapping(value="/CalendarInsert")
	public ModelAndView CalendarInsert(HttpServletRequest request, ModelAndView mv, CalendarVO vo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setCaid(id);
			}
		}
		int cnt=0;
		cnt=service.insert(vo);
		if(cnt>0) {
			System.out.println("*****Calendar insert success*****");
			mv.setViewName("redirect:CalendarMain");
		}else {
			System.out.println("*****Calendar insert fail*****");
			mv.setViewName("redirect:CalendarMain");
		}
		return mv;
	}
	
	@RequestMapping(value="CalendarDelete")
	public ModelAndView CalendarDelete(HttpServletRequest request, ModelAndView mv, CalendarVO vo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setCaid(id);
			}
		}
		int cnt=0;
		System.out.println(vo);
		cnt=service.delete(vo);
		if(cnt>0) {
			System.out.println("*****Calendar delete success*****");
			mv.setViewName("redirect:CalendarMain");
		}else {
			System.out.println("*****Calendar delete fail*****");
			mv.setViewName("redirect:CalendarMain");
		}
		return mv;
	}
}
