package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ProductDao;
import jdbc.connection.ConnectionProvider;
import model.Product;
import model.ProductData;

public class ReadProductService {

	private ProductDao productDao = new ProductDao();

	
	public ProductData getProduct(int productNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			Product product = productDao.selectById(conn, productNum);
			if (product == null) {
				throw new ProductNotFoundException();
			}

			return new ProductData(product);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
