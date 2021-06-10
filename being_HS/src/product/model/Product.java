package product.model;

public class Product {

	private int num;
	private String name;
	private String thumbnail;
	private String introduce;
	private int price;
	private int dcpice;
	private String brand;
	private String keywd;
	private String category;
	private String freeyn;
	private String link;
	private int avggrade;
	
	
	public Product(String name, String thumbnail, String introduce, int price, int dcpice, String brand, String keywd,
			String category, String freeyn, String link) {
		super();
		this.name = name;
		this.thumbnail = thumbnail;
		this.introduce = introduce;
		this.price = price;
		this.dcpice = dcpice;
		this.brand = brand;
		this.keywd = keywd;
		this.category = category;
		this.freeyn = freeyn;
		this.link = link;
	}	
	
	
	public Product(int num, String name, String thumbnail, String introduce, int price, int dcpice, String brand,
			String keywd, String category, String freeyn, String link, int avggrade) {
		super();
		this.num = num;
		this.name = name;
		this.thumbnail = thumbnail;
		this.introduce = introduce;
		this.price = price;
		this.dcpice = dcpice;
		this.brand = brand;
		this.keywd = keywd;
		this.category = category;
		this.freeyn = freeyn;
		this.link = link;
		this.avggrade = avggrade;
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
	public int getDcpice() {
		return dcpice;
	}
	public void setDcpice(int dcpice) {
		this.dcpice = dcpice;
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
	
}
