package store.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreReviewDao;
import store.model.StoreReview;

public class ModifyStoreReviewService {

	private StoreReviewDao storeReviewDao = new StoreReviewDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyStoreReviewRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			StoreReview storeReview = storeReviewDao.selectById(conn, 
					modReq.getNum());
			if (storeReview == null) {
				throw new StoreNotFoundException();
			}
//			if (!canModify(modReq.getUserId(), storeReview)) {
//				throw new PermissionDeniedException();
//			}
			storeReviewDao.update(conn,
					modReq.getNum(), 
					modReq.getGrade(), 
					modReq.getContent()
					);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

//	private boolean canModify(String modfyingUserId, Article article) {
//		return article.getWriter().getId().equals(modfyingUserId);
//	}
}
