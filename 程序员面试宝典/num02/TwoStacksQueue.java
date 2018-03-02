package com.java.num02;

import java.util.Stack;

/**
 * 用两个栈实现队列，支持队列的基本操作（add,push,pop）
 * 注意：1.如果stackPush要向stackPop中压数据，必须一次性把stackPush中的数据全部压入
 * 2.如果stackPop不为空，stackPush决不能向stackPop中压入数据
 * @author lenovo
 *
 */

public class TwoStacksQueue {
	private Stack<Integer> stack1;//负责入队的栈
	private Stack<Integer> stack2;//负责出队的栈
	
	public TwoStacksQueue() {
		stack1=new Stack<Integer>();
		stack2=new Stack<Integer>();
	}
	public void add(int i) {
		stack1.push(i);
	}
	
	/**
	 * 队首元素出队并返回
	 * @param stack1 入队栈
	 * @param stack2  出队栈
	 * @return 出队元素
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
	 * 只返回队首元素但元素不出队
	 * @param stack1 入队栈
	 * @param stack2 出队栈
	 * @return 队首元素
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
