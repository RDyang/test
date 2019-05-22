package com.rdyang.transaction.service;

import com.rdyang.transaction.entity.TranTestEntity;

public interface TranTestService {

	/**
	 * service 层抛出异常
	 */
	void serviceThrowException(TranTestEntity entity);
	
	void serviceNoException(TranTestEntity entity);
	
	void daoThrowException(TranTestEntity entity);
	
	void daoThrowExceAndServiceCatch(TranTestEntity entity);
	
	void invoke2DaosAndOneDaoThrowExceAndServiceCatch(TranTestEntity entity);
	
	void invokeServiceThrowException(TranTestEntity entity);
	
	void invokePrivateServiceMethodAndThrowException(TranTestEntity entity);
	
	void invokeDaoThrowExceAndServiceNoExce(TranTestEntity entity);
	
	void invokeDaoAndserviceThrowExce(TranTestEntity entity);
}
