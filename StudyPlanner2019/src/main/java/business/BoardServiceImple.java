package business;

/**
 * �Խñ� ����ó��(�±׹��� ó��, ���鹮�� ó��, �߹ٲ� ����ó��)
 * �Խñ� ��ȸ�� ���� ó��(���� �ð����� ��ȸ�� �������� �ʵ��� ó��)
 * */
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vo.BoardVO;

@Service("boardservice")
@Repository
public class BoardServiceImple implements BoardService {
	
	@Autowired
	BoardDAO boardDao;

	// 01. �Խñ۾���
	@Transactional // Ʈ����� ó�� �޼���� ����
	@Override
	public void create(BoardVO vo) throws Exception {
		String title = vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		// *�±׹��� ó�� (< ==> &lt; > ==> &gt;)
		// replace(A, B) A�� B�� ����
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		writer = writer.replace("<", "&lt;");
		writer = writer.replace("<", "&gt;");
		// *���鹮�� ó��
		title = title.replace("  ", "&nbsp;&nbsp;");
		writer = writer.replace("  ", "&nbsp;&nbsp;");
		// *�ٹٲ� ����ó��
		content = content.replace("\n", "<br>");
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		// �Խù� ���
		boardDao.create(vo);
	}

	// 02. �Խñ� �󼼺���
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	// 03. �Խñ� ����
	@Transactional
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
	}

	// 04. �Խñ� ����
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	// 05. �Խñ� ��ü ���
	@Override
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		return boardDao.listAll(start, end, searchOption, keyword);
	}

	// 6. �Խñ� ��ȸ�� ����
	public int increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		// ���ǿ� ����� ��ȸ�ð� �˻�
		// ���ʷ� ��ȸ�� ��� ���ǿ� ����� ���� ���� ������ if���� ���� X
		if (session.getAttribute("update_time" + bno) != null) {
			// ���ǿ��� �о����
		}
		// �ý����� ����ð��� current_time�� ����
		long current_time = System.currentTimeMillis();
		// �����ð��� ��� �� ��ȸ�� ���� ó�� 24*60*60*1000(24�ð�)
		// �ý�������ð� - �����ð� > �����ð�(��ȸ�� ������ �����ϵ��� ������ �ð�)
		if (current_time - update_time > 5 * 1000) {
			//boardDao.increaseViewcnt(bno, session);
			// ���ǿ� �ð��� ����:"update_time_+bno�� �ٸ� ������ �ߺ������ʰ� ����� ��
			session.setAttribute("update_time_" + bno, current_time);
		}
		return boardDao.increaseViewcnt(bno, session);
	}

	// 7. �Խñ� ���ڵ� ����
	public int countArticle(String searchOption, String keyword) throws Exception {
		return boardDao.countArticle(searchOption, keyword);
	}
	
	public BoardVO readID(String writer) throws Exception{ //writer
		return boardDao.readID(writer);
	}
	
}
