package faq.service;

import java.sql.Connection;
import java.sql.SQLException;

import faq.dao.FaqDao;
import faq.model.Faq;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteFaqService {

	private FaqDao faqDao = new FaqDao();

	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Faq faq = faqDao.selectById(conn, 
					delReq.getFaqNumber());
			if (faq == null) {
				throw new FaqNotFoundException();
			}

			System.out.println("delReq.Number="+delReq.getFaqNumber());
			
			faqDao.delete(conn, 
					delReq.getFaqNumber());
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

}
