package store.service;

import java.util.Map;

import article.model.Writer;

public class StoreReviewWriteRequest {
		
		private Writer writer;
		private Integer prodNum;
		private Integer grade;
		private String content;
			
		public StoreReviewWriteRequest(Writer writer, 
				Integer prodNum,
				Integer grade,
				String content
				) {
			this.writer = writer;
			this.prodNum = prodNum;
			this.grade = grade;
			this.content = content;
		}
		
		public StoreReviewWriteRequest(Integer prodNum) {
			this.prodNum = prodNum;
		}
		
		public Writer getWriter() {
			return writer;
		}
		
		public Integer getProdNum() {
			return prodNum;
		}
		public Integer getGrade() {
			return grade;
		}
		public String getContent() {
			return content;
		}
		
		public void validate(Map<String, Boolean> errors) {
			if (content == null || content.trim().isEmpty()) {
				errors.put("content", Boolean.TRUE);
			}
		}
		
}






