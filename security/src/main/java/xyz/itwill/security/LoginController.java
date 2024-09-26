package xyz.itwill.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
create table users(username varchar2(100) primary key, password varchar2(100), enabled varchar2(1));

insert into users values('abc123', '123456', '1');
insert into users values('opq456', '123456', '1');
insert into users values('xyz789', '123456', '1');
insert into users values('test000', '123456', '0');
commit;

create table authorities(username varchar2(100), authority varchar2(50)
	    , CONSTRAINT authorities_username_fk foreign key(username) REFERENCES users(username));

create unique index authorities_username_index on authorities(username, authority);    

insert into authorities values('abc123', 'ROLE_USER');
insert into authorities values('opq456', 'ROLE_USER');
insert into authorities values('opq456', 'ROLE_MANAGER');
insert into authorities values('xyz789', 'ROLE_ADMIN');
insert into authorities values('test000', 'ROLE_USER');
commit;
*/

@Controller
public class LoginController {
	@RequestMapping(value = "/csrf", method = RequestMethod.GET)
	public String form() {
		return "csrf";
	}
	
	@RequestMapping(value = "/csrf", method = RequestMethod.POST)
	@ResponseBody
	public String form(@RequestParam String name) {
		System.out.println("name = "+name);
		return "ok";
	}
	
	@RequestMapping(value = "/user_login", method = RequestMethod.GET)
	public String userLogin() {
		return "form_login";
	}
	
	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String accessDenied() {
		return "access_denied";
	}
	
	@RequestMapping(value = "/session_error", method = RequestMethod.GET)
	public String sessionError() {
		return "session_error";
	}
}
