package interior.service;

import java.util.Map;

import article.model.Writer;

public class WriteInteriorRequest {
		
		private Writer writer;
		private Integer num;	
		private String name;
		private String introduce;	
		private String imageA;
		private String imageB;
			

		
		public WriteInteriorRequest(Writer writer, String name, String introduce, String imageA,
				String imageB) {
			super();
			this.writer = writer;
			this.name = name;
			this.introduce = introduce;
			this.imageA = imageA;
			this.imageB = imageB;
		}

		public WriteInteriorRequest( 
				Integer num,
				String name, 
				String introduce, 
				String imageA, 
				String imageB
				) {
			this.num = num;
			this.name = name;
			this.introduce = introduce;
			this.imageA = imageA;
			this.imageB= imageB;
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
			this.imageA= imageA;
		}
		public String getImageB() {
			return imageB;
		}
		public void setImageB(String imageB) {
			this.imageB= imageB;
		}
		
		
		public void validate(Map<String, Boolean> errors) {
			if (name == null || name.trim().isEmpty()) {
				errors.put("name", Boolean.TRUE);
			}
		}
		
}






