package com.java.num01;

import java.util.Stack;

/**
 * ʵ��һ�������ջ����ʵ��ջ�Ļ������ܵĻ����ϣ���ʵ�ַ���ջ����СԪ�صĲ���
 * 1.pop,push,getMin������ʱ�临�Ӷȶ���O(1)��
 * 2.��Ƶ�ջ�����ʹ�����е�ջ�ṹ
 * @author lenovo
 *
 */

public class GetMinStack2 {
	private Stack<Integer> stackData;
	private Stack<Integer> stackMin;
	
	GetMinStack2(){
		stackData=new Stack<Integer>();
		stackMin=new Stack<Integer>();
	}
	
	/**
	 * pop�����У����Ҫ�����Ԫ�ر�stackMinջ��Ԫ��С��������Ԫ��Ҳ����stackMinջ��
	 * ���Ҫ����Ԫ�ش���stackMinջ��Ԫ�أ���stackMinջ��Ԫ���ظ�����stackMinջ
	 * @param newNum
	 */
	public void push(int newNum) {
		if(stackMin.isEmpty() || newNum<=this.getMin()) {
			stackMin.push(newNum);
		}else {
			stackMin.push(stackMin.peek());
		}
		stackData.push(newNum);
	}
	
	public int pop() {
		if(stackData.isEmpty()) {
			System.out.println("Your stack is empty!");
		}
		stackMin.pop();
		return stackData.pop();
	}
	
	public int getMin() {
		if(stackMin.isEmpty()) {
			System.out.println("Your stack is empty!");
		}
		return stackMin.peek();
		}

	public static void main(String[] args) {
		GetMinStack2 stack=new GetMinStack2();
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
