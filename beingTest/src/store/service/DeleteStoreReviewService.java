package store.service;

import java.sql.Connection;
import java.sql.SQLException;

import store.dao.StoreReviewDao;
import store.model.StoreReview;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteStoreReviewService {

	private StoreReviewDao storeReviewDao = new StoreReviewDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void delete(DeleteReviewRequest delReq) {
		// System.out.println("delete() 실행...");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			StoreReview storeReview = storeReviewDao.selectById(conn, 
					delReq.getStoreNumber());
			if (storeReview == null) {
				throw new StoreNotFoundException();
			}
			/*
			 * if (!canModify(delReq.getUserId(), store)) { throw new
			 * PermissionDeniedException(); }
			 */
			System.out.println("delReq.storeNumber="+delReq.getStoreNumber());
			
			storeReviewDao.delete(conn, 
					delReq.getStoreNumber());
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

	/*
	 * private boolean canModify(String modfyingUserId, Store store) { return
	 * store.getId().equals(modfyingUserId); }
	 */
}
