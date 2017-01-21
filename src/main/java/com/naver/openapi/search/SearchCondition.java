package com.naver.openapi.search;

public class SearchCondition {
	
	public enum Category{
		KIN,BLOG,CAFE,DOC,WEBKR,BOOK, SHOP, ENCYC, 
		KRDIC, JPDIC, ENDIC, NEWS, LOCAL, VIDEO,IMAGE;		
		public String toString(){
			return super.toString().toLowerCase();
		}
	}

	private Category target;
	private String sort;
	private int start;
	private int display;
	private String query;
	
	public Category getTarget() {
		return target;
	}
	public void setTarget(Category target) {
		this.target = target;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getDisplay() {
		return display;
	}
	public void setDisplay(int display) {
		this.display = display;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
