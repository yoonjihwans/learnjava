package xyz.itwill.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP 네트워크 프로그램 : DatagramSocket 클래스와 DatagramPacket 클래스를 사용하여 작성
// => 데이타(패킷)를 보내는 컴퓨터와 데이타(패킷)를 받는 컴퓨터로 구분하여 처리

//키보드로 문자열(메세지)을 입력받아 다른 컴퓨터에게 전달하는 프로그램 작성 
public class MessageSendApp {
	public static void main(String[] args) throws IOException {
		//키보드 입력스트림(System.in)을 대량의 문자데이타를 제공받을 수 있는 입력스트림으로 확장 
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

		//확장된 입력스트림을 사용해 키보드 입력값을 문자열(메세지)로 반환받아 저장
		System.out.print("전달 메세지 입력 >> ");
		String message=in.readLine();
		
		//DatagramSocket 클래스 : 연결된 컴퓨터에게 패킷을 전달하거나 전달받는 객체를 생성하기 위한 클래스
		// => 패킷을 전달하기 위한 컴퓨터에서는 DatagramSocket 클래스의 매개변수가 없는 기본 
		//생성자를 사용하여 DatagramSocket 객체 생성
		DatagramSocket datagramSocket=new DatagramSocket();
		
		//연결될 컴퓨터의 네트워크 식별자가 저장된 InetAddress 객체를 반환받아 저장
		InetAddress inetAddress=InetAddress.getByName("192.168.13.25");
		
		//전달할 문자열(메세지)를 byte 배열로 변환하여 저장
		byte[] data=message.getBytes();
		
		//DatagramPacket 클래스 : 보내거나 받을 정보가 저장된 패킷이 저장된 객체를 생성하기 위한 클래스
		// => 패킷을 전달하기 위한 컴퓨터에서는 DatagramPacket 클래스의 DatagramPacket(byte[] data
		//, int length, InetAddress address, int port) 생성자를 사용하여 DatagramPacket 객체 생성
		DatagramPacket datagramPacket=new DatagramPacket(data, data.length, inetAddress, 4000);
		
		//DatagramSocket.send(DatagramPacket packet) : 매개변수에 전달된 DatagramPacket 객체의
		//정보를 사용하여 패킷을 전달하는 메소드
		datagramSocket.send(datagramPacket);
		
		//DatagramSocket.close() : DatagramSocket 객체를 제거하는 메소드
		datagramSocket.close();
		
		System.out.println("[메세지]연결된 컴퓨터에게 메세지를 보냈습니다.");
	}
}