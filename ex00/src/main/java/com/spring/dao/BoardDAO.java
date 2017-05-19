package com.spring.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.common.Criteria;
import com.spring.common.SearchCriteria;
import com.spring.vo.Board;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.spring.BoardMapper";
	
	public void create(Board vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	public Board read(Integer idx) throws Exception {
		return session.selectOne(namespace + ".read", idx);
	}
	
	public void update(Board vo) throws Exception {
		session.update(namespace + ".update", vo);
	}
	
	public void delete(Integer idx) throws Exception {
		session.delete(namespace + ".delete", idx);
	}
	
	public List<Board> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}
	
	public List<Board> listPage(int page) throws Exception {
		return session.selectList(namespace + ".listPage", page);
	}
	
	public List<Board> listCriteria(Criteria cri)  throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}
	
	public int countPaging(Criteria cri) throws Exception {
		//return session.selectOne(namespace + ".countPaging", cri);
		return session.selectOne(namespace + ".countPaging");
	}
	
	
	public List<Board> listSearch(SearchCriteria cri) throws Exception{
		return session.selectList(namespace + ".listSearch", cri);
	}
	
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}
	
}



