package com.wxfeng.test.anotation;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.SystemEnvironmentPropertySource;

public class Demo1 {

	private static ReentrantLock lock = new ReentrantLock();

	public Runnable threadTest() {
		return new Runnable() {
			public void run() {
				while (true) {
					try {
						if (lock.tryLock(500L, TimeUnit.MILLISECONDS)) {
							try {
								System.out.println(Thread.currentThread().getName() + "run");
								Thread.sleep(1000L);
							} catch (Exception e) {
								System.out.println(Thread.currentThread().getName() + e.getMessage());
							}finally {
								if (lock.isHeldByCurrentThread()) {
									lock.unlock();
									System.out.println(Thread.currentThread().getName() + "unlocked");
								}
							}
							break;
						} else {
							System.out
									.println(String.format("%s get lock time out!", Thread.currentThread().getName()));
						}
					} catch (Exception e) {
						System.out.println(Thread.currentThread().getName() + e.getMessage());
					} 
					
				}
			}
		};
	}

	public static void main(String[] args) {

		try {
			Thread t1 = new Thread(new Demo1().threadTest(), "t1");
			Thread t2 = new Thread(new Demo1().threadTest(), "t2");
			t1.start();
			t2.start();
			Thread.sleep(600);
			t1.interrupt();
			t2.interrupt();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

}
