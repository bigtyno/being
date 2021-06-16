package interior.service;

import java.util.Map;

public class DeleteRequest {

	//private String userId;
	private int interiorNum;

	public DeleteRequest(int interiorNum) {
		//this.userId = userId;
		this.interiorNum = interiorNum;
	}

	/*
	 * public String getUserId() { return userId; }
	 */
	public int getInteriorNumber() {
		return interiorNum;
	}
	
	/*
	 * public void validate(Map<String, Boolean> errors) { if (userId == null ||
	 * userId.trim().isEmpty()) { errors.put("userId", Boolean.TRUE); } }
	 */

}


