package com.wat.springmvc.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wat.springmvc.web.entity.User;
import com.wat.springmvc.web.service.IUserService;

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

	static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	@Autowired
	IUserService userService;

	/**
	 * json 返回测试
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody User First(@PathVariable(value = "id") Long id) {
		System.out.println("111111111" + id);

		logger.info("测试logger...");
		logger.error("测试logger...");

		return userService.getUserById(id);
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
