package interior.service;

import java.sql.Connection;
import java.sql.SQLException;

import interior.dao.InteriorDao;
import interior.model.Interior;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class ModifyInteriorService {

	private InteriorDao interiorDao = new InteriorDao();

	public void modify(ModifyInteriorRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Interior interior = interiorDao.selectById(conn, 
					modReq.getNum());
			if (interior == null) {
				throw new InteriorNotFoundException();
			}
			/*
			 * if (!canModify(modReq.getUserId(), interior)) { throw new
			 * PermissionDeniedException(); }
			 */
			interiorDao.update(conn, 
					modReq.getNum(), 
					modReq.getName(),
					modReq.getIntroduce(),
					modReq.getImageA(),
					modReq.getImageB()				
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

	/*
	 * private boolean canModify(String modfyingUserId, Interior interior) { return
	 * interior.getWriter().getId().equals(modfyingUserId); }
	 */

}
