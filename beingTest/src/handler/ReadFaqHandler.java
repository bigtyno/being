package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import faq.model.Faq;
import faq.service.FaqNotFoundException;
import faq.service.ReadFaqService;
import mvc.command.CommandHandler;

public class ReadFaqHandler implements CommandHandler {

	private ReadFaqService readService = new ReadFaqService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int faqNum = Integer.parseInt(noVal);
		try {
			Faq faq = readService.getFaq(faqNum);
			req.setAttribute("faq", faq);
			return "/WEB-INF/view/faq/readFaq.jsp";
		} catch (FaqNotFoundException e) {
			req.getServletContext().log("no faq", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
