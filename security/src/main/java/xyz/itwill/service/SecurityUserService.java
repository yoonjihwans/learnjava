package xyz.itwill.service;

import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;

public interface SecurityUserService {
	void addSecurityUser(SecurityUser user);
	void addSecurityAuth(SecurityAuth auth);
	SecurityUser getSecurityUserByUserid(String userid);
}
