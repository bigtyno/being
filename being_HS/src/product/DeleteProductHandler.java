package product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import article.service.ArticleNotFoundException;
import article.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteProductHandler implements CommandHandler {
	//private static final String FORM_VIEW = "/WEB-INF/view/board/listArticle.jsp";
	
	private DeleteProductService deleteService = new DeleteProductService();
	private ReadProductService readService = new ReadProductService();
	private ModifyProductService modifyService = new ModifyProductService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}


	private boolean canModify(User authUser) {
		if(authUser.getLvl() == 1)
		return true;
		return false;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception { 
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("num");
		int num = Integer.parseInt(noVal); 

		Product delReq = new Product(authUser, num);
		
		req.setAttribute("delReq", delReq);
		
		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/product/DeleteSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}


}
