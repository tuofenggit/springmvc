package com.wat.springmvc.web.DistributedLock;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        ShopService service = new ShopService();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }

        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }

        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }

        Thread.sleep(30000);
        System.out.println(ShopService.atomicInteger.toString());
        /*for (int i = 0; i < 50; i++) {
            A a = new A();
            a.start();
            System.out.println(A.num);
        }*/


    }


    static class A extends Thread {

        public static int num = 50;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "获得了锁");
            num--;
        }
    }


}