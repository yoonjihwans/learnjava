package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.NoticeDTO;
import xyz.itwill.dto.ReviewDTO;

public class NoticeDAO extends JdbcDAO{
	
	private static NoticeDAO _dao;

	private NoticeDAO() {
		// TODO Auto-generated constructor stub
	}

	static {
		_dao = new NoticeDAO();
	}

	public static NoticeDAO getDAO() {
		return _dao;
	}
	
	// 조회정보(조회대상과 조회단어)를 전달받아 notice 테이블에 저장된 행에서 조회정보가 포함된
		// 행의 갯수를 검색하여 반환하는 메소드
		// => 조회기능을 사용하지 않을 경우 notice 테이블에 저장된 모든 행의 갯수를 검색하여 반환
	public int selectTotalNotice() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectTotalNotice() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}

	// 페이징 관련 정보(시작행번호, 종료행번호)
	// 전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 행을 페이징 처리로 검색하여
	// 검색된 게시글 목록(List 객체)을 반환하는 메소드 //NEED
	public List<NoticeDTO> selectNoticeList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeDTO> noticeList = new ArrayList<NoticeDTO>();
		try {
			con = getConnection();

			String sql = "select * from (select rownum rn, temp.* from (select notice_no"
					+ ",notice_title,notice_content ,notice_status,notice_date from notice"
					+ " ) temp) where rn between ? and ? order by notice_date desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				NoticeDTO notice = new NoticeDTO();
				notice.setNoticeNo(rs.getInt("notice_no"));
				notice.setNoticeTitle(rs.getString("notice_title"));
				notice.setNoticeContent(rs.getString("notice_content"));
				notice.setNoticeStatus(rs.getInt("notice_status"));
				notice.setNoticeDate(rs.getString("notice_date"));

				noticeList.add(notice);
			}

		} catch (SQLException e) {
			System.out.println("[에러]selectNoticeList() 메소드의 sql 오류" + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return noticeList;
	}

	// 게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블의 행으로 삽입하고 삽입행의 갯수를 반환하는 메소드 //NEED
	public int insertNotice(NoticeDTO notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "insert into notice values(?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice.getNoticeNo());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setInt(4, notice.getNoticeStatus());
			pstmt.setString(6, notice.getNoticeDate());

			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertNotice() 메소드의 sql 오류" + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 변경행의 갯수를 /NEED
	// 반환하는 메소드
	public int updateNotice(NoticeDTO notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "update notice set notice_title=?,notice_content=?,"
					+ ",notice_status=?,review_update_date=sysdate where review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice.getNoticeNo());
			pstmt.setString(2, notice.getNoticeTitle());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setInt(4, notice.getNoticeStatus());
			pstmt.setString(5, notice.getNoticeDate());
			
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateNotice() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//REVIEW_SEQ 시퀸스의 다음값을 검색하여 반환하는 메소드
		public int selectNoticeNextNum() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int nextNum=0;
			try {
				con=getConnection();
				
				String sql="select notice_seq.nextval from dual";
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					nextNum=rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("[에러]selectNoticeNextNum() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt, rs);
			}
			return nextNum;
		}
		
		public NoticeDTO selectNoticeByNum(int noticeNo) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			NoticeDTO notice=null;
			try {
				con=getConnection();
				
				String sql="select notice_no,notice_title,notice_content,"
						+ " notice_status,notice_date"
						+ " from notice where notice_no=? and notice_status<>0";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, noticeNo);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					notice=new NoticeDTO();
					notice.setNoticeNo(rs.getInt("notice_no"));
					notice.setNoticeTitle(rs.getString("notice_title"));
					notice.setNoticeContent(rs.getString("notice_content"));
					notice.setNoticeStatus(rs.getInt("notice_status"));
					notice.setNoticeDate(rs.getString("notice_date"));
				}
			} catch (SQLException e) {
				System.out.println("[에러]selectNoticewByNum() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt, rs);
			}
			return notice;
		}

}
