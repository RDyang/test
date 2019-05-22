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
@Transactional
public class AmountServiceImplBack implements AmountService {

	@Autowired
	private AmountDao amountDao;
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public boolean transferAccounts(String accountA, String transferAccount, int money) {
		UserAccount account = accountDao.getAccount(accountA);
		Lock lockA = BankLock.getLock(account);
		int times = 0;
		boolean success = false;
		UserAccount you = accountDao.getAccount(transferAccount);
		while(times++ < 3)
		{
			lockA.lock();
			boolean tryLockB = false;
			try 
			{
				Lock lockB = BankLock.getLock(you);
				try {
					tryLockB = lockB.tryLock(1, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(tryLockB)
				{
					try {
						//转账
						break;
					} finally {
						lockB.unlock();
					}
				}
			} 
			finally 
			{
				if(tryLockB)
				{
					Condition condition = BankLock.getLockCondition(account);
					condition.signalAll();
					lockA.unlock();
				}
				else
				{
					Condition condition = BankLock.getLockCondition(account);
					//1s内沒有获取到锁B，先释放锁A等待1秒，然后重新执行
					try {
						condition.await(1,TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		System.out.println("transfer failed." + accountA + " to " + transferAccount);
		
		return success;
	}

}
