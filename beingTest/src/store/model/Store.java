package store.model;

//import article.model.Writer;

public class Store {
	
	private Integer num;
//	private Writer writer;
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
	private Integer avggrade;	
		
//	public Store(Integer num, 
//			Writer writer, 
//			String name, 
//			String thumbnail, 
//			String infoimage, 
//			String introduce, 
//			Integer price,
//			Integer dcprice, 
//			String brand, 
//			String keywd, 
//			String category, 
//			String freeyn, 
//			String link, 
//			Integer avggrade) {
//		this.num = num;
//		this.writer = writer;
//		this.name = name;
//		this.thumbnail = thumbnail;
//		this.infoimage = infoimage;
//		this.introduce = introduce;
//		this.price = price;
//		this.dcprice = dcprice;
//		this.brand = brand;
//		this.keywd = keywd;
//		this.category = category;
//		this.freeyn = freeyn;
//		this.link = link;
//		this.avggrade = avggrade;
//	}
	
	public Store(Integer num, 
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
			String link, 
			Integer avggrade) {
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
	
	public Store(Integer num, 
//			Writer writer,
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
			String link) {
		this.num = num;
//		this.writer = writer;
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
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
//	public Writer getWriter() {
//		return writer;
//	}
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
	public String getInfoimage() {
		return infoimage;
	}
	public void setInfoimage(String infoimage) {
		this.infoimage = infoimage;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getDcprice() {
		return dcprice;
	}
	public void setDcprice(Integer dcprice) {
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
	public Integer getAvggrade() {
		return avggrade;
	}
	public void setAvggrade(Integer avggrade) {
		this.avggrade = avggrade;
	}
	
	
	}



