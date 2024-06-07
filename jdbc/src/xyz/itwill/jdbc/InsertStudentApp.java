package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import oracle.jdbc.driver.OracleDriver;

//JDBC(Java DataBase Connectivity) : Java를 사용하여 DBMS 서버에 접속해 SQL 명령을 전달하여  
//실행하기 위한 기능을 제공하는 Java API(인터페이스 또는 클래스)

//java.sql : JDBC 프로그램을 작성하기 위한 Java 자료형이 선언된 패키지
// => java.sql 패키지에는 JDBC 기능을 제공하기 위한 인터페이스 제공
// => DBMS 종료가 다양하므로 JDK 라이브러리(jrt-fs.jar)에 JDBC 기능의 클래스 제공 불가능
// => DBMS를 개발하여 관리하는 그룹에서 JDBC 기능의 클래스(JDBC Driver)를 만들어 배포하므로
//JDBC Driver 관련 라이브러리 파일를 다운로드 받아 프로젝트에 빌드 처리

//Oracle DBMS 서버를 사용한 JDBC 프로그램을 작성하기 위해 Oracle JDBC Driver 관련 라이브러리
//파일을 다운로드 받아 프로젝트에 빌드 처리
//1.https://www.oracle.com 사이트에서 Oracle JDBC Driver 관련 라이브러리 파일 다운로드
// => Oracle JDBC Driver 관련 라이브러리 파일 : ojdbc11.jar - JDK 버전 참조
//2.Oracle JDBC Driver 관련 라이브러리 파일(ojdbc11.jar)을 프로젝트 폴더에 복사하여 붙여놓기
//3.프로젝트 폴더에 저장된 라이브러리 파일을 프로젝트에 연결하여 프로그램 작성시 사용 가능
//하도록 빌드(Build) 처리
// => 라이브러리 파일에 작성된 인터페이스 또는 클래스를 프로그램에서 사용할 수 있도록 설정
// => 프로젝트 >> 마우스 오른쪽 버튼 >> Properties >> 속성창 >> Java Build Path >> Libraries
//    >> classpath >> Add Jars >> 파일 선택창 >> 프로젝트 폴더의 Jar 파일 선택 >> OK >> Apply And Close

//STUDENT 테이블 : 학생정보를 저장하기 위한 테이블
// => 학번(숫자형 - PK), 이름(문자형), 전화번호(문자형), 주소(문자형), 생년월일(날짜형)
//CREATE TABLE STUDENT(NO NUMBER(4) PRIMARY KEY,NAME VARCHAR2(50),PHONE VARCHAR2(20)
//	,ADDRESS VARCHAR2(100),BIRTHDAY DATE);

//STUDENT 테이블에 행(학생정보)을 삽입하여 저장하는 JDBC 프로그램 작성
public class InsertStudentApp {
	public static void main(String[] args) {
		//JDBC 관련 객체를 저장하기 위한 참조변수 선언
		// => try 영역을 포함한 모든 영역에서 참조변수로 객체를 참조하여 메소드 호출 가능
		Connection con=null;
		Statement stmt=null;
		try {
			/*
			//1.OracleDriver 클래스를 객체로 생성하여 DriverManager 클래스에 JDBC Driver 객체로 등록
			// => 동일한 Driver 클래스로 생성된 다수의 객체가 DriverManager 클래스에 JDBC Driver 객체로 등록 가능
			// => 불필요한 자원이 존재해 프로그램 성능 저하 
			//OracleDriver 클래스 : Oracle DBMS 서버에 접속할 수 있는 기능을 제공하는 Driver 객체를 
			//생성하기 위한 클래스
			//Driver 객체 : DBMS 서버에 접속할 수 있는 기능을 제공하기 위한 객체
			//DriverManager 클래스 : Driver 객체를 관리하기 위한 기능을 제공하는 클래스
			//DriverManager.registerDriver(Driver driver) : 매개변수로 전달받은 Driver 객체를
			//DriverManager 클래스가 관리할 수 있는 JDBC Driver 객체로 등록하는 정적메소드 
			DriverManager.registerDriver(new OracleDriver());
			
			//DriverManager 클래스가 관리하는 Driver 객체를 반환받아 출력 처리
			Enumeration<Driver> drivers=DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = (Driver) drivers.nextElement();
				System.out.println(driver);
			}
			*/
			
			//1.Class.forName(String className) 메소드를 호출하여 OracleDriver 클래스를 
			//제공받아 Class 객체로 생성해 메모리에 저장
			// => OracleDriver 클래스의 정적영역에서 OracleDriver 클래스로 객체를 생성하여 
			//DriverManager 클래스에 JDBC Driver 객체로 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");			

			/*
			Enumeration<Driver> drivers=DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				Driver driver = (Driver) drivers.nextElement();
				System.out.println(driver);
			}
			*/
			
			//2.DBMS 서버를 접속하여 Connection 객체를 반환받아 저장
			//DriverManager.getConnection(String url, String username, String password)
			// => DBMS 서버를 접속하여 Connection 객체를 반환하는 정적 메소드
			// => DriverManager 클래스에 등록된 JDBC Driver 객체로 매개변수로 전달받은 값을
			//사용해 DBMS 서버에 접속
			// => DBMS 서버 접속에 성공하면 Connection 객체를 반환하고 실패한 경우 SQLException 발생
			//Connection 객체 : DBMS 서버에 접속된 정보를 저장한 객체
			//SQLException : JDBC 관련 객체로 메소드를 호출한 경우 발생되는 예외
			// => SQLException 객체에는 예외가 발생된 DBMS 관련 에러메세지 저장
			//URL(Uniform Resource Location) : 인터넷에 존재하는 자원의 위치를 표현하기 위한 주소
			//형식) Protocol:ServerName:Port:ResourcePath
			//ex) https://www.itwill.xyz:80/test/index.html
			//Oracle DBMS 서버에 접속해 전역 데이타베이스(XE)에 접근하기 위한 URL 주소
			//형식) jdbc:oracle:thin:@ServerName:Port:SID
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String username="scott";
			String password="tiger";
			con=DriverManager.getConnection(url, username, password);
			
			//3.Connection 객체로 메소드를 호출하여 Statement 객체를 반환받아 저장
			//Connection.createStatement() : Connection 객체에 SQL 명령을 전달하는 Statement 객체를
			//반환하는 메소드
			//Statement 객체 : 접속된 DBMS 서버에 SQL 명령을 전달하는 기능을 제공하는 객체
			stmt=con.createStatement();
			
			//4.Statement 객체로 메소드를 호출하여 DBMS 서버에 SQL 명령을 전달하여 실행해
			//실행 결과를 반환받아 저장 
			//Statement.executeUpdate(String sql) : DML(INSERT, UPDATE, DELETE) 명령을 전달하여
			//실행하는 메소드 - 조작행의 갯수를 정수값(int)로 반환
			//Statement.executeQuery(String sql) : DQL(SELECT) 명령을 전달하여 실행하는 메소드
			// => SELECT 명령의 실행결과로 검색된 행이 저장된 ResultSet 객체 반환
			//전달하여 실행될 SQL 명령을 잘못된 경우 SQLException 발생
			//String sql="insert into student values(1000,'홍길동','010-1234-5678','서울시 강남구','01/01/01')";
			//String sql="insert into student values(2000,'임꺽정','010-2378-2341','수원시 월정구','02/05/09')";
			String sql="insert into student values(3000,'전우치','010-7214-3911','인천시 상당구','1998-12-11')";
			int rows=stmt.executeUpdate(sql);
			
			//5.SQL 명령을 실행하여 반환받은 정수값 또는 ResultSet 객체를 사용해 출력 처리
			System.out.println("[메세지]"+rows+"명의 학생정보를 삽입하여 저장 하였습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러]DBMS 관련 오류 = "+e.getMessage());
		} finally {
			try {
				//6.JDBC 관련 객체를 모든 제거 - 객체가 생성된 순서의 반대로 제거
				// => DBMS 서버 접속을 종료하기 위해 JDBC 관련 객체를 제거
				//Statement.close() : Statement 객체를 제거하는 메소드
				// => NullPointerException 발생 가능 - if 명령을 사용해 예외 발생 방지
				//NullPointerException : 참조변수에 NULL이 저장된 상태에서 메소드를 호출한 경우 발생되는 예외
				if(stmt!=null) stmt.close();
				//Connection.close() : Connection 객체를 제거하는 메소드 - DBMS 서버 접속 종료
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
}