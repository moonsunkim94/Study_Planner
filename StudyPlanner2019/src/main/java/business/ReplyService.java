package business;

import java.util.List;

import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpSession;

import vo.ReplyVO;

public interface ReplyService {
	// 1. ��� �Է�
	public void create(ReplyVO vo);
	// 2. ��� ���
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session);
	// 3. ��� �󼼺���
	public ReplyVO detail(Integer rno);
	// 4. ��� ����
	public void update(ReplyVO vo);
	// 5. ��� ����
	public void delete(Integer rno);
	// 6. ��� ����
	public int count(Integer bno);
}
