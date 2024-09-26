package xyz.itwill.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.SecurityBoardDAO;
import xyz.itwill.dao.SecurityReplyDAO;
import xyz.itwill.dao.SecurityUserDAO;
import xyz.itwill.dto.SecurityReply;

@Service
@RequiredArgsConstructor
public class SecurityReplyServiceImpl implements SecurityReplyService {
	private final SecurityReplyDAO securityReplyDAO;
	private final SecurityUserDAO securityUserDAO;
	private final SecurityBoardDAO securityBoardDAO;

	@Override
	public void addSecurityReply(SecurityReply reply) {
		if(securityUserDAO.selectSecurityUserByUserid(reply.getWriter()) == null) {
			throw new RuntimeException("작성자를 찾을 수 없습니다.");
		}
		
		securityReplyDAO.insertSecurityReply(reply);
	}

	@Override
	public List<SecurityReply> getSecurityReplyList(int boardNum) {
		if(securityBoardDAO.selectSecurityBoardByNum(boardNum) == null) {
			throw new RuntimeException("게시글을 찾을 수 없습니다.");
		}
			
		return securityReplyDAO.selectSecurityReplyList(boardNum);
	}
}
