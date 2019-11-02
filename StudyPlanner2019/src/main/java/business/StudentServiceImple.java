package business;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vo.StudentVO;

@Service("student")
public class StudentServiceImple implements StudentService {
	@Autowired
	private SqlSession dao;
	private static final String NS="green.mapper.StudentMapper.";

	@Override
	public ArrayList<StudentVO>selectList(){
		return (ArrayList)dao.selectList(NS+"selectList");
	}

	@Override
	public StudentVO selectOne(StudentVO vo) {
		return dao.selectOne(NS+"selectDetail", vo);
	}
	
	@Override
	public StudentVO selectSeq(StudentVO vo) {
		return dao.selectOne(NS+"selectSeq", vo);
	}

	@Override
	public int insert(StudentVO vo) {
		return dao.insert(NS+"insertStudent", vo);
	}

	@Override
	public int update(StudentVO vo) {
		return dao.update(NS+"updateStudent", vo);
	}

	@Override
	public int delete(StudentVO vo) {
		return dao.delete(NS+"deleteStudent", vo);
	}
	
	@Override
	public int updateConfirm(StudentVO vo) {
		return dao.update(NS+"updateConfirm", vo);
	}
	
	// 로그인 및 비번찾기 -------------------------------------┐
	@Override // 로그인 체크
	public StudentVO loginCheck(StudentVO vo) {
		return dao.selectOne(NS+"loginCheck", vo);
	}
	
	@Override // 패스워드 찾을 계정이 있는지 확인
	public StudentVO selectPassword(StudentVO vo) {
		return dao.selectOne(NS+"selectPassword",vo);
	}
	
	@Override // 임시비번으로 변경해주는 ..
	public int updateTemPassword(StudentVO vo) {
		return dao.update(NS+"updateTemPassword", vo);
	}
	
	public StudentVO selectAfterPW(StudentVO vo) {
		// 임시비번 변경 후 메일로 발송하기전..변경된 임시비번 조회..
		return dao.selectOne(NS+"selectAfterPW", vo);
	}
	//--------------------------------------------------┛
}
