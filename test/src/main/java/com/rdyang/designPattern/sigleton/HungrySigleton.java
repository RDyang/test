package com.rdyang.designPattern.sigleton;

/**
 * 饿汉模式单例
 * @author root
 *
 */
public class HungrySigleton {

	private static HungrySigleton instance = new HungrySigleton();
	
	private HungrySigleton()
	{
	}
	
	public static HungrySigleton getInstance()
	{
		return instance;
	}
}
