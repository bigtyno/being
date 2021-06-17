package product;

import java.sql.Connection;
import java.sql.SQLException;
import article.service.PermissionDeniedException;
import dao.ProductDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class DeleteProductService {

	private ProductDao productDao = new ProductDao();

	public void delete(Product delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Product product = productDao.selectById(conn, 
					delReq.getProductNum());
			System.out.println(product);
			if (product == null) {
				throw new ProductNotFoundException();
			}
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
