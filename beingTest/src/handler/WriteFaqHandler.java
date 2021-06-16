package handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import auth.service.User;
import faq.service.WriteFaqRequest;
import faq.service.WriteFaqService;
import mvc.command.CommandHandler;

public class WriteFaqHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/faq/newFaqForm.jsp";
	private WriteFaqService writeService = new WriteFaqService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         e1.printStackTrace();
	      }
		
		User user = (User)req.getSession(false).getAttribute("authUser");

		
		WriteFaqRequest writeFaqReq= createFaqrWriterRequest(user, req);
		writeFaqReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newFaqNo = writeService.faq(writeFaqReq);
		
		req.setAttribute("newFaqNo", newFaqNo);
		
		return "/WEB-INF/view/faq/newFaqSuccess.jsp";
	}


	private WriteFaqRequest createFaqrWriterRequest(User user, HttpServletRequest req) {
		return new	WriteFaqRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("category"),
				req.getParameter("question"),
				req.getParameter("answer"));
	}
}
