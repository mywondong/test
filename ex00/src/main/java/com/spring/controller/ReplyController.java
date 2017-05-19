package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.common.Criteria;
import com.spring.common.PageMaker;
import com.spring.service.ReplyService;
import com.spring.vo.ReplyVO;

@RestController      // @RestController는  ajax @ResponseBody가 없어도 동일하게 동작한다. (생략되었다고 봐도 무방하다. )
@RequestMapping("/replies")
public class ReplyController {

	  @Inject
	  private ReplyService service;

	  // 입력
	  @RequestMapping(value = "insert", method = RequestMethod.POST)
	  public ResponseEntity<String> insert(@RequestBody ReplyVO vo) {  // @RequestBody 전송된 json데이터를 자바의 vo 객체로 변환해주는 어노테이션 @ModelAttribute와 유사한 역활을 하지만 json에서 사용된다.

	    ResponseEntity<String> entity = null;
	    try {
	    	
	      service.insert(vo);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);  // ResponseEntity 호출한 쪽으로 에러의 원인메세지를 전송할때.. 사용
	    }
	    return entity;
	  }
	  
	  
	  // 리스트
	  @RequestMapping(value = "/list/{bno}", method = RequestMethod.GET)
	  public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {

	    ResponseEntity<List<ReplyVO>> entity = null;
	    try {
	      entity = new ResponseEntity<>(service.list(bno), HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }

	    return entity;
	  }	  
	  
	  
	  // 수정
	  @RequestMapping(value = "/modify/{rno}", method = { RequestMethod.PUT, RequestMethod.PATCH })
	  public ResponseEntity<String> update(@PathVariable("rno") Integer rno, @RequestBody ReplyVO vo) {

	    ResponseEntity<String> entity = null;
	    try {
	      vo.setRno(rno);
	      service.modify(vo);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }
	  
	  
	  // 삭제
	  @RequestMapping(value = "/remove/{rno}", method = RequestMethod.DELETE)
	  public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {

	    ResponseEntity<String> entity = null;
	    try {
	      service.remove(rno);
	      entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }	  
	  
	  
	  
	  // 리스트를 10개씩 끊어서 가져오고, 페이징처리
	  @RequestMapping(value= "/{bno}/{page}", method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) {
		  
		  ResponseEntity<Map<String, Object>> entity = null;
		  
		  try {
			  Criteria cri = new Criteria();
			  cri.setPage(page);
			  
			  PageMaker pageMaker = new PageMaker();
			  pageMaker.setCri(cri);

			  // 질문1... 왜 Map, List를 사용했지??
/*			  ArrayList의 경우 단순히 데이터를 입력하고 데이터를 출력하는 용도로(중복키값허용, 순서대로)
			  HashMap의 경우 데이터를 캐쉬해서 특정 key값으로 HashMap에 있는 데이터를 검색해서 사용하는 용도로 많이 쓰인다..(중복키값불허, 순서없다)
			  index정보를 알고 있다면 ArrayList가 HashMap보다 빠름
			  
			  
// 질문2... 부모자료형으로 자식자료형을 담았는데.. 그냥 자기자료형으로 담으면 안됐나?? 이유는?? (인스턴스의 타입과 일치하는 참조변수를 사용하면 인스턴스의 멤버들을 모두 사용할 수 있을 텐데 왜 조상타입의 참조변수를 사용해서 인스턴스의 일부 멤버만을 사용하도록 할까?)
인터페이스의 메소드를 호출하게 되면 실제는 생성된 객체의 메소드가 호출된다.
상속관계에서도 부모에 메서드가 있어도.. 자식에서 재정이된 메서드가 호출되듯이.. ( 부모에 cry메서드가 있어도... 자식에서 오버라이딩 cry를 했다면.. 부모객체 변수로 호출했을때 자식(들) 재정이된 메서드가 호출된다. )
이렇게 하면 좋은 점이 무엇이냐 하면 구상 클래스에 의존하지 않은 인터페이스에 의존하는 프로그램을 작성할 수 있다는 것이다. 
인터페이스에 의존한다는 것은 쉽게 말해서 고정된 틀에 얽매이지 않아도 되는 것이다. 물론 인터페이스의 틀에는 얽매이겠지만 적어도 공통된 틀이기 때문에 어느 클래스에도 접근할 수 있다는 것이다.			  

			  */
			  
			  Map<String, Object> map = new HashMap<String, Object>();
			  List<ReplyVO> list = service.listPage(bno, cri);
			  map.put("list", list);
			  
			  
			  int replyCount = service.count(bno);
			  pageMaker.setTotalCount(replyCount);
			  map.put("pageMaker", pageMaker);
			  
			  entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			  
		} catch (Exception e) {
		      e.printStackTrace();
		      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);			
		}
		  
		  return entity;
		  
	  }
	  
	  
}









