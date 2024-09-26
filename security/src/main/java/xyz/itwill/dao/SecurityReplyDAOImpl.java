package xyz.itwill.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityReply;
import xyz.itwill.mapper.SecurityReplyMapper;

@Repository
@RequiredArgsConstructor
public class SecurityReplyDAOImpl implements SecurityReplyDAO {
	private final SqlSession sqlSession;

	@Override
	public int insertSecurityReply(SecurityReply reply) {
		return sqlSession.getMapper(SecurityReplyMapper.class).insertSecurityReply(reply);
	}

	@Override
	public List<SecurityReply> selectSecurityReplyList(int boardNum) {
		return sqlSession.getMapper(SecurityReplyMapper.class).selectSecurityReplyList(boardNum);
	}
}
