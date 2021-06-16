package faq.service;

import java.util.Map;

public class ModifyFaqRequest {

	private String userId;
	private int num;
	private String category; 
	private String question;
	private String answer;
	
	public ModifyFaqRequest(String userId, Integer num, String category, String question, String answer) {
		this.userId = userId;
		this.num = num;
		this.category = category;
		this.question = question;
		this.answer = answer;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public Integer getNum() {
		return num;
	}
	public String getCategory() {
		return category;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}


	public void validate(Map<String, Boolean> errors) {
		if (category == null || category.trim().isEmpty()) {
			errors.put("category", Boolean.TRUE);
		}
	}
	

}
