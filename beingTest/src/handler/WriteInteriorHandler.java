package handler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import auth.service.User;
import interior.service.WriteInteriorService;
import interior.service.WriteInteriorRequest;
import mvc.command.CommandHandler;

public class WriteInteriorHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/interior/newInteriorForm.jsp";
	private WriteInteriorService writeService = new WriteInteriorService();
	
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

		
		WriteInteriorRequest interiorWriteReq = createInteriorWriterRequest(user, req);
		interiorWriteReq.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newInteriorNo = writeService.interior(interiorWriteReq);
		
		req.setAttribute("newInteriorNo", newInteriorNo);
		
		return "/WEB-INF/view/interior/newInteriorSuccess.jsp";
	}


	private WriteInteriorRequest createInteriorWriterRequest(User user, HttpServletRequest req) {
		return new	WriteInteriorRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("name"),
				req.getParameter("introduce"),
				req.getParameter("imageA"),
				req.getParameter("imageB"));
	}
}
