package com.sky.hiwise.algorithms.leetcode.linkedlist;

import com.sky.hiwise.algorithms.structures.linked.stack.Stack;

public class StackNode {

    private class Node {
        public int key;
        public Node next;

        public Node(int key) {
            this.key = key;
        }
    }

    private Node top;
    public StackNode() {
        top = new Node(-1);
    }

    public void push(int key, int value) {

        Node x = new Node(key);
        if (top.next != null) {
            Node next = top.next;
            top.next = x;
            x.next = next;
        } else {
            top.next = x;
        }
    }

    public int pop() {
        if (top.next != null) {
            //todo next key和next设置null help GC
            Node next = top.next;
            top.next = next.next;
            return next.key;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        StackNode test = new StackNode();
        test.push(1,2);
        test.push(3,2);
        test.push(4,2);
        System.out.println(test.pop());
        System.out.println(test.pop());
        test.push(5,2);
        test.push(6,2);
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
