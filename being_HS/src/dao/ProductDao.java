package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import article.model.Writer;
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
			
			pstmt.setString(1, product.getName());
			pstmt.setString(2, product.getThumbnail());
			pstmt.setString(3, product.getInfoimage());
			pstmt.setString(4, product.getIntroduce());
			pstmt.setInt(5,	(int)product.getPrice());
			pstmt.setInt(6, (int)product.getdcprice());
			pstmt.setString(7, product.getBrand());
			pstmt.setString(8, product.getKeywd());
			pstmt.setString(9, product.getCategory());
			pstmt.setString(10, product.getFreeyn());
			pstmt.setString(11, product.getLink());		
			int insertedCount = pstmt.executeUpdate();

			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(NUM) from PROD_MNG");
				if (rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Product(
							newNum,
							product.getName(),
							product.getThumbnail(),
							product.getInfoimage(),
							product.getIntroduce(),
							product.getPrice(),
							product.getDcprice(),
							product.getBrand(),
							product.getKeywd(),
							product.getCategory(),
							product.getFreeyn(),
							product.getLink(),
							product.getAvggrade());
				}
			}
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

	
	
	public Product selectById(Connection conn, int num) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from PROD_MNG where NUM = ?");
			pstmt.setInt(1, num);
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
	
	public int update(Connection conn, 
			//int num, 
			String name, 
			String thumbnail,
			String infoimage,
			String introduce,
			int price,
			int dcprice,
			String brand,
			String keywd,
			String category,
			String freeyn,
			String link,
			int num
			) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update PROD_MNG set NAME = ?,THUMBNAIL=?, INFOIMAGE=?, INTRODUCE =?, PRICE=?, DCPRICE=?,BRAND=?,KEYWD=?,CATEGORY=?,FREEYN=?,LINK=?,0 "+
						"where NUM = ?")) {
			
			pstmt.setString(1, name);
			pstmt.setString(2, thumbnail);
			pstmt.setString(3, infoimage);
			pstmt.setString(4, introduce);
			pstmt.setInt(5,(int) price);
			pstmt.setInt(6,(int)dcprice);
			pstmt.setString(7, brand);
			pstmt.setString(8, keywd);
			pstmt.setString(9, category);
			pstmt.setString(10, freeyn);
			pstmt.setString(11, link);
			pstmt.setInt(12,num);
			return pstmt.executeUpdate();
		}
	}
		
	public int delete(Connection conn, int num) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from PROD_MNG "+
						"where NUM = ?")) {
			pstmt.setInt(1, num);
			return pstmt.executeUpdate();
			}
	}
}



