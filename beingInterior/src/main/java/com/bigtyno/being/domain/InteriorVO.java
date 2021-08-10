package org.zerock.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class InteriorVO {
	
	private Integer num;
	private String cname;
	private String introduce;
	
	
//대표 사진
	private String fileName;
	private String fileRealName;
	
	
	private List<MultipartFile> uploadfile;
	
//		public BoardVO() {
//			super();
//			this.writerVO = new WriterVO();
//		}
	
	 public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileRealName() {
		return fileRealName;
	}

	public void setFileRealName(String fileRealName) {
		this.fileRealName = fileRealName;
	}

	public List<MultipartFile> getUploadfile() {
	        return uploadfile;
	    }

	 public void setUploadfile(List<MultipartFile> uploadfile) {
	        this.uploadfile = uploadfile;
	    }
	
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

	@Override
	public String toString() {
		return "InteriorVO [num=" + num + ", cname=" + cname + ", introduce=" + introduce + ", fileName=" + fileName
				+ ", fileRealName=" + fileRealName + ", uploadfile=" + uploadfile + "]";
	}
	
	
	

}
