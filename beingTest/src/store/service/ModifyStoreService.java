package store.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;
//import auth.service.User;

public class ModifyStoreService {

	private StoreDao storeDao = new StoreDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyStoreRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Store store = storeDao.selectById(conn, 
					modReq.getNum());
			if (store == null) {
				throw new StoreNotFoundException();
			}
//			if (!canModify(modReq.getUserId(), store)) {
//				throw new PermissionDeniedException();
//			}
			storeDao.update(conn, 
					modReq.getNum(), 
					modReq.getName(),
					modReq.getThumbnail(),
					modReq.getInfoimage(),
					modReq.getIntroduce(),
					modReq.getPrice(),
					modReq.getDcprice(),
					modReq.getBrand(),
					modReq.getKeywd(),
					modReq.getCategory(),
					modReq.getFreeyn(),
					modReq.getLink()
					);
//			articleDao.update(conn, 
//					modReq.getArticleNumber(), modReq.getContent());
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

//	private boolean canModify(User modfyingUserId) {
////		return store.getWriter().getId().equals(modfyingUserId);
//		if(modfyingUserId.getLevel()==1) {
//			return true;
//		} else {
//			return false;
//			
//		}
//		
//	}

}
