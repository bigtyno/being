package handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.service.DeleteRequest;
import store.service.DeleteReviewRequest;
import store.service.DeleteStoreReviewService;
import store.service.DeleteStoreService;
import store.service.PermissionDeniedException;
import store.service.StoreNotFoundException;

public class DeleteStoreReviewHandler implements CommandHandler {
	private DeleteStoreReviewService deleteService = new DeleteStoreReviewService();
	
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

	/*
	 * private boolean canModify(User authUser, StoreData storeData) { String
	 * writerId = storeData.getStore().getWriter().getId(); return
	 * authUser.getId().equals(writerId); }
	 */

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception { 
		// System.out.println("processSubmit 실행....");
		
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal); 
		
		

		DeleteReviewRequest delReq = new DeleteReviewRequest( no);
		req.setAttribute("delReq", delReq);

		
//		Map<String, Boolean> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
//		delReq.validate(errors);
//		if (!errors.isEmpty()) {
//			return "/WEB-INF/view/board/listArticle.jsp";
//		}
		
		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/store/StoreReviewDeleteSuccess.jsp";
		} catch (StoreNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}


}
