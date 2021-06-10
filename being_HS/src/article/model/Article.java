package article.model;

import java.util.Date;

public class Article {

	private Integer number;
	private Writer writer;
	private String type;	// 주거형태
	private String acreage;	// 평수
	private String budget;	// 예산
	private String field;	// 분야
	private String space;	// 공간별
	private String title;
	private String content; //
//	private Integer prodnum;	// 상품번호 
	private Date regDate;
//	private Date modifiedDate;
	private int readCount;

	public Article(Integer number, 
			Writer writer, 
			String type, // 주거
			String acreage,
			String budget,
			String field,
			String space,
			String title, 
			String content, //
//			Integer prodnum,
			Date regDate, 
//			Date modifiedDate, 
			int readCount) {
		
		this.number = number;
		this.writer = writer;
		this.type = type;
		this.acreage = acreage;
		this.budget = budget;
		this.field = field;
		this.space = space;
		this.title = title;
		this.content = content;//
//		this.prodnum = prodnum;
		this.regDate = regDate;
//		this.modifiedDate = modifiedDate;
		this.readCount = readCount;
	}

	public Integer getNumber() {
		return number;
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

	public Date getRegDate() {
		return regDate;
	}

//	public Date getModifiedDate() {
//		return modifiedDate;
//	}

	public int getReadCount() {
		return readCount;
	}

}
