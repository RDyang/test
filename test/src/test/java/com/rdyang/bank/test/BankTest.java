package com.rdyang.bank.test;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rdyang.bank.service.AccountService;
import com.rdyang.bank.service.AmountService;
import com.rdyang.util.ServiceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring.xml")
public class BankTest {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private AmountService amountService;
	
	@Autowired
	private AccountService accountService;
	
	protected ApplicationContext getContext() {
		return context;
	}
	
	@Before
	public void init()
	{
		accountService.clear();
		for(int i = 0; i < 10; i++)
		{
			accountService.insertAccount("user-" + i);
		}
		ServiceContext.getInstance().setApplicationContext(context);
	}
	
	public void testContext()
	{
		System.out.println(context);
	}
	
	@Test
	public void testTransfer()
	{
//		for(int i = 0; i < 10; i++)
//		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true)
					{
						Random random = new Random();
						int accountA = (random.nextInt(1000) + 100)/100;
						int accountB = (random.nextInt(1000) + 100)/100;
						int money = random.nextInt(1000);
						System.out.println(accountA + "to " + accountB + " : " + money);
						amountService.transferAccounts(String.valueOf(accountA), String.valueOf(accountB), money);
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			},"thread-").start();
			
			try {
				Thread.sleep(1000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
	
	@Test
	public void testTransfer1()
	{
		for(int i = 0; i < 10; i++)
		{
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while(true)
					{
						Random random = new Random();
						int accountA = (random.nextInt(1000) % 3);
						int accountB = (random.nextInt(1000) % 3);
						if (accountA == accountB)
							continue;
						int money = random.nextInt(1000);
						System.out.println(accountA + " to " + accountB + " : " + money + " " + Thread.currentThread().getName());
						amountService.transferAccounts(String.valueOf(accountA), String.valueOf(accountB), money);
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			},"thread-" + i).start();
			
		}
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
