package xyz.itwill02.factory;

//Factory 클래스에 의해 객체로 생성되어 제공될 클래스 
// => 인터페이스를 상속받아 작성하는 것을 권장
public class HelloMessageObject implements MessageObject {
	@Override
	public String getMessage() {
		return "Hello!!!";
	}
}