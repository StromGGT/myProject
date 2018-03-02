package com.java.num01;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 1.pop,push,getMin操作的时间复杂度都是O(1)。
 * 2.设计的栈类可以使用现有的栈结构
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
	 * pop操作中，如果要放入的元素比stackMin栈顶元素小或相等则给元素也放入stackMin栈，
	 * 如果要放入元素大于stackMin栈顶元素，则将stackMin栈顶元素重复放入stackMin栈
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
