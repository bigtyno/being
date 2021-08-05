package org.zerock.controller;


import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.zerock.service.UserService;

@Component
public class UserValidator implements Validator {
	
	
	
    @Autowired
    private UserService userService;
    
	@Autowired
	private JavaMailSender mailSender;
	

    private final String emailRegExp = 
    		"^([a-zA-Z0-9_\\.\\+/-]+)@([a-zA-Z0-9]+)\\.([a-zA-Z\\.]{2,})$";

    private final String passRegExp = 
    		"^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,}$"; // Ư������, ������, ���� ���� 8���� �̻�

    private Pattern pattern;
    private Pattern pattern2;

    

    public UserValidator() {

     // TODO Auto-generated constructor stub

     pattern = Pattern.compile(emailRegExp);
     pattern2 = Pattern.compile(passRegExp);

    }
   
  


    //실제로 메일로 메세지를 보내주는 메서드
    public void validateEmail(String email, String code, String url) {

    	try {
    		
    		MimeMessage message = mailSender.createMimeMessage();
    		MimeMessageHelper messageHelper 
    				= new MimeMessageHelper(message, true, "UTF-8");
    		
    		messageHelper.setFrom("bigtyno@naver.com");
    		messageHelper.setSubject("being 서비스 가입 알림");
    		messageHelper.setText("being 회원이 되어주신 것을 감사드립니다. \n\n"
    				+ "being는 고객님께 최선을 다하는 서비스가 되도록 노력하겠습니다.\n\n"
    				+ "계정 활성화를 위해 아래 링크를 클릭하여 이메일 인증을 받아주시기바랍니다.\n\n"
    				+ url + "?cd="
    				+ code);
    		messageHelper.setTo(email);
    			
    		mailSender.send(message);

    	} catch(Exception e) {
    		//errors.rejectValue("contractAgreement", "ValidEmail.userForm.username");
    		
    	}    	
    }



	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
	}
    
    
}
