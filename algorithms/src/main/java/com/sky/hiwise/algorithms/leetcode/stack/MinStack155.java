package com.sky.hiwise.algorithms.leetcode.stack;

import java.util.Stack;

public class MinStack155 {

    private Stack<Integer> data;
    private Stack<Integer> helper;

    /** initialize your data structure here. */
    public MinStack155() {
        data = new Stack<>();
        helper = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if (helper.size() == 0 || helper.peek() > x) {
            helper.push(x);
        } else {
            helper.push(helper.peek());
        }
    }

    public void pop() {
        if (!data.isEmpty()) {
            data.pop();
            helper.pop();
        }
    }

    public int top() {
        if (!data.isEmpty()) {
            return data.peek();
        }
        return -1;
    }

    public int getMin() {
        if (!helper.isEmpty()) {
            return helper.peek();
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
