package store.service;

import java.sql.Connection;
import java.sql.SQLException;

//store
import store.dao.StoreDao;
import store.model.Store;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteStoreService {

	private StoreDao storeDao = new StoreDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void delete(DeleteRequest delReq) {
		// System.out.println("delete() 실행...");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Store store = storeDao.selectById(conn, 
					delReq.getStoreNumber());
			if (store == null) {
				throw new StoreNotFoundException();
			}
			/*
			 * if (!canModify(delReq.getUserId(), store)) { throw new
			 * PermissionDeniedException(); }
			 */
			System.out.println("delReq.storeNumber="+delReq.getStoreNumber());
			
			storeDao.delete(conn, 
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
