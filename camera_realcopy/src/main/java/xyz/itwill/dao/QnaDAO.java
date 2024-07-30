package xyz.itwill.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import xyz.itwill.dto.AdminQnaDTO;
import xyz.itwill.dto.QnaDTO;

public class QnaDAO extends JdbcDAO {
    private static QnaDAO _dao;

    private QnaDAO() {
        // TODO Auto-generated constructor stub
    }

    static {
        _dao = new QnaDAO();
    }

    public static QnaDAO getDAO() {
        return _dao;
    }

    // 회원 번호로 회원 이름을 조회하여 반환하는 메소드
    public String getUserNameByNo(int usersNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String userName = null;
        try {
            con = getConnection();

            String sql = "SELECT USERS_NAME FROM USERS WHERE USERS_NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, usersNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                userName = rs.getString("USERS_NAME");
            }
        } catch (SQLException e) {
            System.out.println("[에러]getUserNameByNo() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return userName;
    }

    // QNA 테이블의 총 행의 갯수를 반환하는 메소드
    public int selectTotalQna(int no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            con = getConnection();

            String sql = "SELECT COUNT(*) FROM QNA WHERE QNA_USERS_NO=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]selectTotalQna() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return count;
    }

    // 페이징 관련 정보(시작행번호, 종료행번호)를 전달받아 QNA 테이블의 행을 페이징 처리로 검색하여 검색된 QNA 목록을 반환하는 메소드
    public List<QnaDTO> selectQnaList(int usersNo,int startRow, int endRow) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<QnaDTO> qnaList = new ArrayList<QnaDTO>();
        try {
            con = getConnection();

            String sql = "SELECT * FROM (SELECT ROWNUM RN, TEMP.* FROM (SELECT QNA_NO, QNA_USERS_NO, USERS_NAME, QNA_TYPE, QNA_TITLE, QNA_CONTENT, QNA_STATUS, QNA_DATE FROM QNA JOIN USERS ON QNA_USERS_NO = USERS_NO WHERE QNA_USERS_NO = ? ORDER BY QNA_NO DESC) TEMP) WHERE RN BETWEEN ? AND ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, usersNo);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);

            rs = pstmt.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                QnaDTO qna = new QnaDTO();
                qna.setQnaNo(rs.getInt("QNA_NO"));
                qna.setQnaUsersNo(rs.getInt("QNA_USERS_NO"));
                qna.setUsersName(rs.getString("USERS_NAME"));
                qna.setQnaType(rs.getString("QNA_TYPE"));
                qna.setQnaTitle(rs.getString("QNA_TITLE"));
                qna.setQnaContent(rs.getString("QNA_CONTENT"));
                qna.setQnaStatus(rs.getInt("QNA_STATUS"));
                qna.setQnaDate(sdf.format(rs.getTimestamp("QNA_DATE")));

                qnaList.add(qna);
            }

        } catch (SQLException e) {
            System.out.println("[에러]selectQnaList() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return qnaList;
    }

    // QNA 번호로 QNA 정보를 조회하여 반환하는 메소드
    public QnaDTO selectQnaByNo(int qnaNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QnaDTO qna = null;
        try {
            con = getConnection();

            String sql = "SELECT QNA_NO, QNA_USERS_NO, USERS_NAME, QNA_TYPE, QNA_TITLE, QNA_CONTENT, QNA_STATUS, QNA_DATE FROM QNA JOIN USERS ON QNA_USERS_NO = USERS_NO WHERE QNA_NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, qnaNo);

            rs = pstmt.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                qna = new QnaDTO();
                qna.setQnaNo(rs.getInt("QNA_NO"));
                qna.setQnaUsersNo(rs.getInt("QNA_USERS_NO"));
                qna.setUsersName(rs.getString("USERS_NAME"));
                qna.setQnaType(rs.getString("QNA_TYPE"));
                qna.setQnaTitle(rs.getString("QNA_TITLE"));
                qna.setQnaContent(rs.getString("QNA_CONTENT"));
                qna.setQnaStatus(rs.getInt("QNA_STATUS"));
                qna.setQnaDate(sdf.format(rs.getTimestamp("QNA_DATE")));
            }
        } catch (SQLException e) {
            System.out.println("[에러]selectQnaByNo() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return qna;
    }

    // QNA 정보를 삽입하고 삽입된 행의 개수를 반환하는 메소드
    public int insertQna(QnaDTO qna) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int rows = 0;

        try {
            con = getConnection();

            String sql ="INSERT INTO QNA values(QNA_SEQ.nextval,?,?,?,?,?,sysdate)"; 
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, qna.getQnaUsersNo());
            pstmt.setString(2, qna.getQnaType());
            pstmt.setString(3, qna.getQnaTitle());
            pstmt.setString(4, qna.getQnaContent());
            pstmt.setInt(5, qna.getQnaStatus());

            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[에러]insertQna() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt);
        }
        return rows;
    }

    // QNA 정보를 수정하고 수정된 행의 개수를 반환하는 메소드
    public int updateQna(QnaDTO qna) {
        Connection con = null;
        PreparedStatement pstmt = null;
        int rows = 0;
        try {
            con = getConnection();

            String sql = "UPDATE QNA SET QNA_TYPE = ?, QNA_TITLE = ?, QNA_CONTENT = ?, QNA_STATUS = ? WHERE QNA_NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, qna.getQnaType());
            pstmt.setString(2, qna.getQnaTitle());
            pstmt.setString(3, qna.getQnaContent());
            pstmt.setInt(4, qna.getQnaStatus());
            pstmt.setInt(5, qna.getQnaNo());

            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[에러]updateQna() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt);
        }
        return rows;
    }

    // QNA_SEQ 시퀀스의 다음 값을 검색하여 반환하는 메소드
    public int selectQnaNextNum() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nextNum = 0;
        try {
            con = getConnection();

            String sql = "SELECT QNA_SEQ.NEXTVAL FROM DUAL";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                nextNum = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("[에러]selectQnaNextNum() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return nextNum;
    }

    // QNA 번호로 QNA 정보를 조회하여 반환하는 메소드
    public QnaDTO selectQnaByNum(int qnaNo) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QnaDTO qna = null;
        try {
            con = getConnection();

            String sql = "SELECT QNA_NO, QNA_USERS_NO, USERS_NAME, QNA_TYPE, QNA_TITLE, QNA_CONTENT, QNA_STATUS, QNA_DATE FROM QNA JOIN USERS ON QNA_USERS_NO = USERS_NO WHERE QNA_NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, qnaNo);

            rs = pstmt.executeQuery();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (rs.next()) {
                qna = new QnaDTO();
                qna.setQnaNo(rs.getInt("QNA_NO"));
                qna.setQnaUsersNo(rs.getInt("QNA_USERS_NO"));
                qna.setUsersName(rs.getString("USERS_NAME"));
                qna.setQnaType(rs.getString("QNA_TYPE"));
                qna.setQnaTitle(rs.getString("QNA_TITLE"));
                qna.setQnaContent(rs.getString("QNA_CONTENT"));
                qna.setQnaStatus(rs.getInt("QNA_STATUS"));
                qna.setQnaDate(sdf.format(rs.getTimestamp("QNA_DATE")));
            }
        } catch (SQLException e) {
            System.out.println("[에러]selectQnaByNum() 메소드의 SQL 오류 = " + e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return qna;
    }

    public AdminQnaDTO selectQnaNo(int no) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AdminQnaDTO qna = null;
        try {
            con = getConnection();
            String sql ="select Qna_no,Qna_users_no,users_name,users_email,Qna_type,qna_title,qna_content"
                    + ",qna_status from qna join users on qna_users_no=users_no where Qna_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
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

        } catch (SQLException e) {
            System.out.println("[에러]selectQnaNo 메소드의 SQL 오류 = "+e.getMessage());
        } finally {
            close(con, pstmt, rs);
        }
        return qna;
    }
}

