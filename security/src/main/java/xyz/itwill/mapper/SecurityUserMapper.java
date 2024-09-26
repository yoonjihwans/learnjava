package xyz.itwill.mapper;

import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;

public interface SecurityUserMapper {
	int insertSecurityUser(SecurityUser user);
	int insertSecurityAuth(SecurityAuth auth);
	SecurityUser selectSecurityUserByUserid(String userid);
}
