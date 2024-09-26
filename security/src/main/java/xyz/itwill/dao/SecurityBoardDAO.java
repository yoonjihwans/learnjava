package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.SecurityBoard;

public interface SecurityBoardDAO {
	int insertSecurityBoard(SecurityBoard board);
	int updateSecurityBoard(SecurityBoard board);
	int deleteSecurityBoard(int num);
	SecurityBoard selectSecurityBoardByNum(int num);
	int selectSecurityBoardCount(Map<String, Object> map);
	List<SecurityBoard> selectSecurityBoardList(Map<String, Object> map);
}
