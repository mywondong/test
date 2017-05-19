package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.common.Criteria;
import com.spring.common.SearchCriteria;
import com.spring.dao.BoardDAO;
import com.spring.vo.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	  public void regist(Board board) throws Exception {
		  dao.create(board);
	  }	
	
	  public List<Board> listAll() throws Exception {
	    return dao.listAll();
	  }
	  
	  public Board read(Integer idx) throws Exception {
	    return dao.read(idx);
	  }	  
	  
	  public void remove(Integer idx) throws Exception {
	     dao.delete(idx);
	  }	  

	  public void modify(Board board) throws Exception {
	    dao.update(board);
	  }
	  
	  public List<Board> listCriteria(Criteria cri) throws Exception {
		  return dao.listCriteria(cri);
	  }
	  
	  public int listCountCriteria(Criteria cri) throws Exception {
		  return dao.countPaging(cri);
	  }
	  
	  
	  
	  // 검색페이징
		public List<Board> listSearchCriteria(SearchCriteria cri) throws Exception{
			return dao.listSearch(cri);
		}
		
		public int listSearchCount(SearchCriteria cri) throws Exception {
			return dao.listSearchCount(cri);
		}	  
	  
}
