package interior.model;

import article.model.Writer;

public class Interior {
	

	private Writer writer;
	private Integer num;
	private String name;
	private String introduce;
	private String imageA;
	private String imageB;
	
	
	//list 출력용
	public Interior(Integer num, String name,String introduce, String imageA, String imageB) {
		super();
		this.num = num;
		this.name = name;
		this.introduce = introduce;
		this.imageA = imageA;
		this.imageB = imageB;
	}
	
 
     public Interior(Integer num, Writer writer, String name, String introduce, String imageA, String imageB) {
        super();
        this.num = num;
        this.writer=writer;
        this.name = name;
        this.introduce=introduce;
        this.imageA = imageA;
        this.imageB = imageB;
     }
	

	public void setWriter(Writer writer) {
		this.writer = writer;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}


	public void setImageA(String imageA) {
		this.imageA = imageA;
	}


	public void setImageB(String imageB) {
		this.imageB = imageB;
	}


	public Writer getWriter() {
		return writer;
	}


	public Integer getNum() {
		return num;
	}


	public String getName() {
		return name;
	}


	public String getIntroduce() {
		return introduce;
	}


	public String getImageA() {
		return imageA;
	}


	public String getImageB() {
		return imageB;
	}


	@Override
	public String toString() {
		return "Interior [writer=" + writer + ", num=" + num + ", name=" + name + ", introduce=" + introduce
				+ ", imageA=" + imageA + ", imageB=" + imageB + "]";
	}
	
}
