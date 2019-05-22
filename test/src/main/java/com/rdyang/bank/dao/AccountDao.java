package com.rdyang.bank.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rdyang.bank.entity.UserAccount;

@Repository
public class AccountDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserAccount getAccount(String account)
	{
		String hql = "from UserAccount where account = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		List list = query.list();
		if(null == list || list.isEmpty())
		{
			return null;
		}
		return (UserAccount) list.get(0);
	}
	
	public List<UserAccount> getAccounts()
	{
		String hql = "from UserAccount";
		Query createQuery = sessionFactory.getCurrentSession().createQuery(hql);
		List list = createQuery.list();
		return list;
	}
	
	public void deleteAll()
	{
		String hql = "delete from UserAccount";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	public void save(UserAccount user)
	{
		sessionFactory.getCurrentSession().save(user);
	}
	
	public void evict()
	{
		sessionFactory.getCurrentSession().clear();
	}
}
