package xyz.itwill09.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dao.PointBoardDAO;
import xyz.itwill09.dao.PointUserDAO;
import xyz.itwill09.dto.PointBoard;
import xyz.itwill09.dto.PointUser;

@Service
@RequiredArgsConstructor
public class PointBoardServiceImpl implements PointBoardService {
	private final PointUserDAO pointUserDAO;
	private final PointBoardDAO pointBoardDAO;
	
	//매개변수로 게시글을 전달받아 POINT_BOARD 테이블의 행으로 삽입하고 게시글의 작성자에 대한
	//회원정보를 POINT_USER 테이블에서 검색하여 회원정보를 반환하는 메소드
	// => POINT_USER 테이블에서 게시글 작성자에 대한 행(회원정보)의 POINT 컬럼값이 증가되도록 변경 처리
	@Transactional
	public PointUser addPointBoard(PointBoard board) {
		pointBoardDAO.insertPointBoard(board);
		
		//게시글 작성자에 대한 회원정보가 없는 경우 인위적으로 예외 발생
		if(pointUserDAO.selectPointUser(board.getWriter()) == null) {
			throw new RuntimeException("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updatePlusPointUser(board.getWriter());
		
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//매개변수로 글번호을 전달받아 POINT_BOARD 테이블의 행을 삭제하고 삭제된 게시글의 작성자에 
	//대한 회원정보를 POINT_USER 테이블에서 검색하여 회원정보를 반환하는 메소드
	// => POINT_USER 테이블에서 게시글의 작성자에 대한 행(회원정보)의 POINT 컬럼값이 감소되도록 변경 처리
	@Override
	//@Transactional : TransactionManager 객체의 의해 트렌젝션 관리 기능을 제공하는 어노테이션
	//rollbackFor 속성 : 롤백 처리될 예외 클래스의 Class 객체를 속성값으로 설정 - 생략 가능
	@Transactional(rollbackFor = Exception.class)	
	public PointUser removePointBoard(int num) {
		PointBoard board=pointBoardDAO.selectPointBoard(num);
		
		if(board == null) {
			throw new RuntimeException("게시글이 존재하지 않습니다.");
		}
		
		pointBoardDAO.deletePointBoard(num);
		
		if(pointUserDAO.selectPointUser(board.getWriter()) == null) {
			throw new RuntimeException("게시글 작성자가 존재하지 않습니다.");
		}
		
		pointUserDAO.updateMinusPointUser(board.getWriter());
		
		return pointUserDAO.selectPointUser(board.getWriter());
	}

	//POINT_BOARD 테이블에 저장된 모든 행을 검색해 게시글 목록을 반환하는 메소드
	@Override
	public List<PointBoard> getPointBoardList() {
		return pointBoardDAO.selectPointBoardList();
	}

}