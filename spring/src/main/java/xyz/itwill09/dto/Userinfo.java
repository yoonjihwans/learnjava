package xyz.itwill09.dto;

import lombok.Data;

@Data
public class Userinfo {
	private String userid;
	private String password;
	private String name;
	private String email;
	private int auth;
}
