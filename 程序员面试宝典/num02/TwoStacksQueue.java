package com.java.num02;

import java.util.Stack;

/**
 * ������ջʵ�ֶ��У�֧�ֶ��еĻ���������add,push,pop��
 * ע�⣺1.���stackPushҪ��stackPop��ѹ���ݣ�����һ���԰�stackPush�е�����ȫ��ѹ��
 * 2.���stackPop��Ϊ�գ�stackPush��������stackPop��ѹ������
 * @author lenovo
 *
 */

public class TwoStacksQueue {
	private Stack<Integer> stack1;//������ӵ�ջ
	private Stack<Integer> stack2;//������ӵ�ջ
	
	public TwoStacksQueue() {
		stack1=new Stack<Integer>();
		stack2=new Stack<Integer>();
	}
	public void add(int i) {
		stack1.push(i);
	}
	
	/**
	 * ����Ԫ�س��Ӳ�����
	 * @param stack1 ���ջ
	 * @param stack2  ����ջ
	 * @return ����Ԫ��
	 */
	public int pop() {
		if(stack1.isEmpty()&&stack2.isEmpty()) {
			System.out.println("Queue is empty!");
		}else if(stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}
	
	/**
	 * ֻ���ض���Ԫ�ص�Ԫ�ز�����
	 * @param stack1 ���ջ
	 * @param stack2 ����ջ
	 * @return ����Ԫ��
	 */
	public int peek() {
		if(stack1.isEmpty()&&stack2.isEmpty()) {
			System.out.println("Queue is wmpty!");
		}else if(stack2.isEmpty()) {
			while(!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.peek();
	}
	
	public static void main(String[] args) {
		TwoStacksQueue queue=new TwoStacksQueue();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		
		System.out.println(queue.pop());;
		System.out.println(queue.peek());
	}

}
