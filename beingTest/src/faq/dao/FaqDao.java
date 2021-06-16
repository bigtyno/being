package faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import faq.model.Faq;
import jdbc.JdbcUtil;
import store.model.Store;

public class FaqDao {

	public Faq insert(Connection conn, Faq faq) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;		
		 
		try {
			pstmt = conn.prepareStatement("insert into FAQ values "
					+ "(FAQ_SEQ.NEXTVAL,"
					+ "?,?,?)");
			pstmt.setString(1, faq.getCategory());
			pstmt.setString(2, faq.getQuestion());
			pstmt.setString(3, faq.getAnswer());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(NUM) from FAQ");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new Faq(newNo,
							faq.getCategory(),
							faq.getQuestion(),
							faq.getAnswer());
							
				}
			}

			 return null;
			 
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

//	private Timestamp toTimestamp(Date date) {
//		return new Timestamp(date.getTime());
//	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from FAQ");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Faq> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM ("
					+ "        SELECT ROW_NUMBER() OVER (ORDER BY NUM) RNUM, A.*"
					+ "          FROM FAQ A ORDER BY NUM)"
					+ " WHERE RNUM BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Faq> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertFaq(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Faq convertFaq(ResultSet rs) throws SQLException {
	      return new Faq(rs.getInt("NUM"),
	               rs.getString("CATEGORY"),
	               rs.getString("QUESTION"),
	               rs.getString("ANSWER"));
	   }

	public Faq selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from FAQ where NUM = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Faq faq = null;
			if (rs.next()) {
				faq = convertFaq(rs);
			}
			return faq;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update WRITING set READCOUNT = READCOUNT + 1 "+
						"where NUM = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no,
			String category, 
			String question, 
			String answer
			) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update FAQ set CATEGORY =?, QUESTION=?, ANSWER=?  "+
						"where NUM = ?")) {
			pstmt.setString(1, category);
			pstmt.setString(2, question);
			pstmt.setString(3, answer);
			pstmt.setInt(4, no);
			return pstmt.executeUpdate();
		}
	}
		
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from FAQ "+
						"where NUM = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
			}
	}
}
