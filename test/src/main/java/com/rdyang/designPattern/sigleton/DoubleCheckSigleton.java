package com.rdyang.designPattern.sigleton;

/**
 * 双重检查模式单例
 * @author root
 *
 */
public class DoubleCheckSigleton {

	private static volatile DoubleCheckSigleton instance;
	
	private DoubleCheckSigleton()
	{
	}
	
	public static DoubleCheckSigleton getInstance()
	{
		if(null == instance)
		{
			synchronized(DoubleCheckSigleton.class)
			{
				if(null == instance)
				{
					instance = new DoubleCheckSigleton();
				}
			}
		}
		return instance;
	}
}
