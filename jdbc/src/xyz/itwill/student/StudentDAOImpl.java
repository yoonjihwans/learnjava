package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//DAO(Data Access Object) 클래스 : 저장매체에 행을 삽입, 변경, 삭제, 검색하는 기능을 제공하는
//객체를 생성하기 위한 클래스
// => 저장매체 : 데이타를 행단위로 저장하기 위한 하드웨어 또는 소프트웨어 - DataBase >> DBMS
// => 인터페이스를 상속받아 작성하는 것을 권장 - 메소드 작성 규칙을 제공받아 DAO 클래스 작성
// => 인터페이스를 상속받아 DAO 클래스를 작성하는 이유는 DAO 클래스가 변경돼도 DAO 클래스를
//사용하여 클래스에 영향을 최소화 하여 유지보수의 효율성 증가를 위해 사용
// => 프로그램에 하나의 객체만 제공하기 위해 싱글톤 디자인 패턴을 적용하여 작성하는 것을 권장 

//STUDENT 테이블에 행을 삽입, 변경, 삭제, 감색하는 기능의 메소드가 작성된 DAO 클래스
// => DAO 클래스의 메소드는 SQL 명령에 필요한 값을 객체로 매개변수에 전달받아 하나의 SQL 
//명령을 DBMS 서버에 전달하여 실행하고 실행결과를 객체로 매핑(검색행의 컬럼값을 객체 필드에 
//저장) 하여 반환하도록 작성
// => JdbcDAO 클래스를 상속받아 DAO 클래스의 메소드에서 JdbcDAO 클래스의 메소드 호출 가능
public class StudentDAOImpl extends JdbcDAO implements StudentDAO {
	private static StudentDAOImpl _dao;
	
	private StudentDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new StudentDAOImpl();
	}
	
	public static StudentDAOImpl getDAO() {
		return _dao;
	}

	//매개변수로 학생정보(StudentDTO 객체)를 전달받아 STUDENT 테이블에 행으로 삽입하여 저장하고
	//삽입행의 갯수를 반환하는 메소드
	@Override
	public int insertStudent(StudentDTO student) {
		Connection con=null;
		PreparedStatement pstmt=null;
		//SQL 명령의 실행결과를 저장하기 위한 변수 - 메소드 반환값으로 사용
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into student values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, student.getNo());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getPhone());
			pstmt.setString(4, student.getAddress());
			pstmt.setString(5, student.getBirthday());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	//매개변수로 학생정보(StudentDTO 객체)를 전달받아 STUDENT 테이블에 저장된 행을 변경하고
	//변경행의 갯수를 반환하는 메소드
	@Override
	public int updateStudent(StudentDTO student) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update student set name=?,phone=?,address=?,birthday=? where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getPhone());
			pstmt.setString(3, student.getAddress());
			pstmt.setString(4, student.getBirthday());
			pstmt.setInt(5, student.getNo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	//매개변수로 학번(정수값)를 전달받아 STUDENT 테이블에 저장된 행을 삭제하고 삭제행의 갯수를
	//반환하는 메소드
	@Override
	public int deleteStudent(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="delete from student where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteStudent() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	//매개변수로 학번(정수값)를 전달받아 STUDENT 테이블에 저장된 하나의 행(학생정보)을 검색하고 
	//검색된 행(학생정보)을 StudentDTO 객체로 바꾸어 반환하는 메소드
	@Override
	public StudentDTO selectStudentByNo(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StudentDTO student=null;
		try {
			con=getConnection();
			
			String sql="select no,name,phone,address,birthday from student where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();
			
			//검색행이 하나인 경우 선택문을 사용하여 명령 실행
			// => 선택문을 사용해 검색된 행이 있는 경우에만 명령을 실행   
			if(rs.next()) {
				//ResultSet 객체에 저장된 검색행을 DTO 객체로 변환되도록 처리 - 매핑 처리
				// => 검색행의 컬럼값을 DTO 객체의 필드에 저장되도록 명령 작성
				student=new StudentDTO();
				//ResultSet 객체의 커서가 위치한 처리행의 컬럼값을 반환받아 DTO 객체의 필드값으로 변경
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudentByNo() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return student;
	}

	//매개변수로 이름(문자열)를 전달받아 STUDENT 테이블에 저장된 다수의 행(학생정보)을 검색하고 
	//검색된 행(학생정보)을 StudentDTO 객체로 바꾸어 List 객체에 요소로 추가하여 반환하는 메소드	
	@Override
	public List<StudentDTO> selectStudentByName(String name) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<StudentDTO> studentList=new ArrayList<StudentDTO>();
		try {
			con=getConnection();
			
			String sql="select no,name,phone,address,birthday from student where name=? order by no";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs=pstmt.executeQuery();
			
			//검색행이 하나인 경우 반복문을 사용하여 명령 실행
			// => 반복문을 사용해 검색된 행이 있는 경우에만 명령을 실행   
			while(rs.next()) {
				//ResultSet 객체의 커서가 위치한 처리행을 DTO 객체로 변환되도록 처리 - 매핑 처리
				StudentDTO student=new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday"));
				
				//처리행이 변환된 DTO 객체를 List 객체의 요소로 추가하여 저장
				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudentByName() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		
		return studentList;
	}

	//STUDENT 테이블에 저장된 모든 행(학생정보)을 검색하고 검색된 행(학생정보)을 StudentDTO 
	//객체로 바꾸어 List 객체에 요소로 추가하여 반환하는 메소드
	@Override
	public List<StudentDTO> selectStudentAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<StudentDTO> studentList=new ArrayList<StudentDTO>();
		try {
			con=getConnection();
			
			String sql="select no,name,phone,address,birthday from student order by no";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				StudentDTO student=new StudentDTO();
				student.setNo(rs.getInt("no"));
				student.setName(rs.getString("name"));
				student.setPhone(rs.getString("phone"));
				student.setAddress(rs.getString("address"));
				student.setBirthday(rs.getString("birthday"));
				
				studentList.add(student);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectStudentAll() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		
		return studentList;
	}
}