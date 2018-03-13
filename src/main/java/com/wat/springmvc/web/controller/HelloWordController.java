package com.wat.springmvc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Spring MVC Controller 测试
 * 
 * @author wangchuang
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloWordController {

	/**
	 * json 返回测试
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody String First(@PathVariable(value = "id") Long id) {
		System.out.println("111111111" + id);
		return "11111";
	}

	/**
	 * 
	 * JSP 跳转测试
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String Second() {
		System.out.println("22222222");
		return "test";
	}

}
