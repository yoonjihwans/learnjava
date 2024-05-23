package xyz.itwill.awt;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//프레임의 [닫기] 버튼을 누른 경우 프로그램을 종료하는 이벤트 처리 프로그램 작성
// => 프레임(Frame 객체)에서는 WindowEvent 발생되므로 WindowListener 인터페이스를 상속받은
//자식클래스를 작성하여 이벤트 처리 클래스로 사용
public class WindowListenerApp extends Frame {
	private static final long serialVersionUID = 1L;
	
	public WindowListenerApp(String title) {
		super(title);
		
		addWindowListener(new WindowEventHandle());
		
		setBounds(800, 200, 300, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new WindowListenerApp("WindowEvent");
	}
	
	//Listener 인터페이스를 상속받은 자식클래스(이벤트 처리 클래스)는 Listener 인터페이스의
	//모든 추상메소드를 무조건 오버라이딩 선언
	// => 이벤트 처리 명령이 없는 불필요한 이벤트 처리 메소드도 오버라이딩 선언
	public class WindowEventHandle implements WindowListener {
		@Override
		public void windowOpened(WindowEvent e) {
			//System.out.println("windowOpened(WindowEvent e) 메소드 호출");
		}

		@Override
		public void windowClosing(WindowEvent e) {
			//System.out.println("windowClosing(WindowEvent e) 메소드 호출");
			System.exit(0);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			//System.out.println("windowClosed(WindowEvent e) 메소드 호출");
		}

		@Override
		public void windowIconified(WindowEvent e) {
			//System.out.println("windowIconified(WindowEvent e) 메소드 호출");
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			//System.out.println("windowDeiconified(WindowEvent e) 메소드 호출");
		}

		@Override
		public void windowActivated(WindowEvent e) {
			//System.out.println("windowActivated(WindowEvent e) 메소드 호출");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			//System.out.println("windowDeactivated(WindowEvent e) 메소드 호출");
		}
	}
}



