package com.bigtyno.being.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
import org.zerock.dto.LoginDTO;

import com.bigtyno.being.domain.MailHandler;
import com.bigtyno.being.domain.TempKey;
import com.bigtyno.being.domain.UserVO;
import com.bigtyno.being.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
@RequestMapping("/user/*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private static final String svrAddr = "http://localhost:8080";
	
	@Inject
	private UserService service;
	
   
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpServletRequest request, HttpSession session, Model model) throws Exception {
		System.out.println("/user/loginPost ?????? ....");
		UserVO vo = service.login(dto);
		// System.out.println("*** UserVO.name="+vo.getName());
		if (vo == null) {
			return;
		}
//		if (vo.getLvl() == 1) {
//			request.getSession().setAttribute("admin", vo);
//		}
//		if (vo.getLvl() == 2) {
//			request.getSession().setAttribute("general", vo);
//		}
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
	public String joinPOST(HttpServletRequest req, UserVO userVO, RedirectAttributes rttr) throws Exception {
		
		String email_code = new TempKey().getKey(30,  false);
		userVO.setEmail_code(email_code);
		
		service.create(userVO);
		
		String email = userVO.getEmail();
		
		String code = (email_code + email);
		
		String activationURL = svrAddr + req.getContextPath() + "/user/activation";
    	
		//validateEmail(userVO.getEmail(), email_code+userVO.getEmail(), activationURL); 
		
		try {
		    		
		    		MimeMessage message = mailSender.createMimeMessage();
		    		MimeMessageHelper messageHelper 
		    				= new MimeMessageHelper(message, true, "UTF-8");
		    		
		    		messageHelper.setFrom("bigtyno@naver.com");
		    		messageHelper.setSubject("being ????????? ?????? ??????");
		    		messageHelper.setText("being ????????? ???????????? ?????? ??????????????????. \n\n"
		    				+ "being??? ???????????? ????????? ????????? ???????????? ????????? ?????????????????????.\n\n"
		    				+ "?????? ???????????? ?????? ?????? ????????? ???????????? ????????? ????????? ???????????????????????????.\n\n"
		    				+ activationURL + "?cd="
		    				+ code);
		    		messageHelper.setTo(email);
		    			
		    		mailSender.send(message);
		
		    	} catch(Exception e) {
		    		//errors.rejectValue("contractAgreement", "ValidEmail.userForm.username");
		    		
		    	}  
		
		rttr.addFlashAttribute("msg", "SUCCESS");

		logger.info(userVO.toString());

		return "/user/joinSuccess";
	}
	
	
	
	public static JsonNode getAccessToken(String autorize_code) {
		 
        final String RequestUrl = "https://kauth.kakao.com/oauth/token";
        final List<BasicNameValuePair> postParams = new ArrayList<BasicNameValuePair>();
 
        postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        postParams.add(new BasicNameValuePair("client_id", "d31e8bec18195625a37e0ff70e60e749"));
        postParams.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/being/user/KaKaoLogin"));
        postParams.add(new BasicNameValuePair("code", autorize_code));
 
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpPost post = new HttpPost(RequestUrl);
 
        JsonNode returnNode = null;
 
        try {
 
            post.setEntity(new UrlEncodedFormEntity(postParams));
            final HttpResponse response = client.execute(post);
            final int responseCode = response.getStatusLine().getStatusCode();
            
            System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
            System.out.println("Post parameters : " + postParams);
            System.out.println("Response Code : " +responseCode);
            
            //JSON ?????? ????????? ??????
            ObjectMapper mapper = new ObjectMapper();
            returnNode = mapper.readTree(response.getEntity().getContent());
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
 
        } catch (ClientProtocolException e) {
            e.printStackTrace();
 
        } catch (IOException e) {
            e.printStackTrace();
 
        } finally {
 
        }
 
        return returnNode;
    }
   
	   public static JsonNode getKakaoUserInfo(String access_token) { 
			
		    final String RequestUrl = "https://kapi.kakao.com/v2/user/me"; 
			final HttpClient client = HttpClientBuilder.create().build(); 
			final HttpPost post = new HttpPost(RequestUrl); 
			String accessToken = access_token;
			
			// add header
			post.addHeader("Authorization", "Bearer " + accessToken); 
			JsonNode returnNode = null; 
			try { 
				final HttpResponse response = client.execute(post); 
				final int responseCode = response.getStatusLine().getStatusCode();
				System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
	            System.out.println("Response Code : " + responseCode);
				
				ObjectMapper mapper = new ObjectMapper(); 
				returnNode = mapper.readTree(response.getEntity().getContent()); } 
			catch (ClientProtocolException e) { e.printStackTrace(); } 
			catch (IOException e) { e.printStackTrace(); } 
			finally {
				// clear resources 
				} return returnNode; 
			}
	   
	  
	   public int kakaoLogout(String access_token) {
	        
		    final String RequestUrl = "https://kapi.kakao.com/v1/user/logout";
	        final HttpClient client = HttpClientBuilder.create().build(); 
			final HttpPost post = new HttpPost(RequestUrl); 
			String accessToken = access_token;
			int responseCode = 0;
			
			post.addHeader("Authorization", "Bearer " + accessToken); 
			JsonNode returnNode = null; 
			
			try { 
				final HttpResponse response = client.execute(post); 
				responseCode = response.getStatusLine().getStatusCode();
				System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
	            System.out.println("Response Code : " + responseCode);
			} catch (UnsupportedEncodingException e){
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				
			}
			
			return responseCode;
			
	   }
	   
	   
	   @RequestMapping(value = "/KaKaoLogin", method = RequestMethod.GET)
	   public String kakaologin(@RequestParam("code") String code, HttpSession session, Model model)throws Exception{
		   
		   JsonNode jsonToken = getAccessToken(code);
		   System.out.println(jsonToken);
		   
		   String access_token  = jsonToken.get("access_token").toString();
		   JsonNode userInfo = getKakaoUserInfo(access_token );
		   System.out.println(userInfo);
		  
		   String id = userInfo.path("kakao_account").path("email").toString();
		   String nickName = userInfo.path("properties").path("nickname").toString();
		   
		   UserVO userVO = new UserVO();
		   
		   userVO.setEmail(id);
		   
		   
		   UserVO vo = service.kakaoLogin(userVO);
		   if (vo == null) {
			   vo = new UserVO();
			   vo.setEmail(id); 
			   vo.setName(nickName);
			   vo.setPassword(id);
			   //vo.setLvl(2);
			   service.create(vo);
			   
		      }
		   
		   System.out.println(vo);
		   session.setAttribute("login", vo);
		   session.setAttribute("access_token", access_token);
		  
		   System.out.println(id+nickName);
		   
		   //model.addAttribute("userVO", vo);
		     
		   
		   return "redirect:/index";
		   
	  }
	   
	   
	    
	    
	   //???????????? ???????????? ?????? ????????? ??????????????? ???????????? ????????????.
	    @RequestMapping(value = "/activation", method = RequestMethod.GET)
	    public String activation(@RequestParam("cd") String cd, Model model) {

	    	String result = "fail";
	    	//int userid = Integer.parseInt(cd.substring(30));
	    	String email = cd.substring(30);
	    	String email_code = cd.substring(0, 30);
	    	//logger.debug("emailcode: " + emailcode + "  | userid: " + cd.substring(30));
	    	
	    	UserVO user = service.findById(email);
	    	
	    	if (user.getEmail_code().equals(email_code)) {
	    		service.confirmEmail(email);
	    		result = "success";
	    	}

	    	model.addAttribute("result", result);
	        return "/index";
	    }
	    
	    @RequestMapping(value = "/find", method = RequestMethod.GET)
	    public String find(Model model) {
	        return "/user/find";
	    }

	    
	    @RequestMapping(value="/find", method=RequestMethod.POST)
	    public @ResponseBody String find(@RequestParam("email") String email) {
	    	logger.info("email input : {}", email);
	    	
	    	UserVO user = service.findById(email);
	        if (user == null) {
	            return "0";
	        }
	    	
	    	String code = new TempKey().getKey(6, false);
	    	logger.info("code : {}", code);
	    	
	    	try {
	    		
	    		MimeMessage message = mailSender.createMimeMessage();
	    		MimeMessageHelper messageHelper 
	    				= new MimeMessageHelper(message, true, "UTF-8");
	    		
	    		messageHelper.setFrom("bigtyno@naver.com");
	    		messageHelper.setSubject("being [??????] : ????????????");
	    		messageHelper.setText("???????????????, ?????????! \n" + "being ??????????????????.\n\n" 
	    				+ "???????????? ?????? ??????????????? ????????? ?????????????????????.\n ????????? ????????? ??????????????? ????????????????????? ????????????."
	    				+"???????????? : "+code);
	    		messageHelper.setTo(email);
	    			
	    		mailSender.send(message);
	    		
	    		user.setPassword(code);   		
	    		service.updatePassword(user);

	    	} catch(Exception e) {
	    		logger.debug(e.getMessage());
	    		return "1";
	    	}
	    	return "2";
	    }
}