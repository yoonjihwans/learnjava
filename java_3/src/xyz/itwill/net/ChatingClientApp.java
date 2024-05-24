package xyz.itwill.net;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//채팅 클라이언트 프로그램 작성
// => JTextField 컴퍼넌트로 입력한 메세지를 서버에 전달 - 이벤트 처리 객체(EventQueue 스레드)
// => 서버에서 보내온 메세지를 전달받아 JTextArea 컴퍼넌트에 출력 처리 - 무한루프(main 스레드)
public class ChatingClientApp extends JFrame {
	private static final long serialVersionUID = 1L;
	
	//이벤트 처리 메소드에서 사용할 컴퍼넌트를 저장하기 위한 필드
	private JTextArea textArea;//출력 컴퍼넌트
	private JTextField textField;//입력 컴퍼넌트
	
	//접속 서버의 정보가 저장된 Socket 객체를 저장하기 위한 필드
	private Socket socket;
	
	//서버에서 보내온 메세지를 전달받기 위한 입력스트림을 저장하기 위한 필드
	private BufferedReader in;
	
	//서버에게 메세지를 전달하기 위한 출력스트림을 저장하기 위한 필드
	private PrintWriter out;
	
	//대화명을 저장하기 위한 필드
	private String aliasName;
	
	public ChatingClientApp(String title) {
		super(title);
		
		textArea=new JTextArea();
		textField=new JTextField();
		
		textArea.setFont(new Font("굴림체", Font.BOLD, 20));
		textField.setFont(new Font("굴림체", Font.BOLD, 20));
		
		textArea.setFocusable(false);
		
		JScrollPane scrollPane=new JScrollPane(textArea);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(textField, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(700, 200, 400, 500);
		setVisible(true);
		
		//JTextField 컴퍼넌트에서 ActionEvent가 발생될 경우 이벤트 처리하기 위한 ActionListener 객체 추가
		// => ActionListener 인터페이스를 상속받은 익명의 클래스를 사용하여 ActionListener 객체 생성
		textField.addActionListener(new ActionListener() {
			//JTextField 컴퍼넌트에 입력된 문자열(메세지)을 반환받아 서버에 전달하는 명령 작성
			@Override
			public void actionPerformed(ActionEvent e) {
				//JTextField 컴퍼넌트에 입력된 문자열(메세지)을 반환빋이 저장
				String message=textField.getText();
				
				if(!message.equals("")) {//반환받은 문자열(메세지)이 있는 경우
					//서버와 연결된 출력스트림을 사용하여 문자열(메세지) 전달
					out.println(message);
					
					//JTextField 컴퍼넌트 초기화 처리 - 기존에 입력된 문자열(메세지) 제거
					textField.setText("");
				}
			}
		});
		
		try {
			//Socket 객체를 생성하여 필드에 저장 - 서버 접속
			socket=new Socket("192.168.13.31", 5000);
			
			//소켓의 입력스트림을 사용해 대량의 문자데이타(문자열)를 전달받을 수 있는 입력스트림으로 확장
			in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//소켓의 출력스트림을 사용해 모든 자료형의 값을 문자열로 전달할 수 있는 출력스트림으로 확장
			out=new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			//메세지 다이얼로그를 사용해 에러메세지 출력 처리
			JOptionPane.showMessageDialog(this, "서버에 접속할 수 없습니다."
				, "접속오류", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
		//대화명을 입력받아 서버에 전달
		// => 대화명을 검증하기 위한 반복문 사용 - 정상적인 대화명을 입력한 경우 반복문 종료
		while(true) {
			//입력 다이얼로그를 사용해 대화명을 입력받아 저장
			aliasName=JOptionPane.showInputDialog(this, "대화명을 입력해 주세요."
					, "대화명 입력", JOptionPane.QUESTION_MESSAGE);
			
			//대화명 : 2~6 범위의 한글만 입력받아 사용되도록 설정 - 정규표현식
			String regEx="^[가-힣]{2,6}$";
			
			//정상적인 대화명인 경우 반복문 종료
			if(aliasName !=null && Pattern.matches(regEx, aliasName)) break;
			
			//메세지 다이얼로그를 사용해 에러메세지 출력
			JOptionPane.showMessageDialog(this, "2~6 범위의 한글로 작성된 대화명을 입력해 주세요."
					, "입력오류", JOptionPane.ERROR_MESSAGE);
		}
		
		//입력받은 대화명을 출력스트림을 사용해 서버에 전달
		out.println(aliasName);
		
		//서버에서 보내온 메세지를 입력스트림을 사용해 전달받아 JTextArea 검퍼넌트에 출력하여 출력
		// => 클라이언트 프로그램이 종료되기 전까지 반복 처리
		while(true) {
			try {
				//입력스트림을 사용해 서버에서 보내온 메세지를 반환받아 JTextArea 컴퍼넌트에 추가하여 출력 처리
				textArea.append(in.readLine()+"\n");
				//JTextArea 컴퍼넌트의 스크롤을 마지막 위치로 이동되도록 처리
				textArea.setCaretPosition(textArea.getText().length());
			} catch (IOException e) {//네크워크 문제로 서버 접속이 종료된 경우
				JOptionPane.showMessageDialog(this, "서버와의 연결이 끊어졌습니다."
						, "접속오류", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new ChatingClientApp("자바채팅");
	}
}







