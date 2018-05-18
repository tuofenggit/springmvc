package com.wat.springmvc.web.controller;

import com.wat.springmvc.web.task.MyThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class MyTaskController {
    Logger logger = LogManager.getLogger(MyTaskController.class);

    static int id = 0;

    /**
     * @return
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String MyTaskTest() {
        for (int i = 0; i < 30; i++) {
            MyThreadPool.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    id++;
                    logger.info("执行结果：：" + id);
                    System.out.println("执行结果：：" + id);
                }
            });
        }
        return id + "";
    }


}
