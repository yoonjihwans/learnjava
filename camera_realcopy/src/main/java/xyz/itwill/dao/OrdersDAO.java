package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import xyz.itwill.dto.OrdersDTO;

public class OrdersDAO extends JdbcDAO {
	private static OrdersDAO _dao;
	
	public OrdersDAO() {
	// TODO Auto-generated constructor stub
	}
	static {
		_dao=new OrdersDAO();		
	}
	
	public static OrdersDAO getDAO() {
		return _dao;
	}
    
    public int insertOrder(OrdersDTO orders) {
    	Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
    	
			String sql = "INSERT INTO ORDERS (orders_no, orders_prod_no, orders_users_id, orders_users_name, orders_users_phone, orders_users_email,"
					+ " orders_users_zipcode, orders_users_address1, orders_users_address2, orders_request, orders_payment, "
					+ "orders_cart_amount, orders_cart_price, orders_date, orders_status) "
					+ "VALUES (orders_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, 0)";
			
			pstmt=con.prepareStatement(sql);
           // pstmt.setInt(1, orders.getOrdersNo());
            pstmt.setInt(1, orders.getOrdersProdNo());
			
            pstmt.setString(2, orders.getOrdersUsersId());
            pstmt.setString(3, orders.getOrdersUsersName());
            pstmt.setString(4, orders.getOrdersUsersPhone());
            pstmt.setString(5, orders.getOrdersUsersEmail());
            pstmt.setString(6, orders.getOrdersUsersZipcode());
            pstmt.setString(7, orders.getOrdersUsersAddress1());
            pstmt.setString(8, orders.getOrdersUsersAddress2());
            pstmt.setString(9, orders.getOrdersRequest());
            pstmt.setString(10, orders.getOrdersPayment()); //value 값 1,2,3,4,5,6,7,
          
            pstmt.setInt(11, orders.getOrdersCartAmount());
         
            pstmt.setInt(12, orders.getOrdersCartPrice());
         
           // pstmt.setDate(13, new java.sql.Date(orders.getOrdersDate().getTime()));
           // pstmt.setInt(14, orders.getOrdersStatus()); // 0:상품준비중, 1:배송중 , 2:배송완료
            
            rows=pstmt.executeUpdate();
            
		} catch (SQLException e) {
			System.out.println("[에러]insertOrders() 메서드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
}
