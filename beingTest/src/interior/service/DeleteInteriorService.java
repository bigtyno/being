package interior.service;

import java.sql.Connection;
import java.sql.SQLException;

import interior.dao.InteriorDao;
import interior.model.Interior;


import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteInteriorService {

	private InteriorDao interiorDao = new InteriorDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void delete(DeleteRequest delReq) {
		// System.out.println("delete() 실행...");
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Interior interior = interiorDao.selectById(conn, 
					delReq.getInteriorNumber());
			if (interior == null) {
				throw new InteriorNotFoundException();
			}
			/*
			 * if (!canModify(delReq.getUserId(), store)) { throw new
			 * PermissionDeniedException(); }
			 */
			System.out.println("delReq.Number="+delReq.getInteriorNumber());
			
			interiorDao.delete(conn, 
					delReq.getInteriorNumber());
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
