package com.java.num06;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边,窗口每次向右边画一个位置。
 * 输入：整形数组arr，窗口大小为w
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
 * @author lenovo
 *
 */

public class Window {
	
	public static int[] getMaxWindow1(int[] arr,int w) {
		if(arr==null||arr.length<w||w<1)	return null;
		//一个双端队列存放数组的下标
		LinkedList<Integer>  qmax=new LinkedList<Integer>();
		int[] res=new int[arr.length-w+1];
		int index=0;
		for(int i=0;i<arr.length;i++) {
			//如果arr[i]>=qmax.peekLast()，则qmax最后一个值出队
			//如果arr[i]<qmax.peekList(),则i入队
			while(!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			//过期数据出队
			if(qmax.peekFirst()==i-w) {
				qmax.pollFirst();
			}
			//从第w-1个数开始，每次都把最大的数放入数组
			if(i>=w-1) {
				res[index++]=arr[qmax.peekFirst()];
			}
		}
		return res;
	}
	
	public static int[] getMaxWindow2(int[] arr,int w) {
		int max=0;
		int tag=0;
		int index=0;
		int n=arr.length;
		int[] res=new int[n-w+1];
		for(int i=0; i<n-w+1;i++) {
			//判断最近进入窗口的值是否比当前最大值大
			if(tag!=0) {
				if(arr[i+2]>max) {
					max=arr[i+2];
					res[index++]=max;
					tag=2;
					continue;
				}
			}
			max=arr[i+0];
			tag=0;
			for(int j=i+1; j<i+3; j++) {
				if(arr[j]>max) {
					max=arr[j];
					tag=j-i;
				}
			}
			res[index++]=max;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr= {4,3,5,4,3,3,6,7};
		System.out.println(Arrays.toString(getMaxWindow2(arr,3)));
		
	}

}
