package com.stone.springmvc.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="用户名密码不匹配") //修饰类，调用这个这个类才会抛出"value"指定的异常
public class UserNameNotMatchPassswordException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
