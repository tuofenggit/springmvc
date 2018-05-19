package com.wat.springmvc.web.task;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 */
public class MyThreadPool {

    private static AtomicInteger integer = new AtomicInteger(0);

    private static int corePoolSize = 1;
    private static int maximumPoolSize = 2;
    private static long keepAliveTime = 60;
    private static TimeUnit unit = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(10);

    private static RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    };

    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new DefaultThreadFactory(), handler);

    public static ThreadPoolExecutor getThreadPool() {
        return poolExecutor;
    }

    public static void main(String[] args) {

        for (int i = 0 ; i <30; i++ ){
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("输出"+integer.incrementAndGet());
                        Thread.sleep(10000);

                        System.out.println(Thread.currentThread().getName()+"结束"+integer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        poolExecutor.shutdown();






    }
}
