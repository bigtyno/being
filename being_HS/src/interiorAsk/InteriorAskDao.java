package interiorAsk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Writer;
import jdbc.JdbcUtil;

public class InteriorAskDao {

	public InteriorAsk insert(Connection conn, InteriorAsk interiorAsk) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		Date selectedDate = new Date();
		java.sql.Date dateSelected = new java.sql.Date(selectedDate.getTime()); 
		 
		try {
			pstmt = conn.prepareStatement("insert into REQ_MNG values "
					+ "(REQ_MNG_SEQ.NEXTVAL,"
					+ "?,?,?,?,"
					+ "?,?,?,?,?,"
					+ "?,?,null,null,null,"
					+ "null,null,?)");
			pstmt.setString(1, interiorAsk.getWriter().getId());
			pstmt.setString(2, interiorAsk.getTitle());
			pstmt.setDate(3, dateSelected);
			pstmt.setString(4, interiorAsk.getBudget());
			pstmt.setString(5, interiorAsk.getArea());
			pstmt.setString(6, interiorAsk.getFieldOf());
			pstmt.setString(7, interiorAsk.getAddress());
			pstmt.setString(8, interiorAsk.getDateStart());
			pstmt.setString(9, interiorAsk.getDateDone());
			pstmt.setString(10, interiorAsk.getMessage());
			pstmt.setString(11, interiorAsk.getName());
			pstmt.setString(12, interiorAsk.getTel());
			int insertedCount = pstmt.executeUpdate();
			
			if (insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select max(NUM) from REQ_MNG");
				if (rs.next()) {
					Integer newNo = rs.getInt(1);
					return new InteriorAsk(newNo,
							interiorAsk.getWriter(),
							interiorAsk.getTitle(),
							interiorAsk.getName(),
							interiorAsk.getArea(),
							interiorAsk.getFieldOf(),
							interiorAsk.getAddress(),
							interiorAsk.getDateStart(),
							interiorAsk.getDateDone(),
							interiorAsk.getBudget(),
							interiorAsk.getMessage(),
							interiorAsk.getTel()
							);
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
			rs = stmt.executeQuery("select count(*) from REQ_MNG");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	public List<InteriorAsk> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("SELECT * FROM ("
					+ "        SELECT ROW_NUMBER() OVER (ORDER BY NUM) RNUM, A.*"
					+ "          FROM REQ_MNG A ORDER BY NUM)"
					+ " WHERE RNUM BETWEEN ? AND ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<InteriorAsk> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertInteriorAsk(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private InteriorAsk convertInteriorAsk(ResultSet rs) throws SQLException {
	      return new InteriorAsk(rs.getInt("NUM"),
	    		  new Writer(
	   				   rs.getString("EMAIL"),
	   				   rs.getString("NAME")),
	    		   rs.getString("TITLE"),
	    		   rs.getString("NAME"),
	               rs.getString("AREA"),
	               rs.getString("FIELDOF"),
	               rs.getString("ADDRESS"),
	               rs.getString("DATESTART"),
	               rs.getString("DATEDONE"),
	               rs.getString("BUDGET"),
	               rs.getString("MESSAGE"),
	               rs.getString("TEL")
	    		  );
	}

	
	public InteriorAsk selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
							"select PC.*, M.NAME from REQ_MNG PC, MEMBERS M"
							+ "where PC.NUM = ?"
							+ "AND PC.EMAIL = M.EMAIL"
							);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			InteriorAsk interiorAsk = null;
			if (rs.next()) {
				interiorAsk = convertInteriorAsk(rs);
			}
			return interiorAsk;
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
