package com.ncs.green;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import business.GgraphService;
import vo.GgraphVO;

@Controller
public class GgraphController {

	@Autowired
	@Qualifier("ggraph")
	private GgraphService service;

	@RequestMapping(value="/GgraphMain")
	public ModelAndView GgraphMain(HttpServletRequest request, 
			ModelAndView mv, GgraphVO vo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setGraID(id);
				String sub = service.selectSubject(vo);
				if(sub.length() > 2) {
					sub = sub.substring(1, sub.length()-1);
					String[] subarr = sub.split(", ");
					
					mv.addObject("subarr",subarr);
				}
			}else {
				System.out.println("***** loginID null *****");
			}
		}else {
			System.out.println("***** session null *****");
		}
		mv.setViewName("graph/graphselect");
		return mv;
	}
	
	@RequestMapping(value="/Ggraph") //member Detail
	public ModelAndView Ggraph(HttpServletRequest request, 
			ModelAndView mv, GgraphVO vo, ArrayList<GgraphVO> gvo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setGraID(id);
				gvo = service.selectgraph(vo);
				mv.addObject("graphData",gvo);
				mv.addObject("size",gvo.size());
				mv.addObject("subname", vo.getExam_subject());
				
			}else {
				System.out.println("***** loginID null *****");
			}
		}else {
			System.out.println("***** session null *****");
		}
		mv.setViewName("graph/graph");
		return mv;
	}

	@RequestMapping(value="/GgraphDetail") //member Detail
	public ModelAndView GgraphDetail(HttpServletRequest request, 
			ModelAndView mv, GgraphVO vo) {
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				vo.setGraID(id);
				ArrayList<GgraphVO> gvo = service.selectList(vo);
				gvo=service.selectList(vo);
				mv.addObject("Ggraph", gvo);
				if(gvo.size()==0) {
					mv.addObject("Ggraphempty", "True");					
				}
			}else {
				System.out.println("***** loginID null *****");
			}
		}else {
			System.out.println("***** session null *****");
		}
		mv.setViewName("graph/insertgraph");
		return mv;
	}

	@RequestMapping(value="/GgraphDelete")
	public ModelAndView GgraphDelete(HttpServletRequest request, ModelAndView mv, GgraphVO vo) {
		System.out.println(vo);
		int cnt=0;
		String id=null;
		HttpSession session = request.getSession(false);
		if(session!=null) {
			id=(String)session.getAttribute("id");
			if(id!=null) {
				cnt=service.delete(vo);
			} else {
				System.out.println("*****id is null****");
			}
		} else {
			System.out.println("***** session null *****");
		}
		if(cnt>0) {
			System.out.println("*****Ggraph Delete Success*****");
			mv.setViewName("");
		}else {
			System.out.println("*****Ggraph Delete Fail*****");
			mv.setViewName("");
		}
		return mv;
	}

	@RequestMapping(value="/Ggraphupdate")
	public ModelAndView Ggraphupdate(HttpServletRequest request, ModelAndView mv, Map<String,GgraphVO> map,GgraphVO vo) {
		HttpSession session = request.getSession(false);
		String id=null;
		int cnt=0;
		if(session!=null) {
			id=(String)session.getAttribute("id");
		}
		vo.setGraID(id);
		String[] arrSeq;
		String[] arrName=map.get("ggraphVO").getExam_name().split(",");
		String[] arrDate=map.get("ggraphVO").getExam_date().split(",");
		String[] arrsubject=map.get("ggraphVO").getExam_subject().split(",");
		String[] arrGrade=map.get("ggraphVO").getExam_grade().split(",");
	
		if(map.get("ggraphVO").getSeq()!=null) { //데이터가 이미 있는 유저
			arrSeq=map.get("ggraphVO").getSeq().split(",");
			for (int i = 0; i < arrSeq.length; i++) {
				vo.setSeq(arrSeq[i]);
				vo.setExam_name(arrName[i]);
				vo.setExam_date(arrDate[i]);
				vo.setExam_subject(arrsubject[i]);
				vo.setExam_grade(arrGrade[i]);
				cnt=service.update(vo);
				if (cnt>0) {
					System.out.println(i+"***update성공***");
				} else {
					System.out.println(i+"***실패***");
				}
			}
		for (int i=arrSeq.length ; i< arrName.length; i++) {
			vo.setExam_name(arrName[i]);
			vo.setExam_date(arrDate[i]);
			vo.setExam_subject(arrsubject[i]);
			vo.setExam_grade(arrGrade[i]);
			cnt=service.insert(vo);
			if (cnt>0) {
				System.out.println(i+"***insert성공***");
			} else {
				System.out.println(i+"***실패***");
			}
		}
		} else { //데이터가 없고 처음 입력하는 유저
			for (int i=0 ; i< arrName.length; i++) {
				vo.setExam_name(arrName[i]);
				vo.setExam_date(arrDate[i]);
				vo.setExam_subject(arrsubject[i]);
				vo.setExam_grade(arrGrade[i]);
				System.out.println(vo.toString());
				cnt=service.insert(vo);
				if (cnt>0) {
					System.out.println(i+"***성공***");
				} else {
					System.out.println(i+"***실패***");
				}
			}
		}

		mv.setViewName("redirect:GgraphMain");

		return mv;
	}



}
