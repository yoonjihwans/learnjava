package xyz.itwill.awt;

import java.awt.Frame;

//GUI 프로그램에서 UI 구현 및 이벤트 처리를 위해서는 Frame 클래스를 상속받아 작성하는 것을 권장
public class FrameTwoApp extends Frame {
	private static final long serialVersionUID = 1L;

	public FrameTwoApp(String title) {
		//super 키워드로 부모클래스(Frame 클래스)의 매개변수가 작성된 생성자를 호출하여 객체 생성
		// => 매개변수에 전달된 문자열을 프레임의 제목으로 사용
		super(title);
		
		//자식클래스에 없는 메소드는 부모클래스의 메소드를 호출하여 사용
		//Component.setBounds(int x, int y, int width, int height) : 컴퍼넌트의 출력위치와
		//크기를 변경하는 메소드
		setBounds(600, 100, 500, 400);//Frame 객체를 참조하여 메소드 호출
		
		//Frame.setResizable(boolean resize) : 프레임 크기 변경 가능 유무를 설정하기 위한 메소드
		// => false : 프레임 크기 변경 불가능, true(기본값) : 프레임의 크기 변경 가능
		setResizable(false);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		//Frame 클래스를 상속받은 자식클래스의 생성자로 객체 생성 - Frame 객체 생성
		new FrameTwoApp("프레임 연습");
	}
}