package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mvc.command.CommandHandler;
import store.service.ReadStoreService;
import store.service.StoreData;
import store.service.StoreNotFoundException;

public class ReadStoreHandler implements CommandHandler {

	private ReadStoreService readService = new ReadStoreService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		String noVal = req.getParameter("no");
		int storeNum = Integer.parseInt(noVal);
		try {
			StoreData storeData = readService.getStore(storeNum, true);
			
			req.setAttribute("storeData", storeData);
			return "/WEB-INF/view/store/readStore.jsp";
		} catch (StoreNotFoundException e) {
			req.getServletContext().log("no store", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
