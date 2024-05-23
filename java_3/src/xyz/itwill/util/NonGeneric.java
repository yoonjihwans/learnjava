package xyz.itwill.util;

//표현대상을 추상화하여 클래스로 선언할 때 필드의 자료형이 부정확한 경우 필드의 자료형에 의해
//다수의 클래스를 선언해 사용할 경우 발생 가능
// => 필드의 자료형을 Object 클래스로 선언하면 다수의 클래스 선언 불필요
// => Object 클래스로 선언된 필드에는 모든 Java 클래스로 생성된 객체 저장 가능
public class NonGeneric {
	private Object field;
	
	public NonGeneric() {
		// TODO Auto-generated constructor stub
	}

	public NonGeneric(Object field) {
		super();
		this.field = field;
	}

	public Object getField() {
		return field;
	}

	public void setField(Object field) {
		this.field = field;
	}
}