package association;

//클래스와 클래스의 관계 - 상속 관계(is a)와 포함 관계(has a) 
// => UML(Unified Modeling Language)을 사용하여 클래스 다이어그램(Class Diagram)으로 설계
//1.일반화 관계(Generalization) : 상속 관계 - X is a Y
// => 기존 클래스를 상속받아 새로운 클래스를 작성하기 위한 관계
// => 사원과 관리자와의 관계 - 관리자는 사원이다.(O), 사원은 관리자이다.(X)
//2.실체화 관계(Realization) : 상속 관계
// => 인터페이스를 상속받아 클래스를 작성하기 위한 관계
// => 인터페이스 : 표현대상을 클래스 보다 추상적으로 표현하기 위한 Java 자료형
// => 도형과 삼각형의 관계 - 도형을 사용하여 삼각형 작성
//3.연관 관계(Association) : 포함 관계 - X has a Y
// => 클래스에서 다른 클래스를 객체로 생성하여 필드 또는 메소드를 사용하는 관계
// => 의사와 환자와의 관계 - 관계가 양방향으로 설정
// => 직접 연관 관계(Direct Association) - 관계가 한쪽방향으로 설정
//4.집합 연관 관계(Aggregation) : 포함 관계로 설정된 객체의 생명주기가 다른 포함 관계
// => 프린터와 컴퓨터와의 관계
//5.복합 연관 관계(Composition) : 포함 관계로 설정된 객체의 생명주기가 같은 포함 관계
// => 게임과 캐릭터와의 관계
//6.의존관계(Dependency) : 포함 관계로 설정된 클래스가 변경돼도 현재 클래스가 영향을 주지 않는 관계
// => TV와 리모컨의 관계

//자동차정보(모델명, 생산년도, 엔진정보)를 저장하기 위한 클래스
public class Car {
	private String modelName;
	private int productionYear;
	//엔진정보를 저장하기 위한 필드 - Engine 클래스를 사용하여 필드 작성
	// => 생성자 또는 Setter 메소드를 사용하여 Engine 객체를 전달받아 필드 저장 - 포함관계 완성
	private Engine engine;
	
	public Car() {
		// TODO Auto-generated constructor stub
	}
	
	public Car(String modelName, int productionYear, Engine engine) {
		super();
		this.modelName = modelName;
		this.productionYear = productionYear;
		this.engine = engine;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public int getProductionYear() {
		return productionYear;
	}
	
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public void displayCar() {
		System.out.println("모델명 = "+modelName);
		System.out.println("생산년도 = "+productionYear);
		//System.out.println("엔진정보 = "+engine);
		
		//필드에 저장된 객체를 사용하여 클래스의 메소드 호출
		//System.out.println("연료타입 = "+engine.getFualType());
		//System.out.println("배기량 = "+engine.getDisplacement());
		engine.displayEngine();
	}
}