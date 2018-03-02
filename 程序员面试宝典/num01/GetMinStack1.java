package com.java.num01;

import java.util.Stack;

/**
 * ʵ��һ�������ջ����ʵ��ջ�Ļ������ܵĻ����ϣ���ʵ�ַ���ջ����СԪ�صĲ���
 * 1.pop,push,getMin������ʱ�临�Ӷȶ���O(1)��
 * 2.��Ƶ�ջ�����ʹ�����е�ջ�ṹ
 * @author lenovo
 *
 */
public class GetMinStack1 {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	GetMinStack1(){
		this.stackData=new  Stack<Integer>();
		this.stackMin=new  Stack<Integer>();
	}
	
	/**
	 * push������,Ҫ�����Ԫ�ر�stackMinջ��Ԫ��С����Ȳŷ���stackMinջ ������ֻ����stackDataջ
	 * @param newData
	 */
	public void push(int newData) {
		if(stackMin.isEmpty() || newData<=this.getMin()) {
			stackMin.push(newData);
		}
		stackData.push(newData);
		
	}
	
	public int pop() {
		if(stackData.isEmpty()) {
			System.out.println("Your stack is empty!");
			//throw new RuntimeException("Your stack is empty!");
		}
		
		int data=stackData.pop();
		if(data==this.getMin()) {
			stackMin.pop();
		}
		return data;
	}
	
	public int getMin() {
		if(stackMin.isEmpty()) {
			System.out.println("Your stack is empty!");
			return -1;
			//throw new RuntimeException("Your stack is empty!");
		}
		return stackMin.peek();
	}
	
	public static void main(String[] args) {
		GetMinStack1 stack=new GetMinStack1();
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(1);
		stack.push(2);
		stack.push(1);
		System.out.println(stack.stackData);
		System.out.println(stack.stackMin);
		
		while(!stack.stackData.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}
		
	}

}
