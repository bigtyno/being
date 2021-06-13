package model;

import java.util.Map;

import article.model.Writer;

public class Product {


	public void setUserId(String userId) {
		this.userId = userId;
	}
	private String userId;
	private Writer writer;
	private int num;
	private String name;
	private String thumbnail;
	private String infoimage;
	private String introduce;
	private int price;
	private int dcprice;
	private String brand;
	private String keywd;
	private String category;
	private String freeyn;
	private String link;
	private int avggrade;
	
@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", thumbnail=" + thumbnail + ", infoimage=" + infoimage
				+ ", introduce=" + introduce + ", price=" + price + ", dcprice=" + dcprice + ", brand=" + brand
				+ ", keywd=" + keywd + ", category=" + category + ", freeyn=" + freeyn + ", link=" + link
				+ ", avggrade=" + avggrade + "]";
	}
//modify 입력용

public Product(String userId, int num, String name, String thumbnail, String infoimage, String introduce, int price,
		int dcprice, String brand, String keywd, String category, String freeyn, String link) {
	super();
	this.userId = userId;
	this.num = num;
	this.name = name;
	this.thumbnail = thumbnail;
	this.infoimage = infoimage;
	this.introduce = introduce;
	this.price = price;
	this.dcprice = dcprice;
	this.brand = brand;
	this.keywd = keywd;
	this.category = category;
	this.freeyn = freeyn;
	this.link = link;
}





public Product(int num, String name, String thumbnail, String infoimage, String introduce, int price, int dcprice,
			String brand, String keywd, String category, String freeyn, String link, int avggrade) {
		super();
		this.num = num;
		this.name = name;
		this.thumbnail = thumbnail;
		this.infoimage = infoimage;
		this.introduce = introduce;
		this.price = price;
		this.dcprice = dcprice;
		this.brand = brand;
		this.keywd = keywd;
		this.category = category;
		this.freeyn = freeyn;
		this.link = link;
		this.avggrade = avggrade;
	}

//write 입력용 
public Product(Writer writer,String name, String thumbnail, String infoimage, String introduce, int price, int dcprice, String brand,
		String keywd, String category, String freeyn, String link) {
	super();
	
	this.writer = writer;
	this.name = name;
	this.thumbnail = thumbnail;
	this.infoimage = infoimage;
	this.introduce = introduce;
	this.price = price;
	this.dcprice = dcprice;
	this.brand = brand;
	this.keywd = keywd;
	this.category = category;
	this.freeyn = freeyn;
	this.link = link;
}

//list 출력용
	public Product(String name, String thumbnail, int dcprice, String brand, String category, String freeyn,
			int avggrade) {
		super();
		this.name = name;
		this.thumbnail = thumbnail;
		this.dcprice = dcprice;
		this.brand = brand;
		this.category = category;
		this.freeyn = freeyn;
		this.avggrade = avggrade;
	}
//list 출력용
	public Product(String name, String thumbnail, int dcprice, String brand, String freeyn, int avggrade) {
		super();
		this.name = name;
		this.thumbnail = thumbnail;
		this.dcprice = dcprice;
		this.brand = brand;
		this.freeyn = freeyn;
		this.avggrade = avggrade;
	}

	
	public String getUserId() {
		return userId;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getdcprice() {
		return dcprice;
	}
	public void setdcprice(int dcprice) {
		this.dcprice = dcprice;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getKeywd() {
		return keywd;
	}
	public void setKeywd(String keywd) {
		this.keywd = keywd;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFreeyn() {
		return freeyn;
	}
	public void setFreeyn(String freeyn) {
		this.freeyn = freeyn;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getAvggrade() {
		return avggrade;
	}
	public void setAvggrade(int avggrade) {
		this.avggrade = avggrade;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public String getInfoimage() {
		return infoimage;
	}

	public void setInfoimage(String infoimage) {
		this.infoimage = infoimage;
	}

	public int getDcprice() {
		return dcprice;
	}

	public void setDcprice(int dcprice) {
		this.dcprice = dcprice;
	}
	public void validate(Map<String, Boolean> errors) {
		if (name == null || name.trim().isEmpty()) {
			errors.put("name", Boolean.TRUE);
		}
		
	}
}
