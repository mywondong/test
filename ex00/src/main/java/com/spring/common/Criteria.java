package com.spring.common;

public class Criteria {

  private int page; // 페이지번호
  private int perPageNum;  // 페이지당 보여질개수

  public Criteria() {
    this.page = 1;
    this.perPageNum = 10;
  }


public void setPage(int page) {       //srping에서 자동으로 값 맵핑된다..
    if (page <= 0) {
      this.page = 1;
      return;  // 여기서 return 의미는 함수종료
    }
    this.page = page;
  }

  public void setPerPageNum(int perPageNum) {     //srping에서 자동으로 값 맵핑된다..

    if (perPageNum <= 0 || perPageNum > 100) {
      this.perPageNum = 10;
      return;
    }
    this.perPageNum = perPageNum;
  }

  public int getPage() {
    return page;
  }
  
  // method for MyBatis SQL Mapper /* mybatis는 내부적으로 gettter를 호출하기에 사용하려고...*/
  public int getPerPageNum() {
    return this.perPageNum;
  }
  
  // method for MyBatis SQL Mapper -  
  public int getPageStart() {
	  return (this.page - 1) * perPageNum; // 페이징 공식 : 시작번호 = (페이지번호-1) * 페이지당 보여질개수
  }

}
