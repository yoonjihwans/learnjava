package xyz.itwill.bean;

//JavaBean : useBean 태그 사용시 WAS 프로그램에 의해 생성되는 Java 객체
// => JSP 문서 요청시 전달된 값을 필드에 저장하기 위한 객체
// => 전달값의 이름과 같은 이름의 필드에 전달값이 자동 저장

//회원정보를 저장하기 위한 클래스 - JavaBean >> DTO
public class HewonBean {
	private String name;
	private String phone;
	private String address;
	
	public HewonBean() {
		// TODO Auto-generated constructor stub
	}

	public HewonBean(String name, String phone, String address) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}