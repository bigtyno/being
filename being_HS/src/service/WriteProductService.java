package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.ProductDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Product;

public class WriteProductService {

	private ProductDao productdao = new ProductDao();

	public Integer write(Product req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Product product = toProduct(req);			
			Product savedProduct = productdao.insert(conn, product);

			if (savedProduct == null) {
				throw new RuntimeException("fail to insert product");
			}
			conn.commit();

			return savedProduct.getNum();
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

	private Product toProduct(Product req) {
		
		return new Product(
				req.getWriter(),
				req.getName(),
				req.getThumbnail(),
				req.getInfoimage(),
				req.getIntroduce(),
				req.getPrice(),
				req.getdcprice(),
				req.getBrand(),
				req.getKeywd(),
				req.getCategory(),
				req.getFreeyn(),
				req.getLink()
				); 
				
		
	}

	/*
	 * private Article toArticle(WriteRequest req) { Date now = new Date(); return
	 * new Article(null, req.getWriter(), req.getType(), req.getAcreage(),
	 * req.getBudget(), req.getField(), req.getSpace(), req.getTitle(),
	 * req.getContent(), // req.getProdnum(), now, 0); }
	 */
}
