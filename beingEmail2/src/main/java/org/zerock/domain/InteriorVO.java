package org.zerock.domain;

public class InteriorVO {
	
	private Integer num;
	private String cname;
	private String introduce;
	private String imagea;
	private String imageb;
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public String getImagea() {
		return imagea;
	}
	public void setImagea(String imagea) {
		this.imagea = imagea;
	}
	
	public String getImageb() {
		return imageb;
	}
	public void setImageb(String imageb) {
		this.imageb = imageb;
	}
	@Override
	public String toString() {
		return "InteriorVO [num=" + num + ", cname=" + cname + ", introduce=" + introduce + ", imagea=" + imagea
				+ ", imageb=" + imageb + "]";
	}

}
