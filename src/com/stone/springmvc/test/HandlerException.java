package com.stone.springmvc.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandlerException {
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handleArithmeticException(Exception ex){
		System.out.println("--->出异常了：" + ex);
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", ex);
		return mv;
	}
	
}
