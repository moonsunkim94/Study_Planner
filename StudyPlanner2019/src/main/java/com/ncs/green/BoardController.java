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

// 0912 추가사항
@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	// 의존 관계 주입 => BoardServiceImpl 생성
	// IoC 의존관계 역전

	@Autowired
	@Qualifier("boardservice")
	private BoardService boardservice;
	
	@Autowired
	@Qualifier("replyservice") // @Inject
	private ReplyService replyservice;

	// 1. 게시글 목록
	@RequestMapping(value = "list")
	// @RequestParam(defaultValue="") =>기본값 할당
	public ModelAndView list(@RequestParam(defaultValue="title") String searchOption, 
			@RequestParam(defaultValue="") String keyword, 
			@RequestParam(defaultValue="1") int curPage, BoardVO vo, HttpSession session, HttpServletRequest request) throws Exception {
		
		
		// 레코드의 갯수 계산
		int count = boardservice.countArticle(searchOption, keyword);
		ModelAndView mv = new ModelAndView();
		//mv.setViewName("board/list");
		//mv.addObject("list",list); // 데이터를 저장
		
		// 페이지 나누기 관련 처리
		BoardPager boardPager = new BoardPager(count, curPage);
		int start = boardPager.getPageBegin();
		int end = boardPager.getPageEnd();
		
		List<BoardVO> list = boardservice.listAll(start,end,searchOption, keyword);
		
		// 데이터를 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("list 는 =>"+list);
		
		map.put("list", list); // list
		map.put("count", count); // 레코드의 갯수
		map.put("searchOption", searchOption); // 검색 옵션
		map.put("keyword", keyword); // 검색 키워드
		map.put("boardPager",boardPager);
		
		mv.addObject("map", map); // 맵에 저장된 데이터를 mv에 저장
		mv.setViewName("board/list"); // 뷰를 list.jsp로 설정
		
		return mv; // list.jsp로 List가 전달된다.
	}

	// 2_1. 게시글 작성화면
	@RequestMapping(value = "write", method=RequestMethod.GET)
	public ModelAndView write(ModelAndView mv, @ModelAttribute BoardVO vo,Model model,HttpSession session) throws Exception { // String

	
		String writer=(String)session.getAttribute("id");	
		vo.setWriter(writer);
		
		System.out.println("1111"+writer);
		mv.setViewName("board/write");
		mv.addObject("dto",boardservice.readID(writer));
		System.out.println("dto안에는 뭐?"+boardservice.readID(writer));
		
		return mv ; 
	}
	
	// 2_2. 게시글 작성처리
	@RequestMapping(value = "insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute BoardVO vo, HttpSession session) throws Exception {
		// session에 저장된 userId를 writer에 저장
		//String writer = (String)session.getAttribute("id");
		
		//vo.setWriter(writer);
		boardservice.create(vo);
		
		System.out.println("vo를 출력합니당~!!  "+vo );
	
		return "redirect:list"; 
	}
	
	// 3. 게시글 상세내용 조회, 게시글 조회수 증가 처리
	@RequestMapping(value = "view", method=RequestMethod.GET)
	public ModelAndView view(@RequestParam int bno, @RequestParam int curPage, @RequestParam String searchOption,
			@RequestParam String keyword, HttpSession session, ModelAndView mv, HttpServletRequest request, BoardVO vo
		
			) throws Exception {
		// 조회 수 증가 처리
		// 본인의 열람수는 빼고 다른사람이 열람시 조회 수 증가
		String loId= null;
		session = request.getSession(false);
		if(session!=null) {
			loId = (String)session.getAttribute("id");
			//  System.out.println("loId 정상입력되는지 test::"+loId);
			//  System.out.println("vo.getWriter에 있는 내용 출력 :: "+vo.getWriter());	
			if(vo.getWriter() != null && (!vo.getWriter().equals(loId))) {
				if(boardservice.increaseViewcnt(bno, session) > 0)
					System.out.println("** Count Up 성공");
				else
					System.out.println("** Count Up 실패");
			}
		}
		int count = replyservice.count(bno); // 댓글 갯수
		ReplyPager replyPager = new ReplyPager(count,curPage);
		int start = replyPager.getPageBegin();
		// 현재 페이지의 페이징 끝 번호
		int end = replyPager.getPageEnd();
		
		mv.setViewName("board/view");
		// 뷰에 전달할 데이터
		// 댓글의 수 : 댓글이 존재하는 게시물의 삭제처리 방지하기 위해
		mv.addObject("count",replyservice.count(bno));
		mv.addObject("dto",boardservice.read(bno));
		mv.addObject("replylist",replyservice.list(bno, start, end, session));
		mv.addObject("curPage", curPage);
		mv.addObject("searchOption", searchOption);
		mv.addObject("keyword",keyword);
		logger.info("mv",mv);
		return mv; 
	}
	
	// 4. 게시글 수정
	// 폼에서 입력한 내용들은 @ModelAttribute BoardVO vo로 전달됨
	@RequestMapping(value = "update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVO vo) throws Exception {
		boardservice.update(vo);
		return "redirect:list"; 
	}
	
	// 5. 게시글 삭제
	@RequestMapping(value = "delete")
	public String update(@RequestParam int bno) throws Exception {
		boardservice.delete(bno);
		return "redirect:list"; 
	}
}
