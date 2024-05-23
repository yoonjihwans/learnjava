package xyz.itwill.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

//NTP 서버에 접속하여 서버에서 보내온 날짜와 시간이 저장된 객체를 제공받아 출력하는 클라이언트
//프로그램 작성
public class TimeClientApp {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			//Socket 클래스 : TCP 프로그램에서 호스트와의 연결정보가 저장된 객체를 생성하기 위한 클래스
			// => Socket(String host, int port) 생성자를 사용하여 Socket 객체 생성 - 서버 접속
			// => host 매개변수에는 접속할 서버의 이름 또는 IP 주소를 전달하고 port 매개변수에는
			//서버에서 활성화 처리된 포트번호를 전달하여 객체 생성
			// => UnknownHostException 및 IOException 발생
			// => 서버에 접속되면 서버의 소켓과 연결되어 데이타를 송수신할 수 있는 입력스트림과
			//출력스트림이 자동 생성돼 Socket 객체에 저장
			Socket socket=new Socket("192.168.13.31", 2000);
			//System.out.println("socket = "+socket);
			
			//Socket.getInputStream() : Socket 객체로부터 접속된 서버에서 보내온 원시데이타를
			//받을 수 있는 입력스트림(InputStream 객체)를 반환하는 메소드
			InputStream inputStream=socket.getInputStream();

			//매개변수로 서버에서 보내온 원시데이타를 받을 수 있는 OutputStream 객체를 전달받아
			//객체를 반환받기 위한 입력스트림(ObjectInputStream 객체)으로 확장
			ObjectInputStream in=new ObjectInputStream(inputStream);
			
			//확장된 입력스트림으로 서버에서 보내온 현재 날짜와 시간이 저장된 Date 객체를 반환받아 저장 
			// => 확장된 입력스트림에 객체가 없는 경우 스레드 일시 중지되며 객체가 전달되면 스레드 재실행
			Date date=(Date)in.readObject();
			
			System.out.println("[결과]서버에서 보내온 날짜와 시간 = "
					+new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초").format(date));
			
			socket.close();
		} catch (UnknownHostException e) {
			System.out.println("[에러]서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("[에러]서버에 접속 할 수 없습니다.");
		}
	}
}






