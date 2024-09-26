package xyz.itwill.dao;

import java.util.List;

import xyz.itwill.dto.SecurityReply;

public interface SecurityReplyDAO {
	int insertSecurityReply(SecurityReply reply);
	List<SecurityReply> selectSecurityReplyList(int boardNum);
}
