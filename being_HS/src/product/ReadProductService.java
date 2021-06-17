package product;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ProductDao;
import jdbc.connection.ConnectionProvider;

public class ReadProductService {

	private ProductDao productDao = new ProductDao();

	
	public Product getProduct(int productNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Product product = productDao.selectById(conn, productNum);
			if (product == null) {
				throw new ProductNotFoundException();
			}

			return product;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
