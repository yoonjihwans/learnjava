package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.AdminProductDTO;
import xyz.itwill.dto.AdminUsersDTO;

public class AdminProductDAO extends JdbcDAO {
	private static AdminProductDAO _dao;
	
	static {
		_dao=new AdminProductDAO();
	}
	
	public static AdminProductDAO getDAO(){
		return _dao;
		
	}
	
	public int insertProduct(AdminProductDTO product) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="insert into product values(PRODUCT_SEQ.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, product.getProdType());
			pstmt.setString(2, product.getProdName());
			pstmt.setInt(3, product.getProdPrice());
			pstmt.setInt(4, product.getProdAmount());
			pstmt.setString(5, product.getProdImage1());
			pstmt.setString(6, product.getProdImage2());
			pstmt.setString(7, product.getProdImage3());
			pstmt.setString(8, product.getProdImage4());
			pstmt.setString(9, product.getProdInfo());
			
			
			rows=pstmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("[에러]insertProduct() 메서드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt);
		}
		return rows;
	}
	public List<AdminProductDTO> selectProductSearchList(String search,String keyword){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<AdminProductDTO> productList=new ArrayList<>();
		
		try {
			con=getConnection();
			if(keyword.equals("")) {
				String sql="select * from product order by PROD_NO";
				pstmt=con.prepareStatement(sql);
			}else {
				String sql="select * from product where "+search+" like '%'||?||'%' order by PROD_NO";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				AdminProductDTO product=new AdminProductDTO();
				product.setProdNo(rs.getInt("PROD_NO"));
				product.setProdType(rs.getInt("PROD_TYPE"));
				product.setProdName(rs.getString("PROD_NAME"));
				product.setProdPrice(rs.getInt("PROD_PRICE"));
				product.setProdAmount(rs.getInt("PROD_AMOUNT"));
				product.setProdImage1(rs.getString("PROD_IMAGE1"));
				product.setProdImage2(rs.getString("PROD_IMAGE2"));
				product.setProdImage3(rs.getString("PROD_IMAGE3"));
				product.setProdImage4(rs.getString("PROD_IMAGE4"));
				product.setProdInfo(rs.getString("PROD_INFO"));
				product.setProdInDate(rs.getString("PROD_IN_DATE"));	
				productList.add(product);
			}
		}catch (SQLException e) {
			System.out.println("[에러]selectProductList 메소드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt,rs);
		}
		return productList;
	}
//	public List<AdminProductDTO> selectProductList(){
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		List<AdminProductDTO> productList=new ArrayList<>();
//		
//		try {
//			con=getConnection();
//			String sql="select PROD_NO,PROD_TYPE,PROD_NAME,PROD_PRICE,PROD_AMOUNT,PROD_IMAGE1,PROD_IMAGE2,PROD_IMAGE3,PROD_IMAGE4,PROD_INFO,PROD_IN_DATE from product order by PROD_NO";
//			pstmt=con.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				AdminProductDTO product=new AdminProductDTO();
//				product.setProdNo(rs.getInt("PROD_NO"));
//				product.setProdType(rs.getInt("PROD_TYPE"));
//				product.setProdName(rs.getString("PROD_NAME"));
//				product.setProdPrice(rs.getInt("PROD_PRICE"));
//				product.setProdAmount(rs.getInt("PROD_AMOUNT"));
//				product.setProdImage1(rs.getString("PROD_IMAGE1"));
//				product.setProdImage2(rs.getString("PROD_IMAGE2"));
//				product.setProdImage3(rs.getString("PROD_IMAGE3"));
//				product.setProdImage4(rs.getString("PROD_IMAGE4"));
//				product.setProdInfo(rs.getString("PROD_INFO"));
//				product.setProdInDate(rs.getString("PROD_IN_DATE"));	
//				productList.add(product);
//				}
//		}catch (SQLException e) {
//			System.out.println("[에러]selectProductList 메소드의 SQL 오류 = "+e.getMessage());
//		}finally {
//			close(con,pstmt,rs);
//		}
//		return productList;
//	}
	public int updateProduct(AdminProductDTO product) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			String sql="update product set prod_type=?,prod_Name=?,prod_price=?,prod_Amount=?,prod_image1=?,prod_image2=?,prod_image3=?,prod_image4=?,prod_info=? where prod_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, product.getProdType());
			pstmt.setString(2, product.getProdName());
			pstmt.setInt(3, product.getProdPrice());
			pstmt.setInt(4, product.getProdAmount());
			pstmt.setString(5, product.getProdImage1());
			pstmt.setString(6, product.getProdImage2());
			pstmt.setString(7, product.getProdImage3());
			pstmt.setString(8, product.getProdImage4());
			pstmt.setString(9, product.getProdInfo());
			pstmt.setInt(10, product.getProdNo());
			
			
			rows=pstmt.executeUpdate();
			
		}catch (SQLException e) {
			System.out.println("[에러]insertProduct() 메서드의 SQL 오류 = "+e.getMessage());
		}finally {
			close(con,pstmt);
		}
		return rows;
	}
	public AdminProductDTO selectProductByNo(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		AdminProductDTO product=null;
		try {
			con=getConnection();
			String sql=" select PROD_NO,PROD_TYPE,PROD_NAME,PROD_PRICE,PROD_AMOUNT,PROD_IMAGE1,PROD_IMAGE2,PROD_IMAGE3,PROD_IMAGE4,PROD_INFO from product where prod_No=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product=new AdminProductDTO();
				product.setProdNo(rs.getInt("PROD_NO"));
				product.setProdType(rs.getInt("Prod_type"));
				product.setProdName(rs.getString("PROD_NAME"));
				product.setProdPrice(rs.getInt("PROD_price"));
				product.setProdAmount(rs.getInt("PROD_amount"));
				product.setProdImage1(rs.getString("PROD_Image1"));
				product.setProdImage2(rs.getString("PROD_Image2"));
				product.setProdImage3(rs.getString("PROD_Image3"));
				product.setProdImage4(rs.getString("PROD_Image4"));
				product.setProdInfo(rs.getString("PROD_Info"));
			}
		
		
		}catch (SQLException e) {
			System.out.println("[에러]selectStudent 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return product;
		
	}
	
	public int selectTotalProduct(String search,String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		try {
			con=getConnection();
			
			if(keyword.equals("")) {//조회기능을 사용하지 않을 경우
				String sql="select count(*) from product";
				pstmt=con.prepareStatement(sql);
			} else {
				String sql="select count(*) from product where "+search+" like '%'||?||'%'";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				count=rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println("[에러]selectTotalProduct() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return count;
		
	}
}
