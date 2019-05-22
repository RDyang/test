package com.rdyang.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestAwait {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition condition = lock.newCondition();

	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("thread get lock successed. begin to await.");
					try {
						condition.await(5, TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("await end");
				} finally {
					lock.unlock();
				}
			}
		}).start();
		//休眠一秒，确保上述线程先获取锁
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lock.lock();
				try {
					System.out.println("Thread b get lock successed.");
					//获取锁10s不释放
					Thread.sleep(10000);
					System.out.println("sleep over.");
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("now release lock");
					lock.unlock();
				}
			}
		}).start();
		//休眠20s,确保两个线程充分执行。
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
