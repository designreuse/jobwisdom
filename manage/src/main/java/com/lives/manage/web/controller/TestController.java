package com.lives.manage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.manage.common.exception.ServiceException;

@Controller
public class TestController {

	@RequestMapping("test")
	@ResponseBody
	public String test() throws ServiceException {
//		if (1 > 0)
//			throw new ServiceException(100001, "test exception");
		return "test";
	}

	@RequestMapping("index")
	public String index() {
		return "index";
	}
}
