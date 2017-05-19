package com.spring.common;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10; // 페이지 번호수?? 페이지 offset
	private Criteria cri;

	
	
	public void setCri(Criteria cri) {  
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.calcData();
	}

	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum ) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		
	}
	
	
	public String makeQuery(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
		.queryParam("page", page)
		.queryParam("perPageNum", cri.getPerPageNum())
		.build();
		
		return uriComponents.toUriString();  // ?page=6&perPageNum=10     이런 형태로 만들어준다.
		
	}
	
	public String makeSearchQuery(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
		.queryParam("page", page)
		.queryParam("perPageNum", cri.getPerPageNum())
		.queryParam("searchType", ((SearchCriteria) cri).getSearchType())  // 상위클래스를 하위클래스로 처리하기 위해 캐스트가 필요하다.
		.queryParam("keyword", ((SearchCriteria) cri).getKeyword())
		.build();
		
		return uriComponents.toUriString();  // ?page=6&perPageNum=10&searchType=t&keyword=한글     이런 형태로 만들어준다.
		
	}
		
	

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

}
