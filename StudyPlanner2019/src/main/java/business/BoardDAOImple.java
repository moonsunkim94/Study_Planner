package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vo.BoardVO;

@Repository // 현재 클래스를 dao bean으로 등록
public class BoardDAOImple implements BoardDAO {
	
	@Autowired
	SqlSession SqlSession;

	// 1. 게시글 작성
	public void create(BoardVO vo) throws Exception {
		SqlSession.insert("board.insert", vo);
	}

	// 2. 게시글 상세보기
	public BoardVO read(int bno) throws Exception {
		return SqlSession.selectOne("board.view", bno);
	}

	// 3. 게시글 수정
	public void update(BoardVO vo) throws Exception {
		SqlSession.selectOne("board.updateArticle", vo);
	}
	
	// 4. 게시글 삭제
	public void delete(int bno) throws Exception {
		SqlSession.delete("board.deleteArticle", bno);
	}

	// 5. 게시글 전체 목록
	public List<BoardVO> listAll(int start, int end,String searchOption, String keyword) throws Exception {
		// 검색옵션, 키워드 맵에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		// between #{start}, #{end}에 입력될 값을 맵에
		map.put("start",start);
		map.put("end",end);
		return SqlSession.selectList("board.listAll", map);
	}

	// 게시글 조회수 증가
	public int increaseViewcnt(int bno, HttpSession session) throws Exception {
		return SqlSession.update("board.increaseViewcnt", bno);
	}
	
	// 7. 게시글 레코드 갯수
	public int countArticle(String searchOption, String keyword) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		
		return SqlSession.selectOne("board.countArticle", map);
	}
		
	// 99. 게시판 이름 남기기
	public BoardVO readID(String writer) throws Exception{
	
		return SqlSession.selectOne("board.readID",writer);
	}
	
	
}
