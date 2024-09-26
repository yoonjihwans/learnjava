package xyz.itwill.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.SecurityUserDAO;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;

@Service
@RequiredArgsConstructor
public class SecurityUserServiceImpl implements SecurityUserService {
	private final SecurityUserDAO securityUserDAO;

	@Override
	public void addSecurityUser(SecurityUser user) {
		securityUserDAO.insertSecurityUser(user);
	}

	@Override
	public void addSecurityAuth(SecurityAuth auth) {
		securityUserDAO.insertSecurityAuth(auth);
	}

	@Override
	public SecurityUser getSecurityUserByUserid(String userid) {
		return securityUserDAO.selectSecurityUserByUserid(userid);
	}
}
