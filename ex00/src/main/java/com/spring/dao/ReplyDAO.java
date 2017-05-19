package com.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.common.Criteria;
import com.spring.vo.ReplyVO;

@Repository
public class ReplyDAO {

	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.spring.ReplyMapper";
	
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace + ".list", bno);
	}
	
	public void insert(ReplyVO vo) throws Exception {
		session.insert(namespace + ".create", vo);
	}
	
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}
	
	public void delete(Integer rno) throws Exception {
		session.delete(namespace + ".delete", rno);
	}
	
	
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {

		//★★★★ 파라미터 2개를 mybatis로 넘길때.. 참고하자
		Map<String, Object> paramMap = new HashMap<>(); // (업캐스팅)( 자동 형변환 ) 자식 클래스 -> 부모 클래스 형변환  // 참조값이 부모자료형으로 변환되어 할당된다.
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listPage", paramMap);
	}
	
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace + ".count", bno);
	}
	
}



