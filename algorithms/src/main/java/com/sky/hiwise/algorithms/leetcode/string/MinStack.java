package com.sky.hiwise.algorithms.leetcode.string;

import java.util.Stack;

/**
 * 题目：定义栈的数据结构，要求添加一个min函数，能够得到栈的最小元素。
 * 要求函数min、push以及pop的时间复杂度都是O(1)。
 * @author lg
 *
 * @param <T>
 */
public class MinStack<T> {
	
	private Stack<T> data;
    private Stack<T> minStack;
    
    public MinStack() {
    	data     = new Stack();
    	minStack = new  Stack();
    }
    
    public T min() {
        return minStack.peek();   //peek() 查看栈顶对象而不移除它
    }
    	 
    public T pop(){
        minStack.pop();
        return data.pop();
    }
    
    public void push(T t) {
    	if (data.size() == 0) {
    		minStack.push(t);
    	} else {
    		T minValue = min();
    		if (((Comparable)t).compareTo(minValue) < 0) {
    			minStack.push(t);
    		} else {
    			minStack.push(minValue);
    		}
    	}
    	data.push(t);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinStack<Integer> ms = new MinStack<Integer>();
        ms.push(4);
        ms.push(3);
        ms.push(2);
        ms.push(5);
        ms.push(1);
        System.out.println("min:" + ms.min() + "  pop:" +ms.pop() );
        System.out.println("min:" + ms.min() + "  pop:" +ms.pop() );       
        System.out.println("min:" + ms.min() + "  pop:" +ms.pop() );       
        System.out.println("min:" + ms.min() + "  pop:" +ms.pop() );
	}

}
