package com.rdyang.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rdyang.bank.dao.AmountDao;
import com.rdyang.bank.entity.AccountAmount;
import com.rdyang.bank.entity.UserAccount;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class TransferUtil {
	
	@Autowired
	private AmountDao amountDao;
	
	public boolean transfer(UserAccount from, UserAccount to, int money)
	{
		AccountAmount mine = amountDao.getAmount(from.getAccount());
		int aAfter = mine.getAmount() - money;
		if (aAfter < 0)
		{
			System.out.println("balance is not much" + mine.getAmount() + " money : " + money);
			return false;
		}
		if(null == to)
		{
			System.out.println("to is not exist.");
			return false;
		}
		AccountAmount your = amountDao.getAmount(to.getAccount());
		System.out.println(mine.getAccount() + " to " + your.getAccount() + " money : " + money + " aBefore : " + mine.getAmount() + ", bBefore : " + your.getAmount() + " " + Thread.currentThread().getName());
		int bAfter = your.getAmount() + money;
		your.setAmount(bAfter);
		mine.setAmount(aAfter);
		amountDao.updateAmount(mine);
		amountDao.updateAmount(your);
		amountDao.flush();
		System.out.println(mine.getAccount() + " to " + your.getAccount() + " money : " + money + " aAfter : " + mine.getAmount() + ", bAfter : " + your.getAmount() + " " + Thread.currentThread().getName());
		return true;
	}
}
