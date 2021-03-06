package store.service;

import java.util.List;

import store.model.StoreReview;

public class StoreReviewPage {

	private int total;
	private int currentPage;
	private List<StoreReview> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	private double avgGrade;
	
	public StoreReviewPage(int total, int currentPage, int size, List<StoreReview> content, double avgGrade) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		this.avgGrade = avgGrade;
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

	public boolean hasNoStores() {
		return total == 0;
	}

	public boolean hasStores() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<StoreReview> getContent() {
		return content;
	}

	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}

	public double getAvgGrade() {
		return avgGrade;
	}

	public void setAvgGrade(double avgGrade) {
		this.avgGrade = avgGrade;
	}
}
