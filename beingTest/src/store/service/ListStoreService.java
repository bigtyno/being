package store.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//import article.dao.ArticleDao;
//import article.model.Article;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;

public class ListStoreService {

	private StoreDao storeDao = new StoreDao();
	private int size = 10;

	public StorePage getStorePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = storeDao.selectCount(conn);
			List<Store> content = storeDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);
				//	conn, (pageNum - 1) * size, size);
			return new StorePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
