package inheritance;

//자동차정보(모델명, 소유자명)를 저장하기 위한 클래스
// => 클래스 작성시 상속받은 부모클래스가 없는 경우 무조건 Object 클래스를 상속받아 사용
// => 모든 Java 클래스는 Object 클래스를 상속받기 때문에 Object 클래스의 메소드 호출 가능
//Object 클래스 : 모든 Java 클래스의 부모클래스 - Root Class
// => Object 클래스로 생성된 참조변수에는 모든 Java 클래스로 생성된 객체 저장 가능
// => 메소드의 반환형이 Object 클래스인 경우 메소드에서 모든 Java 클래스로 생성된 객체 반환 가능
public class Car /* extends Object */ {
	private String modelName;
	private String userName;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(String modelName, String userName) {
		super();
		this.modelName = modelName;
		this.userName = userName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//Object 클래스의 toString() 메소드를 오버라이딩 선언
	// => Object 클래스의 toString() 메소드는 숨겨지고 자식클래스의 toString() 메소드만 사용
	// => 객체의 필드값을 문자열로 반환하는 명령 작성
	@Override
	public String toString() {
		return "modelName : "+modelName+", userName : "+userName;
	}
}












