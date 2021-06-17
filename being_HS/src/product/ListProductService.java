package product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProductDao;
import jdbc.connection.ConnectionProvider;

public class ListProductService {

	private ProductDao productDao = new ProductDao();
	private int size = 10;

	public ProductPage getProductPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			
			int total = productDao.selectCount(conn);
			List<Product> content = productDao.select(
					conn, (pageNum - 1) * size + 1, pageNum * size);				
			
			
			return new ProductPage(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
