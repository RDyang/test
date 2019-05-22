package com.rdyang.source.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HashMapSourceTest {
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "b");
	}
	
	/**
	 * 1110
	 * 0111
	 */
	@Test
	public void testOr()
	{
		int n = 5 - 1;
      System.out.println(n | n >>> 1);
      n |= n >>> 1;
      System.out.println(n | n >>> 2);
      n |= n >>> 2;
      System.out.println(n | n >>> 4);
      n |= n >>> 4;
      System.out.println(n | n >>> 8);
      n |= n >>> 8;
      System.out.println(n | n >>> 16);
      n |= n >>> 16;
      System.out.println(n);
	}
}
