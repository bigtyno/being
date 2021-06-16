package faq.service;

public class DeleteRequest {

	//private String userId;
	private int faqNum;

	public DeleteRequest(int faqNum) {
		//this.userId = userId;
		this.faqNum = faqNum;
	}

	/*
	 * public String getUserId() { return userId; }
	 */
	public int getFaqNumber() {
		return faqNum;
	}
	
	/*
	 * public void validate(Map<String, Boolean> errors) { if (userId == null ||
	 * userId.trim().isEmpty()) { errors.put("userId", Boolean.TRUE); } }
	 */

}


