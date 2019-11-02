package business;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.CalendarVO;

@Service("calendar")
public class CalendarServiceImple implements CalendarService{
	@Autowired
	private SqlSession dao;
	private static final String NS="green.mapper.CalendarMapper.";

	@Override
	public ArrayList<CalendarVO>selectList(CalendarVO vo){
		return (ArrayList)dao.selectList(NS+"selectList", vo);
	}
	
	@Override
	public int listCount(CalendarVO vo) {
		return dao.selectOne(NS+"listCount", vo);
	}

	@Override
	public CalendarVO selectOne(CalendarVO vo) {
		return dao.selectOne(NS+"selectDetail", vo);
	}

	@Override
	public int insert(CalendarVO vo) {
		return dao.insert(NS+"insertCalendar", vo);
	}

	@Override
	public int update(CalendarVO vo) {
		return dao.update(NS+"updateCalendar", vo);
	}

	@Override
	public int delete(CalendarVO vo) {
		return dao.delete(NS+"deleteCalendar", vo);
	}
}
