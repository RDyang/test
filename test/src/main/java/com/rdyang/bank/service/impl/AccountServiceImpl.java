package com.rdyang.bank.service.impl;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdyang.bank.dao.AccountDao;
import com.rdyang.bank.dao.AmountDao;
import com.rdyang.bank.entity.AccountAmount;
import com.rdyang.bank.entity.UserAccount;
import com.rdyang.bank.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private static int account = 0;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private AmountDao amountDao;
	
	@Override
	public void insertAccount(String name)
	{
		UserAccount user = new UserAccount();
		user.setAccount(account++ + "");
		user.setName(name);
		accountDao.save(user);
		AccountAmount amount = new AccountAmount();
		amount.setAccount(user.getAccount());
		Random random = new Random();
//		int nextInt = random.nextInt(10000);
//		System.out.println(account + " : " + nextInt);
		amount.setAmount(1000);
		amountDao.save(amount);
	}

	@Override
	public void clear() {
		accountDao.deleteAll();
		amountDao.deleteAll();
	}

}
