package model;

import java.util.List;

//import article.model.Article;
//import model.Product;

public class ProductPage {
	
	private int total;
	private int currentPage;
	private List<Product> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public ProductPage(int total, int currentPage, int size, List<Product> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage / 5 * 5 + 1;
			if (modVal == 0) startPage -= 5;
			
			endPage = startPage + 4;
			if (endPage > totalPages) endPage = totalPages;
		}
	}

	public int getTotal() {
		return total;
	}



	public int getCurrentPage() {
		return currentPage;
	}



	public List<Product> getContent() {
		return content;
	}

	

	public int getTotalPages() {
		return totalPages;
	}


	public int getStartPage() {
		return startPage;
	}



	public int getEndPage() {
		return endPage;
	}


	public boolean hasNoProducts() {
		return total == 0;
	}

	public boolean hasProducts() {
		return total > 0;
	}
	
}
