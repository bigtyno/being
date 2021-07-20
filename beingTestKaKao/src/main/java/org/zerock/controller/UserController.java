package org.zerock.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;
//import org.zerock.domain.BoardVO;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.service.UserService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/user/*")
public class UserController {

   private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

   @Inject
   private UserService service;

   private final static String id ="d31e8bec18195625a37e0ff70e60e749";
   private final static String url ="http://localhost:8080/being/KaKaoLogin";

   @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
   public void loginGET(@ModelAttribute("dto") LoginDTO dto) {
   }
   
	/*
	 * @RequestMapping(value = "/logininfo") public String loginview(Model model,
	 * HttpSession session) {
	 * 
	 * String kakaoUrl ="https://kauth.kkao.com/oauth/authorize?"
	 * +"client_id"+id+"&redirect_url"+url+"&response_type=code";
	 * model.addAttribute("kakaoUrl",kakaoUrl);
	 * 
	 * return "/user/loginForm"; }
	 */
   

   @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
   public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception {
      System.out.println("/user/loginPost 실행 ....");
      UserVO vo = service.login(dto);
      // System.out.println("*** UserVO.name="+vo.getName());
      if (vo == null) {
         return;
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
   
   @RequestMapping(value ="/joinForm", method = RequestMethod.GET)
   public void joinForm(UserVO userVO, Model model) throws Exception{
	   logger.info("joinForm get ...........");
   }

   @RequestMapping(value = "/joinPost", method = RequestMethod.POST)
   public String joinPOST(UserVO userVO, RedirectAttributes rttr) throws Exception {
      
	   
	   service.create(userVO);
	   
	   rttr.addFlashAttribute("msg", "SUCCESS");
	   
	   logger.info(userVO.toString());
	   
	   return "redirect:/user/loginForm";
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
            
            //JSON 형태 반환값 처리
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
			   service.create(vo);
			   
		      }
		   
		   System.out.println(vo);
		   session.setAttribute("login", vo);
		  
		   System.out.println(id+nickName);
		   
		   //model.addAttribute("userVO", vo);
		     
		   
		   return "redirect:/index";
		   
	  }
	   
		/*
		 * @RequestMapping(value = "/being/KaKaoLogin", method = RequestMethod.GET)
		 * public String kakaologin(@RequestParam("code") String code ,
		 * HttpServletRequest request, HttpServletResponse response, HttpSession
		 * session)throws Exception{
		 * 
		 * JsonNode jsonToken = getAccessToken(code); String access_token =
		 * jsonToken.get("access_token ").toString(); JsonNode userInfo =
		 * getKakaoUserInfo(access_token ); String id = userInfo.get("id").toString();
		 * String nickName = userInfo.get("properties").get("nickname").toString();
		 * session.setAttribute("userid", nickName); System.out.println(id+nickName);
		 * 
		 * UserVO userVO = new UserVO();
		 * 
		 * userVO.setEmail()
		 * 
		 * 
		 * UserVO userVO = service.kakaoLogin(userVO); if (userVO == null) { return "";
		 * // 카카오톡 회원가입 } else (userVO != null){
		 * 
		 * }
		 * 
		 * return "redirect:/index"; }
		 */
	   
}