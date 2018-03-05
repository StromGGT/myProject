package com.java.num07;

import java.util.Stack;

/**
 * ��ŵ��������Σ����Ʋ��ܴ���ֱ���Ƶ��ң�Ҳ���ܴ���ֱ���Ƶ��󣬱��뾭���м䡣
 * �������ƶ����̺������ܲ���
 * 
 * �ǵݹ�ⷨ
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
		//�洢��һ���ߵ����
		Action[] record= {Action.No};
		int step=0;
		while(rs.size()!=num+1) {
			//ͨ���ܽ�ó���ÿһ��ֻ�����ֿ������:L->M,M->,R->M,M->R
			//�����������һ��һ����
			step+=fStackToStack(record,Action.MToL,Action.LToM,ls,ms,left,mid);
			step+=fStackToStack(record,Action.LToM,Action.MToL,ms,ls,mid,left);
			step+=fStackToStack(record,Action.RToM,Action.MToR,ms,rs,mid,right);
			step+=fStackToStack(record,Action.MToR,Action.RToM,rs,ms,right,mid);
		}
		return step;
	}
	
	/**
	 * ÿһ���ߵ����
	 * @param record ��һ�������
	 * @param preNoAct ����һ���෴�����
	 * @param nowAct ��һ���� ���
	 * @param fStack ������ջ
	 * @param tStack �����ջ
	 * @param from ������λ��
	 * @param to �����λ�� 
	 * @return ������һ
	 */
	public static int fStackToStack(Action[] record,Action preNoAct,Action nowAct, Stack<Integer> fStack,
			Stack<Integer> tStack, String from, String to) {
		//��һ���ߵ����������һ�����෴����ҳ���ջ��ջ�����С�ڵ���ջ��ջ����ţ�����
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
