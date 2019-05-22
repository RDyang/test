package com.rdyang.algorithm.list;

public class List {

	/**
	 * 给出一个链表中的节点N，要求在节点N前插入一个节点
	 * 在第N个节点后插入一个节点，然后交换N节点和新节点的值
	 * @param <T>
	 * @param head
	 * @param node
	 */
	public void insertPre(Node nodeN, Node node)
	{
		if(null == nodeN || null == node)
		{
			return;
		}
		node.setNext(nodeN.getNext());
		nodeN.setNext(node);
		int value = nodeN.getValue();
		nodeN.setValue(node.getValue());
		node.setValue(value);
	}
}
