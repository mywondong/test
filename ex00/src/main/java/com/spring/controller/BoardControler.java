package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.common.PageMaker;
import com.spring.common.SearchCriteria;
import com.spring.service.BoardService;
import com.spring.test.HomeController;
import com.spring.vo.Board;

@Controller
@RequestMapping("/board/*")
public class BoardControler {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private BoardService service;
	
	/*
	return으로 뷰이름을 지정 않했는데???? 기본 value페이지로 되네
	뷰네임을 지정하지 않으면 value="/register" value의 register가 return 뷰네임이 된다.
	RequestToViewNameResolver 전략을 통해 자동생성되는 뷰 이름이 사용된다.
	URL과 뷰 이름을 일관되게 통일할 수 있다면 void형의 사용을 적극 고려해볼 만하다.
	*/
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() throws Exception{  
		logger.info("registerGET 호출됨");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	/*Model이 과 RedirectAttributes를 같이 쓰니 RedirectAttributes로 데이터 전송이안되네?? 일단 Model을 제거..*/
	public String registerPOST(Board board, RedirectAttributes attr) throws Exception {
		logger.info("registerPOST 호출됨");
		
		service.regist(board); 
		//model.addAttribute("result", "success");
		
		/*★★★ success 새로고침 문제로 페이지 이동시킨다.*/
		/*그런데 리다이렉트 시키니 주소창에 ?result=success 문자열이 남아있다.. 이걸 없애자*/
		/*addAttribute대신 리다이렉트 시점에 한번만 사용되는 데이터 전송 속성을 사용 RedirectAttributes*/
		/*RedirectAttributes 사용자에게 보여주 싶지 않은 데이타는 노출하지 않고 넘겨 줄 수 있다. 크롬에서 안보인다.*/
		/*★★★ model 대신 attr 사용*/
		attr.addFlashAttribute("msg", "성공했떠염");
		
		return "redirect:/board/listCri"; // GET
		//return "board/success";
	}	
	
/*	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("listAll 호출됨");
		model.addAttribute("list", service.listAll()); 
	}*/
	
	
	// 페이징처리하기 위해.... 추가
	 @RequestMapping(value = "/listCri", method = RequestMethod.GET)
	 public void listAll(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		 
		 System.out.println("getPage" + cri.getPage());
		 //System.out.println("getPerPageNum" + cri.getPerPageNum());
		 /*
		  http://localhost:8080/board/listCri 이렇게만 호출하면 메모리에 객체만 생성된 상태이다.
		  위 SearchCriteria cri는 그냥 객체만 생성된 상태... 그냥객체생성될때 기본속성값 초기화됨 (생성자에서 1페이지에서 10페이지로 셋팅되어 있다.)
		   
		  와 졸라 신기..
		  화면단에서 input name으로 POST 넘긴것도 아닌다!!!...http://localhost:8080/board/listCri?page=20&perPageNum=20  이렇게 그것도 GET으로 호출했다.
		  그런데.. page=20은 SearchCriteria cri의 page (setPage)필드 perPageNum (setPerPageNum)필드 에 각각 값이 맵핑된다... 개신기...
		  결국 내가 알고 있던 vo객체의 원리 settter만 있으면 맵핑이 가능하다는것
		  	
		  * */
	    //model.addAttribute("list", service.listCriteria(cri));
	    model.addAttribute("list", service.listSearchCriteria(cri));
	    
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    //pageMaker.setTotalCount(131);
	    //pageMaker.setTotalCount(service.listCountCriteria(cri));
	    pageMaker.setTotalCount(service.listSearchCount(cri));
	    model.addAttribute("pageMaker", pageMaker);
	    
	 }	
	
	/*★★★ @RequestParam 은 reauest.getParameter()와 유사하다*/
	/*★★★★★ @ModelAttribute와 @RequestParam의 차이점은 
	요청 파라미터를 메소드 파라미터에서 1:1로 받으면 @RequestParam, 
	도메인 오브젝트나 VO객체의 맴버필드에 요청 파라미터가 자동으로 바인딩되어 한번에 받으면 @ModelAttribute라고 볼 수 있다.*/ 	 
	 
/*	 @ModelAttribute는 생략이 가능하다.
	 public String save(@ModelAttribute User user){ ... }  
	 // public String save(User user){ ... } 생략하고 받을수도 있다.	 
*/	 
	 //(질문할것 확인핅요)★★★★★ @ModelAttribute 어노테이션을 메서드에 적용하면 해당 메서드가 생성한 객체가 뷰에 전달된다.????? 이것도 신기하다.. 파라미터로 받고 셋팅된 값이 다시 뷰에 전달이 된다고????
	//  @ModelAttribute 를 앞에 붙이면 뷰로 전달이 되지만 빼면 그냥 파라미터로 넘긴것으고 뷰로는 전달까지 안된다.?????? 맞나???? 일단 내가확인한걸론 그러함..
	 // 답변 : @ModelAttribute를 하게되면 자동으로 Model에 추가되어서 뷰로 전달됩니다. 
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("idx") int idx,  @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		//model.addAttribute("boardVO", service.read(idx)); 
		//System.out.println(cri.getPage());
		//System.out.println(cri.getPerPageNum()); //이 값은 굳이 넘겨받지 않아도 기본셋팅되어 있는 값인데.. 
		
		model.addAttribute(service.read(idx)); //★★★ key와 value인데 key가 없으면  service.read(idx)의 반환 자료형 문자열 "Board"가 소문자로시작하여, 자동으로 "board"가 key값이 된다.
	}
	
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int idx, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {  // int idx ???????   ★★★   @RequestParam("idx") int idx 로 안받아도 저렇게 받아도 되네???????? 생략이 가능하다... 사용을권장
		logger.info("modifyGET 호출됨");
		model.addAttribute(service.read(idx));
		//return "board/modify"
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(Board board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {  // ★★★ Board board 자동으로 vo객체와 맵핑되어 넘어오네..   //참고로 Spring MVC에서는 Map 형태의 파라미터를 받을 수 없다.
		
		logger.info("modifyPOST 호출됨");
		
		/*		System.out.println(board.getTitle());  //크롬에서 form데이터를 확인해보자..          
		System.out.println(board.getContent());
		System.out.println(board.getWriter());
		System.out.println(board.getIdx());
		System.out.println(board.getViewcnt());
		System.out.println(board.getRegdate());*/
		
		service.modify(board);
		rttr.addAttribute("page", cri.getPage());   // redirect 핼때 주소창에 쿼리스트링으로 붙여준다.
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "수정성공했떠염");
		return "redirect:/board/listCri";
		
	}	
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("idx") int idx, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		
		logger.info("remove 호출됨");
		service.remove(idx);
		rttr.addAttribute("page", cri.getPage());   // redirect 핼때 주소창에 쿼리스트링으로 붙여준다.
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());		
		rttr.addFlashAttribute("msg", "삭제성공했떠염");  // redirect 시 model에 값담아서 넘기기 (한번만사용된다??? 맞나?)
		
		//return "redirect:/board/listCri?page="+cri.getPage(); // GET
		return "redirect:/board/listCri"; // GET	
	}	
	
	
	
	
}
