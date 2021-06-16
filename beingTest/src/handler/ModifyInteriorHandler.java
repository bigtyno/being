package handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import interior.model.Interior;
import interior.service.InteriorNotFoundException;
import interior.service.ModifyInteriorRequest;
import interior.service.ModifyInteriorService;
import interior.service.PermissionDeniedException;
import interior.service.ReadInteriorService;
import mvc.command.CommandHandler;

public class ModifyInteriorHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/interior/modifyInteriorForm.jsp";

	private ReadInteriorService readService = new ReadInteriorService();
	private ModifyInteriorService modifyService = new ModifyInteriorService();

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
			Interior interior = readService.getInterior(no);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (authUser.getLevel() != 1) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			
			ModifyInteriorRequest modReq = new ModifyInteriorRequest(authUser.getId(), 
					no,
					interior.getName(),
					interior.getIntroduce(),
					interior.getImageA(),
					interior.getImageB()
					);

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (InteriorNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		try {
	         req.setCharacterEncoding("UTF-8");
	      } catch (UnsupportedEncodingException e1) {
	         e1.printStackTrace();
	    }
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		

		ModifyInteriorRequest modReq = new ModifyInteriorRequest(authUser.getId(), no,
				req.getParameter("name"),
				req.getParameter("introduce"),
				req.getParameter("imageA"),
				req.getParameter("imageA")
				);
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		
		
		
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/interior/modifyInteriorSuccess.jsp";
		} catch (InteriorNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
