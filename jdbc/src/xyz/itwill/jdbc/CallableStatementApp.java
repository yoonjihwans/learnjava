package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/*
CREATE OR REPLACE PROCEDURE DELETE_STUDENT(VNO IN STUDENT.NO%TYPE,VNAME OUT STUDENT.NAME%TYPE) IS
BEGIN
    SELECT NAME INTO VNAME FROM STUDENT WHERE NO=VNO;
    IF SQL%FOUND THEN
        DELETE FROM STUDENT WHERE NO=VNO;
        COMMIT;
    END IF;
EXCEPTION
    WHEN OTHERS THEN  
        VNAME := NULL;
END;
/
*/

//CallableStatement 객체 : 저장 프로시저를 호출하는 기능을 제공하기 위한 객체

//키보드로 학번을 입력받아 STUDENT 테이블에서 NO 컬럼값이 입력된 학번의 행을 삭제하는 JDBC
//프로그램 작성 - 저장 프로시저를 호출하여 STUDENT 테이블의 행을 삭제 처리
public class CallableStatementApp {
	public static void main(String[] args) throws NumberFormatException, IOException, SQLException  {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
				
		System.out.println("<<학생정보 삭제>>");
		System.out.print("학번 입력 >> ");
		int no=Integer.parseInt(in.readLine());
		System.out.println("==============================================================");
		Connection con=ConnectionFactory.getConnection();
		
		//Connection.prepareCall(String sql) : 저장 프로시저를 호출하는 명령이 저장된
		//CallableStatement 객체를 반환하는 메소드
		// => 저장 프로시저를 호출하는 명령 >> 형식) {call 저장프로시저명[(?,?,...)]}
		// => 저장 프로시저를 호출시 저장 프로시저의 매개변수에 값 또는 변수를 전달하기 위해
		//? 기호(InParamter 또는 OutParameter) 사용
		// => 저장 프로시저의 InParameter에는 CallableStatement 객체로 setXXX(int parameterIndex,
		//XXX value) 메소드를 호출하여 Java 변수값을 전달
		// => 저장 프로시저의 OutParameter에는 CallableStatement 객체로 registerOutParameter
		//(int parameterIndex, int sqlType) 메소드를 호출하여 프로시저 실행값을 제공하는 자료형 설정
		String sql="{call delete_student(?,?)}";
		CallableStatement cstmt=con.prepareCall(sql);
		
		//CallableStatement.setXXX(int parameterIndex, XXX value) : 저장 프로시저의 InParameter에
		//변수값을 전달하기 위한 메소드
		cstmt.setInt(1, no);
		
		//CallableStatement.registerOutParameter(int parameterIndex, int sqlType) : 자정 프로시저의
		//OutParameter에 저장된 프로시저 실행값을 제공받기 위한 메소드
		// => sqlType 매개변수에는 DBMS 자료형을 Types 클래스의 상수를 사용하여 전달
		cstmt.registerOutParameter(2, Types.NVARCHAR);
		
		//CallableStatement.execute() : 저장 프로시저을 호출하는 명령을 전달하여 실행하는 메소드
		cstmt.execute();
		
		//CallableStatement.getXXX(int parameterIndex) : 저장 프로시저의 OutParameter에 저장된 값을
		//반환하는 메소드
		String name=cstmt.getString(2);
		
		if(name==null) {//삭제된 행이 없는 경우
			System.out.println("[메세지]입력된 학번의 학생정보를 찾을 수 없습니다.");
		} else {//삭제된 행이 있는 경우
			System.out.println("[메세지]"+name+"님의 학생정보를 삭제 하였습니다.");
		}
		System.out.println("==============================================================");
		ConnectionFactory.close(con, cstmt);
	}
}