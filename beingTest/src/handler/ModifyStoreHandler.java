package handler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.service.PermissionDeniedException;
import auth.service.User;
import mvc.command.CommandHandler;
import store.service.ModifyStoreRequest;
import store.service.ModifyStoreService;
import store.service.ReadStoreService;
import store.service.StoreData;
import store.service.StoreNotFoundException;

public class ModifyStoreHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/store/storeModifyForm.jsp";

	private ReadStoreService readService = new ReadStoreService();
	private ModifyStoreService modifyStoreService = new ModifyStoreService();

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
			StoreData storeData = readService.getStore(no, false);
			User authUser = (User) req.getSession().getAttribute("authUser");
			if (authUser.getLevel() != 1) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			
			ModifyStoreRequest modReq = new ModifyStoreRequest(authUser.getId(), 
					no,
					storeData.getStore().getName(),
					storeData.getStore().getThumbnail(),
					storeData.getStore().getInfoimage(),
					storeData.getStore().getIntroduce(),
					storeData.getStore().getPrice(),
					storeData.getStore().getDcprice(),
					storeData.getStore().getBrand(),
					storeData.getStore().getKeywd(),
					storeData.getStore().getCategory(),
					storeData.getStore().getFreeyn(),
					storeData.getStore().getLink()
					
					);

			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		} catch (StoreNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

//	private boolean canModify(User authUser, StoreData storeData) {
//		String writerId = storeData.getStore().getWriter().getId();
//		return authUser.getId().equals(writerId);
//	}
	
//	private boolean canModify(User authUser, StoreData storeData) {
//		String writerId = storeData.getStore().getWriter().getId();
//		return authUser.getId().equals(writerId);
//	}

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
		

		ModifyStoreRequest modReq = new ModifyStoreRequest(authUser.getId(), no,
				req.getParameter("name"),
				req.getParameter("thumbnail"),
				req.getParameter("infoimage"),
				req.getParameter("introduce"),
				Integer.parseInt(req.getParameter("price")),
				Integer.parseInt(req.getParameter("dcprice")),
				req.getParameter("brand"),
				req.getParameter("keywd"),
				req.getParameter("category"),
				req.getParameter("freeyn"),
				req.getParameter("link")
				);
		req.setAttribute("modReq", modReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		modReq.validate(errors);
		
		
		
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyStoreService.modify(modReq);
			return "/WEB-INF/view/store/storeModifySuccess.jsp";
		} catch (StoreNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}
}
