package com.ncs.green;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import business.BoardPager;
import business.BoardService;
import business.ReplyPager;
import business.ReplyService;
import vo.BoardVO;
import vo.ReplyVO;

// 
@RestController
@RequestMapping("/reply/*")
public class ReplyController {

	//@Inject
	@Autowired
	ReplyService replyService;

	// 1_1. ��� �Է�(@Controller������� ��� �Է�)
	@ResponseBody
	@RequestMapping("insert")
	public void insert(@ModelAttribute ReplyVO vo, HttpSession session) {
		// ���ǿ� ����� ȸ�����̵� ����ۼ��ڿ� ����
		String userId = (String) session.getAttribute("id");
		vo.setReplyer(userId);
		// ��� �Է� �޼��� ȣ��
		replyService.create(vo);
	}

	// 1_2. ����Է� (@RestController������� json�����Ͽ� ����Է�)
	// @ResponseEntity : ������ + http status code
	// @ResponseBody : ��ü�� json���� (json - String)
	// @RequestBody : json�� ��ü��
	@RequestMapping(value = "insertRest", method = RequestMethod.POST)
	public ResponseEntity<String> insertRest(@RequestBody ReplyVO vo, HttpSession session) {
		ResponseEntity<String> entity = null;
		try {
			// ���ǿ� ����� ȸ�����̵� ����ۼ��ڿ� ����
			String userId = (String) session.getAttribute("id");
			vo.setReplyer(userId);
			// ����Է� �޼��� ȣ��
			replyService.create(vo);
			// ����Է��� �����ϸ� �����޽��� ����
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// ����Է��� �����ϸ� ���и޽��� ����
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// �Է� ó�� HTTP ���� �޽��� ����
		return entity;
	}

	// 2_1. ��� ���(@Controller��� : veiw(ȭ��)�� ����)
	// 0926
	@RequestMapping("list")
	public ModelAndView list(@RequestParam int bno, @RequestParam(defaultValue = "1") int curPage, ModelAndView mav,
			HttpSession session) {
		// ����¡ ó��
		int count = replyService.count(bno); // ��� ����
		ReplyPager replyPager = new ReplyPager(count, curPage);
		// ���� �������� ����¡ ���� ��ȣ
		int start = replyPager.getPageBegin();
		// ���� �������� ����¡ �� ��ȣ
		int end = replyPager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// ���̸� ����
		mav.setViewName("board/replyList");
		// �信 ������ ������ ����
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		// replyList.jsp�� ������
		
		
		return mav;
	}

	// 2_2. ��� ���(@RestController��� : Json���� �����͸� ����)
	@RequestMapping("listJson")
	@ResponseBody // ���ϵ����͸� json���� ��ȯ(RestController���� @ResponseBody��������)
	public List<ReplyVO> listJson(@RequestParam int bno, @RequestParam(defaultValue = "1") int curPage,
			HttpSession session) {
		// ����¡ ó��
		int count = replyService.count(bno); // ��� ����
		ReplyPager pager = new ReplyPager(count, curPage);
		// ���� �������� ����¡ ���� ��ȣ
		int start = pager.getPageBegin();
		// ���� �������� ����¡ �� ��ȣ
		int end = pager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// list�� ����
		return list;
	}

	// ** Controller �߰� ���� - Rest������� ��� ���, ����, ���� ó��

	// 2_3. ��� ���(@RestController��� : json���� �����Ͽ� ��ϻ���)
	// /reply/list/1 => 1�� �Խù��� ��� ��� ����
	// /reply/list/2 => 2�� �Խù��� ��� ��� ����
	// @PathVariable : url�� �Էµ� ������ ����
	@RequestMapping(value = "/list/{bno}/{curPage}", method = RequestMethod.GET)
	public ModelAndView replyList(@PathVariable("bno") int bno, @PathVariable int curPage, ModelAndView mav,
			HttpSession session) {
		// ����¡ ó��
		int count = replyService.count(bno); // ��� ����
		ReplyPager replyPager = new ReplyPager(count, curPage);
		// ���� �������� ����¡ ���� ��ȣ
		int start = replyPager.getPageBegin();
		// ���� �������� ����¡ �� ��ȣ
		int end = replyPager.getPageEnd();
		List<ReplyVO> list = replyService.list(bno, start, end, session);
		// ���̸� ����
		mav.setViewName("board/replyList");
		// �信 ������ ������ ����
		mav.addObject("list", list);
		mav.addObject("replyPager", replyPager);
		// replyList.jsp�� ������
		return mav;
	}

	// 3. ��� �� ����
	// /reply/detail/1 => 1�� ����� ��ȭ�� ����
	// /reply/detail/2 => 2�� ����� ��ȭ�� ����
	// @PathVariable : url�� �Էµ� ������ ����
	@RequestMapping(value = "/detail/{rno}", method = RequestMethod.GET)
	public ModelAndView replyDetail(@PathVariable("rno") Integer rno, ModelAndView mav) {
		ReplyVO vo = replyService.detail(rno);
		// ���̸� ����
		mav.setViewName("board/replyDetail");
		// �信 ������ ������ ����
		mav.addObject("vo", vo);
		// replyDetail.jsp�� ������
		return mav;
	}

	// 4. ��� ���� ó�� - PUT:��ü ����, PATCH:�Ϻμ���
	// RequestMethod�� ���� ������� ������ ��� {}�ȿ� �ۼ�
	@RequestMapping(value = "/update/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	public ResponseEntity<String> replyUpdate(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		try {
			vo.setRno(rno);
			replyService.update(vo);
			// ��� ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// ��� ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// ���� ó�� HTTP ���� �޽��� ����
		return entity;
	}

	// 5. ��� ����ó��
	@RequestMapping(value = "/delete/{rno}")
	public ResponseEntity<String> replyDelete(@PathVariable("rno") Integer rno) {
		ResponseEntity<String> entity = null;
		try {
			replyService.delete(rno);
			// ��� ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			// ��� ������ �����ϸ� ���� ���¸޽��� ����
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		// ���� ó�� HTTP ���� �޽��� ����
		return entity;
	}
}
