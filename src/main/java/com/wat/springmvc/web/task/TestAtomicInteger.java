package com.wat.springmvc.web.task;
import java.util.concurrent.atomic.AtomicInteger;  
 
/**
 * 
 * 验证int 在高并发中 并不是线程安全的
 * 
 * @author chuang
 *
 */
public class TestAtomicInteger {  
    private int shareI=0;  
    private AtomicInteger atomicLoop = new AtomicInteger(0);  
    private int loop =0;  
    
    public static void main(String []args) throws Exception{  
    	
        TestAtomicInteger t = new TestAtomicInteger();  
        t.testMethod();  
        System.out.println(t);  
    
    }  
    
    public void testMethod() throws Exception{  
        Thread th1 = new Thread(new ThreadTest());  
        Thread th2 = new Thread(new ThreadTest());  
        th1.start();  
        th2.start();
        
        /**
         * 等待线程结束
         */
        th1.join();  
        th2.join();  
    }  
    
    public synchronized void add(){  
        shareI++;  
    }  
    
    class ThreadTest implements Runnable{  
        @Override  
        public void run() {  
            //testAtomicInteger();  
            testInt();  
        }  
    }  
    public void testInt(){ //测试，输出结果随机，为线程不安全  
        for(;loop<100000;loop++) {  
            add();  
        }  
    }  
    public void testAtomicInteger(){  //输出1000000，线程安全  
        for(;atomicLoop.getAndAdd(1)<100000;) {  
            add();  
        }  
    }  
    @Override  
    public String toString() {  
        return ""+shareI;  
    }  
}  