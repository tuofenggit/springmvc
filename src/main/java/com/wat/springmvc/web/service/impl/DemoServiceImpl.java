package com.wat.springmvc.web.service.impl;

import com.wat.springmvc.web.service.DemoService;

public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "你好";
    }
}
