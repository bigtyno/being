package store.service;

import java.util.Map;

//import article.model.Writer;

public class ModifyStoreRequest {

	private String userId;
	private int num;

	private String name;	
	private String thumbnail;	
	private String infoimage;
	private String introduce;	
	private Integer price;
	private Integer dcprice; 
	private String brand;	
	private String keywd;
	private String category; 
	private String freeyn  ;
	private String link  ;
		
	public ModifyStoreRequest(String userId, 
			int num,
			String name, 
			String thumbnail, 
			String infoimage, 
			String introduce, 
			Integer price,
			Integer dcprice, 
			String brand, 
			String keywd, 
			String category, 
			String freeyn, 
			String link
			) {
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
	
	public String getUserId() {
		return userId;
	}
	
	public Integer getNum() {
		return num;
	}

	public String getName() {
		return name;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String getInfoimage() {
		return infoimage;
	}
	
	public String getIntroduce() {
		return introduce;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getDcprice() {
		return dcprice;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getKeywd() {
		return keywd;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getFreeyn() {
		return freeyn;
	}
	
	public String getLink() {
		return link;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if (name == null || name.trim().isEmpty()) {
			errors.put("name", Boolean.TRUE);
		}
	}
	

}
