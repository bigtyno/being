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
import store.service.ReadStoreService;
import store.service.StoreData;
import store.service.StoreReviewService;
import store.service.StoreReviewWriteRequest;
//import store.model.Store;
//import store.service.StoreService;
//import store.service.StoreWriteRequest;

public class StoreReviewWriteHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/storeReview/newStoreReviewForm.jsp";
	private StoreReviewService storeReviewService = new StoreReviewService();
	private ReadStoreService readService = new ReadStoreService();
	
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
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		StoreData storeData = readService.getStore(no, false);
		
		StoreReviewWriteRequest storeReq = new StoreReviewWriteRequest(
				storeData.getStore().getNum());
		
		req.setAttribute("storeReq", storeReq);
		
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
		
		StoreReviewWriteRequest storeReviewWriteReq = createStoreReviewWriteRequest(user, req);
		storeReviewWriteReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newStoreReviewNo = storeReviewService.storeReview(storeReviewWriteReq);
		req.setAttribute("newStoreReviewNo", newStoreReviewNo);
		
		return "/WEB-INF/view/storeReview/newStoreReviewSuccess.jsp";
	}

	private StoreReviewWriteRequest createStoreReviewWriteRequest(User user, HttpServletRequest req) {
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		System.out.println("no="+no);
		System.out.println("grade="+req.getParameter("grade"));
		return new StoreReviewWriteRequest(
				new Writer(user.getId(), user.getName()),
				no,
				Integer.parseInt(req.getParameter("grade")),
				req.getParameter("content")
				);
	}
}
