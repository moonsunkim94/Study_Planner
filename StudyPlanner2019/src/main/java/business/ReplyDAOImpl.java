package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Inject
	//@Autowired
	SqlSession sqlSession;

	// 1. ´ñ±Û ÀÔ·Â
	@Override
	public void create(ReplyVO vo) {
		sqlSession.insert("reply.insertReply", vo);
	}

	// 2. ´ñ±Û ¸ñ·Ï
	@Override
	public List<ReplyVO> list(Integer bno, int start, int end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("reply.listReply", map);
	}

	// 3. ´ñ±Û »ó¼¼º¸±â
	@Override
	public ReplyVO detail(Integer rno) {
		return sqlSession.selectOne("reply.detailReply", rno);
	}

	// 4. ´ñ±Û ¼öÁ¤
	@Override
	public void update(ReplyVO vo) {
		sqlSession.update("reply.updateReply", vo);
	}

	// 5. ´ñ±Û »èÁ¦
	@Override
	public void delete(Integer rno) {
		sqlSession.delete("reply.deleteReply", rno);
	}

	// 6. ´ñ±Û °¹¼ö
	@Override
	public int count(Integer bno) {
		return sqlSession.selectOne("reply.countReply", bno);
	}

}
