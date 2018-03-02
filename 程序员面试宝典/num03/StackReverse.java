package com.java.num03;

import java.util.Stack;

/**
 * 一个栈依次压入1,2,3,4,5，那么从栈顶到栈底分别为5,4,3,2,1。将这个栈转置后，
 * 从栈顶到栈底为1,2，3,4,5，也就是实现占中元素的逆置，但只能用递归函数来实现，不能用其他数据结构。
 * @author lenovo
 *
 */

public class StackReverse {
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result=stack.pop();
		if(stack.isEmpty()) {
			return result;
		}else {
			int last=getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}
	
	public static void reverse(Stack<Integer> stack) {
		if(stack.isEmpty()) {
			return;
		}
		int i=getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
		
	}

	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		
		reverse(stack);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop()+" ");
		}

	}

}
