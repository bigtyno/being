package handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import faq.model.Faq;
import faq.service.FaqNotFoundException;
import faq.service.ModifyFaqRequest;
import faq.service.ModifyFaqService;
import faq.service.PermissionDeniedException;
import faq.service.ReadFaqService;
import mvc.command.CommandHandler;

public class ModifyFaqHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/faq/faqModifyForm.jsp";

	private ReadFaqService readService = new ReadFaqService();
	private ModifyFaqService modifyFaqService = new ModifyFaqService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			Faq faq = readService.getFaq(no);
			User authUser = (User) req.getSession().getAttribute("authUser");
			
			
			
			ModifyFaqRequest modReq = new ModifyFaqRequest(authUser.getId(), 
					no,
					faq.getCategory(),
					faq.getQuestion(),
					faq.getAnswer()
					
					);

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (FaqNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}


	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         // TODO Auto-generated catch block
	         e1.printStackTrace();
	    }
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		

		ModifyFaqRequest modReq = new ModifyFaqRequest(authUser.getId(), no,
				req.getParameter("category"),
				req.getParameter("question"),
				req.getParameter("answer")
				);
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		
		
		
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyFaqService.modify(modReq);
			return "/WEB-INF/view/faq/faqModifySuccess.jsp";
		} catch (FaqNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
