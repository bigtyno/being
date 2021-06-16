package faq.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import faq.dao.FaqDao;
import faq.model.Faq;
//import article.dao.ArticleDao;
//import article.model.Article;
import jdbc.connection.ConnectionProvider;


public class ListFaqService {

	private FaqDao faqDao = new FaqDao();
	private int size = 10;

	public FaqPage getFaqPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int total = faqDao.selectCount(conn);
			List<Faq> content = faqDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);
				//	conn, (pageNum - 1) * size, size);
			return new FaqPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
