package xyz.itwill.record;

public class MemberOneApp {
	public static void main(String[] args) {
		MemberOne member = new MemberOne("abc123", "홍길동", "abc@dsdsd23");
		
		System.out.println("아이디 =" + member.getId());
		System.out.println("이름 = " + member.getName());
		System.out.println("이메일 = "+ member.getEmail());
	}

}
