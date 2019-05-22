package com.rdyang.transaction.dao;

import org.springframework.stereotype.Repository;

import com.rdyang.transaction.entity.TranTestEntity;

@Repository("tranTestDao")
public class TranTestDaoNoTran extends TranTestDao{
	
	public void save(TranTestEntity entity)
	{
		super.save(entity);
	}
	
	public void saveAndThrowException(TranTestEntity entity)
	{
		super.saveAndThrowException(entity);
	}
}
