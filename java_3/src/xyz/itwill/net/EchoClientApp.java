package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//키보드로 문자열(메세지)를 입력받아 서버에 전달하는 클라이언트 프로그램 작성
public class EchoClientApp {
	public static void main(String[] args) throws IOException {
		//키보드 입력스트림(System.in)을 대량의 문자데이타를 제공받을 수 있는 입력스트림으로 확장 
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		
		//확장된 입력스트림을 사용해 키보드 입력값을 문자열(메세지)로 반환받아 저장
		System.out.print("전달 메세지 입력 >> ");
		String message=in.readLine();
		
		try {
			//서버 접속
			Socket socket=new Socket("192.168.13.31", 3000);
			
			/*
			//소켓의 출력스트림을 반환받아 대량의 문자데이타(문자열)를 전달할 수 있는
			//출력스트림으로 확장
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//확장된 출력스트림을 사용하여 문자열(메세지)를 서버에 전달
			out.write(message);
			out.flush();
			*/

			//소켓의 출력스트림을 반환받아 모든 자료형의 값을 문자열로 전달할 수 있는
			//출력스트림으로 확장하여 서버에 문자열 전달
			PrintWriter out=new PrintWriter(socket.getOutputStream());
			out.println(message);
			out.flush();
			
			socket.close();
		} catch (UnknownHostException e) {
			System.out.println("[에러]서버를 찾을 수 없습니다.");
		} catch (IOException e) {
			System.out.println("[에러]서버에 접속 할 수 없습니다.");
		}
	}
}






