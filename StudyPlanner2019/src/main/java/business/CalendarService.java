package business;

import java.util.ArrayList;

import vo.CalendarVO;

public interface CalendarService {

	ArrayList<CalendarVO> selectList(CalendarVO vo);

	CalendarVO selectOne(CalendarVO vo);

	int insert(CalendarVO vo);

	int update(CalendarVO vo);

	int delete(CalendarVO vo);

	int listCount(CalendarVO vo);

}