package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.ReviewDTO;

public class ReviewDAO {
private static ReviewDAO _dao;
	
	private ReviewDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new ReviewDAO();
	}
	
	public static ReviewDAO getDAO() {
		return _dao;
	}
	
	public List<ReviewDTO> selectReviewList(int startRow, int endRow){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();
		try {
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return reviewList;
	}

}
