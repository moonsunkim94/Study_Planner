package business;

/**
 * 게시글 쓰기처리(태그문자 처리, 공백문자 처리, 중바꿈 문자처리)
 * 게시글 조회수 증가 처리(일정 시간동안 조회수 증가하지 않도록 처리)
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

	// 01. 게시글쓰기
	@Transactional // 트랜잭션 처리 메서드로 설정
	@Override
	public void create(BoardVO vo) throws Exception {
		String title = vo.getTitle();
		String content = vo.getContent();
		String writer = vo.getWriter();
		// *태그문자 처리 (< ==> &lt; > ==> &gt;)
		// replace(A, B) A를 B로 변경
		title = title.replace("<", "&lt;");
		title = title.replace("<", "&gt;");
		writer = writer.replace("<", "&lt;");
		writer = writer.replace("<", "&gt;");
		// *공백문자 처리
		title = title.replace("  ", "&nbsp;&nbsp;");
		writer = writer.replace("  ", "&nbsp;&nbsp;");
		// *줄바꿈 문자처리
		content = content.replace("\n", "<br>");
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		// 게시물 등록
		boardDao.create(vo);
	}

	// 02. 게시글 상세보기
	@Override
	public BoardVO read(int bno) throws Exception {
		return boardDao.read(bno);
	}

	// 03. 게시글 수정
	@Transactional
	@Override
	public void update(BoardVO vo) throws Exception {
		boardDao.update(vo);
	}

	// 04. 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		boardDao.delete(bno);
	}

	// 05. 게시글 전체 목록
	@Override
	public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception {
		return boardDao.listAll(start, end, searchOption, keyword);
	}

	// 6. 게시글 조회수 증가
	public int increaseViewcnt(int bno, HttpSession session) throws Exception {
		long update_time = 0;
		// 세션에 저장된 조회시간 검색
		// 최초로 조회할 경우 세션에 저장된 값이 없기 때문에 if문은 실행 X
		if (session.getAttribute("update_time" + bno) != null) {
			// 세션에서 읽어오기
		}
		// 시스템의 현재시간을 current_time에 저장
		long current_time = System.currentTimeMillis();
		// 일정시간이 경과 후 조회수 증가 처리 24*60*60*1000(24시간)
		// 시스템현재시간 - 열람시간 > 일정시간(조회수 증가가 가능하도록 지정한 시간)
		if (current_time - update_time > 5 * 1000) {
			//boardDao.increaseViewcnt(bno, session);
			// 세션에 시간을 저장:"update_time_+bno는 다른 변수와 중복되지않게 명명한 것
			session.setAttribute("update_time_" + bno, current_time);
		}
		return boardDao.increaseViewcnt(bno, session);
	}

	// 7. 게시글 레코드 갯수
	public int countArticle(String searchOption, String keyword) throws Exception {
		return boardDao.countArticle(searchOption, keyword);
	}
	
	public BoardVO readID(String writer) throws Exception{ //writer
		return boardDao.readID(writer);
	}
	
}
