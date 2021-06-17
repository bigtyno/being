package interiorAsk;

import java.util.List;

import interiorAsk.InteriorAsk;

public class InteriorAskPage {

	private int total;
	private int currentPage;
	private List<InteriorAsk> content;
	private int totalPages;
	private int startPage;
	private int endPage;

	public InteriorAskPage(int total, int currentPage, int size, List<InteriorAsk> content) {
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

	public boolean hasNoInteriorAsk() {
		return total == 0;
	}

	public boolean hasInteriorAsk() {
		return total > 0;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<InteriorAsk> getContent() {
		return content;
	}

	public int getStartPage() {
		return startPage;
	}
	
	public int getEndPage() {
		return endPage;
	}
}
