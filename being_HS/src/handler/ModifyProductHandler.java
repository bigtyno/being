package handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.PermissionDeniedException;
import auth.service.User;
import member.service.ModifyProductService;
import model.Product;
import mvc.command.CommandHandler;
import service.ReadProductService;

public class ModifyProductHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/product/modifyProductForm.jsp";

	private ReadProductService readService = new ReadProductService();
	private ModifyProductService modifyService = new ModifyProductService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			Product product = readService.getProduct(no);
			
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (!canModify(authUser)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			
			Product modReq = new Product(authUser.getId(), 
					no,					
					product.getName(),
					product.getThumbnail(),
					product.getInfoimage(),
					product.getIntroduce(),
					product.getPrice(),
					product.getDcprice(),
					product.getBrand(),
					product.getKeywd(),
					product.getCategory(),
					product.getFreeyn(),
					product.getLink()
					);
			
			System.out.println("modify:" + modReq);
			req.setAttribute("modReq", modReq);

			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private boolean canModify(User authUser) {		
		if(authUser.getLvl()==1)
			return true;
		
		return false;
		
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	    }
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		

		Product modReq = new Product(
				authUser.getId(),
				no,
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
				
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		
		
		
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/product/modifyProductSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
