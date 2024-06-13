package xyz.itwill.student;

//DTO(Data Transfer Object) 클래스 : 데이타를 전달하기 위한 객체를 생성하기 위한 클래스
// => DAO 클래스의 메소드를 작성할 때 필요한 값들을 매개변수로 작성하여 객체를 전달받거나 
//메소드 호출결과를 객체로 생성하여 반환하기 위해 DTO 클래스 사용
// => 테이블의 하나의 행을 객체로 생성하기 위한 클래스 
// => DTO 클래스는 테이블의 컬럼값을 저장할 수 있는 필드와 Getter 메소드, Setter 메소드 작성
// => DTO 클래스의 필드명은 테이블의 컬럼명과 동일하게 작성하는 권장

/*
이름       널?       유형            
-------- -------- ------------- 
NO       NOT NULL NUMBER(4)     
NAME              VARCHAR2(50)  
PHONE             VARCHAR2(20)  
ADDRESS           VARCHAR2(100) 
BIRTHDAY          DATE 
*/

//STUDENT 테이블에 저장된 하나의 행(학생정보)을 객체로 생성하여 전달하기 위한 클래스
public class StudentDTO {
	private int no;
	private String name;
	private String phone;
	private String address;
	private String birthday;

	//[Ctrl]+[Space] >> Constructor
	public StudentDTO() {
		// TODO Auto-generated constructor stub
	}

	//[Alt]+[Shift]+[S] >> 팝업메뉴 >> [O] >> 생성자 생성창 >> 필드 선택 >> Generate
	public StudentDTO(int no, String name, String phone, String address, String birthday) {
		super();
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
	}

	//[Alt]+[Shift]+[S] >> 팝업메뉴 >> [R] >> Getter & Setter 생성창 >> 필드 선택 >> Generate
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}