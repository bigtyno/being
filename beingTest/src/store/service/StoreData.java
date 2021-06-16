package store.service;

//import article.model.ArticleContent;
import store.model.Store;

public class StoreData {

	private Store store;
//	private ArticleContent content;
//	private StoreReview storeReview;

//	public ArticleData(Article article, ArticleContent content) {
		public StoreData(Store store) {
		this.store = store;
//		this.content = content;
	}

	public Store getStore() {
		return store;
	}

//	public String getContent() {
//		return content.getContent();
//	}

}
