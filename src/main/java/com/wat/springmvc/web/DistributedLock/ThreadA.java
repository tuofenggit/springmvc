package com.wat.springmvc.web.DistributedLock;

public class ThreadA extends  Thread {
    private ShopService service;

    public ThreadA(ShopService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }


}
