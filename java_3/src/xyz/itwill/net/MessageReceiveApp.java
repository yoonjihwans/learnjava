package xyz.itwill.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//다른 컴퓨터에서 보내온 문자열(메세지)를 제공받아 출력하는 프로그램 작성
public class MessageReceiveApp {
	public static void main(String[] args) throws IOException {
		//패킷을 전달받기 위한 컴퓨터에서는 DatagramSocket 클래스의 DatagramSocket(int port) 
		//생성자를 사용하여 DatagramSocket 객체 생성 - 패킷을 받기 위한 포트 활성화
		DatagramSocket datagramSocket=new DatagramSocket(4000);
		
		//전달받은 패킷의 데이타를 저장하기 위한 byte 배열 생성
		byte[] data=new byte[1024];
		
		//패킷을 전달받기 위한 컴퓨터에서는 패킷을 저장된 객체를 생성하기 위해 DatagramPacket
		//클래스의 DatagramPacket(byte[] data, int length) 생성자로 DatagramPacket 객체 생성
		DatagramPacket datagramPacket=new DatagramPacket(data, data.length);
		
		//DatagramSocket.receive(DatagramPacket packet) : 연결된 컴퓨터에서 보내온 패킷을
		//매개변수로 전달된 DatagramPacket 객체에 저장하기 위한 메소드
		// => DatagramPacket 객체에 저장된 byte 배열에 자동으로 전달된 데이타 저장
		// => 연결된 컴퓨터에서 보내온 패킷이 없는 경우 스레드는 일시 중지
		datagramSocket.receive(datagramPacket);
		
		//매개변수로 byte[] 배열을 전달받아 문자열로 반환받아 저장 
		String message=new String(data);
		
		System.out.println("[결과]보내온 메세지 = "+message);
		
		datagramSocket.close();
	}
}









