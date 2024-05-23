package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

//BorderLayout 클래스 : 컴퍼넌트를 컨테이너의 중앙을 기준으로 동서남북에 배치하는 클래스
// => Frame, Window, Dialog 등의 클래스로 생성된 컨테이너의 기본 배치관리자

public class BorderLayoutApp extends Frame {
	private static final long serialVersionUID = 1L;
	
	public BorderLayoutApp(String title) {
		super(title);
		
		//프레임의 배치관리자를 [BorderLayout]으로 변경
		// => 프레임의 기본 배치관리자가 [BorderLayout]이므로 배치관리자 변경 생략 가능
		//setLayout(new BorderLayout());
		
		Button button1=new Button("Center");
		Button button2=new Button("East");
		Button button3=new Button("West");
		Button button4=new Button("South");
		Button button5=new Button("North");
		
		//Container.add(Component c, Object constraints) : 매개변수로 전달받은 컴퍼넌트를 
		//컨테이너의 원하는 위치에 배치하는 메소드
		// => 컨테이너의 배치관리자가 [BorderLayout]인 경우에만 호출하는 메소드
		// => 컴퍼넌트의 배치위치는 BorderLayout 클래스의 상수필드를 사용하는 것을 권장
		// => 컴퍼넌트의 배치위치가 생략된 경우 무조건 중앙에 배치
		add(button1, BorderLayout.CENTER);
		add(button2, BorderLayout.EAST);
		add(button3, BorderLayout.WEST);
		add(button4, BorderLayout.SOUTH);
		add(button5, BorderLayout.NORTH);
		
		setBounds(600, 100, 400, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BorderLayoutApp("BorderLayout");
	}
}