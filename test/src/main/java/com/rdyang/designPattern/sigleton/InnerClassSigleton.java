package com.rdyang.designPattern.sigleton;

/**
 * 内部类模式单例
 * @author root
 *
 */
public class InnerClassSigleton {

	private InnerClassSigleton()
	{
	}
	
	public static InnerClassSigleton getInstance()
	{
		return InstanceHolder.instance;
	}
	
	private static class InstanceHolder
	{
		public static InnerClassSigleton instance = new InnerClassSigleton();
	}
}
