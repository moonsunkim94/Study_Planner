package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vo.BoardVO;

@Repository // ���� Ŭ������ dao bean���� ���
public class BoardDAOImple implements BoardDAO {
	
	@Autowired
	SqlSession SqlSession;

	// 1. �Խñ� �ۼ�
	public void create(BoardVO vo) throws Exception {
		SqlSession.insert("board.insert", vo);
	}

	// 2. �Խñ� �󼼺���
	public BoardVO read(int bno) throws Exception {
		return SqlSession.selectOne("board.view", bno);
	}

	// 3. �Խñ� ����
	public void update(BoardVO vo) throws Exception {
		SqlSession.selectOne("board.updateArticle", vo);
	}
	
	// 4. �Խñ� ����
	public void delete(int bno) throws Exception {
		SqlSession.delete("board.deleteArticle", bno);
	}

	// 5. �Խñ� ��ü ���
	public List<BoardVO> listAll(int start, int end,String searchOption, String keyword) throws Exception {
		// �˻��ɼ�, Ű���� �ʿ� ����
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// between #{start}, #{end}�� �Էµ� ���� �ʿ�
		map.put("start",start);
		map.put("end",end);
		return SqlSession.selectList("board.listAll", map);
	}

	// �Խñ� ��ȸ�� ����
	public int increaseViewcnt(int bno, HttpSession session) throws Exception {
		return SqlSession.update("board.increaseViewcnt", bno);
	}
	
	// 7. �Խñ� ���ڵ� ����
	public int countArticle(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return SqlSession.selectOne("board.countArticle", map);
	}
		
	// 99. �Խ��� �̸� �����
	public BoardVO readID(String writer) throws Exception{
	
		return SqlSession.selectOne("board.readID",writer);
	}
	
	
}
