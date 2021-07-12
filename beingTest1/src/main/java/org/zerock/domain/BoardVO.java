package org.zerock.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

public class BoardVO {
	
	private WriterVO writerVO;
	private Integer num;

	private String type;
	private String acreage;
	private String budget;
	private String field;
	private String space;
	private String title;
	private String contentof;
	private Date registday;
	
	public BoardVO() {
		super();
		this.writerVO = new WriterVO();
	}
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return writerVO.getName();
	}
	public void setName(String name) {
		this.writerVO.setName(name);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContentOf() {
		return contentof;
	}
	public void setContentOf(String contentof) {
		this.contentof = contentof;
	}
	public String getEmail() {
		return this.writerVO.getId();
	}
	public void setEmail(String email) {
		this.writerVO.setId(email);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAcreage() {
		return acreage;
	}
	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getContentof() {
		return contentof;
	}
	public void setContentof(String contentof) {
		this.contentof = contentof;
	}
	public Date getRegistday() {
		return registday;
	}
	public void setRegistday(Date registday) {
		this.registday = registday;
	}

	@Override
	public String toString() {
		return "BoardVO [writerVO=" + writerVO + ", num=" + num + ", type=" + type + ", acreage=" + acreage
				+ ", budget=" + budget + ", field=" + field + ", space=" + space + ", title=" + title + ", contentof="
				+ contentof + ", registday=" + registday + "]";
	}
	

}