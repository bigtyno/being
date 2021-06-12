package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductPage;
import mvc.command.CommandHandler;
import service.ListProductService;

public class ListProductHandler implements CommandHandler {

	private ListProductService productService = new ListProductService();
//
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ProductPage productPage = productService.getProductPage(pageNo);
		System.out.println(productPage.getContent().get(0).getName());
		req.setAttribute("ProductPage", productPage);		
		return "/WEB-INF/view/product/listProduct.jsp";
		
	}

}
