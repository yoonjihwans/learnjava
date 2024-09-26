package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityBoard;
import xyz.itwill.mapper.SecurityBoardMapper;

@Repository
@RequiredArgsConstructor
public class SecurityBoardDAOImpl implements SecurityBoardDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertSecurityBoard(SecurityBoard board) {
		return sqlSession.getMapper(SecurityBoardMapper.class).insertSecurityBoard(board);
	}

	@Override
	public int updateSecurityBoard(SecurityBoard board) {
		return sqlSession.getMapper(SecurityBoardMapper.class).updateSecurityBoard(board);
	}

	@Override
	public int deleteSecurityBoard(int num) {
		return sqlSession.getMapper(SecurityBoardMapper.class).deleteSecurityBoard(num);
	}

	@Override
	public SecurityBoard selectSecurityBoardByNum(int num) {
		return sqlSession.getMapper(SecurityBoardMapper.class).selectSecurityBoardByNum(num);
	}

	@Override
	public int selectSecurityBoardCount(Map<String, Object> map) {
		return sqlSession.getMapper(SecurityBoardMapper.class).selectSecurityBoardCount(map);
	}

	@Override
	public List<SecurityBoard> selectSecurityBoardList(Map<String, Object> map) {
		return sqlSession.getMapper(SecurityBoardMapper.class).selectSecurityBoardList(map);
	}

}
