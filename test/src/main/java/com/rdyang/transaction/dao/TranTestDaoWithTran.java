package com.rdyang.transaction.dao;

import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rdyang.transaction.entity.TranTestEntity;

@Repository("tranTestDaoWithTran")
@Transactional(propagation=Propagation.REQUIRED,
isolation=Isolation.READ_COMMITTED,
rollbackFor = IOException.class,
readOnly=false)
public class TranTestDaoWithTran extends TranTestDao{
	
	public void save(TranTestEntity entity)
	{
		super.save(entity);
	}
	
	public void saveAndThrowException(TranTestEntity entity)
	{
		super.saveAndThrowException(entity);
	}
}
