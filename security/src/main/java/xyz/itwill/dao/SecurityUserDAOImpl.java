package xyz.itwill.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;
import xyz.itwill.mapper.SecurityUserMapper;

@Repository
@RequiredArgsConstructor
public class SecurityUserDAOImpl implements SecurityUserDAO {
	private final SqlSession sqlSession;
	
	public int insertSecurityUser(SecurityUser user) {
		return sqlSession.getMapper(SecurityUserMapper.class).insertSecurityUser(user);
	}

	@Override
	public int insertSecurityAuth(SecurityAuth auth) {
		return sqlSession.getMapper(SecurityUserMapper.class).insertSecurityAuth(auth);
	}

	@Override
	public SecurityUser selectSecurityUserByUserid(String userid) {
		return sqlSession.getMapper(SecurityUserMapper.class).selectSecurityUserByUserid(userid);
	}

}
