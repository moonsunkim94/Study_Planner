package business;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.GgraphVO;


@Service("ggraph")
public class GgraphServiceImple implements GgraphService{

	
	@Autowired
	private SqlSession dao;
	private static final String NS="green.mapper.GgraphMapper.";

	@Override
	public ArrayList<GgraphVO>selectList(GgraphVO vo){
		return (ArrayList)dao.selectList(NS+"selectList", vo);
	}
	
	@Override
	public ArrayList<GgraphVO>selectgraph(GgraphVO vo){
		return (ArrayList)dao.selectList(NS+"selectgraph", vo);
	}

	@Override
	public GgraphVO selectOne(GgraphVO vo) {
		return dao.selectOne(NS+"selectDetail", vo);
	}
	
	@Override
	public String selectSubject(GgraphVO vo){
		return dao.selectList(NS+"selectsubject", vo).toString();
	}

	@Override
	public int insert(GgraphVO vo) {
		return dao.insert(NS+"insertGgraph", vo);
	}

	@Override
	public int update(GgraphVO vo) {
		return dao.update(NS+"updateGgraph", vo);
	}

	@Override
	public int delete(GgraphVO vo) {
		return dao.delete(NS+"deleteGgraph", vo);
	}
	
	
}
