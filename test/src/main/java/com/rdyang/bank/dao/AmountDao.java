package com.rdyang.bank.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rdyang.bank.entity.AccountAmount;

@Repository
public class AmountDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public AccountAmount getAmount(String account)
	{
		String hql = "from AccountAmount where account = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		List list = query.list();
		if(null == list || list.isEmpty())
		{
			return null;
		}
		return (AccountAmount) list.get(0);
	}
	
	public void updateAmount(AccountAmount amount)
	{
		sessionFactory.getCurrentSession().update(amount);
	}
	
	public void save(AccountAmount amount)
	{
		sessionFactory.getCurrentSession().save(amount);
	}
	
	public void deleteAll()
	{
		String hql = "delete from AccountAmount";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	public void flush()
	{
		sessionFactory.getCurrentSession().flush();
	}
}
