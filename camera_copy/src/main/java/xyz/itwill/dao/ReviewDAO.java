package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.ReviewDTO;

public class ReviewDAO extends JdbcDAO {
	private static ReviewDAO _dao;

	private ReviewDAO() {
		// TODO Auto-generated constructor stub
	}

	static {
		_dao = new ReviewDAO();
	}

	public static ReviewDAO getDAO() {
		return _dao;
	}

	// 조회정보(조회대상과 조회단어)를 전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된
	// 행의 갯수를 검색하여 반환하는 메소드
	// => 조회기능을 사용하지 않을 경우 REVIEW 테이블에 저장된 모든 행의 갯수를 검색하여 반환
	public int selectTotalReview() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();

			String sql = "select count(*) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectTotalReview() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return count;
	}

	// 페이징 관련 정보(시작행번호, 종료행번호)
	// 전달받아 REVIEW 테이블에 저장된 행에서 조회정보가 포함된 행을 페이징 처리로 검색하여
	// 검색된 게시글 목록(List 객체)을 반환하는 메소드 //NEED
	public List<ReviewDTO> selectReviewList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		try {
			con = getConnection();

			String sql = "select * from (select rownum rn, temp.* from (select  review_num"
					+ ",review_users_no, users_name, review_title,review_content,review_image"
					+ ",review_status,review_date from review join users on"
					+ " review_users_no = users_no) temp) where rn between ? and ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDTO review = new ReviewDTO();
				review.setReviewNum(rs.getInt("review_num"));
				review.setUsersName(rs.getString("users_name"));
				review.setReviewTitle(rs.getString("review_title"));
				review.setReviewContent(rs.getString("review_content"));
				review.setReviewImage(rs.getString("review_image"));
				review.setReviewStatus(rs.getInt("review_status"));
				review.setReviewDate(rs.getString("review_date"));

				reviewList.add(review);
			}

		} catch (SQLException e) {
			System.out.println("[에러]selectReviewList() 메소드의 sql 오류" + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return reviewList;
	}

	// 게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블의 행으로 삽입하고 삽입행의 갯수를 반환하는 메소드 //NEED
	public int insertReview(ReviewDTO review) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			con = getConnection();

			String sql = "insert into review values(?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review.getReviewNum());
			pstmt.setInt(2, review.getReviewUserNo());
			pstmt.setString(3, review.getReviewTitle());
			pstmt.setString(4, review.getReviewContent());
			pstmt.setString(5, review.getReviewImage());
			pstmt.setInt(6, review.getReviewStatus());

			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]insertReview() 메소드의 sql 오류" + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 게시글(ReviewDTO 객체)을 전달받아 REVIEW 테이블에 저장된 행을 변경하고 변경행의 갯수를 /NEED
	// 반환하는 메소드
	public int updateReview(ReviewDTO review) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "update review set review_title=?,review_content=?,review_image=?"
					+ ",review_status=?,review_update_date=sysdate where review_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, review.getReviewTitle());
			pstmt.setString(2, review.getReviewContent());
			pstmt.setString(3, review.getReviewImage());
			pstmt.setInt(4, review.getReviewStatus());
			pstmt.setInt(5, review.getReviewNum());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]updateReview() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//REVIEW_SEQ 시퀸스의 다음값을 검색하여 반환하는 메소드
		public int selectReviewNextNum() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int nextNum=0;
			try {
				con=getConnection();
				
				String sql="select review_seq.nextval from dual";
				pstmt=con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					nextNum=rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("[에러]selectReviewNextNum() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt, rs);
			}
			return nextNum;
		}
		
		public ReviewDTO selectReviewByNum(int reviewNum) {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			ReviewDTO review=null;
			try {
				con=getConnection();
				
				String sql="select review_num,review_users_no,users_name,review_title"
						+ ",review_content,review_image,review_status,review_date"
						+ " from review join users on review_users_no=users_no"
						+ " where review_num=? and review_status<>0";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, reviewNum);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					review=new ReviewDTO();
					review.setReviewNum(rs.getInt("review_num"));
					review.setReviewUserNo(rs.getInt("review_users_no"));
					review.setUsersName(rs.getString("users_name"));
					review.setReviewTitle(rs.getString("review_title"));
					review.setReviewContent(rs.getString("review_content"));
					review.setReviewImage(rs.getString("review_image"));
					review.setReviewStatus(rs.getInt("review_status"));
					review.setReviewDate(rs.getString("review_date"));
				}
			} catch (SQLException e) {
				System.out.println("[에러]selectReviewByNum() 메소드의 SQL 오류 = "+e.getMessage());
			} finally {
				close(con, pstmt, rs);
			}
			return review;
		}

}
