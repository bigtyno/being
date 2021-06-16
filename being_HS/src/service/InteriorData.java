package interior.service;

import interior.model.Interior;
//import article.model.ArticleContent;
import store.model.Store;

public class InteriorData {

	private Interior interior;
//	private ArticleContent content;

//	public ArticleData(Article article, ArticleContent content) {
		public InteriorData(Interior interior) {
		this.interior = interior;
//		this.content = content;
	}

	public Interior getInterior() {
		return interior;
	}

//	public String getContent() {
//		return content.getContent();
//	}

}
