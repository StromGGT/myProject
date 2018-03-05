package com.java.num07;

/**
 * ��ŵ��������Σ����Ʋ��ܴ���ֱ���Ƶ��ң�Ҳ���ܴ���ֱ���Ƶ��󣬱��뾭���м䡣
 * �������ƶ����̺������ܲ���
 * 
 * �ݹ�ⷨ
 * @author lenovo
 *
 */

public class Hanoi {
	
	public static int hanoiProblem1(int num, String left, String mid, String right) {
		if(num<1) {
			return 0;
		}
		return process(num,left,mid,right,left,right);
	}
	
	/**
	 * ÿһ���ߵ����
	 * @param num ��ǰ�����ӱ��
	 * @param left ��ߵ�����
	 * @param mid �м������
	 * @param right �ұߵ�����
	 * @param from ����������
	 * @param to ���������
	 * @return �ܹ��ߵĲ���
	 */
	public static int process(int num, String left, String  mid, String right, String from, String to ) {
		//���Ϊ1�������ߵ����
		if(num==1) {
			//���м䵽���߻������ ���м�
			if(from.equals(mid)||to.equals(mid)) {
				System.out.println("Mov 1 from "+from+" to "+to);
				return 1;
			}else {
				//����ߵ��ұ߻���ұߵ����
				System.out.println("Mov 1 from "+from+" to "+mid);
				System.out.println("Mov 1 from "+mid+" to "+to);
				return 2;
			}
		}
		//��Ų���1������
		//���м䵽���߻������ ���м�--�� 3��
		if(from.equals(mid)||to.equals(mid)) {
			//��һ��û�ߵ�����
			String another=(from.equals(left)||to.equals(left))?right:left;
			
			//������n-1�������Ƶ�ʣ���������
			int part1=process(num-1,left,mid,right,from,another);
			//�ѵ�һ�������Ƶ�����λ��
			int part2=1;
			System.out.println("Mov "+num+" from "+from+" to "+to);
			//������n-1�����Ӵ�����һ���������Ƶ��յ�
			int part3=process(num-1,left,mid,right,another,to);
			return part1+part2+part3;
		}else {
			//����ߵ��ұ߻���ұߵ���ߣ��������ߵ��ұߣ�--��5��
			//������n-1�����Ӵ�����Ƶ��ұ�
			int part1=process(num-1,left,mid,right,from,to);
			//��1�����Ӵ�����Ƶ��м�
			int part2=1;
			System.out.println("Mov "+num+" from "+from+" to "+mid);
			//������n-1�����Ӵ��ұ��Ƶ����
			int part3=process(num-1,left,mid,right,to,from);
			//��1�����Ӵ��м��Ƶ��ұ�
			int part4=1;
			System.out.println("Mov "+num+" from "+mid+" to "+to);
			//������n-1�����Ӵ�����Ƶ��ұ�
			int part5=process(num-1,left,mid,right,from,to);
			return part1+part2+part3+part4+part5;
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(hanoiProblem1(2,"left","mid","right"));
	}

}
