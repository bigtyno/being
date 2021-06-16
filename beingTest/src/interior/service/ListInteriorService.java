package interior.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import interior.dao.InteriorDao;
import interior.model.Interior;
import jdbc.connection.ConnectionProvider;


public class ListInteriorService {

	private InteriorDao interiorDao = new InteriorDao();
	private int size = 10;

	public InteriorPage getInteriorPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = interiorDao.selectCount(conn);
			List<Interior> content = interiorDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);
			return new InteriorPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
