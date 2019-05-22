package com.rdyang.bank.service.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdyang.bank.dao.AccountDao;
import com.rdyang.bank.dao.AmountDao;
import com.rdyang.bank.entity.AccountAmount;
import com.rdyang.bank.entity.UserAccount;
import com.rdyang.bank.service.AmountService;
import com.rdyang.bank.util.BankLock;

/**
 * 转账流程，拿到自己的帐号和转账帐号，
 * @author root
 *
 */
public class AmountServiceImplBack2 implements AmountService {

	@Autowired
	private AmountDao amountDao;
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public boolean transferAccounts(String accountA, String transferAccount, int money) {
		//获取金额
		//获取对方账户
		//转账 
		UserAccount account = accountDao.getAccount(accountA);
		Lock lockA = BankLock.getLock(account);
		int times = 0;
		boolean success = false;
		UserAccount you = accountDao.getAccount(transferAccount);
		
		System.out.println("get lock : " + lockA + " " + Thread.currentThread().getName());
		lockA.lock();
		System.out.println("get lock success : " + lockA + " " + Thread.currentThread().getName());
		boolean tryLockB = false;
		try 
		{
			AccountAmount mine = amountDao.getAmount(account.getAccount());
			int aAfter = mine.getAmount() - money;
			if (aAfter < 0)
			{
				System.out.println("balance is not much" + mine.getAmount() + " money : " + money);
				return false;
			}
			if(null == you)
			{
				System.out.println("to is not exist.");
				return false;
			}
			Lock lockB = BankLock.getLock(you);
			System.out.println("get lockB : " + lockB + " " + Thread.currentThread().getName());
			while(times++ < 3)
			{
				try {
					tryLockB = lockB.tryLock(1, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(!tryLockB)
				{
					System.out.println("lock B didn't own." + account.getAccount() + " to " + you.getAccount() + " " + Thread.currentThread().getName());
					Condition condition = BankLock.getLockCondition(account);
					System.out.println("condition : " + condition + " " + Thread.currentThread().getName());
					//1s内沒有获取到锁B，先释放锁A等待1秒，然后重新执行
					try {
						condition.await(1,TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else
				{
					break;
				}
			}
			if(tryLockB)
			{
				System.out.println("get lockb success : " + lockB + " " + Thread.currentThread().getName());
				AccountAmount your = amountDao.getAmount(transferAccount);
				try {
					System.out.println(mine.getAccount() + " to " + your.getAccount() + " money : " + money + " aBefore : " + mine.getAmount() + ", bBefore : " + your.getAmount() + " " + Thread.currentThread().getName());
					int bAfter = your.getAmount() + money;
					your.setAmount(bAfter);
					mine.setAmount(aAfter);
					amountDao.updateAmount(mine);
					amountDao.updateAmount(your);
					amountDao.flush();
					System.out.println(mine.getAccount() + " to " + your.getAccount() + " money : " + money + " aAfter : " + mine.getAmount() + ", bAfter : " + your.getAmount() + " " + Thread.currentThread().getName());
					success = true;
				} finally {
					lockB.unlock();
				}
			}
		} 
		finally 
		{
			
			System.out.println("transfer success. " + Thread.currentThread().getName());
			Condition condition = BankLock.getLockCondition(account);
			System.out.println("conditionA : " + condition + " " + Thread.currentThread().getName());
			condition.signalAll();
			lockA.unlock();
		}
		
		System.out.println("transfer failed." + accountA + " to " + transferAccount);
		
		return success;
	}

}
