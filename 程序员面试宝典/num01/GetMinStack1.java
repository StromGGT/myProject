package com.java.num01;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 1.pop,push,getMin操作的时间复杂度都是O(1)。
 * 2.设计的栈类可以使用现有的栈结构
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
	 * push操作中,要放入的元素比stackMin栈顶元素小或相等才放入stackMin栈 ，否则只放入stackData栈
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
