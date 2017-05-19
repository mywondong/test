package com.spring.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.vo.Product;

@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA 호출됨");
	}
	
	@RequestMapping("/doB")
	public void doB() {
		logger.info("doB 호출됨");
	}
	
	@RequestMapping("/doC")
	//@ModelAttribute("msg") 는 request의에 담긴 파라미터 msg
	public String doC(@ModelAttribute("msg") String msg) {
		
		logger.info("doC 호출됨");
		// result.jsp
		return "result";
	}	
	
	@RequestMapping("/doD")
	//Model model 는 뷰에 원하는 데이터를 전달하는 컨테이너상자 같은 역활
	public String doD(Model model) {
		logger.info("doD 호출됨");
		Product product = new Product("텔레비젼", "20000");
		model.addAttribute(product);
		return "product";
	}
	
	@RequestMapping("/doE")
	public String doD(RedirectAttributes rttr) {
		logger.info("doE 호출됨");
		rttr.addFlashAttribute("msg", "리다이렉트로 보낼때 메세지추가"); //  리다이렉트 시점에 한번만 사용되는 데이터 전송 속성
		return "redirect:/doF";
	}	
	
	@RequestMapping("/doF")
	public String doF(String msg) {
		logger.info("doF 호출됨");
		return "redir";
	}	
	
	@RequestMapping("/doJSON") 
	@ResponseBody // pom.xml에 jackson 추가후 아래처럼 vo로 return하면 json형태로 리턴된다.
	public Product doJSON(String msg) {
		logger.info("doJSON 호출됨");
		Product vo = new Product("바나나", "20521");
		return vo;
	}		
	
	
	
	
	
}
