package inheritance;

public class StudentExample {
	private int id; // 학번
	private String name; // 이름
	private int kor; // 국어 점수
	private int eng; // 영어 점수
	private int math; // 수학점수

	private static int avg; // 총점

	public StudentExample() {
		// TODO Auto-generated constructor stub
	}

	public StudentExample(int id, String name, int kor, int eng, int math) {
		super();
		this.id = id;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public static int getAvg() {
		return avg;
	}

	public static void setAvg(int avg) {
		StudentExample.avg = avg;
	}
	
	public void tot() {
		 avg = kor + eng + math;
	}
	
	public static int totds() {
		return avg;
	}
	
	public void display() { //호출 메소드
		System.out.println(name + "님의 성적은" + "국어 = "+ kor+ " 영어 = " + eng + "수학 = " + math + "입니다");
		
	}

}
