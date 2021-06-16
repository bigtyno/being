package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ReadInteriorHandler implements CommandHandler {

	private ReadInteriorService readService = new ReadInteriorService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int interiorNum = Integer.parseInt(noVal);
		try {
			Interior interiorData = readInterior.getInterior(interiorNum, true);
			req.setAttribute("interiorData", interiorData);
			return "/WEB-INF/view/interior/Interior.jsp";
		} catch (InteriorNotFoundException e) {
			req.getServletContext().log("no store", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
