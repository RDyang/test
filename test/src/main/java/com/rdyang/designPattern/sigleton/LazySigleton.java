package com.rdyang.designPattern.sigleton;

/**
 * 懒汉模式单例
 * @author root
 *
 */
public class LazySigleton {

	private static LazySigleton instance;
	
	private LazySigleton()
	{
	}
	
	public static synchronized LazySigleton getInstance()
	{
		if(null == instance)
			instance = new LazySigleton();
		return instance;
	}
}
