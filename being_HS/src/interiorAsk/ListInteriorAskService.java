package interiorAsk;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;


public class ListInteriorAskService {

	private InteriorAskDao interiorAskDao = new InteriorAskDao();
	private int size = 10;

	public InteriorAskPage getInteriorAskPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = interiorAskDao.selectCount(conn);
			List<InteriorAsk> content = interiorAskDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);
			return new InteriorAskPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
