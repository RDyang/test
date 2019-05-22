package com.rdyang.transaction.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rdyang.transaction.dao.TranTestDao;

@Service("tranTestServiceWithTranDao")
@Transactional(propagation=Propagation.REQUIRED,
isolation=Isolation.READ_COMMITTED,
rollbackFor = IOException.class,
readOnly=false)
public class TranTestServiceImplInvokeTranDao extends BaseServiceImpl {

	@Autowired
	@Qualifier("tranTestDaoWithTran")
	public void initDao(TranTestDao tranTestDao)
	{
		super.tranTestDao = tranTestDao;
	}
}
