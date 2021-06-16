package store.service;

import java.util.Map;

public class DeleteReviewRequest {

	//private String userId;
	private int storeNum;

	public DeleteReviewRequest(int storeNum) {
		//this.userId = userId;
		this.storeNum = storeNum;
	}

	/*
	 * public String getUserId() { return userId; }
	 */
	public int getStoreNumber() {
		return storeNum;
	}
	
	/*
	 * public void validate(Map<String, Boolean> errors) { if (userId == null ||
	 * userId.trim().isEmpty()) { errors.put("userId", Boolean.TRUE); } }
	 */

}


