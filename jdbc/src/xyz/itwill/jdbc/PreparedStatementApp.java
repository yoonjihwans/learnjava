package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//PreparedStatement 객체 : DBMS 서버에 SQL 명령을 전달하여 실행하기 위한 기능을 제공하는 객체
// => PreparedStatement 객체에는 DBMS 서버에 전달할 SQL 명령 저장
//장점 : InParameter를 사용하여 SQL 명령에 Java 변수값을 문자값으로 포함하여 사용 가능
// => InParameter를 사용해 가독성이 향상되고 유지보수의 효율성 증가
// => InParameter로 전달받은 값은 무조건 문자값으로 처리되므로 InSQL 해킹 기술을 무효화 처리 
//단점 : 하나의 PreparedStatement 객체로 하나의 SQL 명령만 전달하여 실행 가능
public class PreparedStatementApp {
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		/*
		//키보드로 학생정보를 입력받아 STUDENT 테이블에 행으로 삽입하여 저장하고 STUDENT 테이블에
		//저장된 모든 행(학생정보)을 검색하여 출력하는 JDBC 프로그램 작성
		
		//키보드로 학생정보를 입력받기 위한 입력스트림 생성
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
				
		//키보드로 학생정보를 입력받아 저장
		System.out.println("<<학생정보 입력>>");
		System.out.print("학번 입력 >> ");
		//키보드로 입력받아 반환된 문자열을 정수값으로 변환하여 저장
		int no=Integer.parseInt(in.readLine());
		System.out.print("이름 입력 >> ");
		String name=in.readLine();
		System.out.print("전화번호 입력 >> ");
		String phone=in.readLine();
		System.out.print("주소 입력 >> ");
		String address=in.readLine();
		System.out.print("생년월일 입력 >> ");
		String birthday=in.readLine();
		System.out.println("==============================================================");
		//키보드로 입력받은 학생정보를 STUDENT 테이블에 행으로 삽입하여 저장
		Connection con=ConnectionFactory.getConnection();
		
		//Connection.prepareStatement(String sql) : 매개변수로 전달받은 SQL 명령이 저장된
		//PreparedStatement 객체를 반환하는 메소드
		// => PreparedStatement 객체에 저장되는 SQL 명령에서는 ?(InParameter) 기호 사용
		//InParameter : Java 변수값을 제공받아 SQL 명령의 문자값으로 포함하기 위한 기호
		String sql1="insert into student values(?,?,?,?,?)";//미완성된 SQL 명령
		PreparedStatement pstmt=con.prepareStatement(sql1);//InParameter가 포함된 SQL 명령 저장
		//PreparedStatement.setXXX(int parameterIndex, XXX value) : PreparedStatement 객체에
		//저장된 SQL 명령의 InParameter에 Java 변수값을 전달하여 SQL 명령에 포함하는 메소드
		// => XXX : InParameter에 전달하기 위한 변수값의 Java 자료형
		// => parameterIndex : InParameter의 첨자(1부터 1씩 증가되는 정수값)를 전달하여 저장
		// => 모든 InParameter에 Java 변수값을 전달해야만 SQL 명령을 완성하여 DBMS 서버에
		//전달하여 실행 가능 - 미완성된 SQL 명령을 전달하여 실행할 경우 예외 발생
		pstmt.setInt(1, no);
		pstmt.setString(2, name);
		pstmt.setString(3, phone);
		pstmt.setString(4, address);
		pstmt.setString(5, birthday);
		
		//PreparedStatement.executeUpdate() : PreparedStatement 객체에 저장된 SQL 명령(INSERT
		//, UPDATE, DELETE)을 전달하여 실행하고 조작행의 갯수를 정수값(int)으로 반환하는 메소드
		int rows=pstmt.executeUpdate();
		
		System.out.println(rows+"명의 학생정보를 삽입하여 저장 하였습니다.");
		System.out.println("==============================================================");
		//STUDENT 테이블에 저장된 모든 행(학생정보)을 검색하여 출력
		String sql2="select no,name,phone,address,birthday from student order by no";
		pstmt=con.prepareStatement(sql2);
		
		//PreparedStatement.executeUpdate() : PreparedStatement 객체에 저장된 SQL 명령(SELECT)을
		//전달하여 실행하고 검색행을 ResultSet 객체로 반환하는 메소드
		ResultSet rs=pstmt.executeQuery();
		
		System.out.println("<<학생정보 출력>>");
		while(rs.next()) {
			System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")
				+", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")
				+", 생년월일 = "+rs.getString("birthday").substring(0, 10));
		}
		System.out.println("==============================================================");
		ConnectionFactory.close(con, pstmt, rs);
		*/
		
		//키보드로 이름을 입력받아 STUDENT 테이블의 NAME 컬럼에 해당 이름이 저장된 행만  
		//검색하여 출력하는 JDBC 프로그램 작성 - 조회

		//키보드로 학생정보를 입력받기 위한 입력스트림 생성
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
				
		//키보드로 학생정보를 입력받아 저장
		System.out.println("<<학생정보 조회>>");
		System.out.print("이름 입력 >> ");
		String name=in.readLine();
		System.out.println("==============================================================");
		//STUDENT 테이블의 NAME 컬럼에 해당 이름이 저장된 행을 검색하여 출력
		Connection con=ConnectionFactory.getConnection();

		String sql="select no,name,phone,address,birthday from student where name=? order by no";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		
		ResultSet rs=pstmt.executeQuery();
		
		System.out.println("<<조회결과>>");
		if(rs.next()) {
			do {
				System.out.println("학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name")
					+", 전화번호 = "+rs.getString("phone")+", 주소 = "+rs.getString("address")
					+", 생년월일 = "+rs.getString("birthday").substring(0, 10));
			} while(rs.next());
		} else {
			System.out.println("입력한 이름의 학생정보를 찾을 수 없습니다.");
		}
		System.out.println("==============================================================");
		ConnectionFactory.close(con, pstmt, rs);
	}
}














