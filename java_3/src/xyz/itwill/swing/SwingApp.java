package xyz.itwill.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//java.awt 패키지의 컴퍼넌트(컨테이너)를 사용하여 UI 클래스를 작성하는 것보다 javax.swing 
//패키지의 컴퍼넌트(컨테이너)를 사용하여 GUI 프로그램을 작성하는 권장
// => 플렛폼에 독립적인 컴퍼넌트(컨테이너)를 사용하여 GUI 프로그램 작성
// => java.awt 패키지의 컴퍼넌트(컨테이너)를 생성하는 클래스 이름앞에 [J]를 붙이면 javax.swing
//패키지의 컴퍼넌트(컨테이너)를 생성할 수 있는 클래스 

//JTextField 컴퍼넌트에 문자열(메세지)을 입력한 후 엔터를 누르면 JTextArea 컴퍼넌트에 문자열
//(메세지)를 추가하여 출력하는 이벤트 처리 프로그램 작성
// => JTextField 컴퍼넌트에 문자열(메세지)을 입력한 후 엔터를 누르면 ActionEvent 발생
public class SwingApp extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea textArea;
	private JTextField textField;
	
	public SwingApp(String title) {
		super(title);

		textArea=new JTextArea("[홍길동]님이 입장 하였습니다.\n");
		textField=new JTextField();
		
		//Swing 컴퍼넌트는 플렛폼에서 제공하는 모든 글꼴 사용 가능
		textArea.setFont(new Font("굴림체", Font.BOLD, 20));
		textField.setFont(new Font("굴림체", Font.BOLD, 20));
		
		textArea.setBackground(Color.PINK);
		
		//JTextArea 컴퍼넌트에 입력 포커스를 제공하지 않도록 설정
		// => JTextArea 컴퍼넌트를 출력 전용 컴퍼넌트로 사용
		textArea.setFocusable(false);
		
		//JFrame.getContentPane() : JFrame 객체의 Container 객체를 반환하는 메소드
		// => JFrame 객체에 직접 컴퍼넌트 배치, 배경색 변경, 배치관리자 변경 등을 설정하지 않고
		//JFrame 객체의 Container 객체에 컴퍼넌트 배치, 배경색 변경, 배치관리자 등 설정
		Container container=getContentPane();
		
		
		//JTextArea 컴퍼넌트는 문자열이 폭 또는 높이를 벗어날 경우 스크롤 미생성
		// => 문자열이 JTextArea 컴퍼넌트의 폭 또는 높이를 벗어날 경우 스크롤이 생성되도록 
		//JScrollPane 클래스로 객체(컨테이너)를 생성하여 JTextArea 컴퍼넌트를 배치 처리 
		JScrollPane scrollPane=new JScrollPane(textArea);
		
		container.add(scrollPane, BorderLayout.CENTER);
		container.add(textField, BorderLayout.SOUTH);
		
		//JFrame.setDefaultCloseOperation(int operation) : 매개변수로 전달받은 정수값을 사용해
		//프레임의 닫기 버튼을 누른 경우 동작될 기능을 변경하는 메소드
		// => 메소드 매개변수에는 WindowConstants 클래스의 상수필드를 전달하여 메소드 호출
		//WindowConstants 클래스의 상수필드
		// => DO_NOTHING_ON_CLOSE : 아무런 기능도 구현되지 않도록 설정하는 상수필드
		// => HIDE_ON_CLOSE(기본값) : 프레임이 보여지지 않도록 설정하는 상수필드
		// => DISPOSE_ON_CLOSE : 메모리를 정리한 후 프로그램을 종료하도록 설정하는 상수필드
		// => EXIT_ON_CLOSE :  프로그램을 무조건 종료하도록 설정하는 상수필드
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		textField.addActionListener(new TextComponentEventHandle());
		
		setBounds(800, 200, 400, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new SwingApp("Swing");
	}
	
	public class TextComponentEventHandle implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//JTextField 컴퍼넌트에 입력된 문자열을 반환받아 저장
			//TextComponent.getText() : JTextField 컴퍼넌트 또는 JTextArea 컴퍼넌트에 입력된
			//문자열을 얻어와 반환하는 메소드
			String text=textField.getText();
			
			if(!text.equals("")) {//JTextField 컴퍼넌트에 입력된 문자열이 있는 경우
				//JTextArea.append(String text) : 매개변수로 전달받은 문자열을 JTextArea 컴퍼넌트에
				//추가하여 출력하는 메소드
				textArea.append("[홍길동]"+text+"\n");
				
				//JTextArea.setCaretPosition(int position) : JTextArea 컴퍼넌트의 포커스를
				//매개변수로 전달받은 위치로 이동하는 메소드
				// => JTextArea 컴퍼넌트의 스크롤을 맨끝지점으로 이동되도록 처리
				textArea.setCaretPosition(textArea.getText().length());
				
				//JTextField 컴퍼넌트에 입력된 문자열을 제거하여 초기화 처리
				//TextComponent.setText(String text) : JTextField 컴퍼넌트 또는 JTextArea 컴퍼넌트의
				//문자열을 변경하는 메소드
				textField.setText("");
			}
		}
	}
}