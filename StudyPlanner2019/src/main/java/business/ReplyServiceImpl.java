package business;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import business.ReplyDAO;
import vo.ReplyVO;

@Service("replyservice")
@Repository
public class ReplyServiceImpl implements ReplyService {

	// @Inject
	@Autowired
	ReplyDAO replyDao;

	// 1. ��� �Է�
	@Override
	public void create(ReplyVO vo) {
		replyDao.create(vo);
	}

	// 2. ��� ���
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end, HttpSession session) {
		List<ReplyVO> items = replyDao.list(bno, start, end);
		// ���ǿ��� ���� ����� id�� ����
		String userId = (String) session.getAttribute("id");
		for (ReplyVO vo : items) {
			// ��� ����߿� �߿� ��� ����� ���� ���
			if (vo.getSecretReply().equals("y")) {
				if (userId == null) { // ��α��� ���¸� ��� ��۷� ó��
					vo.setReplytext("��� ����Դϴ�.");
				} else { // �α��� ������ ���
					String writer = vo.getWriter(); // �Խù� �ۼ��� ����
					String replyer = vo.getReplyer(); // ��� �ۼ��� ����
					// �α����� ����ڰ� �Խù��� �ۼ���X ��� �ۼ��ڵ� X ��д�۷� ó��
					if (!userId.equals(writer) && !userId.equals(replyer)) {
						vo.setReplytext("��� ����Դϴ�.");
					}
				}
			}
		}
		return items;
	}

	// 3. ��� �󼼺���
	@Override
	public ReplyVO detail(Integer rno) {
		return replyDao.detail(rno);
	}

	// 4. ��� ����
	@Override
	public void update(ReplyVO vo) {
		replyDao.update(vo);
	}

	// 5. ��� ����
	@Override
	public void delete(Integer rno) {
		replyDao.delete(rno);
	}

	// 6. ��� ����
	@Override
	public int count(Integer bno) {
		return replyDao.count(bno);
	}
}
