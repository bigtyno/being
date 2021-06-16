package store.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//import article.dao.ArticleDao;
//import article.model.Article;
import jdbc.connection.ConnectionProvider;
//import store.dao.StoreDao;
import store.dao.StoreReviewDao;
//import store.model.Store;
import store.model.StoreReview;

public class ListStoreReviewService {

	private StoreReviewDao storeReviewDao = new StoreReviewDao();
	private int size = 10;

	public StoreReviewPage getStorePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = storeReviewDao.selectCount(conn);
			List<StoreReview> content = storeReviewDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);
				//	conn, (pageNum - 1) * size, size);
			return new StoreReviewPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
