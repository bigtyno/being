package article.service;

import java.util.Map;

import article.model.Writer;

public class WriteRequest {

	private Writer writer;
	private String type;
	private String acreage;	// 평수
	private String budget;	// 예산
	private String field;	// 분야
	private String space;	// 공간별
	private String title;
	private String content;
//	private Integer prodnum;	// 상품번호

	public WriteRequest(Writer writer, 
			String type, // 주거
			String acreage,
			String budget,
			String field,
			String space,
			String title, 
			String content
//			, //
//			Integer prodnum
			) {
		this.writer = writer;
		this.type = type;
		this.acreage = acreage;
		this.budget = budget;
		this.field = field;
		this.space = space;
		this.title = title;
		this.content = content;//
//		this.prodnum = prodnum;
	}

	public Writer getWriter() {
		return writer;
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

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}
	
//	public Integer getProdnum() {
//		return prodnum;
//	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
