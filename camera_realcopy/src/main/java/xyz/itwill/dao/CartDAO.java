package xyz.itwill.dao;
/*
이름            널?       유형           
------------- -------- ------------ 
CART_NO       NOT NULL NUMBER       
CART_USERS_ID          VARCHAR2(20) 
CART_PROD_NO           NUMBER       
CART_QUANTITY          NUMBER(20)  
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.CartDTO;

public class CartDAO extends JdbcDAO {
	private static CartDAO _dao;
	
	public CartDAO() {
	// TODO Auto-generated constructor stub
	}
	static {
		_dao=new CartDAO();		
	}
	
	public static CartDAO getDAO() {
		return _dao;
	}

    public int insertCart(CartDTO cart) {
    	Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
					    	
			String sql = "INSERT INTO CART (cart_no,	cart_users_id, cart_prod_no, cart_quantity)"
					+ "VALUES (cartlist_seq.nextval,?,?,? )";
			
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, cart.getCartusersId());
			pstmt.setInt(2, cart.getCartproductNo());
			pstmt.setInt(3, cart.getCartQuantity());
                 
            
            rows=pstmt.executeUpdate();
            
		} catch (SQLException e) {
			System.out.println("[에러]insertOrders() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	public int updateCart(CartDTO cart) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "update cart set cart_users_id = ?, cart_prod_no = ?, cart_quantity = ? where cart_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart.getCartusersId());
			pstmt.setInt(2, cart.getCartproductNo());
			pstmt.setInt(3, cart.getCartQuantity());
			pstmt.setInt(4, cart.getCartNo());
			
			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Cart 테이블 update 오류 => " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	public int deleteCart(int cart_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			
			String sql = "delete from cart where cart_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_no);

			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("cart 테이블 delete 오류 => " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int clearCart(String cart_users_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "delete from cart where CART_USERS_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cart_users_id);

			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("cart 테이블 clear 오류 => " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public CartDTO selectCart(int cart_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartDTO cart = null;
		try {
			con = getConnection();

			String sql = "select * from cart where cart_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_no);

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cart = new CartDTO();
				cart.setCartNo(rs.getInt("cart_no"));
				cart.setCartusersId(rs.getString("users_id"));
				cart.setCartproductNo(rs.getInt("prod_no"));
				cart.setCartQuantity(rs.getInt("quantity"));
			}			
		} catch (SQLException e) {
			System.out.println("cart 테이블 select 오류 => " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return cart;
	}

	public List<CartDTO> selectAllCartList(String usersId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CartDTO> list = new ArrayList<CartDTO>();
		try {
			con = getConnection();

			String sql = "SELECT * FROM Cart WHERE CART_USERS_ID = ? ORDER BY CART_NO";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usersId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				CartDTO cart = new CartDTO();
				cart.setCartNo(rs.getInt("CART_NO"));
				cart.setCartusersId(rs.getString("CART_USERS_ID"));
				cart.setCartproductNo(rs.getInt("CART_PROD_NO"));
				cart.setCartQuantity(rs.getInt("CART_QUANTITY"));
				list.add(cart);
			}
		} catch (SQLException e) {
			System.out.println("cart 테이블 selectAll 오류 => " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
}



