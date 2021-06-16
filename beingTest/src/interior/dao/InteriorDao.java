package interior.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jdbc.JdbcUtil;
import store.model.Store;
import interior.model.Interior;

public class InteriorDao {

	public Interior insert(Connection conn, Interior interior) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;		
		 
		try {
			pstmt = conn.prepareStatement("insert into CORP_MNG values "
					+ "(CORP_MNG_SEQ.NEXTVAL,"
					+ "?,?,?,?)");
			pstmt.setString(1, interior.getName());
			pstmt.setString(2, interior.getIntroduce());
			pstmt.setString(3, interior.getImageA());
			pstmt.setString(4, interior.getImageB());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(NUM) from CORP_MNG");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new Interior(newNo,
							interior.getName(),
							interior.getIntroduce(),
							interior.getImageA(),
							interior.getImageB());
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
			rs = stmt.executeQuery("select count(*) from CORP_MNG");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<Interior> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM ("
					+ "        SELECT ROW_NUMBER() OVER (ORDER BY NUM) RNUM, A.*"
					+ "          FROM CORP_MNG A ORDER BY NUM)"
					+ " WHERE RNUM BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Interior> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertInterior(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Interior convertInterior(ResultSet rs) throws SQLException {
	      return new Interior(rs.getInt("NUM"),
	               rs.getString("NAME"),
	               rs.getString("INTRODUCE"),
	               rs.getString("IMAGEA"),
	               rs.getString("IMAGEB"));
	   }

	
	public Interior selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from CORP_MNG where NUM = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Interior interior = null;
			if (rs.next()) {
				interior = convertInterior(rs);
			}
			return interior;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update CORP_MNG set READCOUNT = READCOUNT + 1 "+
						"where NUM = ?")) {
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, 
			String name,
			String introduce, 
			String imageA, 
			String imageB
			) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update CORP_MNG set NAME = ?, INTRODUCE =?, IMAGEA=?, IMAGEB=? "+
						"where NUM = ?")) {
			pstmt.setString(1, name);
			pstmt.setString(2, introduce);
			pstmt.setString(3, imageA);
			pstmt.setString(4, imageB);
			pstmt.setInt(5, no);
			return pstmt.executeUpdate();
		}
	}
		
	public int delete(Connection conn, int no) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from CORP_MNG "+
						"where NUM = ?")) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
			}
	}
}
