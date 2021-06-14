package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import mvc.command.CommandHandler;
import service.ProductNotFoundException;
import service.ReadProductService;

public class ReadProductHandler implements CommandHandler {

	private ReadProductService readService = new ReadProductService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("num");
		int productNum = Integer.parseInt(noVal);
		try {
			Product product = readService.getProduct(productNum);
			System.out.println(product);
			req.setAttribute("product", product);
			return "/WEB-INF/view/product/readProduct.jsp";
		} catch (ProductNotFoundException e) {
			req.getServletContext().log("no product", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
