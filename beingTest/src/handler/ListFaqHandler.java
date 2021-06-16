package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.service.FaqPage;
import faq.service.ListFaqService;
//import article.service.ArticlePage;
//import article.service.ListArticleService;
import mvc.command.CommandHandler;


public class ListFaqHandler implements CommandHandler {

	private ListFaqService listService = new ListFaqService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		FaqPage faqPage = listService.getFaqPage(pageNo);
		req.setAttribute("faqPage", faqPage);
		return "/WEB-INF/view/faq/listFaq.jsp";
	}

}
