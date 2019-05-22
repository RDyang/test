package com.rdyang.algorithm.string;

public class ResetString {

	/**
	 * 根据特殊字符分割字符串
	 * 该方法实现了将特殊字符提取到最前边。后边的字母不改变原有顺序
	 * 输入：#a##bc# #
	 * 输出：####abc
	 * @param old
	 * @param specialChar
	 * @return
	 */
	public String drawSpecialChar(String old, char specialChar)
	{
		if(null == old || old.split(String.valueOf(specialChar)).length == 0)
		{
			return old;
		}
		int len = old.length();
		char[] newChar = new char[len];
		int left = 0;
		int right = len - 1;
		for(int i = len - 1; i >= 0; i--)
		{
			char charAt = old.charAt(i);
			if(charAt == specialChar)
			{
				newChar[left++] = charAt;
			}
			else
			{
				newChar[right--] = charAt;
			}
		}
		
		return String.copyValueOf(newChar);
		
	}
}
