package handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import auth.service.User;
import model.Product;
import mvc.command.CommandHandler;
import service.WriteProductService;

public class WriteProductHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/product/newStoreForm.jsp";
	private WriteProductService writeProductService = new WriteProductService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
				
		User user = (User)req.getSession(false).getAttribute("authUser");

		Product productreq = createProduct(user, req);
		System.out.println(productreq );
		productreq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newProductNo = writeProductService.write(productreq);
		System.out.println("ㅇㅇ");
		
		req.setAttribute("newProductNo", newProductNo);
		
		return "/WEB-INF/view/product/newStoreSuccess.jsp";
	}

	private Product createProduct(User user, HttpServletRequest req) {
		return new Product(
				new Writer(user.getId(),user.getName()),
				req.getParameter("name"),
				req.getParameter("thumbnail"),
				req.getParameter("infoimage"),
				req.getParameter("introduce"),
				Integer.parseInt(req.getParameter("price")),
				Integer.parseInt(req.getParameter("dcprice")),
				req.getParameter("brand"),
				req.getParameter("keywd"),
				req.getParameter("category"),
				req.getParameter("freeyn"),
				req.getParameter("link"));
	}
}
