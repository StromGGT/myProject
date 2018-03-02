package com.java.num05;

import java.util.Stack;

public class StackTest {
	public static void sortStackByStack(Stack<Integer> stack) {
		Stack<Integer> help=new Stack<Integer>();
		while(!stack.isEmpty()) {
			int cur=stack.pop();
			while(!help.isEmpty()&&help.peek()>cur) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		while(!help.isEmpty()) {
			stack.push(help.pop());
		}

	}
	
	public static void main(String[] args) {
		Stack<Integer> stack=new Stack<Integer>();
		stack.push(2);
		stack.push(5);
		stack.push(1);
		stack.push(9);
		stack.push(6);
		
		sortStackByStack(stack);
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
//			System.out.println("hahaaha");
		}
	}

}
