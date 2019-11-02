package business;
// 0912 �߰�����
import java.util.List;
import javax.servlet.http.HttpSession;

import vo.BoardVO;

public interface BoardService {
	// 1. �Խñ� �ۼ�
	public void create(BoardVO vo) throws Exception;
	// 2. �Խñ� �󼼺���
	public BoardVO read(int bno) throws Exception;
	// 3. �Խñ� ����
	public void update(BoardVO vo) throws Exception;
	// 4. �Խñ� ����
	public void delete(int bno) throws Exception;
	// 5. �Խñ� ��ü ���
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
	// 6. �Խñ� ��ȸ
	public int increaseViewcnt(int bno, HttpSession session) throws Exception;
	// 7. �Խñ� ���ڵ� ���� �޼��� �߰�
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	// 99.
	public BoardVO readID(String writer) throws Exception;
}
