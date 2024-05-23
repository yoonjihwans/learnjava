package xyz.itwill.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

//Panel 클래스 : 컴퍼넌트를 배치하여 그룹화하기 위한 클래스 - 종속적인 컨테이너
// => 독립적으로 사용 불가능한 컨테이너로 다른 컨테이너에 배치하여 사용
public class PanelApp extends Frame {
	private static final long serialVersionUID = 1L;

	public PanelApp(String title) {
		super(title);
		
		Button red=new Button("RED");
		Button green=new Button("GREEN");
		Button blue=new Button("BLUE");
		
		//Panel 객체의 기본 배치관리자는 [FlowLayout]로 설정
		Panel panel=new Panel();
		
		//패널 컨테이너에 버튼 컴퍼넌트 배치
		panel.add(red);
		panel.add(green);
		panel.add(blue);
		
		//TextArea 클래스 : 여러 줄의 문자열을 입출력 처리하기 위한 컴퍼넌트를 구현한 클래스
		TextArea textArea=new TextArea();
		
		//TextField 클래스 : 한줄의 문자열을 입출력 처리하기 위한 컴퍼넌트를 구현한 클래스
		TextField textField=new TextField();
		
		//프레임에 컨테이너 또는 컴퍼넌트 배치
		add(panel, BorderLayout.NORTH);
		add(textArea, BorderLayout.CENTER);
		add(textField, BorderLayout.SOUTH);
		
		//Component.setFont(Font f) : 컴퍼넌트의 글자 관련 속성을 변경하는 메소드
		// => 컨테이너에 적용된 글자 관련 속성은 컨테이너에 종속된 컴퍼넌트에 동일하게 적용
		//Font 클래스 : 글자 관련 속성을 저장한 객체를 생성하기 위한 클래스
		// => Font 클래스의 Font(String name, int style, int size) 생성자를 사용하여 Font 객체 생성
		// => name : 글자의 글꼴, style : 글자 스타일, size : 글자 크기
		// => 글자의 글꼴과 스타일은 Font 클래스에 상수필드 사용
		panel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
		textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD+Font.ITALIC, 22));
		
		//Component.setForeground(Color c) : 컴퍼넌트의 글자색을 변경하는 메소드
		//Color 클래스 : 색에 대한 정보를 저장한 객체를 생성하기 위한 클래스
		// => Color 클래스의 Color(int red, int green, int blue) 생성자를 사용하여 Color 객체 생성
		// => 매개변수에 전달되는 색은 0~255 범위의 정수값만 사용 가능
		red.setForeground(new Color(255, 0, 0));
		green.setForeground(new Color(0, 255, 0));
		blue.setForeground(new Color(0, 0, 255));
		
		//Component.setBackground(Color c) : 컴퍼넌트의 배경색을 변경하는 메소드
		// => Color 객체는 Color 클래스의 상수필드로 사용 가능
		textArea.setBackground(Color.YELLOW);
		
		//Component.setEnable(boolean b) : 컴퍼넌트의 활성화 및 비활성화를 변경하는 메소드
		// => false : 컴퍼넌트 비활성화, true(기본) : 컴퍼넌트 활성화
		red.setEnabled(false);
		
		//TextComponent.setFocusable(boolean b) : 텍스트 컴퍼넌트에 포커스(Focus) 제공 여부를 
		//변경하는 메소드 - 텍스트 컴퍼넌트를 사용한 입력 처리 불가능
		// => false : 포커스 미제공, true(기본) : 포커스 제공
		textArea.setFocusable(false);
		
		setBounds(600, 100, 400, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PanelApp("Panel");
	}
}