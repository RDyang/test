package com.rdyang.bank.service;

public interface AmountService {

	boolean transferAccounts(String accountA, String transferAccount,int money);
	
}
