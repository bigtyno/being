package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ListProductHandler implements CommandHandler {

	private ListProductService productService = new ListProductService();
//
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNum");
		int pageNum = 1;
		if (pageNoVal != null) {
			pageNum = Integer.parseInt(pageNoVal);
		}
		ProductPage productPage = productService.getProductPage(pageNum);
		System.out.println(productPage.getContent());
		req.setAttribute("productPage", productPage);		
		return "/WEB-INF/view/product/listProduct.jsp";
		
	}

}
