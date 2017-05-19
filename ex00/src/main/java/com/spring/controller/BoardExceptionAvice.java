package com.spring.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 발생된 익셉션을 모두 처리하는 역활
public class BoardExceptionAvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView commonAdvice(Exception e) {
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/error");
		mv.addObject("exception", e);
		
		return mv;
	}

}


/*@ControllerAdvice // 발생된 익셉션을 모두 처리하는 역활
public class BoardExceptionAvice {

	@ExceptionHandler(Exception.class)
	public String commonAdvice(Exception e, Model model) {
		
		model.addAttribute("exception", e);
		
		return "board/error";
	}

}*/
