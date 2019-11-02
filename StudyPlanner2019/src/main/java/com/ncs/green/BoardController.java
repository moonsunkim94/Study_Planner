package com.ncs.green;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import business.BoardPager;
import business.ReplyPager;
import business.BoardService;
import business.ReplyService;
import vo.BoardVO;

// 0912 �߰�����
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// ���� ���� ���� => BoardServiceImpl ����
	// IoC �������� ����

	@Autowired
	@Qualifier("boardservice")
	private BoardService boardservice;
	
	@Autowired
	@Qualifier("replyservice") // @Inject
	private ReplyService replyservice;

	// 1. �Խñ� ���
	@RequestMapping(value = "list")
	// @RequestParam(defaultValue="") =>�⺻�� �Ҵ�
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, 
			@RequestParam(defaultValue="") String keyword, 
			@RequestParam(defaultValue="1") int curPage, BoardVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		
		
		// ���ڵ��� ���� ���
		int count = boardservice.countArticle(searchOption, keyword);
		ModelAndView mv = new ModelAndView();
		//mv.setViewName("board/list");
		//mv.addObject("list",list); // �����͸� ����
		
		// ������ ������ ���� ó��
		BoardPager boardPager = new BoardPager(count, curPage);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		
		List<BoardVO> list = boardservice.listAll(start,end,searchOption, keyword);
		
		// �����͸� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("list �� =>"+list);
		
		map.put("list", list); // list
		map.put("count", count); // ���ڵ��� ����
		map.put("searchOption", searchOption); // �˻� �ɼ�
		map.put("keyword", keyword); // �˻� Ű����
		map.put("boardPager",boardPager);
		
		mv.addObject("map", map); // �ʿ� ����� �����͸� mv�� ����
		mv.setViewName("board/list"); // �並 list.jsp�� ����
		
		return mv; // list.jsp�� List�� ���޵ȴ�.
	}

	// 2_1. �Խñ� �ۼ�ȭ��
	@RequestMapping(value = "write", method=RequestMethod.GET)
	public ModelAndView write(ModelAndView mv, @ModelAttribute BoardVO vo,Model model,HttpSession session) throws Exception { // String

	
		String writer=(String)session.getAttribute("id");	
		vo.setWriter(writer);
		
		System.out.println("1111"+writer);
		mv.setViewName("board/write");
		mv.addObject("dto",boardservice.readID(writer));
		System.out.println("dto�ȿ��� ��?"+boardservice.readID(writer));
		
		return mv ; 
	}
	
	// 2_2. �Խñ� �ۼ�ó��
	@RequestMapping(value = "insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception {
		// session�� ����� userId�� writer�� ����
		//String writer = (String)session.getAttribute("id");
		
		//vo.setWriter(writer);
		boardservice.create(vo);
		
		System.out.println("vo�� ����մϴ�~!!  "+vo );
	
		return "redirect:list"; 
	}
	
	// 3. �Խñ� �󼼳��� ��ȸ, �Խñ� ��ȸ�� ���� ó��
	@RequestMapping(value = "view", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, @RequestParam String searchOption,
			@RequestParam String keyword, HttpSession session, ModelAndView mv, HttpServletRequest request, BoardVO vo
		
			) throws Exception {
		// ��ȸ �� ���� ó��
		// ������ �������� ���� �ٸ������ ������ ��ȸ �� ����
		String loId= null;
		session = request.getSession(false);
		if(session!=null) {
			loId = (String)session.getAttribute("id");
			//  System.out.println("loId �����ԷµǴ��� test::"+loId);
			//  System.out.println("vo.getWriter�� �ִ� ���� ��� :: "+vo.getWriter());	
			if(vo.getWriter() != null && (!vo.getWriter().equals(loId))) {
				if(boardservice.increaseViewcnt(bno, session) > 0)
					System.out.println("** Count Up ����");
				else
					System.out.println("** Count Up ����");
			}
		}
		int count = replyservice.count(bno); // ��� ����
		ReplyPager replyPager = new ReplyPager(count,curPage);
		int start = replyPager.getPageBegin();
		// ���� �������� ����¡ �� ��ȣ
		int end = replyPager.getPageEnd();
		
		mv.setViewName("board/view");
		// �信 ������ ������
		// ����� �� : ����� �����ϴ� �Խù��� ����ó�� �����ϱ� ����
		mv.addObject("count",replyservice.count(bno));
		mv.addObject("dto",boardservice.read(bno));
		mv.addObject("replylist",replyservice.list(bno, start, end, session));
		mv.addObject("curPage", curPage);
		mv.addObject("searchOption", searchOption);
		mv.addObject("keyword",keyword);
		logger.info("mv",mv);
		return mv; 
	}
	
	// 4. �Խñ� ����
	// ������ �Է��� ������� @ModelAttribute BoardVO vo�� ���޵�
	@RequestMapping(value = "update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardservice.update(vo);
		return "redirect:list"; 
	}
	
	// 5. �Խñ� ����
	@RequestMapping(value = "delete")
	public String update(@RequestParam int bno) throws Exception {
		boardservice.delete(bno);
		return "redirect:list"; 
	}
}
