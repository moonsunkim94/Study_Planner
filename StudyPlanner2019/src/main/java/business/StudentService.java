package business;

import java.util.ArrayList;

import vo.StudentVO;

public interface StudentService {

	ArrayList<StudentVO> selectList();

	StudentVO selectOne(StudentVO vo);

	int insert(StudentVO vo);

	int update(StudentVO vo);

	int delete(StudentVO vo);

	int updateConfirm(StudentVO vo);

	StudentVO selectSeq(StudentVO vo);

	// 로그인 및 비번찾기 --------------------------------------------
	StudentVO loginCheck(StudentVO vo); // 로그인

	StudentVO selectPassword(StudentVO vo); // 패스워드계정찾는 ..

	int updateTemPassword(StudentVO vo); // 임시비번으로 변경

	StudentVO selectAfterPW(StudentVO vo); //임시비번으로 변경된 거 조회
	//---------------------------------------------------------┘

}