package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interior.service.InteriorPage;
import interior.service.ListInteriorService;
import mvc.command.CommandHandler;

public class ListInteriorHandler implements CommandHandler {

	private ListInteriorService listService = new ListInteriorService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		InteriorPage interiorPage = listService.getInteriorPage(pageNo);
		req.setAttribute("interiorPage", interiorPage);
		return "/WEB-INF/view/interior/listInterior.jsp";
	}

}
