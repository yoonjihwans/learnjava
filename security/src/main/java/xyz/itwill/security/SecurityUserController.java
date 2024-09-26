package xyz.itwill.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityAuth;
import xyz.itwill.dto.SecurityUser;
import xyz.itwill.service.SecurityUserService;

@Controller
@RequiredArgsConstructor
public class SecurityUserController {
	private final SecurityUserService securityUserService;
	private final PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/member/add", method = RequestMethod.GET)
	@ResponseBody
	public String addUser() {
		SecurityUser user1=new SecurityUser("abc123", passwordEncoder.encode("123456"), "홍길동", "abc@itwill.xyz", "1", null);
		SecurityUser user2=new SecurityUser("opq456", passwordEncoder.encode("123456"), "임꺽정", "opq@itwill.xyz", "1", null);
		SecurityUser user3=new SecurityUser("xyz789", passwordEncoder.encode("123456"), "전우치", "xyz@itwill.xyz", "1", null);
		
		securityUserService.addSecurityUser(user1);
		securityUserService.addSecurityUser(user2);
		securityUserService.addSecurityUser(user3);
		
		SecurityAuth auth1=new SecurityAuth("abc123", "ROLE_USER");
		SecurityAuth auth2=new SecurityAuth("opq456", "ROLE_USER");
		SecurityAuth auth3=new SecurityAuth("opq456", "ROLE_MANAGER");
		SecurityAuth auth4=new SecurityAuth("xyz789", "ROLE_ADMIN");
		
		securityUserService.addSecurityAuth(auth1);
		securityUserService.addSecurityAuth(auth2);
		securityUserService.addSecurityAuth(auth3);
		securityUserService.addSecurityAuth(auth4);
		
		return "success";
	}
}
