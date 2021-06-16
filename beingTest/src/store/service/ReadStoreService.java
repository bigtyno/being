package store.service;

import java.sql.Connection;
import java.sql.SQLException;

//import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.service.ArticleData;
//import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;
import store.dao.StoreDao;
import store.model.Store;

public class ReadStoreService {

	private StoreDao storeDao = new StoreDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public StoreData getStore(int storeNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Store store = storeDao.selectById(conn, storeNum);
			if (store == null) {
				throw new StoreNotFoundException();
			}
//			ArticleContent content = contentDao.selectById(conn, articleNum);
//			if (content == null) {
//				throw new ArticleContentNotFoundException();
//			}
			/*
			 * if (increaseReadCount) { storeDao.increaseReadCount(conn, storeNum); }
			 */
//			return new ArticleData(article, content);
			return new StoreData(store);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * public StoreData getStore(int storeNum, boolean b) { // TODO Auto-generated
	 * method stub return null; }
	 */
}
