package com.java.num06;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * һ����������arr��һ����СΪw�Ĵ��ڴ����������߻������ұ�,����ÿ�����ұ߻�һ��λ�á�
 * ���룺��������arr�����ڴ�СΪw
 * �����һ������Ϊn-w+1������res��res[i]��ʾÿһ�ִ���״̬�µ����ֵ
 * @author lenovo
 *
 */

public class Window {
	
	public static int[] getMaxWindow1(int[] arr,int w) {
		if(arr==null||arr.length<w||w<1)	return null;
		//һ��˫�˶��д��������±�
		LinkedList<Integer>  qmax=new LinkedList<Integer>();
		int[] res=new int[arr.length-w+1];
		int index=0;
		for(int i=0;i<arr.length;i++) {
			//���arr[i]>=qmax.peekLast()����qmax���һ��ֵ����
			//���arr[i]<qmax.peekList(),��i���
			while(!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			//�������ݳ���
			if(qmax.peekFirst()==i-w) {
				qmax.pollFirst();
			}
			//�ӵ�w-1������ʼ��ÿ�ζ�����������������
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
			//�ж�������봰�ڵ�ֵ�Ƿ�ȵ�ǰ���ֵ��
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
