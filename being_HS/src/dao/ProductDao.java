package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

//import article.model.Article;
//import article.model.Writer;
import jdbc.JdbcUtil;
import model.Product;

public class ProductDao {
	public Product insert(Connection conn, Product product) throws SQLException {
		
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		 
		try {
			pstmt = conn.prepareStatement("insert into PROD_MNG values "
					+ "(PRODMNG_SEQ.NEXTVAL,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,0)");
			
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getThumbnail());
			pstmt.setString(4, product.getInfoimage());
			pstmt.setString(5, product.getIntroduce());
			pstmt.setInt(6,	(int)product.getPrice());
			pstmt.setInt(7, (int)product.getdcprice());
			pstmt.setString(8, product.getBrand());
			pstmt.setString(9, product.getKeywd());
			pstmt.setString(10, product.getCategory());
			pstmt.setString(11, product.getFreeyn());
			pstmt.setString(12, product.getLink());		
			
//			int insertedCount = pstmt.executeUpdate();

			
			/*
			 * if (insertedCount > 0) { stmt = conn.createStatement(); rs =
			 * stmt.executeQuery("select max(NUM) from WRITING"); if (rs.next()) { Integer
			 * newNo = rs.getInt(1); return new Article(newNo, article.getWriter(),
			 * article.getType(), article.getAcreage(), article.getBudget(),
			 * article.getField(), article.getSpace(), article.getTitle(),
			 * article.getContent(), // article.getProdnum(), article.getRegDate(), //
			 * article.getModifiedDate(), 0); } }
			 */
			 return null;
			 
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}

	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from PROD_MNG");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Product> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM ("
					+ "        SELECT ROW_NUMBER() OVER (ORDER BY NUM) RNUM, A.*"
					+ "          FROM PROD_MNG A ORDER BY NUM)"
					+ " WHERE RNUM BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Product> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertProduct(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Product convertProduct(ResultSet rs) throws SQLException {
		return new Product(
				rs.getInt("NUM"),
				rs.getString("NAME"),
				rs.getString("THUMBNAIL"),
				rs.getString("INFOIMAGE"),
				rs.getString("INTRODUCE"),
				rs.getInt("PRICE"),
				rs.getInt("DCPRICE"),
				rs.getString("BRAND"),
				rs.getString("KEYWD"),
				rs.getString("CATEGORY"),
				rs.getString("FREEYN"),
				rs.getString("LINK"),
				rs.getInt("AVGGRADE")
				);
	}

	
	
	public Product selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from PROD_MNG where NUM = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Product product = null;
			if (rs.next()) {
				product = convertProduct(rs);
			}
			return product;
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
			String title, 
			String content,
			String type,
			String acreage,
			String budget,
			String field,
			String space
			) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update WRITING set title = ?,CONTENTOF=?, TYPE=?, ACREAGE =?, BUDGET=?,FIELD=?,SPACE=?  "+
						"where NUM = ?")) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, type);
			pstmt.setString(4, acreage);
			pstmt.setString(5, budget);
			pstmt.setString(6, field);
			pstmt.setString(7, space);
			pstmt.setInt(8, no);
			return pstmt.executeUpdate();
		}
	}
		
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from WRITING "+
						"where NUM = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
			}
	}
}



