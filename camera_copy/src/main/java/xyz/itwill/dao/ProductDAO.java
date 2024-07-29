package xyz.itwill.dao;

import xyz.itwill.dto.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends JdbcDAO {
    private static ProductDAO _dao;

    private ProductDAO() {
        // TODO Auto-generated constructor stub
    }

    static {
        _dao = new ProductDAO();
    }

    public static ProductDAO getDAO() {
        return _dao;
    }

    // 상품정보(ProductDTO 객체)를 전달받아 PRODUCT 테이블의 행으로 삽입하고 삽입행의 갯수(int)를 반환하는 메소드
    public int insertProduct(ProductDTO product) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            con = getConnection();

            String sql = "insert into product (PROD_NO, PROD_TYPE, PROD_NAME, PROD_PRICE, PROD_AMOUNT,"
            		+ " PROD_IMAGE1, PROD_IMAGE2, PROD_IMAGE3, PROD_IMAGE4, PROD_INFO, PROD_IN_DATE) " +
                         "values (PRODUCT_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, product.getProdType());
            pstmt.setString(2, product.getProdName());
            pstmt.setInt(3, product.getProdPrice());
            pstmt.setInt(4, product.getProdAmount());
            pstmt.setString(5, product.getProdImage1());
            pstmt.setString(6, product.getProdImage2());
            pstmt.setString(7, product.getProdImage3());
            pstmt.setString(8, product.getProdImage4());
            pstmt.setString(9, product.getProdInfo());

            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[에러]insertProduct() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt);
        }
        return rows;
    }

    // 상품번호(int)를 전달받아 PRODUCT 테이블에 저장된 하나의 행을 검색하여 검색된 상품정보(ProductDTO)를 반환하는 메소드
    public ProductDTO selectProductByNo(int prodNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductDTO product = null;
        try {
            con = getConnection();

            String sql = "select PROD_NO, PROD_TYPE, PROD_NAME, PROD_PRICE, PROD_AMOUNT, PROD_IMAGE1, "
            		+ "PROD_IMAGE2, PROD_IMAGE3, PROD_IMAGE4, PROD_INFO, PROD_IN_DATE from product where PROD_NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, prodNo);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                product = new ProductDTO();
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
            }
        } catch (SQLException e) {
            System.out.println("[에러]selectProductByNo() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return product;
    }

    public List<ProductDTO> selectProductListByType1() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<ProductDTO>();
        try {
            con = getConnection();

            String sql = "select * from product where PROD_TYPE = 1";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
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
        } catch (SQLException e) {
            System.out.println("[에러]selectProductListByType1() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return productList;
    }

    public int getTotalProductsByType1(String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalProducts = 0;
        try {
            con = getConnection();

            String sql = "select count(*) from product where PROD_TYPE = 1";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalProducts = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]getTotalProductsByType1() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return totalProducts;
    }

    public List<ProductDTO> selectProductListByType1WithPaging(int startRow, int endRow, String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<ProductDTO>();
        try {
            con = getConnection();

            String sql = "select * from (select rownum rnum, a.* from (select * from product where PROD_TYPE = 1";
            if (filter != null) {
                if (filter.equals("newest")) {
                    sql += " order by PROD_IN_DATE desc";
                } else if (filter.equals("lowestPrice")) {
                    sql += " order by PROD_PRICE asc";
                } else if (filter.equals("highestPrice")) {
                    sql += " order by PROD_PRICE desc";
                }
            }
            sql += ") a where rownum <= ?) where rnum >= ?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, endRow);
            pstmt.setInt(2, startRow);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductDTO product = new ProductDTO();
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
        } catch (SQLException e) {
            System.out.println("[에러]selectProductListByType1WithPaging() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return productList;
    }

    public int getTotalProductsByType2(String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total = 0;
        try {
            con = getConnection();
            String sql = "SELECT COUNT(*) FROM product WHERE PROD_TYPE = 2";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]getTotalProductsByType2() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return total;
    }

    public List<ProductDTO> selectProductListByType2WithPaging(int startRow, int endRow, String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<ProductDTO>();
        try {
            con = getConnection();
            String orderBy = "";
            if (filter != null) {
                if (filter.equals("newest")) {
                    orderBy = "ORDER BY PROD_IN_DATE DESC";
                } else if (filter.equals("lowestPrice")) {
                    orderBy = "ORDER BY PROD_PRICE ASC";
                } else if (filter.equals("highestPrice")) {
                    orderBy = "ORDER BY PROD_PRICE DESC";
                }
            }
            String sql = "SELECT * FROM (SELECT rownum rn, temp.* FROM (SELECT * FROM product WHERE PROD_TYPE = 2 " + orderBy + ") temp) WHERE rn BETWEEN ? AND ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
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
        } catch (SQLException e) {
            System.out.println("[에러]selectProductListByType2WithPaging() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return productList;
    }


    public int getTotalProductsByType3(String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total = 0;
        try {
            con = getConnection();
            String sql = "SELECT COUNT(*) FROM product WHERE PROD_TYPE = 3";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]getTotalProductsByType3() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return total;
    }

    public List<ProductDTO> selectProductListByType3WithPaging(int startRow, int endRow, String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<ProductDTO>();
        try {
            con = getConnection();
            String orderBy = "";
            if (filter != null) {
                if (filter.equals("newest")) {
                    orderBy = "ORDER BY PROD_IN_DATE DESC";
                } else if (filter.equals("lowestPrice")) {
                    orderBy = "ORDER BY PROD_PRICE ASC";
                } else if (filter.equals("highestPrice")) {
                    orderBy = "ORDER BY PROD_PRICE DESC";
                }
            }
            String sql = "SELECT * FROM (SELECT rownum rn, temp.* FROM (SELECT * FROM product WHERE PROD_TYPE = 3 " + orderBy + ") temp) WHERE rn BETWEEN ? AND ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
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
        } catch (SQLException e) {
            System.out.println("[에러]selectProductListByType3WithPaging() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return productList;
    }
    
    
    public List<ProductDTO> searchProducts(String keyword, String search, int startRow, int endRow, String filter) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<ProductDTO> productList = new ArrayList<>();
        try {
            con = getConnection();
            String sql = "SELECT * FROM (SELECT rownum rn, temp.* FROM (SELECT * FROM product WHERE ";
            if (!keyword.equals("")) {
                sql += search + " LIKE '%' || ? || '%' ";
            } else {
                sql += "1=1"; // 검색어가 없는 경우 모든 상품
            }

            if (filter != null) {
                if (filter.equals("new")) {
                    sql += " ORDER BY PROD_IN_DATE DESC";
                } else if (filter.equals("lowestPrice")) {
                    sql += " ORDER BY PROD_PRICE ASC";
                } else if (filter.equals("highestPrice")) {
                    sql += " ORDER BY PROD_PRICE DESC";
                }
            }

            sql += ") temp) WHERE rn BETWEEN ? AND ?";

            pstmt = con.prepareStatement(sql);
            int parameterIndex = 1;
            if (!keyword.equals("")) {
                pstmt.setString(parameterIndex++, keyword);
            }
            pstmt.setInt(parameterIndex++, startRow);
            pstmt.setInt(parameterIndex++, endRow);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductDTO product = new ProductDTO();
                product.setProdNo(rs.getInt("prod_no"));
                product.setProdType(rs.getInt("prod_type"));
                product.setProdName(rs.getString("prod_name"));
                product.setProdPrice(rs.getInt("prod_price"));
                product.setProdAmount(rs.getInt("prod_amount"));
                product.setProdImage1(rs.getString("prod_image1"));
                product.setProdImage2(rs.getString("prod_image2"));
                product.setProdImage3(rs.getString("prod_image3"));
                product.setProdImage4(rs.getString("prod_image4"));
                product.setProdInfo(rs.getString("prod_info"));
                product.setProdInDate(rs.getString("prod_in_date"));
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println("[에러]searchProducts() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return productList;
    }


    public int getTotalProducts(String keyword, String search) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int total = 0;
        try {
            con = getConnection();
            String sql = "SELECT COUNT(*) FROM product WHERE ";
            if (!keyword.equals("")) {
                sql += search + " LIKE '%' || ? || '%' ";
            } else {
                sql += "1=1"; // 검색어가 없는 경우 모든 상품
            }
            pstmt = con.prepareStatement(sql);
            if (!keyword.equals("")) {
                pstmt.setString(1, keyword);
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]getTotalProducts() 메서드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return total;
    }

}