package interior.service;

import java.sql.Connection;
import java.sql.SQLException;

import interior.dao.InteriorDao;
import interior.model.Interior;
import jdbc.connection.ConnectionProvider;

public class ReadInteriorService {

	private InteriorDao interiorDao = new InteriorDao();
	
	public Interior getInterior(int interiorNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Interior interior = interiorDao.selectById(conn, interiorNum);
			if (interior == null) {
				throw new InteriorNotFoundException();
			}

			return interior;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
