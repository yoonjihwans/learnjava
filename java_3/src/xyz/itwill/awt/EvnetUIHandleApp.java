package xyz.itwill.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//[EXIT] 버튼을 누른 경우 프로그램을 종료하는 이벤트 프로그램 작성
// => UI 클래스와 이벤트 처리 클래스를 하나의 클래스로 작성하여 사용
public class EvnetUIHandleApp extends Frame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public EvnetUIHandleApp(String title) {  
		super(title);
		
		setLayout(new FlowLayout());
		Button exit=new Button("EXIT");
		exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		add(exit);
		
		//이벤트 소스가 작성된 UI 클래스가 이벤트 처리 클래스이므로 this 키워드를 사용해
		//현재 클래스의 객체를 제공받아 이벤트 처리
		exit.addActionListener(this);
		
		setBounds(800, 200, 300, 300);
		setVisible(true);
	}

	public static void main(String[] args) { 
		new EvnetUIHandleApp("이벤트 처리");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		System.exit(0);
	}
}