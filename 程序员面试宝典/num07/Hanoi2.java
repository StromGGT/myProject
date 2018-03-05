package com.java.num07;

import java.util.Stack;

/**
 * 汉诺塔问题变形：限制不能从左直接移到右，也不能从右直接移到左，必须经过中间。
 * 求最优移动过程和最优总步数
 * 
 * 非递归解法
 * @author lenovo
 *
 */

public class Hanoi2 {
	public enum Action{
		No,LToM,MToL,MToR,RToM
	}
	public static int  hanoiProblem2(int num, String left, String mid, String right) {
		Stack<Integer> ls=new Stack<Integer>();
		Stack<Integer> ms=new Stack<Integer>();
		Stack<Integer> rs=new Stack<Integer>();
		ls.push(Integer.MAX_VALUE);
		ms.push(Integer.MAX_VALUE);
		rs.push(Integer.MAX_VALUE);
		for(int i=num; i>0; i--) {
			ls.push(i);
		}
		//存储上一步走的情况
		Action[] record= {Action.No};
		int step=0;
		while(rs.size()!=num+1) {
			//通过总结得出，每一步只有四种可能情况:L->M,M->,R->M,M->R
			//所以四种情况一个一个试
			step+=fStackToStack(record,Action.MToL,Action.LToM,ls,ms,left,mid);
			step+=fStackToStack(record,Action.LToM,Action.MToL,ms,ls,mid,left);
			step+=fStackToStack(record,Action.RToM,Action.MToR,ms,rs,mid,right);
			step+=fStackToStack(record,Action.MToR,Action.RToM,rs,ms,right,mid);
		}
		return step;
	}
	
	/**
	 * 每一步走的情况
	 * @param record 上一步的情况
	 * @param preNoAct 与这一步相反的情况
	 * @param nowAct 这一步的 情况
	 * @param fStack 出发的栈
	 * @param tStack 到达的栈
	 * @param from 出发的位置
	 * @param to 到达的位置 
	 * @return 步数加一
	 */
	public static int fStackToStack(Action[] record,Action preNoAct,Action nowAct, Stack<Integer> fStack,
			Stack<Integer> tStack, String from, String to) {
		//这一步走的情况不是上一步的相反情况且出发栈的栈顶编号小于到达栈的栈顶编号，则走
		if(record[0]!=preNoAct && fStack.peek() < tStack.peek()) {
			tStack.push(fStack.pop());
			System.out.println("Mov "+tStack.peek()+" from "+from+" to "+to);
			record[0]=nowAct;
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(hanoiProblem2(2,"left","mid","right"));
	}
	

}
