package xyz.itwill.record;

public class MemberTwoApp {
	public static void main(String[] args) {
		MemberTwo membeR = new MemberTwo("abc123", "홍길동", "abc@2323sf");
		
		System.out.println("아이디 =" + membeR.id());
		System.out.println("이름 =" + membeR.name());
		System.out.println("이메일 =" + membeR.email());
	}

}
