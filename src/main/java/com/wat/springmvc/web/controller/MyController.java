package com.wat.springmvc.web.controller;

import com.wat.springmvc.web.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.concurrent.*;

@Controller
@RequestMapping("/myTest")
public class MyController {
    static ExecutorService executorService = Executors.newFixedThreadPool(4);

    @RequestMapping(value = "/test", produces = "text/html;charset=UTF-8")
    public @ResponseBody
    UserInfo test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserInfo user = new UserInfo();
        user.setUsername("lisi");
        final UserInfo finalUser = user;
        Future<UserInfo> result = executorService.submit(new Callable<UserInfo>() {
            @Override
            public UserInfo call() throws Exception {
                Thread.sleep(60000);
                finalUser.setUsername("zhangsan");
                return finalUser;
            }
        });
        try {
            user = result.get(50, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (!result.isDone()) {
                result.cancel(true);
            }
        }
        return user;
    }
}