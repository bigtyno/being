package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interior.model.Interior;
import interior.service.InteriorNotFoundException;
import interior.service.ReadInteriorService;
import mvc.command.CommandHandler;

public class ReadInteriorHandler implements CommandHandler {

	private ReadInteriorService readInterior = new ReadInteriorService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int interiorNum = Integer.parseInt(noVal);
		try {
			Interior interior = readInterior.getInterior(interiorNum);
			req.setAttribute("interior", interior);
			return "/WEB-INF/view/interior/readInterior.jsp";
		} catch (InteriorNotFoundException e) {
			req.getServletContext().log("no interior", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
