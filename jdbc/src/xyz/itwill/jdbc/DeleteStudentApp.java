package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//STUDENT 테이블에서 학번이 [3000]인 행(학생정보)를 삭제하는 JDBC 프로그램 작성
public class DeleteStudentApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String name = "scott";
			String password = "tiger";
			con = DriverManager.getConnection(url, name, password);

			stmt = con.createStatement();

			String sql = "delete from student where no = 3000";
			int rows = stmt.executeUpdate(sql);

			System.out.println(rows + "완료");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을수없음");
		} catch (SQLException e) {
			System.out.println("dbms오류");
		} try {
		  if(con != null) con.close();
		  if(stmt != null) stmt.close();
		  
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
