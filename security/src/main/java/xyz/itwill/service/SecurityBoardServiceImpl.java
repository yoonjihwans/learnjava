package xyz.itwill.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.SecurityBoardDAO;
import xyz.itwill.dao.SecurityUserDAO;
import xyz.itwill.dto.SecurityBoard;
import xyz.itwill.util.Pager;

@Service
@RequiredArgsConstructor
public class SecurityBoardServiceImpl implements SecurityBoardService {
	private final SecurityUserDAO securityUserDAO;
	private final SecurityBoardDAO securityBoardDAO;

	@Transactional
	@Override
	public void addSecurityBoard(SecurityBoard board) {
		if(securityUserDAO.selectSecurityUserByUserid(board.getWriter()) == null) {
			throw new RuntimeException("게시글 작성자를 찾을 수 없습니다.");
		}
		securityBoardDAO.insertSecurityBoard(board);
	}

	@Transactional
	@Override
	public void modifySecurityBoard(SecurityBoard board) {
		if(securityUserDAO.selectSecurityUserByUserid(board.getWriter()) == null) {
			throw new RuntimeException("게시글 작성자를 찾을 수 없습니다.");
		}
		if(securityBoardDAO.selectSecurityBoardByNum(board.getNum()) == null) {
			throw new RuntimeException("변경하고자 하는 게시글을 찾을 수 없습니다.");
		}
		securityBoardDAO.updateSecurityBoard(board);
	}

	@Transactional
	@Override
	public void removeSecurityBoard(int num) {
		if(securityBoardDAO.selectSecurityBoardByNum(num) == null) {
			throw new RuntimeException("삭제 하고자 하는 게시글을 찾을 수 없습니다.");
		}
		securityBoardDAO.deleteSecurityBoard(num);
	}

	@Override
	public SecurityBoard getSecurityBoardByNum(int num) {
		SecurityBoard board=securityBoardDAO.selectSecurityBoardByNum(num);
		if(board == null) {
			throw new RuntimeException("게시글을 찾을 수 없습니다.");
		}
		return board;
	}

	@Override
	public Map<String, Object> getSecurityBoardList(Map<String, Object> map) {
		int pageNum=1;
		if(map.get("pageNum") != null && !map.get("pageNum").equals("")) {
			pageNum=Integer.parseInt((String)map.get("pageNum"));
		}
		
		int pageSize=5;
		if(map.get("pageSize") != null && !map.get("pageSize").equals("")) {
			pageSize=Integer.parseInt((String)map.get("pageSize"));
		}
		
		int totalBoard=securityBoardDAO.selectSecurityBoardCount(map);
		
		int blockSize=5;
		
		Pager pager=new Pager(pageNum, pageSize, totalBoard, blockSize);
		
		map.put("startRow", pager.getStartRow());
		map.put("endRow", pager.getEndRow());
		List<SecurityBoard> boardList=securityBoardDAO.selectSecurityBoardList(map);
		
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("pager", pager);
		result.put("securityBoardList", boardList);
		
		return result;
	}
}
