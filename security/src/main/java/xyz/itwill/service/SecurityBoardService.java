package xyz.itwill.service;

import java.util.Map;

import xyz.itwill.dto.SecurityBoard;

public interface SecurityBoardService {
	void addSecurityBoard(SecurityBoard board);
	void modifySecurityBoard(SecurityBoard board);
	void removeSecurityBoard(int num);
	SecurityBoard getSecurityBoardByNum(int num);
	Map<String, Object> getSecurityBoardList(Map<String, Object> map);
}
