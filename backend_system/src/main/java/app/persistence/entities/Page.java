package app.persistence.entities;

import java.util.HashSet;

public class Page {
	
	private HashSet<Employee> data;
	
	private boolean firstPage;
	
	private boolean lastPage;

	public HashSet<Employee> getData() {
		return data;
	}

	public void setData(HashSet<Employee> data) {
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
