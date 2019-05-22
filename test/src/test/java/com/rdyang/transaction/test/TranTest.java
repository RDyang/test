package com.rdyang.transaction.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rdyang.transaction.entity.TranTestEntity;
import com.rdyang.transaction.service.TranTestService;

/**
 * 事物测试类
 *  测试用例：
 *  	service存在事物
 *  		无异常抛出
 *  		service层抛出异常
 *  		dao抛出异常，service不捕获
 *  		dao抛出异常，service捕获
 *  		同一个service调用两个dao，其中一个dao抛出异常,service捕获
 *  		service层method1调用method2（public），method2抛出异常，method1捕获
 *  		service层method1调用method2（private），method2抛出异常，method1捕获
 *  		service层method1调用dao抛出异常，method1捕获，然后调用method2
 *  		service层method1调用dao，然后调用method2，method2抛出异常，method1捕获
 *  		mysql> select * from tran_test_entity;
   			+----+--------------------------------------+
			| ID | NAME                                 |
			+----+--------------------------------------+
			| 15 | testDaoAndMethod2ThrowExce           |
			| 16 | testDaoThrowExceAndMethod2           |
			| 17 | testServiceInvoke2DaoAndOneThrowExce |
			| 18 | test                                 |
			| 19 | testInvokePrivateMethod              |
			| 21 | testInvokePublicMethod               |
			| 22 | testDaoThrowExceAndServiceCatch      |
			+----+--------------------------------------+
			
			service层开启事物，内部处理无论怎样，只要在退出该service层的方法前捕获异常，事物不回滚
			同一个事物开启类中，以最后出该类是否抛出异常为准，中途抛出异常不回滚
			
		service,dao同时存在事物，执行相同用例
		
		mysql> select * from tran_test_entity;
		+----+----------------------------+
		| ID | NAME                       |
		+----+----------------------------+
		| 81 | testDaoAndMethod2ThrowExce |
		| 84 | test                       |
		| 85 | testInvokePrivateMethod    |
		| 87 | testInvokePublicMethod     |
		+----+----------------------------+
		4 rows in set (0.00 sec)

			
 * @author root
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring.xml")
public class TranTest {
	
	@Autowired
	@Qualifier("tranTestServiceWithTranDao")
	//@Qualifier("tranTestService")
	private TranTestService tranTestService;
	private TranTestEntity entity = new TranTestEntity();
	{
		entity.setName("aaa");
	}
	
	/**
	 * 正常保存
	 */
	@Test
	public void test()
	{
		TranTestEntity e = new TranTestEntity("test");
		tranTestService.serviceNoException(e);
		System.out.println("aaa");
	}
	
	/**
	 * service层抛出异常，且只有service开启事物
	 * 无法保存
	 */
	@Test
	public void testServiceThrowException()
	{
		tranTestService.serviceThrowException(entity);
	}
	
	/**
	 * dao抛出异常，service未捕获，且只有service层开启事物
	 * 无法保存
	 */
	@Test
	public void testDaoThrowException()
	{
		TranTestEntity e = new TranTestEntity("testDaoThrowException");
		tranTestService.daoThrowException(e);
	}
	
	/**
	 * dao抛出异常，service捕获，且只有service层开启事物
	 * 保存成功
	 */
	@Test
	public void testDaoThrowExceAndServiceCatch()
	{
		TranTestEntity e = new TranTestEntity("testDaoThrowExceAndServiceCatch");
		tranTestService.daoThrowExceAndServiceCatch(e);
	}
	
	@Test
	public void testServiceInvoke2DaoAndOneThrowExce()
	{
		TranTestEntity e = new TranTestEntity("testServiceInvoke2DaoAndOneThrowExce");
		tranTestService.invoke2DaosAndOneDaoThrowExceAndServiceCatch(e);
	}
	
	@Test
	public void testInvokePublicMethod()
	{
		TranTestEntity e = new TranTestEntity("testInvokePublicMethod");
		tranTestService.invokeServiceThrowException(e);
	}
	
	@Test
	public void testInvokePrivateMethod()
	{
		TranTestEntity e = new TranTestEntity("testInvokePrivateMethod");
		tranTestService.invokePrivateServiceMethodAndThrowException(e);
	}
	
	@Test
	public void testDaoThrowExceAndMethod2()
	{
		TranTestEntity e = new TranTestEntity("testDaoThrowExceAndMethod2");
		tranTestService.invokeDaoThrowExceAndServiceNoExce(e);
	}
	
	@Test
	public void testDaoAndMethod2ThrowExce()
	{
		TranTestEntity e = new TranTestEntity("testDaoAndMethod2ThrowExce");
		tranTestService.invokeDaoAndserviceThrowExce(e);
	}
}
