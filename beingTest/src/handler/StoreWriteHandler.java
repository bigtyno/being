package handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
//import article.service.WriteArticleService;
//import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;
//import store.model.Store;
import store.service.StoreService;
import store.service.StoreWriteRequest;

public class StoreWriteHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/store/newStoreForm.jsp";
	private StoreService storeService = new StoreService();
	
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
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	      }
		
		User user = (User)req.getSession(false).getAttribute("authUser");
//		System.out.println("user="+user);
//		System.out.println("user.id="+user.getId());
//		System.out.println("user.name="+user.getName());
//		WriteRequest writeReq = createWriteRequest(user, req);
//		writeReq.validate(errors);
		
		StoreWriteRequest storeWriteReq = createStoreWriteRequest(user, req);
		storeWriteReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newStoreNo = storeService.store(storeWriteReq);
		req.setAttribute("newStoreNo", newStoreNo);
		
		return "/WEB-INF/view/store/newStoreSuccess.jsp";
	}

	private StoreWriteRequest createStoreWriteRequest(User user, HttpServletRequest req) {
		return new	StoreWriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("name"),
				req.getParameter("thumbnail"),
				req.getParameter("infoimage"),
				req.getParameter("introduce"),
				Integer.parseInt(req.getParameter("PRICE")),
				Integer.parseInt(req.getParameter("DCPRICE")),
				req.getParameter("BRAND"),
				req.getParameter("KEYWD"),
				req.getParameter("CATEGORY"),
				req.getParameter("FREEYN"),
				req.getParameter("LINK")
				);
	}
}
