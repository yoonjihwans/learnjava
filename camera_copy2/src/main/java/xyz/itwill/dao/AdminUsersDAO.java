package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import xyz.itwill.dto.AdminUsersDTO;

public class AdminUsersDAO extends JdbcDAO{
	private static AdminUsersDAO _dao;
	static {
		_dao=new AdminUsersDAO();
	}
	public static AdminUsersDAO getDAO() {
		return _dao;
	}
	
	public List<AdminUsersDTO> selectUsersList(String search, String keyword){
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AdminUsersDTO> usersList=new ArrayList<>();
		try {
			con=getConnection();
			
			if(keyword.equals("")) {
			String sql="SELECT USERS_NO,USERS_ID,USERS_PW,USERS_NAME,USERS_ZIPCODE,"
					+ "USERS_ADDRESS1,USERS_ADDRESS2,USERS_PHONE,USERS_EMAIL,USERS_SIGNDATE,"
					+ "USERS_LAST_LOGIN,USERS_STATUS FROM USERS ORDER BY USERS_NO";
			pstmt=con.prepareStatement(sql);
			}else {
				String sql="SELECT USERS_NO,USERS_ID,USERS_PW,USERS_NAME,USERS_ZIPCODE,"
						+ "USERS_ADDRESS1,USERS_ADDRESS2,USERS_PHONE,USERS_EMAIL,USERS_SIGNDATE,"
						+ "USERS_LAST_LOGIN,USERS_STATUS FROM users where "+search+" like '%'||?||'%' ORDER BY USERS_NO";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AdminUsersDTO users=new AdminUsersDTO();
				users.setUsersNo(rs.getInt("USERS_NO"));
				users.setUsersId(rs.getString("USERS_ID"));
				users.setUsersPw(rs.getString("USERS_PW"));
				users.setUsersName(rs.getString("USERS_NAME"));
				users.setUsersZipcode(rs.getString("USERS_ZIPCODE"));
				users.setUsersAddress1(rs.getString("USERS_ADDRESS1"));
				users.setUsersAddress2(rs.getString("USERS_ADDRESS2"));
				users.setUsersPhone(rs.getString("USERS_PHONE"));
				users.setUsersEmail(rs.getString("USERS_EMAIL"));
				users.setUsersSigndate(rs.getString("USERS_SIGNDATE"));
				users.setUsersLastLogin(rs.getString("USERS_LAST_LOGIN"));
				users.setUsersStatus(rs.getInt("USERS_STATUS"));
				
				usersList.add(users);	
			}
			
			
		}catch (SQLException e) {
			System.out.println("[에러]selectUsersList 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt,rs);
		}
		return usersList;
	}
	
	public int updateUsers(AdminUsersDTO users) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="update users set Users_No=?,Users_Id=?,USERS_PW=?,Users_status=? where Users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, users.getUsersNo());
			pstmt.setString(2, users.getUsersId());
			pstmt.setString(3, users.getUsersPw());
			pstmt.setInt(4, users.getUsersStatus());
			pstmt.setInt(5, users.getUsersNo());
			
			rows=pstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println("[에러]updateUsers 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con, pstmt);
		}
	
	return rows;
	}
	public AdminUsersDTO selectUsers(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AdminUsersDTO users=null;
		try {
			con=getConnection();
			String sql="SELECT USERS_NO,USERS_ID,USERS_PW,USERS_STATUS FROM USERS where Users_No=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				users=new AdminUsersDTO();
				users.setUsersNo(rs.getInt("USERS_NO"));
				users.setUsersId(rs.getString("USERS_ID"));
				users.setUsersPw(rs.getString("USERS_PW"));
//				users.setUsersName(rs.getString("USERS_NAME"));
				users.setUsersStatus(rs.getInt("USERS_STATUS"));
			}
		
		
		}catch (SQLException e) {
			System.out.println("[에러]selectStudent 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return users;
		
	}
	
	public int updateUsersStatus(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();

			String sql="update users set users_status=0 where users_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);

			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateAuth() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	/*
	 * public UsersDTO selectUsers(int no) { Connection con=null; PreparedStatement
	 * pstmt=null; ResultSet rs=null; UsersDTO users=null; try {
	 * con=getConnection(); String
	 * sql="SELECT USERS_NO,USERS_ID,USERS_PW,USERS_NAME,USERS_ZIPCODE," +
	 * "USERS_ADDRESS1,USERS_ADDRESS2,USERS_PHONE,USERS_EMAIL,USERS_SIGNDATE," +
	 * "USERS_LAST_LOGIN,USERS_STATUS FROM USERS where User_No=? ";
	 * pstmt=con.prepareStatement(sql); rs=pstmt.executeQuery(); if(rs.next()) {
	 * users=new UsersDTO(); users.setUsersNo(rs.getInt("USERS_NO"));
	 * users.setUsersId(rs.getString("USERS_ID"));
	 * users.setUsersPw(rs.getString("User_PW"));
	 * users.setUsersName(rs.getString("USERS_NAME"));
	 * users.setUsersZipcode(rs.getString("USERS_ZIPCODE"));
	 * users.setUsersAddress1(rs.getString("USERS_ADDRESS1"));
	 * users.setUsersAddress2(rs.getString("USERS_ADDRESS2"));
	 * users.setUsersPhone(rs.getString("USERS_PHONE"));
	 * users.setUsersEmail(rs.getString("USERS_EMAIL"));
	 * users.setUsersSigndate(rs.getString("USERS_SIGNDATE"));
	 * users.setUsersLastLogin(rs.getString("USERS_LAST_LOGIN"));
	 * users.setUsersStatus(rs.getInt("USERS_STATUS")); }
	 * 
	 * 
	 * }catch (SQLException e) {
	 * System.out.println("[에러]selectStudent 메소드의 SQL 오류 = "+e.getMessage()); }
	 * finally { close(con, pstmt, rs); } return users;
	 * 
	 * }
	 */	

}

