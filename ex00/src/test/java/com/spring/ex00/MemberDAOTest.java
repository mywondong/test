package com.spring.ex00;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.spring.vo.*;
import com.spring.dao.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testTime()throws Exception{
		System.out.println(dao.getTime());
	}
	
	@Test
	public void testInsertMember()throws Exception{
		
		Member vo = new Member();
		vo.setUserid("user03");
		vo.setUserpw("user03");
		vo.setUsername("USER03");
		vo.setEmail("user00@aaa.com");
		
		dao.insertMember(vo);
	}	
}


