package faq.service;

import java.sql.Connection;
import java.sql.SQLException;

import faq.dao.FaqDao;
import faq.model.Faq;
import jdbc.connection.ConnectionProvider;

public class ReadFaqService {

	private FaqDao faqDao = new FaqDao();
	
	public Faq getFaq(int faqNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Faq faq = faqDao.selectById(conn, faqNum);
			if (faq == null) {
				throw new FaqNotFoundException();
			}
			return faq;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
