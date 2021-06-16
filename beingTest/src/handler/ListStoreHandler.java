package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import article.service.ArticlePage;
//import article.service.ListArticleService;
import mvc.command.CommandHandler;
import store.service.ListStoreService;
import store.service.StorePage;

public class ListStoreHandler implements CommandHandler {

	private ListStoreService listService = new ListStoreService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		StorePage storePage = listService.getStorePage(pageNo);
		req.setAttribute("storePage", storePage);
		return "/WEB-INF/view/store/listStore.jsp";
	}

}
