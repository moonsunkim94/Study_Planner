package business;

import java.util.List;

import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpSession;

import vo.ReplyVO;

public interface ReplyService {
	// 1. ´ñ±Û ÀÔ·Â
	public void create(ReplyVO vo);
	// 2. ´ñ±Û ¸ñ·Ï
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session);
	// 3. ´ñ±Û »ó¼¼º¸±â
	public ReplyVO detail(Integer rno);
	// 4. ´ñ±Û ¼öÁ¤
	public void update(ReplyVO vo);
	// 5. ´ñ±Û »èÁ¦
	public void delete(Integer rno);
	// 6. ´ñ±Û °¹¼ö
	public int count(Integer bno);
}
