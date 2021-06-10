package article.service;

import java.util.Map;

public class ModifyRequest {

	private String userId;
	private int articleNumber;
	private String title;
	private String content;
	private String type;
	private String acreage;
	private String budget;
	private String field;
	private String space;
	

	public ModifyRequest(String userId, 
			int articleNumber, 
			String title, 
			String content,
			String type,
			String acreage,
			String budget,
			String field,
			String space
			) {
		this.userId = userId;
		this.articleNumber = articleNumber;
		this.title = title;
		this.content = content;
		this.type = type;
		this.acreage = acreage;
		this.budget = budget;
		this.field = field;
		this.space = space;
	}

	public String getUserId() {
		return userId;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAcreage() {
		return acreage;
	}
	
	public String getBudget() {
		return budget;
	}
	
	public String getField() {
		return field;
	}
	
	public String getSpace() {
		return space;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}

}
