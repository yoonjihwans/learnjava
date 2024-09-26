package xyz.itwill.dao;

import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;

public interface SecurityUserDAO {
	int insertSecurityUser(SecurityUser user);
	int insertSecurityAuth(SecurityAuth auth);
	SecurityUser selectSecurityUserByUserid(String userid);
}
