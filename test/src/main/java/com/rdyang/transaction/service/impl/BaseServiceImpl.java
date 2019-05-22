package com.rdyang.transaction.service.impl;

import java.io.IOException;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rdyang.transaction.dao.TranTestDao;
import com.rdyang.transaction.entity.TranTestEntity;
import com.rdyang.transaction.service.TranTestService;

/**
 * 无法使用注解注入父类属性
 * @author root
 *
 */
@Transactional(propagation=Propagation.REQUIRED,
isolation=Isolation.READ_COMMITTED,
rollbackFor = IOException.class,
readOnly=false)
public class BaseServiceImpl implements TranTestService {

	protected TranTestDao tranTestDao;
	
	@Override
	@Transactional
	public void serviceThrowException(TranTestEntity entity) {
		tranTestDao.save(entity);
		throw new RuntimeException();
	}

	@Override
	
	public void serviceNoException(TranTestEntity entity) {
		tranTestDao.save(entity);
	}

	@Override
	public void daoThrowException(TranTestEntity entity) {
		tranTestDao.saveAndThrowException(entity);
	}

	@Override
	public void daoThrowExceAndServiceCatch(TranTestEntity entity) {
		try {
			tranTestDao.saveAndThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
	}

	@Override
	public void invoke2DaosAndOneDaoThrowExceAndServiceCatch(TranTestEntity entity) {
		tranTestDao.save(entity);
		try {
			tranTestDao.saveAndThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
	}

	@Override
	public void invokeServiceThrowException(TranTestEntity entity) {
		try {
			serviceThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
	}

	@Override
	public void invokePrivateServiceMethodAndThrowException(TranTestEntity entity) {
		try {
			saveAndThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
	}
	
	private void saveAndThrowException(TranTestEntity entity)
	{
		tranTestDao.save(entity);
		throw new RuntimeException();
	}

	@Override
	public void invokeDaoThrowExceAndServiceNoExce(TranTestEntity entity) {
		try {
			tranTestDao.saveAndThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
		serviceNoException(entity);
	}

	@Override
	public void invokeDaoAndserviceThrowExce(TranTestEntity entity) {
		tranTestDao.save(entity);
		try {
			serviceThrowException(entity);
		} catch (Exception e) {
			System.out.println("here catch exce");
		}
	}

}
