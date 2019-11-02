package business;

import java.util.ArrayList;

import vo.GgraphVO;

public interface GgraphService {

	ArrayList<GgraphVO> selectList(GgraphVO vo);

	GgraphVO selectOne(GgraphVO vo);

	int insert(GgraphVO vo);

	int update(GgraphVO vo);

	int delete(GgraphVO vo);

	String selectSubject(GgraphVO vo);
	
	ArrayList<GgraphVO>selectgraph(GgraphVO vo);

}
