package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interior.service.DeleteInteriorService;
import interior.service.DeleteRequest;
import interior.service.InteriorNotFoundException;
import interior.service.PermissionDeniedException;
import mvc.command.CommandHandler;


public class DeleteInteriorHandler implements CommandHandler {
	
	private DeleteInteriorService deleteService = new DeleteInteriorService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// System.out.println("processSubmit 실행....");

		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		DeleteRequest delReq = new DeleteRequest(no);
		req.setAttribute("delReq", delReq);

//		Map<String, Boolean> errors = new HashMap<>();
//		req.setAttribute("errors", errors);
//		delReq.validate(err4ors);
//		if (!errors.isEmpty()) {
//			return "/WEB-INF/view/board/listArticle.jsp";
//		}

		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/interior/InteriorDeleteSuccess.jsp";
		} catch (InteriorNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
