package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xyz.itwill.dto.AjaxMemberDTO;
import xyz.itwill.dto.AjaxMemberDTO;

public class AjaxMemberDAO extends JdbcDAO{
	private static AjaxMemberDAO _dao;
	
	public AjaxMemberDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao= new AjaxMemberDAO();
	}
	
	public static AjaxMemberDAO getDAO() {
		return getDAO();
	}
	
	public int insertAjaxMember (AjaxMemberDTO ajaxmMembe)  {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		
		try {
			con = getConnection();
		    
			String sql = "insert inro ajax_member values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ajaxmMembe.getId());
			pstmt.setString(2, ajaxmMembe.getPasswd());
			pstmt.setString(3, ajaxmMembe.getName());
			pstmt.setString(4, ajaxmMembe.getEmail());
			
		} catch (SQLException e) {
			System.out.println("에러");
		} finally {
			close(con, pstmt);
			
		}
		return rows;
		
	}

	
	public int selectAjaxMember (String id)  {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rows = 0;
		AjaxMemberDTO ajaxmMembe = null;
		try {
			con = getConnection();
		    
			String sql = "insert inro ajax_member values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ajaxmMembe.getId());
			pstmt.setString(2, ajaxmMembe.getPasswd());
			pstmt.setString(3, ajaxmMembe.getName());
			pstmt.setString(4, ajaxmMembe.getEmail());
			
		} catch (SQLException e) {
			System.out.println("에러");
		} finally {
			close(con, pstmt);
			
		}
		return rows;
		
	}
}
