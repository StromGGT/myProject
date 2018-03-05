package com.java.num07;

/**
 * 汉诺塔问题变形：限制不能从左直接移到右，也不能从右直接移到左，必须经过中间。
 * 求最优移动过程和最优总步数
 * 
 * 递归解法
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
	 * 每一步走的情况
	 * @param num 当前的盘子编号
	 * @param left 左边的柱子
	 * @param mid 中间的柱子
	 * @param right 右边的柱子
	 * @param from 出发的柱子
	 * @param to 到达的柱子
	 * @return 总共走的步数
	 */
	public static int process(int num, String left, String  mid, String right, String from, String to ) {
		//编号为1的盘子走的情况
		if(num==1) {
			//从中间到两边或从两边 到中间
			if(from.equals(mid)||to.equals(mid)) {
				System.out.println("Mov 1 from "+from+" to "+to);
				return 1;
			}else {
				//从左边到右边或从右边到左边
				System.out.println("Mov 1 from "+from+" to "+mid);
				System.out.println("Mov 1 from "+mid+" to "+to);
				return 2;
			}
		}
		//编号不是1的盘子
		//从中间到两边或从两边 到中间--共 3步
		if(from.equals(mid)||to.equals(mid)) {
			//这一步没走的柱子
			String another=(from.equals(left)||to.equals(left))?right:left;
			
			//把其他n-1个盘子移到剩余的柱子上
			int part1=process(num-1,left,mid,right,from,another);
			//把第一个盘子移到到达位置
			int part2=1;
			System.out.println("Mov "+num+" from "+from+" to "+to);
			//把其他n-1个盘子从另外一根柱子上移到终点
			int part3=process(num-1,left,mid,right,another,to);
			return part1+part2+part3;
		}else {
			//从左边到右边或从右边到左边（假设从左边到右边）--共5步
			//把其他n-1个盘子从左边移到右边
			int part1=process(num-1,left,mid,right,from,to);
			//把1号盘子从左边移到中间
			int part2=1;
			System.out.println("Mov "+num+" from "+from+" to "+mid);
			//把其他n-1个盘子从右边移到左边
			int part3=process(num-1,left,mid,right,to,from);
			//把1号盘子从中间移到右边
			int part4=1;
			System.out.println("Mov "+num+" from "+mid+" to "+to);
			//把其他n-1个盘子从左边移到右边
			int part5=process(num-1,left,mid,right,from,to);
			return part1+part2+part3+part4+part5;
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(hanoiProblem1(2,"left","mid","right"));
	}

}
