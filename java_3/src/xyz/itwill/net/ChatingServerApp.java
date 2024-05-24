package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

//채팅 서버 프로그램 작성
// => 클라이언트의 접속으로 생성된 소켓을 사용해 클라이언트가 보내온 메세지를 전달받아
//모든 클라이언트에게 전달하는 기능 구현
// => 클라이언트의 접속으로 생성된 소켓은 새로운 스레드를 생성하여 독립적으로 입력 또는 출력
//처리되도록 다중 스레드 프로그램으로 작성
public class ChatingServerApp {
	//현재 서버에 접속중인 클라이언트와 연결된 소켓이 요소에 저장된 List 객체를 저장할 필드
	private List<SocketThread> clientList;
	
	public ChatingServerApp() {
		ServerSocket chatingServer=null;
		
		//Vector 객체(List 객체)를 생성하여 필드에 저장
		clientList=new Vector<SocketThread>();
		
		try {
			chatingServer=new ServerSocket(5000);
			System.out.println("[메세지]채팅 서버 동작중...");
			
			while(true) {
				Socket socket=chatingServer.accept();
				
				//생성자 매개변수에 클라이언트와 연결된 정보가 저장된 Socket 전달하여 SocketThread 객체 생성
				SocketThread socketThread=new SocketThread(socket);
				
				//Vector 객체(List 객체)에 소켓이 저장된 SocketThread 객체를 요소에 저장하여 추가
				clientList.add(socketThread);
				
				//SocketThread 객체(Thread 객체)를 사용하여 새로운 스레드를 생성하여 run() 메소드의 명령 실행
				socketThread.start();
				
				System.out.println("[접속로그]"+socket.getInetAddress().getHostAddress()
						+"의 컴퓨터에서 채팅서버에 접속 하였습니다.");
			}
			
		} catch (IOException e) {
			System.out.println("[에러로그]서버가 정상적으로 동작되지 않습니다.");
		}
	}
	
	public static void main(String[] args) {
		new ChatingServerApp();
	}

	//매개변수로 전달받은 문자열(메세지)를 서버에 접속중인 모든 클라이언트에게 전달하는 메소드
	public void sendMessage(String message) {
		//Vector 객체(List 객체)에 저장된 요소값(SocketThread 객체)를 차례대로 제공받아 처리하는 반복문
		for(SocketThread socketThread : clientList) {
			//SocketThread 객체의 out 필드에 저장된 출력스트림을 사용하여 문자열(메세지) 전달
			// => 외부클래스의 메소드에서는 내부클래스로 생성된 객체를 사용해 접근제한자에
			//상관없이 필드 또는 메소드 사용 가능
			socketThread.out.println(message);
		}
	}
	
	public class SocketThread extends Thread {
		private Socket socket;
		
		//클라이언트에서 보내온 메세지를 전달받기 위한 입력스트림을 저장하기 위한 필드
		private BufferedReader in;
		
		//클라이언트에게 메세지를 전달하기 위한 출력스트림을 저장하기 위한 필드
		private PrintWriter out;
		
		public SocketThread(Socket socket) {
			this.socket=socket;
		}
		
		@Override
		public void run() {
			//클라이언트의 대화명을 저장하기 위한 변수
			String aliasName="";
			try {
				//소켓의 입력스트림을 사용해 대량의 문자데이타(문자열)를 전달받을 수 있는 입력스트림으로 확장
				in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				//소켓의 출력스트림을 사용해 모든 자료형의 값을 문자열로 전달할 수 있는 출력스트림으로 확장
				// => PrintWriter(OutputStream out, boolean autoFlush) 생성자로 PrintWriter 객체 생성
				// => autoFlush 매개변수에 [true]를 전달할 경우 출력버퍼를 사용하지 않고
				//출력스트림으로 데이타를 직접 전달할 수 있도록 처리
				out=new PrintWriter(socket.getOutputStream(), true);
				
				//클라이언트에서 보내온 대화명을 입력스트림으로 반환받아 저장
				aliasName=in.readLine();
				
				//현재 접속중인 모든 클라이언트에게 입장 메세지를 출력스트림으로 전달
				// => 내부클래스의 메소드에서는 외부클래스의 필드 또는 메소드를 접근제한자에
				//상관없이 사용 가능
				sendMessage("["+aliasName+"]님이 입장 하였습니다.");
				
				//클라이언트에서 보내온 메세지를 전달받아 현재 서버에 접속중인 모든 클라이언트에게 전달
				// => 클라이언트가 서버 접속을 종료하기 전까지 반복 처리
				// => 클라이언트가 서버 접속을 종료하면 IOExcetion 발생 - 반복문 종료
				while(true) {
					sendMessage("["+aliasName+"]"+in.readLine());
				}
			} catch (IOException e) {//클라이언트가 서버 접속을 종료한 경우 실행될 명령 작성
				//서버에 접속된 클라이언트의 소켓이 요소로 저장된 List 객체에서 접속을 종료한
				//클라이언트의 소켓을 삭제 처리
				clientList.remove(this);//this >> SocketThread 객체
				
				//현재 서버에 접속중인 모든 클라이언트에게 퇴장 메세지 전달
				sendMessage("["+aliasName+"]님이 퇴장 하였습니다.");
				
				System.out.println("[종료로그]"+socket.getInetAddress().getHostAddress()
						+"의 컴퓨터에서 채팅서버의 접속을 종료 하였습니다.");
			}
		}
	}
}