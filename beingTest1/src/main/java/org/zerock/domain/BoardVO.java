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
	private String contentOf;
	private Date registday;
	private Integer readcount;
	
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
		return contentOf;
	}
	public void setContentOf(String contentOf) {
		this.contentOf = contentOf;
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
	public Date getRegistday() {
		return registday;
	}
	public void setRegistday(Date registday) {
		this.registday = registday;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "BoardVO [writerVO=" + writerVO + ", num=" + num + ", type=" + type + ", acreage=" + acreage
				+ ", budget=" + budget + ", field=" + field + ", space=" + space + ", title=" + title + ", contentOf="
				+ contentOf + ", registday=" + registday + ", readcount=" + readcount + "]";
	}

	

}