package interior.service;

import java.util.Map;

public class ModifyInteriorRequest {

	private String userId;
	private Integer num;
	private String name;	
	private String introduce;	
	private String imageA;
	private String imageB;	

	public ModifyInteriorRequest(String userId, Integer num, String name, String introduce, String imageA,
			String imageB) {
		super();
		this.userId = userId;
		this.num = num;
		this.name = name;
		this.introduce = introduce;
		this.imageA = imageA;
		this.imageB = imageB;
	}

	public void validate(Map<String, Boolean> errors) {
		if (name == null || name.trim().isEmpty()) {
			errors.put("name", Boolean.TRUE);
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getImageA() {
		return imageA;
	}

	public void setImageA(String imageA) {
		this.imageA = imageA;
	}

	public String getImageB() {
		return imageB;
	}

	public void setImageB(String imageB) {
		this.imageB = imageB;
	}
	

}
