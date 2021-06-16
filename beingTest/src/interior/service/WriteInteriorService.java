package interior.service;

import java.sql.Connection;
import java.sql.SQLException;

import interior.dao.InteriorDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

import interior.model.Interior;

public class WriteInteriorService {

	private InteriorDao interiorDao = new InteriorDao();

	public Integer interior(WriteInteriorRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Interior interior = toInterior(req);
			Interior savedInterior = interiorDao.insert(conn, interior);
			System.out.println(interior);
			if (savedInterior == null) {
				throw new RuntimeException("fail to insert Interior");
			}

			conn.commit();

			return savedInterior.getNum();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Interior toInterior(WriteInteriorRequest req) {		
		return new Interior(null,
				req.getName(),
				req.getIntroduce(), 
				req.getImageA(),
				req.getImageB());
	}
	
}
