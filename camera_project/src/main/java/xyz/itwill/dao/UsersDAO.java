package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.itwill.dto.UsersDTO;

public class UsersDAO extends JdbcDAO {
	private static UsersDAO _dao;
	
	private UsersDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new UsersDAO();		
	}
	
	public static UsersDAO getDAO() {
		return _dao;
	}
	
	//회원정보(UsersDTO 객체)를 전달받아 USERS 테이블의 행으로 삽입하고 삽입행의 갯수(int)를 반환하는 메소드
	public int insertUsers(UsersDTO users) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into users values(users_seq.nextval,?,?,?,?,?,?,?,?,sysdate,null,1)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users.getUsersId());
			pstmt.setString(2, users.getUsersPw());
			pstmt.setString(3, users.getUsersName());
			pstmt.setString(4, users.getUsersZipcode());
			pstmt.setString(5, users.getUsersAddress1());
			pstmt.setString(6, users.getUsersAddress2());
			pstmt.setString(7, users.getUsersPhone());
			pstmt.setString(8, users.getUsersEmail());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertUsers() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//아이디(String 객체)를 전달받아 USERS 테이블에 저장된 하나의 행을 검색하여 검색된 
	//회원정보(UsersDTO)를 반환하는 메소드
	public UsersDTO selectUsersById(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UsersDTO users=null;
		try {
			con=getConnection();
			
			String sql="select users_no,users_id,users_pw,users_name,users_zipcode"
				+",users_address1,users_address2,users_phone,users_email"
				+",users_signdate,users_last_login,users_status from users where users_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();			
			
			if(rs.next()) {
				users=new UsersDTO();
				users.setUsersNo(rs.getInt("users_no"));
				users.setUsersId(rs.getString("users_id"));
				users.setUsersPw(rs.getString("users_pw"));
				users.setUsersName(rs.getString("users_name"));
				users.setUsersZipcode(rs.getString("users_zipcode"));
				users.setUsersAddress1(rs.getString("users_address1"));
				users.setUsersAddress2(rs.getString("users_address2"));
				users.setUsersPhone(rs.getString("users_phone"));
				users.setUsersEmail(rs.getString("users_email"));
				users.setUsersSigndate(rs.getString("users_signdate"));				
				users.setUsersLastLogin(rs.getString("users_last_login"));
				users.setUsersStatus(rs.getInt("users_status"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectUsersById() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return users;
	}
	
	//회원번호(int)를 전달받아 USERS 테이블에 저장된 하나의 행을 검색하여 검색된 회원정보
	//(UsersDTO)를 반환하는 메소드
	public UsersDTO selectUsersByNo(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		UsersDTO users=null;
		try {
			con=getConnection();
			
			String sql="select users_no,users_id,users_pw,users_name,users_zipcode"
				+",users_address1,users_address2,users_phone,users_email"
				+",users_signdate,users_last_login,users_status from users where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs=pstmt.executeQuery();			
			
			if(rs.next()) {
				users=new UsersDTO();
				users.setUsersNo(rs.getInt("users_no"));
				users.setUsersId(rs.getString("users_id"));
				users.setUsersPw(rs.getString("users_pw"));
				users.setUsersName(rs.getString("users_name"));
				users.setUsersZipcode(rs.getString("users_zipcode"));
				users.setUsersAddress1(rs.getString("users_address1"));
				users.setUsersAddress2(rs.getString("users_address2"));
				users.setUsersPhone(rs.getString("users_phone"));
				users.setUsersEmail(rs.getString("users_email"));
				users.setUsersSigndate(rs.getString("users_signdate"));				
				users.setUsersLastLogin(rs.getString("users_last_login"));
				users.setUsersStatus(rs.getInt("users_status"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectUsersByNo() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return users;
	}
	
	//회원번호(int)를 전달받아 USERS 테이블에 저장된 행에서 마지막 로그인 날짜를 현재 날짜와
	//시간으로 변경하고 변경행의 갯수(int)를 반환하는 메소드
	public int updateLastLogin(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update users set users_last_login=sysdate where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateLastLogin() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//회원정보(UsersDTO 객체)를 전달받아 USERS 테이블에 저장된 행을 변경하고 변경행의 갯수를 
	//반환하는 메소드
	public int updateUsers(UsersDTO users) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update users set users_name=?,users_zipcode=?,users_address1=?,users_address2=?"
					+ "users_phone=?,users_email=? where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users.getUsersName());
			pstmt.setString(2, users.getUsersZipcode());
			pstmt.setString(3, users.getUsersAddress1());
			pstmt.setString(4, users.getUsersAddress2());
			pstmt.setString(5, users.getUsersPhone());
			pstmt.setString(6, users.getUsersEmail());
			pstmt.setInt(7, users.getUsersNo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateUsers() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//회원정보(UsersDTO 객체)를 전달받아 USERS 테이블에 저장된 행의 비밀번호를 변경하고 
	//변경행의 갯수를 반환하는 메소드
	public int updatePassword(UsersDTO users) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update users set users_pw=? where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, users.getUsersPw());
			pstmt.setInt(2, users.getUsersNo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updatePassword() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//회원정보(UsersDTO 객체)를 전달받아 USERS 테이블에 저장된 행의 회원권한을 변경하고 
	//변경행의 갯수를 반환하는 메소드
	public int updateStatus(UsersDTO users) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update users set users_status=? where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users.getUsersStatus());
			pstmt.setInt(2, users.getUsersNo());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateStatus() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
}