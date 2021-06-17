package interiorAsk;

import article.model.Writer;

public class InteriorAsk {
	

	private Integer num;	
	private Writer writer;
	private String title;
	private String name;
	private String area;	
	private String fieldof;
	private String address;
	private String dateStart;
	private String dateDone;
	private String budget;
	private String message;	
	private String tel;
	
	
	//list 출력용
	public InteriorAsk(
			Writer writer, 
			String title, 
			String name,
			String area, 
			String fieldof,
			String address, 
			String dateStart, 
			String dateDone, 
			String budget, 
			String message, 
			String tel) {
		super();
		this.writer = writer;
		this.title = title;
		this.name = name;
		this.area = area;
		this.fieldof = fieldof;
		this.address = address;
		this.dateStart = dateStart;
		this.dateDone = dateDone;
		this.budget = budget;
		this.message = message;
		this.tel = tel;
	}
	
	public InteriorAsk(
			Integer num,
			Writer writer, 
			String title, 
			String name,
			String area, 
			String fieldof,
			String address, 
			String dateStart, 
			String dateDone, 
			String budget, 
			String message, 
			String tel) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.name = name;
		this.area = area;
		this.fieldof = fieldof;
		this.address = address;
		this.dateStart = dateStart;
		this.dateDone = dateDone;
		this.budget = budget;
		this.message = message;
		this.tel = tel;
	}
	

	public InteriorAsk(Integer num, String title, String name, String area, String fieldof, String address,
			String dateStart, String dateDone, String budget, String message, String tel) {
		super();
		this.num = num;
		this.title = title;
		this.name = name;
		this.area = area;
		this.fieldof = fieldof;
		this.address = address;
		this.dateStart = dateStart;
		this.dateDone = dateDone;
		this.budget = budget;
		this.message = message;
		this.tel = tel;
	}

	public Writer getWriter() {
		return writer;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArea() {
		return area;
	}
	
	public String getFieldOf() {
		return fieldof;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getDateStart() {
		return dateStart;
	}
	
	public String getDateDone() {
		return dateDone;
	}
	
	public String getBudget() {
		return budget;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTel() {
		return tel;
	}


	
}
