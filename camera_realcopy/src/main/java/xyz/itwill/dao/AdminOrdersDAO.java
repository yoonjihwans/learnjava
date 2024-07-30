package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.AdminOrdersDTO;
import xyz.itwill.dto.OrdersDTO;


public class AdminOrdersDAO extends JdbcDAO{
	 private static AdminOrdersDAO _dao;

	    private AdminOrdersDAO() {
	        // TODO Auto-generated constructor stub
	    }

	    static {
	        _dao = new AdminOrdersDAO();
	    }

	    public static AdminOrdersDAO getDAO() {
	        return _dao;
	    }
	 
	   public List<AdminOrdersDTO> selectOrdersList(String search,String keyword){
		   Connection con=null;
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   List<AdminOrdersDTO> ordersList=new ArrayList<AdminOrdersDTO>();
		   
		   try {
			   con=getConnection();
			   if(keyword.equals("")) {
			   String sql="select * from orders order by orders_no";
			   pstmt=con.prepareStatement(sql);
			   
			   }else {
				   String sql="select * from orders where "+search+" like '%'||?||'%' order by orders_no";
				   pstmt=con.prepareStatement(sql);
				   pstmt.setString(1, keyword);
			   }
			   rs=pstmt.executeQuery();
			   while(rs.next()) {
				   AdminOrdersDTO order=new AdminOrdersDTO();
				   order.setOrdersNo(rs.getInt("ORDERS_NO"));
				   order.setOrdersProdNo(rs.getInt("ORDERS_PROD_NO"));
				   order.setOrdersUsersId(rs.getString("ORDERS_USERS_ID"));
				   order.setOrdersUsersName(rs.getString("ORDERS_USERS_NAME"));
				   order.setOrdersUsersPhone(rs.getString("ORDERS_USERS_PHONE"));
				   order.setOrdersUsersEmail(rs.getString("ORDERS_USERS_EMAIL"));
				   order.setOrdersUsersZipcode(rs.getString("ORDERS_USERS_ZIPCODE"));
				   order.setOrdersUsersAddress1(rs.getString("ORDERS_USERS_ADDRESS1"));
				   order.setOrdersUsersAddress2(rs.getString("ORDERS_USERS_ADDRESS2"));
				   order.setOrdersRequest(rs.getString("ORDERS_REQUEST"));
				   order.setOrdersPayment(rs.getString("ORDERS_PAYMENT"));
				   order.setOrdersCartAmount(rs.getInt("ORDERS_CART_AMOUNT"));
				   order.setOrdersCartPrice(rs.getInt("ORDERS_CART_PRICE"));
				   order.setOrdersDate(rs.getString("ORDERS_DATE"));
				   order.setOrdersStatus(rs.getInt("ORDERS_STATUS"));
				   
				   ordersList.add(order);
				   
				   
				   
				   
			   }
		   }catch (SQLException e) {
				System.out.println("[에러]selectOrdersList 메소드의 SQL 오류 = "+e.getMessage());
		   }finally {
			   close(con,pstmt,rs);
			   
		   }
		   return ordersList;
	   }
	   public AdminOrdersDTO selectOrderByNo(int no) {
		   Connection con=null;
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   AdminOrdersDTO order=null;
		   try {
			   con=getConnection();
			   String sql="select ORDERS_NO,ORDERS_PROD_NO,ORDERS_USERS_ID,ORDERS_USERS_NAME,ORDERS_USERS_PHONE,ORDERS_USERS_EMAIL,ORDERS_USERS_ZIPCODE"
			   		+ ",ORDERS_USERS_ADDRESS1,ORDERS_USERS_ADDRESS2,ORDERS_REQUEST,ORDERS_PAYMENT,ORDERS_CART_AMOUNT,ORDERS_CART_PRICE"
			   		+ ",ORDERS_STATUS from orders where orders_no=?";
			   pstmt=con.prepareStatement(sql);
			   pstmt.setInt(1, no);
			   rs=pstmt.executeQuery();
			   if(rs.next()) {
				   order=new AdminOrdersDTO();
				   order.setOrdersNo(rs.getInt("ORDERS_NO"));
				   order.setOrdersProdNo(rs.getInt("ORDERS_PROD_NO"));
				   order.setOrdersUsersId(rs.getString("ORDERS_USERS_ID"));
				   order.setOrdersUsersName(rs.getString("ORDERS_USERS_NAME"));
				   order.setOrdersUsersPhone(rs.getString("ORDERS_USERS_PHONE"));
				   order.setOrdersUsersEmail(rs.getString("ORDERS_USERS_EMAIL"));
				   order.setOrdersUsersZipcode(rs.getString("ORDERS_USERS_ZIPCODE"));
				   order.setOrdersUsersAddress1(rs.getString("ORDERS_USERS_ADDRESS1"));
				   order.setOrdersUsersAddress2(rs.getString("ORDERS_USERS_ADDRESS2"));
				   order.setOrdersRequest(rs.getString("ORDERS_REQUEST"));
				   order.setOrdersPayment(rs.getString("ORDERS_PAYMENT"));
				   order.setOrdersCartAmount(rs.getInt("ORDERS_CART_AMOUNT"));
				   order.setOrdersCartPrice(rs.getInt("ORDERS_CART_PRICE"));
				   order.setOrdersStatus(rs.getInt("ORDERS_STATUS"));
			   }
			   		
					   
			   
		   }catch (SQLException e) {
				System.out.println("[에러]selectOrderByNo 메소드의 SQL 오류 = "+e.getMessage());
		   }finally {
			   close(con,pstmt,rs);
			   
		   }
		   return order;
		   
	   }
	   public int updateOrder(AdminOrdersDTO order) {
			Connection con=null;
			PreparedStatement pstmt=null;
			int rows=0;
			try {
				con=getConnection();
				String sql="update orders set ORDERS_NO=?,ORDERS_PROD_NO=?,ORDERS_USERS_ID=?,ORDERS_USERS_NAME=?,ORDERS_USERS_PHONE=?"
						+ ",ORDERS_USERS_EMAIL=?,ORDERS_USERS_ZIPCODE=?,ORDERS_USERS_ADDRESS1=?,ORDERS_USERS_ADDRESS2=?,ORDERS_REQUEST=?"
						+ ",ORDERS_PAYMENT=?,ORDERS_CART_AMOUNT=?,ORDERS_CART_PRICE=?,ORDERS_STATUS=? where ORDERS_NO=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, order.getOrdersNo());
				pstmt.setInt(2, order.getOrdersProdNo());
				pstmt.setString(3, order.getOrdersUsersId());
				pstmt.setString(4, order.getOrdersUsersName());
				pstmt.setString(5, order.getOrdersUsersPhone());
				pstmt.setString(6, order.getOrdersUsersEmail());
				pstmt.setString(7, order.getOrdersUsersZipcode());
				pstmt.setString(8, order.getOrdersUsersAddress1());
				pstmt.setString(9, order.getOrdersUsersAddress2());
				pstmt.setString(10, order.getOrdersRequest());
				pstmt.setString(11, order.getOrdersPayment());
				pstmt.setInt(12, order.getOrdersCartAmount());
				pstmt.setInt(13, order.getOrdersCartPrice());
				pstmt.setInt(14, order.getOrdersStatus());
				pstmt.setInt(15, order.getOrdersNo());
				
				
				rows=pstmt.executeUpdate();
				
			}catch (SQLException e) {
				System.out.println("[에러]updateOrder() 메서드의 SQL 오류 = "+e.getMessage());
			}finally {
				close(con,pstmt);
			}
			return rows;
		} 
	   
		public List<AdminOrdersDTO> selectOrderByStatus(String search,String keyword){
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<AdminOrdersDTO> ordersByStatus=new ArrayList<>();
			try {
				   con=getConnection();
				   if(keyword.equals("")) {

				   String sql="select orders_users_id,orders_prod_no, prod_name,prod_price,orders_cart_amount,orders_cart_price, orders_status"
				   		+ " from orders inner join product on orders_prod_no=prod_no inner join users on orders_users_id=users_id where orders_status <>2";
				   pstmt=con.prepareStatement(sql);
//				   }else if(orderStatus==1) {
//					   String sql="select orders_users_id,orders_prod_no, prod_name,prod_price,orders_cart_amount,orders_cart_price, orders_status"
//					   		+ " from orders inner join product on orders_prod_no=prod_no inner join users on orders_users_id=users_id where orders_status=1";
//					   pstmt=con.prepareStatement(sql);   
//				   }else if(orderStatus==2){
//					   String sql="select orders_users_id,orders_prod_no, prod_name,prod_price,orders_cart_amount,orders_cart_price, orders_status"
//					   		+ " from orders inner join product on orders_prod_no=prod_no inner join users on orders_users_id=users_id where orders_status=3";
//					   pstmt=con.prepareStatement(sql);
//				   }
					   
				   
				   }else {
					   		
					   String sql = "SELECT orders_users_id, orders_prod_no, prod_name, prod_price, orders_cart_amount, orders_cart_price, orders_status " +
					             "FROM orders " +
					             "INNER JOIN product ON orders_prod_no = prod_no " +
					             "INNER JOIN users ON orders_users_id = users_id " +
					             "WHERE orders_status <> 2 AND "+search+" like '%'||?||'%' ORDER BY orders_no" ;
					           
						   pstmt=con.prepareStatement(sql);
						   pstmt.setString(1, keyword);
//					   }else if(orderStatus==1) {
//						   String sql="select orders_users_id,orders_prod_no, prod_name,prod_price,orders_cart_amount,orders_cart_price, orders_status"
//							   		+ " from orders inner join product on orders_prod_no=prod_no inner join users on orders_users_id=users_id "
//							   		+ "where orders_status=1 AND where "+search+" like '%'||?||'%' order by orders_no";
//							   pstmt=con.prepareStatement(sql);
//							   pstmt.setString(1, keyword);
//						   
//					   }else if(orderStatus==2) {
//						   String sql="select orders_users_id,orders_prod_no, prod_name,prod_price,orders_cart_amount,orders_cart_price, orders_status"
//							   		+ " from orders inner join product on orders_prod_no=prod_no inner join users on orders_users_id=users_id "
//							   		+ "where orders_status=2 AND where "+search+" like '%'||?||'%' order by orders_no";
//							   pstmt=con.prepareStatement(sql);
//							   pstmt.setString(1, keyword);
//						   
//					   }
					  
				   }
				   rs=pstmt.executeQuery();
				   while(rs.next()) {
					   AdminOrdersDTO order=new AdminOrdersDTO();
					   order.setOrdersUsersId(rs.getString("orders_users_id"));
					   order.setOrdersProdNo(rs.getInt("ORDERS_PROD_NO"));
					   order.setProdName(rs.getString("prod_name"));
					   order.setProdPrice(rs.getInt("prod_price"));			  
					   order.setOrdersCartAmount(rs.getInt("ORDERS_CART_AMOUNT"));
					   order.setOrdersCartPrice(rs.getInt("ORDERS_CART_PRICE"));
					   order.setOrdersStatus(rs.getInt("ORDERS_STATUS"));
					   
					   ordersByStatus.add(order);
					   
					   
					   
					   
				   }
			   }catch (SQLException e) {
					System.out.println("[에러]selectOrdersList 메소드의 SQL 오류 = "+e.getMessage());
			   }finally {
				   close(con,pstmt,rs);
				   
			   }
			   return ordersByStatus;
		}
}
