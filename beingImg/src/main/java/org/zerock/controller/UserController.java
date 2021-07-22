package org.zerock.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchCriteria;
//import org.zerock.domain.BoardVO;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.interceptor.LoginInterceptor;
import org.zerock.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	private UserService service;

//   @RequestMapping(value = "/loginForm")
//   public String userLogin(Model model, LoginDTO dto) throws Exception {
//      logger.info("// /user/loginForm");
//
//      UserVO loginForm = userService.login(dto);
//
//      logger.info("// loginForm.toString()=" + loginForm.toString());
//
//      model.addAttribute("loginForm", loginForm);
//      
//      return "/user/loginForm";
//      
//   }

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		System.out.println("/user/loginPost 실행 ....");
		UserVO vo = service.login(dto);
		// System.out.println("*** UserVO.name="+vo.getName());
		if (vo == null) {
			return;
		}
		if (vo.getLvl() == 1) {
			request.getSession().setAttribute("admin", vo);
		}
		if (vo.getLvl() == 2) {
			request.getSession().setAttribute("general", vo);
		}
		model.addAttribute("userVO", vo);
		if (dto.isUseCookie()) {
			int amount = 60 * 60 * 24 * 7;
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			service.keepLogin(vo.getEmail(), session.getId(), sessionLimit);
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		Object obj = session.getAttribute("login");
		if (obj != null) {
			UserVO vo = (UserVO) obj;
			session.removeAttribute("login");
			session.invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getEmail(), session.getId(), new Date());
			}
		}

		return "user/logout";
	}

	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public void joinForm(UserVO userVO, Model model) throws Exception {
		logger.info("joinForm get ...........");
	}

	@RequestMapping(value = "/joinPost", method = RequestMethod.POST)
	public String joinPOST(UserVO userVO, RedirectAttributes rttr) throws Exception {

		service.create(userVO);

		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(userVO.toString());

		return "redirect:/user/loginForm";
	}

}