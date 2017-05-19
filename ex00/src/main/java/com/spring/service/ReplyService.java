package com.spring.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.common.Criteria;
import com.spring.dao.ReplyDAO;
import com.spring.vo.ReplyVO;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyDAO dao;
	
	  public void insert(ReplyVO vo) throws Exception {
		  dao.insert(vo);
	  }	
	
	  public List<ReplyVO> list(Integer bno) throws Exception {
	    return dao.list(bno);
	  }
	  
	  public void modify(ReplyVO vo) throws Exception {
		  dao.update(vo);
	  }
	  
	  public void remove(Integer rno) throws Exception {
	     dao.delete(rno);
	  }	  

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		return dao.listPage(bno, cri);
	}
	
	public int count(Integer bno) throws Exception {
		return dao.count(bno);
	}	  
	  
}
