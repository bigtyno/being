package service;

import java.sql.Connection;
import java.sql.SQLException;


import article.service.PermissionDeniedException;
import dao.ProductDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import model.Product;
import service.ProductNotFoundException;

public class ModifyProductService {

	private ProductDao productDao = new ProductDao();

	public void modify(Product modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Product product = productDao.selectById(conn, 
					modReq.getNum());
			
			if (product == null) {
				throw new ProductNotFoundException();
			}					
			 
			productDao.update(conn,
					modReq.getName(),
					modReq.getThumbnail(),
					modReq.getInfoimage(),
					modReq.getIntroduce(),
					modReq.getPrice(),
					modReq.getDcprice(),
					modReq.getBrand(),
					modReq.getKeywd(),
					modReq.getCategory(),
					modReq.getFreeyn(),
					modReq.getLink(),
					modReq.getNum()
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
