package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import store.service.ListStoreReviewService;
import store.service.StoreReviewPage;

public class ListStoreReviewHandler implements CommandHandler {

	private ListStoreReviewService listService = new ListStoreReviewService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int no = Integer.parseInt(req.getParameter("no"));
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		StoreReviewPage storeReviewPage = listService.getStorePage(pageNo, no);
		System.out.println(storeReviewPage.getContent().get(0).getGrade());
		req.setAttribute("storeReviewPage", storeReviewPage);
		
		return "/WEB-INF/view/storeReview/listStoreReview.jsp";
	}

}
