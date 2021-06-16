package store.service;

import java.util.Map;

public class ModifyStoreReviewRequest {

	private String userId;
	private int num;
//	private Integer prodNum;
	private Integer grade;
	private String content;
	
	public ModifyStoreReviewRequest(
			int num
			) {
		this.num = num;
	}
	

	public ModifyStoreReviewRequest(
			String userId, 
			int num, 
//			int prodNum,
			int grade, 
			String content
			) {
		this.userId = userId;
		this.num = num;
//		this.prodNum = prodNum;
		this.grade = grade;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public Integer getNum() {
		return num;
	}
	
//	public Integer getProdNum() {
//		return prodNum;
//	}

	public Integer getGrade() {
		return grade;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (content == null || content.trim().isEmpty()) {
			errors.put("content", Boolean.TRUE);
		}
	}

}
