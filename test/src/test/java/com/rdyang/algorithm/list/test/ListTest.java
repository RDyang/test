package com.rdyang.algorithm.list.test;

import org.junit.After;
import org.junit.Test;

import com.rdyang.algorithm.list.List;
import com.rdyang.algorithm.list.Node;

public class ListTest {
	
	Node node4 = new Node(44);
	Node node3 = new Node(33,node4);
	Node node2 = new Node(22,node3);
	Node node1 = new Node(11,node2);
	Node head = new Node(0,node1);
	List list = new List();
	
	@Test
	public void testNullNodeN()
	{
		Node node = new Node(123);
		list.insertPre(null, node);
	}
	
	@Test
	public void testHead()
	{
		Node node = new Node(1234);
		list.insertPre(head, node);
	}
	
	@Test
	public void testTail()
	{
		Node node = new Node(12345);
		list.insertPre(node4, node);
	}
	
	@Test
	public void test()
	{
		Node node = new Node(123456);
		list.insertPre(node1, node);
		
		Node node6 = new Node(111);
		list.insertPre(node, node6);
	}
	
	@After
	public void after()
	{
		System.out.println(head);
	}
}
