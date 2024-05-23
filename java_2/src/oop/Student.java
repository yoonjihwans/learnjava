package oop;

//static : 객체가 아닌 클래스로 필드 또는 메소드에 접근하여 사용하기 위한 기능을 제공하는 키워드(제한자) 
// => 클래스(내부클래스), 필드, 메소드 작성시 사용하는 제한자

//제한자(Modifier) : 특별한 기능(제한)을 제공하는 키워드
// => Access Modifier(private, package, protected, public), static, final, abstract

//학생정보(학번, 이름, 국어, 영어, 총점)를 저장하기 위한 클래스
public class Student {
	//인스턴스 필드(Instance Field) : 객체 생성시 객체에 만들어지는 필드로 객체를 사용하여 필드에 접근
	// => 생성자를 사용하여 객체 생성시 필드에 초기값 저장 
	private int num;
	private String name;
	private int kor, eng, tot;
	
	//정적 필드(Static Field) : 클래스 파일(XXX.class)를 읽어 메모리(MethodArea)에 저장될 때 
	//만들지는 필드로 클래스를 사용하여 필드에 접근
	// => 객체 생성전에 클래스에 하나만 만들어지는 필드
	// => 정적 필드는 직접 값을 저장하여 초기화 처리 - 생성자를 사용하여 초기화 미처리
	// => 클래스로 생성된 모든 객체는 정적 필드 사용 가능 - 모든 객체가 값을 공유하여 사용
	private static int total=0; //클래스를 사용해서 호출!!!  public class Student << 이거!!
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(int num, String name, int kor, int eng) { //값을 초기화
		super();
		//매개변수로 전달받은 값을 사용해 필드 초기화 처리
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		
		//매개변수로 전달받은 값을 계산하여 필드 초기화 처리
		// => 총점을 계산하는 메소드 호출하여 필드 초기화 처리 - 생성자에서 메소드 호출 가능
		//tot=kor+eng;
		calcTot();
	}

	//인스턴스 메소드(Instance Method) : this 키워드를 제공받아 사용할 수 있는 메소드로 
	//객체를 사용하여 호출
	// => this 키워드를 사용하여 인스턴스 필드 및 인스턴스 메소드 사용 가능
	// => 정적 필드 및 정적 메소드 사용 가능
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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}
	
	//총점을 계산하여 필드에 저장하는 메소드
	public void calcTot() {
		tot=kor+eng;
	}
	
	public void display() {
		System.out.print("["+name+"]님의 성적 >> ");
		System.out.println("국어 = "+kor+", 영어 = "+eng+", 총점 = "+tot);
	}

	//정적 메소드(Static Method) : this 키워드를 제공받지 않는 메소드로 클래스를 사용하여 호출
	// => this 키워드를 사용할 수 없으므로 인스턴스 필드 및 인스턴스 메소드 사용 불가능
	// => 정적 필드 및 정적 메소드만 사용 가능
	public static int getTotal() {
		return total;
	}

	public static void setTotal(int total) {
		Student.total = total;
	}
}











