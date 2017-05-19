package com.spring.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.vo.Member;

@Repository //spring에서 dao로 인식
public class MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.spring.MemberMapper";
	
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}
	
	public void insertMember(Member vo) {
		sqlSession.insert(namespace+".insertMember", vo);
	}
	
	public Member selectMember(String userid) throws Exception {
		return (Member) sqlSession.selectOne(namespace+".selectMember", userid);
	}

	public Member selectMemberIdPW(String userid, String userpw) throws Exception {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return (Member) sqlSession.selectOne(namespace+".selectMemberIdPW", paramMap);
	}
	
}
