package business;

import java.util.List;

import javax.servlet.http.HttpSession;

import vo.BoardVO;

public interface BoardDAO {
	// 01. �Խñ� �ۼ�
	public void create(BoardVO vo) throws Exception;
	// 02. �Խñ� �󼼺���
	public BoardVO read(int bno) throws Exception;
	// 03. �Խñ� ����
	public void update(BoardVO vo) throws Exception;
	// 04. �Խñ� ����
	public void delete(int bno) throws Exception;
	// 05. �Խñ� ��ü ��� ==> �˻��ɼ�, Ű���� �Ű����� �߰�
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
	// 6. �Խñ� ��ȸ ��
	public int increaseViewcnt(int bno, HttpSession session) throws Exception;
	// 7. �Խñ� ���ڵ� ���� �޼��� �߰�
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	
	// 99. �Խ��� �۾��� �̸� �ҷ�����
	public BoardVO readID(String writer) throws Exception;
}
