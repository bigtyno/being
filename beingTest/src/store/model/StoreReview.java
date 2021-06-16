package store.model;

import java.util.Date;

import article.model.Writer;

public class StoreReview {
	
	private Integer num;
	private Writer writer;
	private Integer prodNum;
	private Date regDate;
	private Integer grade;	
	private String content;
	
	public StoreReview(
			Integer num,
			Writer writer,
			Integer prodNum,	
			Date regDate,
			Integer grade,
			String content
			) {
		this.num = num;
		this.writer = writer;
		this.prodNum = prodNum;
		this.regDate = regDate;
		this.grade = grade;
		this.content = content;
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Writer getWriter() {
		return writer;
	}
	public Integer getProdNum() {
		return prodNum;
	}
	public void setProdNum(Integer prodNum) {
		this.prodNum = prodNum;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	}



