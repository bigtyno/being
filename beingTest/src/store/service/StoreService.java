package store.service;

import java.sql.Connection;
import java.sql.SQLException;
//import java.util.Date;

//import article.dao.ArticleContentDao;
//import article.model.ArticleContent;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;

public class StoreService {

	private StoreDao storeDao = new StoreDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public Integer store(StoreWriteRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Store store = toStore( req);
//			storeDao.insert(conn, store);
			
			Store savedstore  = storeDao.insert(conn, store);
//			System.out.println("article insert 성공....");

			if (savedstore == null) {
				throw new RuntimeException("fail to insert Store");
			}
//			ArticleContent content = new ArticleContent(savedArticle.getNumber(), req.getContent());
//			ArticleContent savedContent = contentDao.insert(conn, content);
//			if (savedContent == null) {
//				throw new RuntimeException("fail to insert article_content");
//			}

			conn.commit();
//			return 1;
			return savedstore.getNum();
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

	private Store toStore(StoreWriteRequest req) {
//		Date now = new Date();
		return new Store(null, 
//				req.getWriter(),
				req.getName(), 
				req.getThumbnail(),
				req.getInfoimage(), 
				req.getIntroduce(), 
				req.getPrice(),
			req.getDcprice(),
			req.getBrand(),
			req.getKeywd(),
			req.getCategory(),
			req.getFreeyn(),
			req.getLink());
	}
	
}
