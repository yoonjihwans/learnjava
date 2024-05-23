package xyz.itwill.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//컴퍼넌트(컨테이너)에서는 다양한 이벤트가 발생될 수 있으며 이벤트 발생시 원하는 명령이 실행
//되도록 프로그램 작성 필요 - 이벤트 처리 프로그램

//java.awt.event 패키지 : 이벤트 처리를 위한 Java 자료형이 선언된 패키지

//이벤트 처리 프로그램을 작성하는 방법
//1.컴퍼넌트(컨테이너) 관련 클래스를 사용하여 UI 클래스 작성
// => UI 클래스는 기본적으로 Frame 클래스를 상속받아 작성
// => UI 클래스에서 사용된 컴퍼넌트(컨테이너)에서는 다양한 이벤트 발생 - 이벤트 소스(Event Source)
// => 컴퍼넌트(컨테이너)에서 이벤트가 발생될 경우 이벤트 관련 정보가 저장된 XXXEvent 객체가 자동 생성
//ex) Button 컴퍼넌트를 누른 경우 ActionEvent 클래스로 객체가 자동 생성 - ActionEvent 발생
//2.이벤트 소스에서 발생된 이벤트를 처리하는 기능을 제공할 이벤트 처리 클래스 작성
// => 이벤트 처리 클래스는 발생된 이벤트를 처리하기 위해 XXXListener 인터페이스를 상속받아 작성
//ex) ActionEvent >> ActionListener 인터페이스를 상속받은 자식클래스 작성 - 이벤트 처리 클래스
// => Listener 인터페이스에는 이벤트 처리를 위한 추상메소드가 1개 이상 작성
// => 이벤트 처리 클래스에서는 Listener 인터페이스의 모든 추상메소드를 오버라이딩 선언하여
//메소드에 이벤트 처리 명령 작성
//3.이벤트 소스에서 이벤트가 발생될 경우 이벤트 처리 클래스로 생성된 객체를 제공받아 이벤트
//처리 메소드가 자동 호출되어 이벤트 처리되도록 설정
//형식) Component.addXXXListener(Listener l) : 컴퍼넌트에서 발생된 이벤트를 처리하기 위한 
//이벤트 처리 클래스의 객체를 추가(등록)하는 메소드 
// => addXXXListener() 메소드를 호출하면 컴퍼넌트의 이벤트를 감지하는 스레드가 자동 생성되어 
//컴퍼넌트에서 이벤트가 발생되면 이벤트 처리 객체로 이벤트 처리 메소드 자동 호출
// => 이벤트 처리 프로그램은 기본적으로 다중 스레드 프로그램

//[EXIT] 버튼을 누른 경우 프로그램을 종료하는 이벤트 프로그램 작성
// => 이벤트 처리 클래스의 메소드에서 UI 클래스의 컴퍼넌트(컨테이너) 사용 불가능
public class EventHandleApp extends Frame {
	private static final long serialVersionUID = 1L;

	public EventHandleApp(String title) {
		super(title);
		
		setLayout(new FlowLayout());
		Button exit=new Button("EXIT");
		exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		add(exit);
		
		exit.addActionListener(new ButtonEventHandle());
		
		setBounds(800, 200, 300, 300);
		setVisible(true);
	} 
	
	public static void main(String[] args) {
		new EventHandleApp("이벤트 처리");
	}
}

//이벤트 처리 클래스 - Listener 인터페이스를 상속받아 작성
class ButtonEventHandle implements ActionListener {
	//이베트 처리 메소드 - 이벤트 처리 명령 작성
	// => 이벤트가 발생되면 이벤트 처리 클래스의 객체로 자동 호출되는 메소드
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
}