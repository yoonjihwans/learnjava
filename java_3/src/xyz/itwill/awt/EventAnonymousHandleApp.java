package xyz.itwill.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;

//[EXIT] 버튼을 누른 경우 프로그램을 종료하는 이벤트 프로그램 작성
// => 익명의 내부클래스를 사용해 이벤트 처리 객체를 생성해 이벤트 처리 명령이 실행되도록 등록 처리
public class EventAnonymousHandleApp extends Frame {
	private static final long serialVersionUID = 1L;

	public EventAnonymousHandleApp(String title) {
		super(title);
		
		setLayout(new FlowLayout());
		Button exit=new Button("EXIT");
		exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		add(exit);

		//이벤트 소스에서 발생된 이벤트를 처리하기 위한 이벤트 처리 객체를 익명의 클래스로 생성
		/*
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		*/

		//Listener 인터페이스에 추상메소드가 하나만 작성된 경우 람다표현식을 사용하여 객체를
		//생성해 메소드의 매개변수에 전달하여 이벤트 처리 객체 등록
		exit.addActionListener(e -> System.exit(0));
		
		setBounds(800, 200, 300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new EventAnonymousHandleApp("이벤트 처리");
	}
}