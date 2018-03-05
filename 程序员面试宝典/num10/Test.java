package com.java.num10;

import java.util.LinkedList;

/**
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况:
 * 最大值减去最小值小于或等于num的子数组数量
 * @author lenovo
 *
 */

public class Test {
	
	public static int numOfArray(int[] arr, int num) {
		if(arr==null || arr.length==0) return 0;
		
		LinkedList<Integer> maxStack=new LinkedList<Integer>();
		LinkedList<Integer> minStack=new LinkedList<Integer>();
		int res=0,i=0,j=0;
		for(i=0; i<arr.length; i++) {
			//maxStack.add(arr[i]);
			//minStack.add(arr[i]);
			for(; j<arr.length; j++) {
				while(!maxStack.isEmpty() && arr[j]>=maxStack.peekLast()) {
					maxStack.pollLast();
				}
				maxStack.addLast(arr[j]);
				while(!minStack.isEmpty() && arr[j]<=minStack.peekLast()) {
					minStack.pollLast();
				}
				minStack.addLast(arr[j]);
				if(maxStack.getFirst()-minStack.getFirst()>num)
					break;
			}
			if(minStack.peekFirst() == arr[i])	minStack.pollFirst();
			if(maxStack.peekFirst() == arr[i])	maxStack.pollFirst();
			res+=j-i;
		}
		return res;
	}
	
	
	public static  int numOfArr(int[] arr, int num) {
		if(arr == null || arr.length == 0)
			return 0;
		LinkedList<Integer> qmax=new LinkedList<Integer>();
		LinkedList<Integer> qmin=new LinkedList<Integer>();
		int i=0, j=0, res=0;
		while(i < arr.length) {
			while(j < arr.length) {
				
				while(!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j])
					qmin.pollLast();
				qmin.addLast(j);
				while(!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j])
					qmax.pollLast();
				qmax.addLast(j);
				if(arr[qmax.getFirst()] - arr[qmin.getFirst()] > num)
					break;
				j++;
			}
			if(qmin.peekFirst() == i)
				qmin.pollFirst();
			if(qmax.peekFirst() == i)
				qmax.pollFirst();
			res += j-i;
			i++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr= {3,2,5,8,4,7};
		int num=4;//9   123456789
		System.out.println(numOfArr(arr,num));
	}

}
