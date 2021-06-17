package store.service;

import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Date;
import java.util.Date;

//import article.model.Writer;
//import auth.service.User;
//import article.dao.ArticleContentDao;
//import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
//import store.dao.StoreDao;
import store.dao.StoreReviewDao;
//import store.model.Store;
import store.model.StoreReview;

public class StoreReviewService {

	private StoreReviewDao storeReviewDao = new StoreReviewDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public Integer storeReview(StoreReviewWriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			StoreReview storeReview = toStoreReview(req);
			
			StoreReview savedstore  = storeReviewDao.insert(conn, storeReview);

			if (savedstore == null) {
				throw new RuntimeException("fail to insert Store");
			}

			conn.commit();
			return savedstore.getProdNum();
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

	private StoreReview toStoreReview(StoreReviewWriteRequest req) {
		Date now = new Date();
		return new StoreReview(
				null,
				req.getWriter(),
				req.getProdNum(),
				now,
				req.getGrade(),
				req.getContent());
	}
	
}
