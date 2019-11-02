package business;

import java.util.List;

import javax.servlet.http.HttpSession;

import vo.BoardVO;

public interface BoardDAO {
	// 01. 게시글 작성
	public void create(BoardVO vo) throws Exception;
	// 02. 게시글 상세보기
	public BoardVO read(int bno) throws Exception;
	// 03. 게시글 수정
	public void update(BoardVO vo) throws Exception;
	// 04. 게시글 삭제
	public void delete(int bno) throws Exception;
	// 05. 게시글 전체 목록 ==> 검색옵션, 키워드 매개변수 추가
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
	// 6. 게시글 조회 평가
	public int increaseViewcnt(int bno, HttpSession session) throws Exception;
	// 7. 게시글 레코드 갯수 메서드 추가
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	
	// 99. 게시판 글쓰기 이름 불러오기
	public BoardVO readID(String writer) throws Exception;
}
