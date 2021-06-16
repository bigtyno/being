package faq.model;

import article.model.Writer;

public class Faq {
	

	private Writer writer;
	private Integer num;
	private String category;
	private String question;
	private String answer;
	
	public Faq(Writer writer, Integer num, String category, String question, String answer) {
		super();
		this.writer = writer;
		this.num = num;
		this.category = category;
		this.question = question;
		this.answer = answer;
	}

	public Faq(Integer num, String category, String question, String answer) {
		super();
		this.num = num;
		this.category = category;
		this.question = question;
		this.answer = answer;
	}


	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
}
