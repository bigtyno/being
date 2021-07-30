package org.zerock.domain;

public class FileVO {
	
	private Integer filenum;
	private Integer bnum;
	private String	filename;
	private String	fileOriName;
	private String  fileurl;
	
	public Integer getFilenum() {
		return filenum;
	}
	public void setFilenum(Integer filenum) {
		this.filenum = filenum;
	}
	public Integer getBnum() {
		return bnum;
	}
	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileOriName() {
		return fileOriName;
	}
	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
}
