package com.spring.common;

public class SearchCriteria extends Criteria{

	private String searchType;
	private String keyword;
	
	public SearchCriteria() {
		// 개인적인 생각 이걸 매번 생성하는것보다 static으로 만들고 메모리에 유지하는건 어떨지???
		System.out.println("SearchCriteria 객체생성");
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}


