package faq.service;

import java.sql.Connection;
import java.sql.SQLException;

import faq.dao.FaqDao;
import faq.model.Faq;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
public class ModifyFaqService {

	private FaqDao faqDao = new FaqDao();
//	private ArticleContentDao contentDao = new ArticleContentDao();

	public void modify(ModifyFaqRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Faq faq = faqDao.selectById(conn, 
					modReq.getNum());
			if (faq == null) {
				throw new FaqNotFoundException();
			}
		
			faqDao.update(conn, 
					modReq.getNum(), 
					modReq.getCategory(),
					modReq.getQuestion(),
					modReq.getAnswer()
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


}
