package app.web.models;

import java.util.HashSet;

public class PageModel {

	private HashSet<SavedEmployeeModel> data;
	
	private boolean firstPage;
	
	private boolean lastPage;

	public HashSet<SavedEmployeeModel> getData() {
		return data;
	}

	public void setData(HashSet<SavedEmployeeModel> data) {
		this.data = data;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	
}
