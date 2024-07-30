package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.AdminNoticeDTO;

public class AdminNoticeDAO extends JdbcDAO{
	private static AdminNoticeDAO _dao;
	public AdminNoticeDAO() {
		// TODO Auto-generated constructor stub
	}
	static {
		_dao=new AdminNoticeDAO();
	}
	public static AdminNoticeDAO getDAO() {
		return _dao;
		
	}
	
	public List<AdminNoticeDTO> selectNoticeList(String search,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AdminNoticeDTO> adminNotice=new ArrayList<>();
		
		try {
			con=getConnection();
			
			if(keyword.equals("")) {
				String sql="select Notice_No,Notice_Title,Notice_Content,Notice_Status,Notice_Date from Notice";
				pstmt=con.prepareStatement(sql);
			}else {
				String sql="select Notice_No,Notice_Title,Notice_Content,Notice_Status,Notice_Date from Notice where "+search+" like '%'||?||'%' order by Notice_no";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AdminNoticeDTO notice=new AdminNoticeDTO();
				notice.setNoticeNo(rs.getInt("Notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeStatus(rs.getInt("Notice_status"));
				notice.setNoticeDate(rs.getString("notice_date"));
				
				adminNotice.add(notice);
				
				
			}
			
		}catch (SQLException e) {
			System.out.println("[에러]selectNoticeList 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt,rs);
		}
		return adminNotice;
	}
	
	
	public int insertNotice(AdminNoticeDTO notice) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="insert into notice values(notice_seq.nextval,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeStatus());
			
			rows=pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			System.out.println("[에러]insertNotice() 메서드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt);
		}
		return rows;
		
	}
	public AdminNoticeDTO selectNoticeNo(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AdminNoticeDTO notice=null;
		try {
			con=getConnection();
			String sql="select Notice_No,Notice_Title,Notice_Content,Notice_Status from Notice where Notice_No=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				notice = new AdminNoticeDTO();
				notice.setNoticeNo(rs.getInt("Notice_No"));
				notice.setNoticeTitle(rs.getString("Notice_Title"));
				notice.setNoticeContent(rs.getString("Notice_Content"));
				notice.setNoticeStatus(rs.getInt("Notice_Status"));
//				notice.setNoticeDate(rs.getString("Notice_Date"));
			}
			
			
		}catch (SQLException e) {
			System.out.println("[에러]selectNoticeNo 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return notice;
		
	}
	public int updateNotice(AdminNoticeDTO notice) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="update notice set Notice_No=?,Notice_Title=?,Notice_Content=?,Notice_Status=? where Notice_No=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, notice.getNoticeNo());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setInt(4, notice.getNoticeStatus());
			pstmt.setInt(5, notice.getNoticeNo());
			
			rows=pstmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("[에러]updateNotice 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con, pstmt);
		}
	
	return rows;
		
	}
	
}
