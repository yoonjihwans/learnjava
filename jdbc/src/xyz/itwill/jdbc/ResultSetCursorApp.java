package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetCursorApp {
	public static void main(String[] args) throws SQLException {
		Connection con=ConnectionFactory.getConnection();
		
		//Connection.createStatement() : SQL 명령을 전달하여 실행하기 위한 Statement 객체를 생성하여 반환하는 메소드
		Statement stmt=con.createStatement();
		
		String sql="select no,name from student order by no";
		//Statement.executeQuery(String sql) : SELECT 명령을 전달하여 실행하고 검색된 모든 
		//행이 저장된 ResultSet 객체를 반환하는 메소드
		// => Connection.createStatement() 메소드에 의해 생성된 Statement 객체로 SELECT 명령을
		//전달해 반환받은 ResultSet 객체는 커서를 다음행으로만 이동 가능하며 커서 위치의
		//처리행에 대한 조작 불가능
		ResultSet rs=stmt.executeQuery(sql);
		//System.out.println("rs = "+rs);
		
		//ResultSet.next() : ResultSet 객체에 저장된 검색행을 처리하기 위한 커서를 다음행으로 이동하는 메소드
		// => 이동된 커서 위치에 처리행이 없는 경우 [false]를 반환하고 처리행이 있는 경우 [true] 반환
		while(rs.next()) {
			//ResultSet.getRow() : ResultSet 객체에서 커서 위치의 처리행에 대한 행번호(RowIndex)를 반환하는 메소드
			System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		System.out.println("==============================================================");
		//Connection.createStatement(int resultSetType, int resultSetConcurrency) 
		// => SQL 명령을 전달하여 실행하기 위한 Statement 객체를 생성하여 반환하는 메소드
		// => 매개변수에 전달되는 정수값에 따라 Statement 객체로 생성되어 반환되는 ResultSet
		//객체의 커서에 대한 이동 설정 및 커서가 위치한 처리행의 조작 설정 가능
		//ResultSetType : ResultSet 객체의 커서 이동 관련 속성값(ResultSet 인터페이스에 의해 
		//제공되는 상수 중 하나를 선택하여 사용) 전달
		// => ResultSet.TYPE_FORWARD_ONLY : ResultSet 객체의 커서를 다음행으로만 이동 가능 - 기본값
		// => ResultSet.TYPE_SCROLL_INSENSITIVE : ResultSet 객체의 커서를 자유롭게 이동 가능 - 행에 대한 변경 미반영
		// => ResultSet.TYPE_SCROLL_SENSITIVE : ResultSet 객체의 커서를 자유롭게 이동 가능 - 행에 대한 변경 반영
		//resultSetConcurrency : ResultSet 객체의 커서 위치에 처리행 조작 관련 속성값(ResultSet
		//인터페이스에 의해  제공되는 상수 중 하나를 선택하여 사용) 전달 
		// => ResultSet.CONCUR_READ_ONLY : ResultSet 객체의 커서 위치에 처리행 조작 불가능
		// => ResultSet.CONCUR_UPDATABLE : ResultSet 객체의 커서 위치에 처리행 조작 가능
		stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		sql="select no,name from student order by no";
		rs=stmt.executeQuery(sql);
		//System.out.println("rs = "+rs);
		
		//ResultSet.first() : ResultSet 객체의 커서를 첫번째 행으로 이동하는 메소드
		rs.first();
		System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));

		//ResultSet.first() : ResultSet 객체의 커서를 마지막 행으로 이동하는 메소드
		rs.last();
		System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		
		//ResultSet.absolute(int rowIndex) : 매개변수로 전달받은 행첨자 위치로 ResultSet 객체의
		//커서를 이동하는 메소드
		rs.absolute(2);
		System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		System.out.println("==============================================================");
		//ResultSet.afterLast() : ResultSet 객체의 EOF(End Of File) 영역으로 커서를 이동하는 메소드
		rs.afterLast();
		
		//ResultSet.previous() : ResultSet 객체의 이전행으로 커서를 이동하는 메소드
		while(rs.previous()) {
			System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		System.out.println("==============================================================");
		//ResultSet.beforeFirst() : ResultSet 객체의 BOF(Before Of File) 영역으로 커서를 이동하는 메소드
		rs.beforeFirst();
		
		while(rs.next()) {
			System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		System.out.println("==============================================================");
		stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		sql="select no,name,phone,address,birthday from student order by no";
		rs=stmt.executeQuery(sql);
		//System.out.println("rs = "+rs);
		
		/*
		rs.absolute(2);//ResultSet 객체의 커서를 2번째 행으로 이동
		//ResultSet.updateXXX(int columnLabek, XXX value) : ResultSet 객체의 커서가 위치한
		//처리행의 컬럼값을 변경하는 메소드
		// => XXX는 컬럼값을 변경하기 위한 Java 자료형
		rs.updateString("name", "임걱정");
		
		//ResultSet.updateRow() : ResultSet 객체의 커서가 위치한 처리행을 테이블에 적용하여
		//행을 변경 처리하는 메소드 - UPDATE 명령을 전달하여 실행한 것과 동일한 결과 제공 
		rs.updateRow();
		*/
		
		/*
		rs.absolute(3);//ResultSet 객체의 커서를 3번째 행으로 이동
		
		//ResultSet.moveToInsertRow() : ResultSet 객체의 커서가 위치한 다음행에 새로운 행을
		//삽입하여 커서를 이동하고 기존 행은 다음행을 차례대로 이동 처리하는 메소드
		rs.moveToInsertRow();
		
		//ResultSet 객체의 커서가 위치한 삽입행의 컬럼값 변경
		rs.updateInt("no", 4000);
		rs.updateString("name", "일지매");
		rs.updateString("phone", "010-6367-1347");
		rs.updateString("address", "서울시 종로구");
		rs.updateString("birthday", "2000-12-31");

		//ResultSet.insertRow() : ResultSet 객체의 커서가 위치한 처리행을 테이블에 적용하여
		//행을 삽입 처리하는 메소드 - INSERT 명령을 전달하여 실행한 것과 동일한 결과 제공 
		rs.insertRow();
		*/
		
		rs.absolute(4);//ResultSet 객체의 커서를 4번째 행으로 이동
		
		//ResultSet.deleteRow() : ResultSet 객체의 커서가 위치한 처리행을 테이블에 적용하여
		//행을 삭제 처리하는 메소드 - DELETE 명령을 전달하여 실행한 것과 동일한 결과 제공 
		rs.deleteRow();
		
		rs.beforeFirst();
		while(rs.next()) {
			System.out.println(rs.getRow()+ "행 : 학번 = "+rs.getInt("no")+", 이름 = "+rs.getString("name"));
		}
		System.out.println("==============================================================");
		ConnectionFactory.close(con, stmt, rs);
	}
}