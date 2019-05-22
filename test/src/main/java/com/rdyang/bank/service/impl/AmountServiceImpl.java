package com.rdyang.bank.service.impl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rdyang.bank.dao.AccountDao;
import com.rdyang.bank.entity.UserAccount;
import com.rdyang.bank.service.AmountService;
import com.rdyang.bank.util.BankLock;

/**
 * 转账流程，拿到自己的帐号和转账帐号，
 * @author root
 *
 */
@Service
@Transactional
public class AmountServiceImpl implements AmountService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private TransferUtil transferUtil;
	
	@Override
	public synchronized boolean transferAccounts(String accountA, String transferAccount, int money) {
		//获取金额
		//获取对方账户
		//转账 
		UserAccount account = BankLock.getUserAccount(accountA);
		boolean success = false;
		UserAccount you = BankLock.getUserAccount(transferAccount);
		
		if(account.getId() > you.getId())
		{
			synchronized (you) {
				synchronized (account) {
					success = transferUtil.transfer(account, you, money);
				}
			}
		}
		else
		{
			synchronized (account) {
				synchronized (you) {
					success = transferUtil.transfer(account, you, money);
				}
			}
		}
		return success;
	}
	
	public synchronized void transfer(String from, String to, int money)
	{
		//转账逻辑
	}

}
