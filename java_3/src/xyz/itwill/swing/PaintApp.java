package xyz.itwill.swing;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

//프레임에서 마우스 버튼을 클릭한 경우 클릭한 위치의 좌표값을 문자열로 출력하는 프로그램 작성
// => 마우스 관련 이벤트는 MouseEvent 클래스를 사용하여 표현
public class PaintApp extends JFrame {
	private static final long serialVersionUID = 1L;

	//마우스 버튼을 클릭한 좌표값을 저장하기 위한 필드
	// => 이벤트 처리 메소드에서 좌표값을 제공받아 필드에 저장하고 paint() 메소드에서 필드값 출력
	private int x, y;
	
	public PaintApp(String title) {
		super(title);
		
		//JFrame 컨테이너에서 마우스 관련 이벤트가 발생될 경우 이벤트 처리를 위해 이벤트 처리 객체 등록
		// => MouseAdapter 추상클래스를 상속받은 익명의 클래스를 사용하여 객체를 생성해 전달
		addMouseListener(new MouseAdapter() {
			//컴퍼넌트(컨테이너)에서 마우스 버튼을 누른 경우 자동 호출되는 이벤트 처리 메소드
			// => 이벤트 처리 메소드의 매개변수에서는 이벤트 관련 정보가 저장된 Event 객체를 
			//전달받아 이벤트 처리 메소드에서 사용 가능
			@Override
			public void mouseClicked(MouseEvent e) {
				//MouseEvent.getX() : 마우스 버튼을 클릭한 X 좌표값을 반환하는 메소드
				x=e.getX();

				//MouseEvent.getY() : 마우스 버튼을 클릭한 Y 좌표값을 반환하는 메소드
				y=e.getY();
				
				//Window.repaint() : paint() 메소드를 수동으로 호출하는 메소드
				// => paint() 메소드를 직접 호출할 수 없으므로 repaint() 호출하여 사용
				repaint();
			}
		});
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(800, 200, 300, 400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PaintApp("Paint 메소드");
	}
	
	//Window.paint(Graphics g) : 컨테이너(JFrame, JWindow, JDialog 등)에 그림을 출력하는 메소드
	// => 프로그램이 실행되어 컨테이너가 보여지는 경우 또는 아이콘에서 해제되어 컨테이너가
	//보여지는 경우, 컨테이너의 크기가 변경되는 경우 자동 호출되는 메소드
	// => Graphics 객체 : 컨테이너에 그림을 그리는 기능을 제공하는 객체
	//컨테이너에 원하는 그림을 그리기 위해 Window 클래스의 paint() 메소드를 오버라이딩 선언
	@Override
	public void paint(Graphics g) {
		//System.out.println("paint(Graphics g) 메소드 호출");
		
		//super 키워드로 부모클래스의 숨겨진 메소드 호출
		// => 부모클래스의 paint() 메소드를 호출하여 컨테이너 초기화 처리
		super.paint(g);
		
		/*
		//Graphics 객체로 메소드를 호출하여 다양한 형태의 그림 출력 가능
		g.setColor(Color.RED);//Graphics 객체의 색을 변경하는 메소드
		g.drawRect(20, 40, 50, 50);//사각형(선)을 그리는 메소드
		g.fillRect(20, 100, 100, 50);//사각형(면)을 그리는 메소드

		g.setColor(Color.GREEN);
		g.drawOval(125, 175, 50, 50);//타원(선)을 그리는 메소드
		g.fillOval(100, 250, 100, 50);//타원(면)을 그리는 메소드
		
		g.setColor(Color.BLUE);
		g.drawLine(100, 320, 200, 320);//선을 그리는 메소드
		g.drawString("홍길동", 100, 350);//문자열을 그리는 메소드
		*/
		
		g.drawString("["+x+", "+y+"]", x, y);
	}
}









