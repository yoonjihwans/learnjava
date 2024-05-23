package xyz.itwill.record;

public class MemberOne {
	private String id;
	private String name;
	private String email;
	
	public MemberOne() {
		// TODO Auto-generated constructor stub
	}

	public MemberOne(String id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
	    return "아이디 = " + id +" 이름 = "+name +"이메일 = " + email;
	}

}
