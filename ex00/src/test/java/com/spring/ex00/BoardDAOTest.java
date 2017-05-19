package com.spring.ex00;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.common.Criteria;
import com.spring.dao.BoardDAO;
import com.spring.vo.Board;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

  @Autowired
  private BoardDAO dao;

  private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

  @Test
  public void testCreate() throws Exception {

    Board board = new Board();
    board.setTitle("새로운 글을 넣습니다. ");
    board.setContent("새로운 글을 넣습니다. ");
    board.setWriter("user00");
    dao.create(board);
  }

  @Test
  public void testRead() throws Exception {
    logger.info(dao.read(24).toString());
  }

  @Test
  public void testUpdate() throws Exception {

    Board board = new Board();
    board.setIdx(1);
    board.setTitle("수정된 글입니다.");
    board.setContent("수정 테스트 ");
    dao.update(board);
  }

  @Test
  public void testDelete() throws Exception {
    dao.delete(1);
  }

  @Test
  public void testListAll() throws Exception {
    logger.info(dao.listAll().toString());
  }
  
  @Test
  public void testListPage() throws Exception {

    int page = 24;

    List<Board> list = dao.listPage(page);

    for (Board vo : list) {
      logger.info(vo.getIdx() + ":" + vo.getTitle());
    }
  }  
  
  
  @Test
  public void testListCriteria() throws Exception {

	  Criteria cri = new Criteria();
	  cri.setPage(2);
	  cri.setPerPageNum(20);
	  
	  List<Board> list = dao.listCriteria(cri);
	  
	  for (Board board : list) {
		  logger.info(board.getIdx() + "   :  " + board.getTitle());
	  }
	  
  }   
  
  
  
  
  
  
  
  

}
