package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.service.DeleteFaqService;
import faq.service.DeleteRequest;
import interior.service.InteriorNotFoundException;
import interior.service.PermissionDeniedException;
import mvc.command.CommandHandler;


public class DeleteFaqHandler implements CommandHandler {
	
	private DeleteFaqService deleteService = new DeleteFaqService();

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		DeleteRequest delReq = new DeleteRequest(no);
		req.setAttribute("delReq", delReq);

		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/faq/deleteFaqSuccess.jsp";
		} catch (InteriorNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
