package xyz.itwill.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

//네트워크(Network) : 두 대이상의 컴퓨터에서 값을 송수신할 수 있는 기능을 제공하는 환경

//인터넷(Internet) : 모든 컴퓨터들을 연결한 네트워크 통신망

//프로토콜(Protocol) : 인터넷을 사용하기 위한 네트워크 관련 약속 - 통신규약
// => 네트워크 계층(HW) >> 인터넷 계층(IP) >> 전송 계층(TCP or UDP) >> 응용 계층(FTP, HTTP, SMTP 등)

//네트워크 계층(링크 계층) - 이더넷, 라우터, 스위치허브 등과 같은 통신 장비
// => 이더넷(Ethernet) : 호스트(컴퓨터)에서 사용하는 네트워크 통신 장비
// => 라우터(Router) : 네트워크 그룹(SubNet)과 네트워크 그룹을 연결하기 위한 통신 장비
// => 스위치허브(SwitchHub) : 호스트와 호스트 또는 호스트와 네트워크 그룹을 연결하기 위한 통신 장비

//호스트(Host) : 네트워크 기능을 사용하는 컴퓨터(프로그램)

//인터넷 계층 - IP(Internet Protocol)
// => 인터넷을 사용하기 위한 네트워크 주소(IP Address)에 대한 통신규약 - IPV4, IPV6

//IP 주소(IP Address) : 인터넷을 사용하기 위해 컴퓨터에 부여하는 네트워크 식별자
// => IPV4 : 32Bit를 사용하여 IP 주소 표현 - 10진수 0~255 범위의 정수값 4개를 [.] 기호로 구분하여 표현
// => IPV6 : 128Bit를 사용하여 IP 주소 표현 - 16진수 0000~FFFF 범위의 정수값 8개를 [:] 기호로 구분하여 표현
// => 공인 IP 주소와 사설 IP 주소(네트워크 그룹에서만 사용)로 구분
// => 사설 IP 주소 : A Class(10.0.0.0 ~ 10.255.255.255), B Class(172.16.0.0 ~ 172.31.255.255)
//, C Class(192.168.0.0 ~ 192.168.255.255)

//Netmask 주소 : 네트워크 그룹(SubNet)를 표현하기 위한 주소
// => A Class : 255.0.0.0 - 16,777,216 => 0.X.X.X ~ 127.X.X.X
// => B Class : 255.255.0.0 - 65,536 => 128.X.X.X ~ 191.X.X.X
// => C Class : 255.255.255.0 - 256 => 192.X.X.X ~ 232.X.X.X

//Geteway 주소 : 라우터 통신장비에 부여된 IP 주소 - 이더넷 장치에 설정하기 위한 주소

//도메인(Domain) : 인터넷을 사용하기 위해 그룹(개인)에게 부여하는 문자로 구성된 네트워크 주소
// => IP 주소 대신 도메인을 사용한 호스트명을 이용해 인터넷 사용

//DNS 서버 : 도메인(호스트)을 IP 주소로 변환하는 서비스를 제공하는 컴퓨터 

//서버(Server) : 네트워크 서비스를 제공하기 위한 컴퓨터
//클라이언트(Client) : 네트워크 서비스를 제공받기 위한 컴퓨터

//전송 계층 - TCP or UDP
//TCP(Transmission Control Protocol) : 연결형 프로토콜로 신뢰할 수 있는 데이타 전송을 위한 프로토콜
// => 연결형 프로토콜 : 호스트와 호스트가 1:1 관계로 연결된 후 데이타 전송 기능 제공 - 소켓(Socket) 통신
//UDP(User Datagram Protocol) : 비연결형 프로토콜로 신뢰할 수 없는 데이타 전송을 위한 프로토콜
// => 비연결형 프로토콜 : 호스트가 1:N 관계로 연결된 후 데이타 전송 기능 제공 - 고속 통신

//응용 계층 - FTP, HTTP, SMTP, POP3, IMAP 등
// => 전송 계층의 프로토콜을 사용해 다양한 네트워크 서비스를 제공하기 위한 프로토콜

//포트번호(Port Number) : 데이타를 송수신하기 위한 고유의 통신경로
// => 네트워크 프로그램은 반드시 0~65535 범위의 포트 중 하나를 사용하여 데이타 송수신
// => 약속된 포트(Well-Known Port) : 0 ~ 1023 - HTTP : 80, HTTPS : 443, FTP : 21, SMTP : 25, POP3 : 110 등
// => 숨겨진 포트(Private Port) : 49152 ~ 65535

//패킷(Packet) : 인터넷상에 데이타를 송수신하기 위한 단위 
// => Java 언어에서는 패킷(Packet)를 byte 배열로 생성하여 사용

//방화벽(FireWall) : 네트워크 접속 허용 또는 차단을 제공하기 위한 시스템(프로그램) 

//java.net 패키지 : 네트워크 기능을 제공하기 위한 Java 자료형이 선언된 패키지

public class InetAddressApp {
	public static void main(String[] args) throws UnknownHostException {
		//InetAddress 클래스 : 네트워크 식별자(IP 주소와 호스트이름)를 저장한 객체를 생성하기 위한 클래스
		//InetAddress.getLocalHost() : 현재 사용중인 컴퓨터의 네트워크 식별자가 저장된 
		//InetAddress 객체를 반환하는 정적메소드
		// => 호스트 이름의 컴퓨터를 검색할 수 없는 경우 UnknownHostException 발생 
		//현재 사용중인 컴퓨터는 기본적으로 [127.0.0.1]의 IP 주소(LoopBack IP)라 제공되며
		//[localhost]라는 호스트이름 사용
		InetAddress myComputer=InetAddress.getLocalHost(); 
		
		//InetAddress.toString() : InetAddress 객체에 저장된 네트워크 식별자를 문자열로 반환하는 메소드
		System.out.println("myComputer = "+myComputer);//toString() 메소드 자동 호출
		//InetAddress.getHostName() : InetAddress 객체에 저장된 호스트이름을 문자열로 반환하는 메소드
		System.out.println("myComputer.getHostName() = "+myComputer.getHostName());
		//InetAddress.getHostAddress() : InetAddress 객체에 저장된 IP 주소를 문자열로 반환하는 메소드
		System.out.println("myComputer.getHostAddress() = "+myComputer.getHostAddress());
		System.out.println("=============================================================");
		//InetAddress.getByName(String name) : 매개변수로 전달받은 호스트이름(IP 주소)에 대한
		//네트워크 식별자가 저장된 InetAddress 객체를 반환하는 정적메소드
		InetAddress itwill=InetAddress.getByName("www.itwill.xyz");
		System.out.println("[www.itwill.xyz] 컴퓨터의 IP 주소 = "+itwill.getHostAddress());
		System.out.println("=============================================================");
		//InetAddress.getByName(String name) : 매개변수로 전달받은 호스트이름(IP 주소)에 대한
		//네트워크 식별자가 저장된 InetAddress 객체 배열을 반환하는 정적메소드
		InetAddress[] naver=InetAddress.getAllByName("www.naver.com");
		
		for(InetAddress address : naver) {
			System.out.println("[www.naver.com] 컴퓨터의 IP 주소 = "+address.getHostAddress());
		}
		System.out.println("=============================================================");
	}
}