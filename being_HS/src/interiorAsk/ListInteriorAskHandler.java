package interiorAsk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class ListInteriorAskHandler implements CommandHandler {

	private ListInteriorAskService listService = new ListInteriorAskService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		InteriorAskPage interiorAskPage = listService.getInteriorAskPage(pageNo);
		System.out.println(interiorAskPage.getContent().get(0));
		req.setAttribute("interiorAskPage", interiorAskPage);
		return "/WEB-INF/view/interiorAsk/listInteriorAsk.jsp";
	}

}
