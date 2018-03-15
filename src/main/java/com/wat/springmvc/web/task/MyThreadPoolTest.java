package com.wat.springmvc.web.task;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class MyThreadPoolTest {

	static AtomicInteger atomicInteger = new AtomicInteger(0);
	static int corePoolSize = 5;
	static int maximumPoolSize = 5;
	static long keepAliveTime = 60;
	public static BlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<>(100);
	static DefaultThreadFactory factory = new DefaultThreadFactory();
	static RejectedExecutionHandler executionHandler = new AbortPolicy();
	static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
			TimeUnit.SECONDS, arrayBlockingQueue, factory, executionHandler);

	public static void main(String[] args) {
		
		
		MyThreadPoolTest myThreadPoolTest = new MyThreadPoolTest();
		
		for (int i = 0; i < 50; i++) {
			MyRunnable command= new MyRunnable();
			myThreadPoolTest.executor.execute(command);
		}
		
		MyThreadPoolTest myThreadPoolTest2 = new MyThreadPoolTest();
		for (int i = 0; i < 60; i++) {
			MyRunnable command= new MyRunnable();
			myThreadPoolTest2.executor.execute(command);
		}
	}
   
	
	static class MyRunnable implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			atomicInteger.getAndAdd(1);
			System.out.println(atomicInteger);
		}
		
	}
	
	
	
}
