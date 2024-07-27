package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.AdminQnaDTO;

public class AdminQnaDAO extends JdbcDAO{
	private static AdminQnaDAO _dao;
	public AdminQnaDAO() {
		// TODO Auto-generated constructor stub
	}
	static {
		_dao=new AdminQnaDAO();
	}
	public static AdminQnaDAO getDAO() {
		return _dao;
		
	}
	
	public List<AdminQnaDTO> selectQnaList(String search,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AdminQnaDTO> adminQna=new ArrayList<>();
		
		try {
			con=getConnection();
			
			if(keyword.equals("")) {
				String sql="select Qna_no,Qna_users_no,users_name,users_email,Qna_type,qna_title"
						+ ",qna_content,qna_status,qna_date from qna join users on qna_users_no=users_no order by qna_no";
				pstmt=con.prepareStatement(sql);
			}else {
				String sql="select Qna_no,Qna_users_no,users_name,users_email,Qna_type,qna_title "
						+ ",qna_content,qna_status,qna_date from qna join users on qna_users_no=users_no where "+search+" like '%'||?||'%' order by qna_no";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AdminQnaDTO qna=new AdminQnaDTO();
				qna.setQnaNo(rs.getInt("qna_no"));
				qna.setQnaUsersno(rs.getInt("Qna_users_no"));
				qna.setUsersName(rs.getString("users_name"));
				qna.setUsersEmail(rs.getString("users_email"));
				qna.setQnaType(rs.getString("Qna_type"));
				qna.setQnaTitle(rs.getString("qna_title"));
				qna.setQnaContent(rs.getString("qna_content"));
				qna.setQnaStatus(rs.getInt("qna_status"));
				qna.setQnaDate(rs.getString("qna_date"));
				
				adminQna.add(qna);
				
				
			}}catch (SQLException e) {
				System.out.println("[에러]selectQnaList 메소드의 SQL 오류 = "+e.getMessage());
			}finally {
				close(con,pstmt,rs);
			}
			return adminQna;
		}
	
	public AdminQnaDTO selectQnaNo(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AdminQnaDTO qna=null;
		try {
			con=getConnection();
			String sql ="select Qna_no,Qna_users_no,users_name,users_email,Qna_type,qna_title,qna_content"
					+ ",qna_status from qna join users on qna_users_no=users_no where Qna_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				qna = new AdminQnaDTO();
				qna.setQnaNo(rs.getInt("qna_no"));
				qna.setQnaUsersno(rs.getInt("Qna_users_no"));
				qna.setUsersName(rs.getString("users_name"));
				qna.setUsersEmail(rs.getString("users_email"));
				qna.setQnaType(rs.getString("Qna_type"));
				qna.setQnaTitle(rs.getString("qna_title"));
				qna.setQnaContent(rs.getString("qna_content"));
				qna.setQnaStatus(rs.getInt("qna_status"));
				
				
			}
			
		}catch (SQLException e) {
			System.out.println("[에러]selectQnaNo 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt,rs);
		}
		return qna;
	}
	
	public int updateQna(AdminQnaDTO qna) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="update qna set Qna_no=?,Qna_Users_no=?,Qna_type=?,qna_title=?,qna_content=?,qna_status=? where qna_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna.getQnaNo());
			pstmt.setInt(2, qna.getQnaUsersno());
			pstmt.setString(3, qna.getQnaType());
			pstmt.setString(4, qna.getQnaTitle());
			pstmt.setString(5, qna.getQnaContent());
			pstmt.setInt(6, qna.getQnaStatus());
			pstmt.setInt(7, qna.getQnaNo());
			
			
			rows=pstmt.executeUpdate();
			
			
		}catch (SQLException e) {
			System.out.println("[에러]updateQna 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt);
		}
		return rows;
	}
		
	
	

}
