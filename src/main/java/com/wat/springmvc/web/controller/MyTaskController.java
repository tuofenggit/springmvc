package com.wat.springmvc.web.controller;

import com.wat.springmvc.web.service.DemoService;
import com.wat.springmvc.web.task.MyThreadPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class MyTaskController {
    private Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    DemoService demoService;

    static int id = 0;

    /**
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String MyTaskTest() {
     /*   for (int i = 0; i < 30; i++) {
            MyThreadPool.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    id++;
                    logger.info("执行结果：：" + id);
                    System.out.println("执行结果：：" + id);
                }
            });
        }*/
        demoService.sayHello("牛");
        return id + "";
    }


}
