package prod.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import article.service.ArticlePage;
import jdbc.connection.ConnectionProvider;

public class ProdListService {

	private ProdDao prodDao = new ProdDao();
	private int size = 10;

	public ArticlePage getArticlePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = prodDao.selectCount(conn);
			List<Article> content = prodDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);				
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
