package com.rdyang.transaction.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rdyang.transaction.entity.TranTestEntity;

public class TranTestDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(TranTestEntity entity)
	{
		sessionFactory.getCurrentSession().save(entity);
	}
	
	public void saveAndThrowException(TranTestEntity entity)
	{
		save(entity);
		throw new RuntimeException();
	}
}
