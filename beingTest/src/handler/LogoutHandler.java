package handler;

//import java.util.HashMap;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import auth.service.LoginFailException;
//import auth.service.LoginService;
//import auth.service.User;
import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/login/logoutsuccess"
			+ ".jsp";
//	private static final String SUCCESS = "/WEB-INF/view/login/loginSuccess.jsp";
//	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processForm(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
}