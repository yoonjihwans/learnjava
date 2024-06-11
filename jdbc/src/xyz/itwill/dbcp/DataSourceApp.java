package xyz.itwill.dbcp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//DBCP(DataBase Connection Pool) : 다수의 Connection 객체를 미리 생성하여 저장해 JDBC 프로그램
//작성시 제공하기 위한 기능의 객체
// => Connection 객체를 미리 생성해 제공하므로 JDBC 프로그램의 실행 속도 증가
// => Connection 객체를 생성하기 위한 정보의 변경 용이 - 생산성 및 유지보수의 효율성 증가
// => Connection 객체를 갯수를 제한 가능 - 자원의 낭비를 최소화

//DBCP 객체를 생성하기 위한 클래스는 DataSource 인터페이스를 상속받아 작성
// => 모든 DBCP 클래스가 동일한 메소드로 작성되도록 메소드 작성 규칙 제공
// => 클래스의 결합도를 낮춰 유지보수의 효율성 증가

//Oracle 그룹에서 배포한 UCP(Universal Connection Pool) 라이브러리의 DBCP 클래스를 사용해
//JDBC 프로그램 작성
// => https://www.oracle.com 사이트에서 UCP 라이브러리 파일(ucp11.jar)을 다운로그 받아
//프로젝트에 빌드 처리
public class DataSourceApp {
	public DataSourceApp() throws IOException, SQLException {
		InputStream in=getClass().getClassLoader().getResourceAsStream("xyz/itwill/dbcp/dbcp.properties");
		Properties properties=new Properties();
		properties.load(in);
		String driver=(String)properties.get("driver");
		String url=(String)properties.get("url");
		String user=(String)properties.get("user");
		String password=(String)properties.get("password");
		String initCons=(String)properties.get("initCons");
		String maxCons=(String)properties.get("maxCons");
		
		//DataSource 객체(PoolDataSource 객체 - DBCP 객체)를 반환받아 저장
		//PoolDataSourceFactory.getPoolDataSource() : PoolDataSource 객체를 반환하는 정적 메소드
		//PoolDataSource pds=new PoolDataSourceImpl();
		PoolDataSource pds=PoolDataSourceFactory.getPoolDataSource();
		
		//PoolDataSource 객체에 저장될 Connection 객체를 미리 생성하기 위한 메소드 호출 - 필수
		//PoolDataSource.setConnectionFactoryClassName(String driver) : JDBC Driver 클래스를 변경하는 메소드
		pds.setConnectionFactoryClassName(driver);
		//PoolDataSource.setURL(String url) : DBMS 서버에 접속하기 위한 URL 주소를 변경하는 메소드
		pds.setURL(url);
		//PoolDataSource.setUser(String user) : DBMS 서버에 접속하기 위한 계정명을 변경하는 메소드
		pds.setUser(user);
		//PoolDataSource.setPassword(String password) : DBMS 서버에 접속하기 위한 비밀번호를 변경하는 메소드
		pds.setPassword(password);
		
		//PoolDataSource 객체에 저장될 Connection 객체의 갯수를 제한 - 선택 : 생략시 기본값 사용
		//PoolDataSource.setInitialPoolSize(int size) : PoolDataSource 객체에 최초 생성될 
		//Connection 객체의 갯수를 변경하는 메소드
		pds.setInitialPoolSize(Integer.parseInt(initCons));
		//PoolDataSource.setMaxPoolSize(int size) : PoolDataSource 객체에 생성되어 저장할 수 
		//있는 최대 Connection 객체의 갯수를 변경하는 메소드
		pds.setMaxPoolSize(Integer.parseInt(maxCons));
		
		//PoolDataSource.getConnection() : PoolDataSource 객체에 저장된 Connection 객체 중
		//하나를 반환하는 메소드 - PoolDataSource 객체의 반환된 Connection 객체는 비활성화 처리 
		Connection con1=pds.getConnection();
		System.out.println("con1 = "+con1);
		//Connection 객체로 close() 메소드를 호출하면 PoolDataSource 객체에 저장된 Connection
		//객체를 활성화 처리
		//con1.close();
		
		Connection con2=pds.getConnection();
		System.out.println("con2 = "+con2);
		//con2.close();
		
		Connection con3=pds.getConnection();
		System.out.println("con3 = "+con3);
		//con3.close();
		
		Connection con4=pds.getConnection();
		System.out.println("con4 = "+con4);
		con4.close();
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		new DataSourceApp();
	}
}