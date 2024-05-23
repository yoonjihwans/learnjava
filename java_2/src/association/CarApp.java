package association;

public class CarApp {
	public static void main(String[] args) {
		//Engine 클래스의 기본 생성자를 사용하여 객체 생성 - 엔진 생성
		Engine engine=new Engine();
		
		//Setter 메소드를 호출하여 객체 필드값 변경
		engine.setFualType("경유");
		engine.setDisplacement(2500);
		
		//engine.displayEngine();
		
		//Car 클래스의 기본 생성자를 사용하여 객체 생성 - 자동차 생성
		Car carOne=new Car();
		
		//Setter 메소드를 호출하여 객체 필드값 변경
		carOne.setModelName("쏘렌토");
		carOne.setProductionYear(2020);
		//Setter 메소드를 호출하요 Car 객체의 engine 필드에 엔진정보(Engine 객체) 저장
		// => Setter 메소드를 사용하여 Car 객체의 필드에 Engine 객체를 저장하여 포함 관계 구현
		carOne.setEngine(engine);
		
		carOne.displayCar();
		System.out.println("==============================================================");
		//Car 클래스의 매개변수가 작성된 생성자를 사용하여 객체 생성 - 자동차 생성
		// => Engine 클래스의 매개변수가 작성된 생성자를 사용하여 객체 생성하여 매개변수에 전달 - 엔진 생성
		// => 생성자 메소드를 사용하여 Car 객체의 필드에 Engine 객체를 저장하여 포함 관계 구현
		Car carTwo=new Car("싼타페", 2022, new Engine("휘발유", 2900));
		carTwo.displayCar();
		System.out.println("==============================================================");
		System.out.println(carOne.getModelName()+"의 엔진정보 >> ");
		engine.displayEngine();
		System.out.println("==============================================================");
		System.out.println(carTwo.getModelName()+"의 엔진정보 >> ");
		//Car 객체의 engine 필드에 저장된 Engine 객체를 Getter 메소드를 호출하여 반환받아
		//직접 Engine 클래스의 메소드 호출
		carTwo.getEngine().displayEngine();
		System.out.println("==============================================================");
	}
}