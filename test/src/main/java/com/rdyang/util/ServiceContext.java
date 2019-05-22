package com.rdyang.util;

import org.springframework.context.ApplicationContext;

public class ServiceContext {

	private ApplicationContext applicationContext;
	
	private ServiceContext()
	{
	}
	
	public static ServiceContext getInstance()
	{
		return InstanceHolder.context;
	}
	
	public static ApplicationContext getContext()
	{
		return InstanceHolder.context.applicationContext;
	}
	
	private static class InstanceHolder 
	{
		private static ServiceContext context = new ServiceContext();
	}
	
	public void setApplicationContext(ApplicationContext applicationContext)
	{
		this.applicationContext = applicationContext;
	}
}
