package faq.service;

import java.sql.Connection;
import java.sql.SQLException;

import faq.dao.FaqDao;
import faq.model.Faq;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class WriteFaqService {

	private FaqDao faqDao = new FaqDao();

	public Integer faq(WriteFaqRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Faq faq = toFaq(req);
			Faq savedFaq = faqDao.insert(conn, faq);
			if (savedFaq == null) {
				throw new RuntimeException("fail to insert Faq");
			}

			conn.commit();

			return savedFaq.getNum();
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

	private Faq toFaq(WriteFaqRequest req) {		
		return new Faq(null,
				req.getCategory(),
				req.getQuestion(), 
				req.getAnswer());
	}
	
}
