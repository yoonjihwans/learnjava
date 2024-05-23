package xyz.itwill.awt;

import java.awt.Button;
import java.awt.Frame;

//배치관리자(LayoutManager) : 컨테이너 컴퍼넌트를 자동으로 배치하기 위한 기능을 제공하는 객체
// => BorderLayout, FlowLayout, GridLayout, CardLayout, GridLayout  등

//컨테이너에는 컴포넌트를 자동 배치하기 위한 배치관리자가 기본적으로 설정되어 제공
// = > Frame, Window, Dialog 등 - BorderLayout
// = > Pannel, Applet 등 - FlowLayout


//배치관리자를 사용하지않고 프레임에 컴퍼넌트를 배치하여 사용하는 프로그램 작성
public class NonLayoutManagerApp extends Frame{
	private static final long serialVersinUID = 1L;
   
     	public NonLayoutManagerApp(String title) {
     		super(title);
     		
     		
     		//Container.setLayout();
     		
     		setLayout(null);
     		
     		Button button1 = new Button("Button-1");
     		Button button2 = new Button("Button-2");
     		
     		button1.setBounds(80, 100, 150, 80);
     		button2.setBounds(80, 300, 100, 120);
     		
     		add(button1);
     		add(button2);
     		
     		
     		setBounds(600, 100, 400, 500);
     		setVisible(true);
     	}
     	
     	public static void main(String[] args) {
			new NonLayoutManagerApp("배치관리자");
		}
}
