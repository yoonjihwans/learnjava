package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//STUDENT 테이블에서 NO 컬럼값(학번)이 [3000]인 행(학생정보)를 삭제하는 JDBC 프로그램 작성
public class DeleteStudentApp {
	public static void main(String[] args) {
		Connection con=null;
		Statement stmt=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String username="scott";
			String password="tiger";
			con=DriverManager.getConnection(url, username, password);
			 
			stmt=con.createStatement();
			
			String sql="delete from student where no=3000";
			int rows=stmt.executeUpdate(sql);
			
			System.out.println("[메세지]"+rows+"명의 학생정보를 삭제 하였습니다.");					
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러]DBMS 관련 오류 = "+e.getMessage());
		} finally {
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}