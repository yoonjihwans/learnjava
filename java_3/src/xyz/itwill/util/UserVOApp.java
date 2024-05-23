package xyz.itwill.util;

import java.util.HashSet;
import java.util.Set;

public class UserVOApp {
	public static void main(String[] args) {
		Set<UserVO> set=new HashSet<UserVO>();
		
		set.add(new UserVO("abc123", "홍길동", "abc@itwill.xyz"));
		set.add(new UserVO("opq456", "임꺽정", "opq@itwill.xyz"));
		set.add(new UserVO("xyz789", "전우치", "xyz@itwill.xyz"));
		
		System.out.println("set = "+set);
		System.out.println("==============================================================");
		set.add(new UserVO("abc123", "홍길동", "abc@itwill.xyz"));
		System.out.println("set = "+set);
		System.out.println("==============================================================");
	}
}