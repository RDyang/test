package com.rdyang.bank.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.ApplicationContext;

import com.rdyang.bank.dao.AccountDao;
import com.rdyang.bank.entity.UserAccount;
import com.rdyang.util.ServiceContext;

public class BankLock {
	
	private HashMap<UserAccount, Lock> accounts = new HashMap<UserAccount, Lock>();
	
	private Map<String, UserAccount> userAccounts = new HashMap<String, UserAccount>();
	
	private HashMap<UserAccount, Condition> conditions = new HashMap<UserAccount, Condition>();
	
	private BankLock()
	{
		ApplicationContext context = ServiceContext.getContext();
		AccountDao bean = context.getBean("accountDao", AccountDao.class);
		List<UserAccount> accounts2 = bean.getAccounts();
		bean.evict();
		for (UserAccount userAccount : accounts2) {
			Lock lock = new ReentrantLock();
			accounts.put(userAccount, lock);
			conditions.put(userAccount, lock.newCondition());
			userAccounts.put(userAccount.getAccount(),userAccount);
		}
	}
	
	public static Lock getLock(UserAccount account)
	{
		return InstanceHolder.lock.getAccount(account);
	}
	
	public static Condition getLockCondition(UserAccount account)
	{
		return InstanceHolder.lock.getCon(account);
	}
	
	public static UserAccount getUserAccount(String account)
	{
		return InstanceHolder.lock.userAccounts.get(account);
	}
	
	private Condition getCon(UserAccount account)
	{
		return conditions.get(account);
	}
	
	private Lock getAccount(UserAccount account)
	{
		return accounts.get(account);
	}
	
	private static class InstanceHolder
	{
		public static BankLock lock = new BankLock();
	}
	
}
