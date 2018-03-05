package com.java.num08;

import java.util.HashMap;
import java.util.Stack;

/**
 * ���������MaxTree
 * MaxTree������û���ظ�Ԫ�أ���һ�Ŷ�������MaxTree�������������ֵ���Ǹ��ڵ�
 * @author lenovo
 *
 */

public class MaxTree {
	
	//������������
	public static class Node{
		public int value;
		public Node left;
		public Node right;
		
		public Node(int data) {
			this.value=data;
		}
	}
	
	public static void popStackSetMap(Stack<Node> stack, HashMap<Node,Node> map) {
		Node popNode=stack.pop();
		//���ջ�գ���popNode����(��)�ߵ�һ�����ֵΪ�գ����ջ���գ���popNode����(��)�ߵ�һ�����ֵΪջ�е�ֵ
		if(stack.isEmpty()) {
			map.put(popNode, null);
		}else {
			map.put(popNode, stack.peek());
		}
	}
	
	public static Node getMaxTree(int[] arr) {
		Node[] nArr=new Node[arr.length];
		for(int i=0; i!=arr.length; i++) {
			nArr[i]=new Node(arr[i]);
		}
		Stack<Node> stack=new Stack<Node>();
		//��ߵ�һ�����ֵӳ��
		HashMap<Node,Node> lBigMap=new HashMap<Node,Node>();
		//�ұߵ�һ�����ֵӳ��
		HashMap<Node,Node> rBigMap=new HashMap<Node,Node>();
		for(int i=0; i!=nArr.length; i++) {
			Node curNode=nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, lBigMap);
			}
			stack.push(curNode);
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, lBigMap);
		}
		for(int i=nArr.length-1; i!=-1; i--) {
			Node curNode=nArr[i];
			while((!stack.isEmpty()) && stack.peek().value < curNode.value) {
				popStackSetMap(stack, rBigMap);
			}
			stack.push(curNode);			
		}
		while(!stack.isEmpty()) {
			popStackSetMap(stack, rBigMap);
		}
		Node head=null;
		for(int i=0; i!=nArr.length; i++) {
			Node curNode=nArr[i];
			Node left=lBigMap.get(curNode);
			Node right=rBigMap.get(curNode);
			if(left == null && right == null) {
				head=curNode;
			}else if(left==null){
				if(right.left==null) {
					right.left=curNode;
				}else {
					right.right=curNode;
				}
			}else if(right==null){
				if(left.left==null) {
					left.left=curNode;
				}else {
					left.right=curNode;
				}
			}else{
				Node parent=left.value < right.value ? left : right;
				if(parent.left==null) {
					parent.left=curNode;
				}else{
					parent.right=curNode;
				}
			}
		}
		return head;
	}
	
	public static void print(Node head) {
		System.out.print(head.value+" ");
		if(head.left!=null) {
			print(head.left);
		}
		if(head.right!=null) {
			print(head.right);
		}
	}
	
	public static void main(String[] args) {
		int[] arr= {3,4,5,1,2};
		Node head=getMaxTree(arr);
		print(head);
		
		
	}

}
