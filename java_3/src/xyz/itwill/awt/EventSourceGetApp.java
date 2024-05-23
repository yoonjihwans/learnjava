package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//색상 버튼을 누르면 캔버스의 배경을 변경하는 이벤트 처리 프로그램 작성
public class EventSourceGetApp extends Frame {
	private static final long serialVersionUID = 1L;

	//이벤트 처리 클래스의 메소드에서 사용하는 이벤트 소스(컴퍼넌트)는 필드를 작성해 저장
	// => 이벤트 처리 클래스의 메소드에서 필드에 저장된 컴퍼넌트를 사용하여 이벤트 처리
	private Canvas canvas;//생성자에서 컴퍼넌트를 생성하여 필드에 저장
	private Button red, green, blue, white;
	
	public EventSourceGetApp(String title) {
		super(title);
		
		red=new Button("RED");
		green=new Button("GREEN");
		blue=new Button("BLUE");
		white=new Button("WHITE");
		
		Panel panel=new Panel();
		
		panel.setLayout(new GridLayout(1, 4));
		
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		panel.add(white);
		
		canvas=new Canvas();//필드에 Canvas 객체(컴퍼넌트) 저장
		
		add(panel, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		
		panel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		
		/*
		//컴퍼넌트에서 이벤트가 발생될 경우 이벤트 처리를 위한 객체를 추가하여 이벤트 처리 
		//메소드가 자동 호출되도록 이벤트 처리 객체 등록 처리
		// => 컴퍼넌트마다 이벤트를 처리하는 클래스를 다르게 작성
		// => 익명의 클래스로 이벤트 처리 객체를 생성해 이벤트 처리되도록 등록하는 것을 권장 
		red.addActionListener(new RedButtonEventHandle());
		green.addActionListener(new GreenButtonEventHandle());
		blue.addActionListener(new BlueButtonEventHandle());
		white.addActionListener(new WhiteButtonEventHandle());
		*/
		
		//모든 버튼에서 발생되는 이벤트를 하나의 이벤트 처리 클래스의 객체로 처리되도록 작성
		// => 이벤트 처리 메소드에서는 이벤트가 발생된 컴퍼넌트를 비교하여 명령이 선택 실행되도록 작성
		red.addActionListener(new ColorButtonEventHandle());
		green.addActionListener(new ColorButtonEventHandle());
		blue.addActionListener(new ColorButtonEventHandle());
		white.addActionListener(new ColorButtonEventHandle());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(800, 200, 400, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new EventSourceGetApp("이벤트 처리");
	}

	/*
	public class RedButtonEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.RED);
		}
	}
	
	public class GreenButtonEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.GREEN);
		}
	}
	
	public class BlueButtonEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.BLUE);
		}
	}
	
	public class WhiteButtonEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			canvas.setBackground(Color.WHITE);
		}
	}
	*/
	
	//모든 색상 버튼에서 발생된 이벤트를 처리하기 위한 이벤트 처리 클래스
	public class ColorButtonEventHandle implements ActionListener {
		//이벤트 처리 메소드의 매개변수에는 컴퍼넌트에서 발생된 이벤트 정보가 저장된
		//Event 객체를 전달받아 저장
		// => 이벤트 처리 메소드에서는 Event 객체의 메소드를 호출하여 필요한 기능 구현
		@Override
		public void actionPerformed(ActionEvent e) {
			//Event.getSource() : 이벤트가 발생된 컴퍼넌트(객체)를 반환하는 메소드
			// => 이벤트가 발생된 컴퍼넌트를 Object 클래스의 객체로 반환되므로 자식클래스
			//(컴퍼넌트)의 메소드를 호출하기 위해서는 명시적 객체 형변환 필요
			Object eventSource=e.getSource();
			
			//이벤트 소스와 컴퍼넌트의 메모리 주소를 비교하여 명령 선택 실행
			if(eventSource == red) {
				canvas.setBackground(Color.RED);
			} else if(eventSource == green) {
				canvas.setBackground(Color.GREEN);
			} else if(eventSource == blue) {
				canvas.setBackground(Color.BLUE);
			} else if(eventSource == white) {
				canvas.setBackground(Color.WHITE);
			}
		}
	}
}







