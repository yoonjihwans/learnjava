package xyz.itwill.util;

//Comparable 인터페이스 : 객체 필드값을 비교한 결과값을 반환하는 추상메소드가 작성된 인터페이스
// => compareTo() 메소드를 오버라이딩 선언하여 자식클래스의 객체(this) 필드값을 매개변수로
//전달받은 객체 필드값과 비교하여 양수, 음수, 0 중 하나를 반환하는 명령 작성
// => 객체의 필드값을 비교하기 위한 클래스가 반드시 상속받아야 되는 인터페이스 

//학생정보(학번, 이름)를 저장하기 위한 클래스
// => List 객체에 저장된 학생정보를 비교하여 정렬하기 위해 Comparable 인터페이스를 상속받아 작성
public class Student implements Comparable<Student> {
	private int num;
	private String name;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "{학번 = "+num+", 이름 = "+name+"}";
	}

	@Override
	public int compareTo(Student o) {
		//학번(정수값)을 빼기(-) 연산 처리하여 결과값(양수, 음수, 0)을 반환 - 학번 정렬 처리
		//return this.num-o.num;//오름차순 정렬
		//return o.num-this.num;//내림차순 정렬

		//이름(문자열)은 String 클래스의 compareTo() 메소드를 호출하여 결과값(양수, 음수, 0)을
		//제공받아 반환 - 이름 정렬 처리
		//return this.name.compareTo(o.name);//오름차순 이름
		return o.name.compareTo(this.name);//내림차순 이름
	}
}